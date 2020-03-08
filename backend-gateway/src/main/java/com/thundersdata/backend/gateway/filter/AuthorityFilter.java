package com.thundersdata.backend.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.thundersdata.backend.common.model.ResponseData;
import com.thundersdata.backend.common.utils.HttpUtils;
import com.thundersdata.backend.common.utils.ResultCode;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.thundersdata.backend.common.constant.SymbolConstants.*;
import static com.thundersdata.backend.common.utils.ResultCode.SUCCESS;


/**
 * @author paris
 */
@Slf4j
@Component
public class AuthorityFilter extends ZuulFilter {

    @Value("${zuul.routes.basic.url}")
    private String basicHost;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = context.getRequest();
        String methodPath = httpServletRequest.getRequestURI();

        if (methodPath.equals(MODIFYPASSWORD)) {

            return false;
        }
        // !methodPath.equals(PERMISSION_BY_URL); 修改密码方法不需要权限过滤
        if (!methodPath.equals(LOGIN)) {
            Map<String, List<String>> requestQueryParams = context.getRequestQueryParams();
            String userIdStr;
            if (null != requestQueryParams && requestQueryParams.get("userId") != null) {
                userIdStr = requestQueryParams.get("userId").get(0);
            } else {
                return false;
            }

            Integer userId = null;
            if (!StringUtils.isEmpty(userIdStr)) {
                userId = Integer.valueOf(userIdStr);
            }

            // 获取权限数据
            String userRoleData = getAuthorityDataList(userId);
            JSONObject jsonObject = JSON.parseObject(userRoleData);
            if (userRoleData != null) {
                requestQueryParams.put("dataList", Collections.singletonList(jsonObject.get("dataList").toString()));
                requestQueryParams.put("isScope", Collections.singletonList(jsonObject.get("isScope").toString()));
            }
        }

        // todo 暂时关闭权限控制
        return true;
    }

    @Override
    public Object run() {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = context.getRequest();
        log.info("--->>> Authority Filter {},{}", httpServletRequest.getMethod(), httpServletRequest.getRequestURL().toString());

        //获取uri
        String methodPath = httpServletRequest.getRequestURI();
        if (methodPath.equals(LOGIN)) {
            context.setSendZuulResponse(true);
            context.setResponseStatusCode(200);
            context.set("isSuccess", true);
            return null;
        }

        String passing = getAuthorityRequestUrl(context, methodPath);
        JSONObject jsonObject = JSON.parseObject(passing);
        if (String.valueOf(SUCCESS).equals(jsonObject.get("code").toString())) {
            context.setSendZuulResponse(true);
            context.setResponseStatusCode(200);
            context.set("isSuccess", true);

            return null;
        } else {
            String message = jsonObject.get("message").toString();
            context.setResponseBody(ResponseData.getInstanceJson(ResultCode.NOT_AUTHORIZED, null, message, false));
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(405);
            context.set("isSuccess", false);
        }

        String message = jsonObject.get("message").toString();
        context.setResponseBody(ResponseData.getInstanceJson(ResultCode.NOT_AUTHORIZED, null, message, false));
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(500);
        context.set("isSuccess", false);

        return null;

    }

    /**
     * 接口权限认证
     *
     * @param context    RequestContext
     * @param methodPath 方法路径
     * @return
     */
    private String getAuthorityRequestUrl(RequestContext context, String methodPath) {
        String userId = context.getRequestQueryParams().get("userId").get(0);
        String param = String.format("?userId=%s&apiUrl=%s", userId, methodPath);

        return HttpUtils.get(basicHost + MENU_AUTH + param);
    }

    /**
     * 数据权限范围查询
     *
     * @param userId userId
     * @return
     */
    private String getAuthorityDataList(Integer userId) {
        String param = String.format("?userId=%s", userId);

        return HttpUtils.get(basicHost + DATA_AUTH + param);
    }
}
