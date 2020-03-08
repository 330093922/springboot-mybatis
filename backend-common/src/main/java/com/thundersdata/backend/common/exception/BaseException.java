package com.thundersdata.backend.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.thundersdata.backend.common.model.ResultCode;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.Data;

@Data
public class BaseException extends StatusRuntimeException {
    private int code;
    private String description;
    private String message;


    public BaseException(int code, String message) {
        super(Status.INTERNAL.withDescription(message));
        this.code = code;
        this.description = message;

        this.message = getExceptionJSON(code, message);
    }

    public BaseException(String message) {
        this(ResultCode.FAIL, message);
    }

    private String getExceptionJSON(int code, String message) {
        JSONObject exception = new JSONObject();
        exception.put("code", code);
        exception.put("message", message);

        return exception.toString();
    }
}
