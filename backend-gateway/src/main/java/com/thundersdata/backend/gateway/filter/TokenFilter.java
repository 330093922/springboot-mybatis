package com.thundersdata.backend.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.thundersdata.backend.common.model.ResponseData;
import com.thundersdata.backend.common.model.ResultCode;
import com.thundersdata.backend.common.utils.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 网关过滤器
 * 1.请求字符串中包含login直接放行
 * 2.其他请求会校验header中的accessToken,然后从后台获取到用户信息,把userId和loginType写入请求参数体中,
 *  module中的controller可以直接以形参的方式写入Integer userId,Integer loginType,springboot会自动注入,方法体中直接使用
 *
 */
@Component
public class TokenFilter extends ZuulFilter {
    @Value("${zuul.routes.basic.url}")
    private String basicUrl;

    private final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 登录接口和生成二维码接口直接放行,不需要登录校验
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = context.getRequest();
        String methodPath = httpServletRequest.getRequestURI();
        if (methodPath.contains("login") || methodPath.contains("qr/generate") || methodPath.contains("v2/api-docs")
            || methodPath.indexOf("/basic/order/orderDetailForApp") == 0) {

            return false;
        }

        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        ctx.getResponse().setContentType("text/html;charset=UTF-8");

        logger.info("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());

        // 获取请求的参数
        String token = request.getHeader("accessToken");

        if (StringUtils.isNotBlank(token)) {
            //对请求进行路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);

            String param = String.format("?token=%s", token);
            String jsonStr = HttpUtils.get(basicUrl + "auth/getUserByToken" + param);
            if ("".equals(jsonStr)){
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(400);
                ctx.setResponseBody(ResponseData.getInstanceJson(ResultCode.ACCESS_TOKEN_ERROR, null, "token已过期或者不存在,请重新登录", false));
                ctx.set("isSuccess", false);

                return null;
            }

            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            String userId = jsonObject.getString("id");
            String userType = jsonObject.getString("loginType");
            Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();

            if (requestQueryParams == null) {
                requestQueryParams = new HashMap<>();
            }

            //空格和空字符串处理
            modifyParameterValues(requestQueryParams);
            List<String> userIdList = new ArrayList<>();
            userIdList.add(userId);
            requestQueryParams.put("userId", userIdList);

            List<String> userTypeList = new ArrayList<>();
            userTypeList.add(userType);
            requestQueryParams.put("loginType", userTypeList);

            ctx.setRequestQueryParams(requestQueryParams);

            return null;
        }else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody(ResponseData.getInstanceJson(ResultCode.ACCESS_TOKEN_ERROR, null, "token不能为空,请重新登录", false));
            ctx.set("isSuccess", false);

            return null;
        }
    }

    private void modifyParameterValues(Map<String, List<String>> params) {
        Set<String> set = params.keySet();
        for (String key : set) {
            List<String> values = params.get(key);
            for (int i = 0; i < values.size(); i++) {
                String param = values.get(i);
                values.set(i,param.trim()) ;
                if ("".equals(param.trim())) {
                    values.set(i,null);
                }
            }
            params.put(key, values);
        }
    }
}
