package com.thundersdata.backend.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.thundersdata.backend.common.model.ResponseData;
import com.thundersdata.backend.common.model.ResultCode;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            ctx.setResponseBody(ResponseData.getInstanceJson(ResultCode.FAIL, null, "访问异常", false));
            ctx.getResponse().flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
