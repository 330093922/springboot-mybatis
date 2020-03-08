package com.thundersdata.backend.common.exception;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

/**
 * @Description: GRPC调用异常类
 */
public class GRPCException extends StatusRuntimeException {
    public GRPCException(Status status, String message) {
        super(status.withDescription(message));
    }

    public GRPCException(String message) {
        this(Status.INTERNAL, message);
    }
}
