package com.thundersdata.backend.basic.service.impl;

import com.google.protobuf.Int32Value;
import com.thundersdata.backend.basic.service.GreeterService;
import com.thundersdata.backend.common.exception.BaseException;
import com.thundersdata.backend.facade.client.BasicClient;
import com.thundersdata.backend.facade.grpc.GreeterServiceGrpc;
import com.thundersdata.backend.facade.grpc.HelloReply;
import com.thundersdata.backend.facade.grpc.HelloRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreeterServiceImpl extends GreeterServiceGrpc.GreeterServiceImplBase implements GreeterService {
    @Autowired
    private BasicClient basicClient;

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sayTest(Int32Value id, StreamObserver<Int32Value> responseObserver) {
        responseObserver.onNext(id);
        responseObserver.onCompleted();
    }

    @Override
    public String sayHello(String hello){
        return hello;
    }

    /**
     * grpc内部抛出异常
     * @return
     */
    @Override
    public String grpcException() {
        HelloRequest helloRequest = HelloRequest.newBuilder().setAge(20).setName("xiaoming").build();
        basicClient.getGreeterService().helloException(helloRequest); //抛出异常

        return "aaa";
    }

    @Override
    public void helloException(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        responseObserver.onError(new BaseException("测试grpc抛出异常信息"));
    }
}
