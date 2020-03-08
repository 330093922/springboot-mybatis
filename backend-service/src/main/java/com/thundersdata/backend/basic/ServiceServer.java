package com.thundersdata.backend.basic;

import com.thundersdata.backend.basic.service.impl.GreeterServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ServiceServer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${grpc.port}")
    private int port;

    @Autowired
    private GreeterServiceImpl greeterService;

    private Server server;

    public void start() {
        try{
            server = ServerBuilder.forPort(port)
                    .addService(greeterService)
                    .build()
                    .start();

            logger.info("grpc service start with port {}...", port);

        }catch (IOException e){
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                ServiceServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }


    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // block 一直到退出程序
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
