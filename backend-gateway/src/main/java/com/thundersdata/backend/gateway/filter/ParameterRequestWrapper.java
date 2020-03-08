package com.thundersdata.backend.gateway.filter;

import org.apache.catalina.util.ParameterMap;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;
import java.util.Set;

/**
 * 描述：
 *
 * @author yangqiang
 * @date 2019-03-11
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private ParameterMap<String, String[]> params;

    @SuppressWarnings("all")
    public
    ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        params = new ParameterMap<String, String[]>(request.getParameterMap());
        modifyParameterValues();
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }

        if (StringUtils.isBlank(values[0])) {
            values[0] = null;
        }

        return values[0];
    }

    //将parameter的值去除空格后重写回去
    private void modifyParameterValues() {
        Set<String> set = params.keySet();
        for (String key : set) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].trim();
                if ("".equals(values[i])) {
                    values[i] = null;
                }
            }

            params.put(key, values);
        }

    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    public void addParameter(String name, Object value) {
        if (value != null) {
            params.setLocked(false);
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }

            params.setLocked(true);
        }
    }
}
