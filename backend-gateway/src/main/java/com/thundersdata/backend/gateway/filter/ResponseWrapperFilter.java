package com.thundersdata.backend.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.thundersdata.backend.common.constant.HttpConstants;
import com.thundersdata.backend.common.model.ResponseData;
import com.thundersdata.backend.common.model.ResultCode;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

@Component
public class ResponseWrapperFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(ResponseWrapperFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 对调用二维码生成的返回值不需要包装为json
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = context.getRequest();
        String methodPath = httpServletRequest.getRequestURI();
        if (methodPath.contains("qr/generate") || methodPath.contains("v2/api-docs")) {

            return false;
        }
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        try {
            InputStream stream = ctx.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));

            if (ctx.getResponseStatusCode() == 200) {
                if (isJSONStr(body)) {
                    ctx.setResponseBody(ResponseData.getInstanceJson(ResultCode.SUCCESS, JSON.parse(body), "成功", true));
                } else {
                    ctx.setResponseBody(ResponseData.getInstanceJson(ResultCode.SUCCESS, body, "成功", true));
                }
            } else if (ctx.getResponseStatusCode() == 404) {
                ctx.setResponseBody(ResponseData.getInstanceJson(ResultCode.REQUEST_NOT_FOUND, null, "请求的资源不存在", false));
            } else if (ctx.getResponseStatusCode() == 500) {
                ctx.setResponseStatusCode(200);
                JSONObject data = JSON.parseObject(body);
                if (data != null && isJSONStr(data.getString("message"))) {
                    JSONObject exception = JSON.parseObject(data.getString("message"));

                    ctx.setResponseBody(ResponseData.getInstanceJson(exception.getInteger("code"), null, exception.getString("message"), false));
                } else if (data != null && data.getString("message") != null) {
                    ctx.setResponseBody(ResponseData.getInstanceJson(ResultCode.FAIL, null, data.getString("message"), false));
                } else if (JSON.parseObject(ctx.getResponseBody()).get("code") != String.valueOf(ResultCode.SUCCESS)) {
                    String message = JSON.parseObject(ctx.getResponseBody()).get("message").toString();
                    ctx.setResponseBody(ResponseData.getInstanceJson(ResultCode.FAIL, null, message, false));
                } else {
                    ctx.setResponseBody(ResponseData.getInstanceJson(ResultCode.FAIL, null, "请求异常", false));
                }
            }
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            ctx.getResponse().flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isJSONStr(String str) {
        if (str.isEmpty()) {
            return false;
        } else {
            return "[".equals(str.substring(0, 1)) || "{".equals(str.substring(0, 1));
        }
    }


    /**
     * 根据response返回的Content-Type判断是否是excel类型数据
     *
     * @param currentRequestContext {@link RequestContext}
     * @return true: excel类型数据, 跳过数据包装直接返回
     */
    private boolean isExcelContentType(RequestContext currentRequestContext) {
        List<Pair<String, String>> list = currentRequestContext.getOriginResponseHeaders();
        if (!CollectionUtils.isEmpty(list)) {
            for (Pair<String, String> header : list) {
                String headerName = header.first();
                String headerValue = header.second();
                if (HttpHeaders.CONTENT_TYPE.equals(headerName) && HttpConstants.EXCEL_CONTENT_TYPE.equals(headerValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isSuccess(RequestContext currentRequestContext) {
        return (boolean) currentRequestContext.get("isSuccess");
    }
}