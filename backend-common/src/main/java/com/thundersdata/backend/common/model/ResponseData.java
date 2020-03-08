package com.thundersdata.backend.common.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class ResponseData {
    private Integer code;

    private Object data;

    private String message;

    private Boolean success;

    public static ResponseData getInstance(Integer code, Object data, String msg, Boolean success) {
        return new ResponseData(code, data, msg, success);
    }

    public static String getInstanceJson(Integer code, Object data, String msg, Boolean success) {
        return JSON.toJSONString(new ResponseData(code, data, msg, success));
    }

    private ResponseData() {

    }

    public ResponseData(Integer code, Object data, String msg, Boolean success) {
        this.code = code;
        this.data = data;
        this.message = msg;
        this.success = success;
    }
}
