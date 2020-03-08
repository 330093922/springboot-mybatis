package com.thundersdata.backend.facade.client;

import com.thundersdata.backend.facade.grpc.GreeterServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Data;

@Data
public class BasicClient {

    private final ManagedChannel channel;

    private final GreeterServiceGrpc.GreeterServiceBlockingStub greeterService;

    public BasicClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        greeterService = GreeterServiceGrpc.newBlockingStub(channel);
    }

}
