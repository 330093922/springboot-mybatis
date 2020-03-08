package com.thundersdata.backend.basic.test;

import com.google.protobuf.Int32Value;
import com.thundersdata.backend.basic.ServiceApplication;
import com.thundersdata.backend.facade.client.BasicClient;
import com.thundersdata.backend.facade.grpc.GreeterServiceGrpc;
import com.thundersdata.backend.facade.grpc.HelloReply;
import com.thundersdata.backend.facade.grpc.HelloRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class GrpcTest {
    @Autowired
    private BasicClient serviceClient;

    private GreeterServiceGrpc.GreeterServiceBlockingStub greeterService;

    @Before
    public void before(){
        greeterService = serviceClient.getGreeterService();
    }

    @Test
    public void helloTest() {
        HelloRequest helloRequest = HelloRequest.newBuilder().setAge(20).setName("xiaoming").build();
        HelloReply helloReply = greeterService.sayHello(helloRequest);
        Assert.assertEquals("Hello: xiaoming", helloReply.getMessage());
    }

    @Test
    public void sayTest(){
        Int32Value id = Int32Value.of(10);
        Int32Value int32Value = greeterService.sayTest(id);
        assert  int32Value.getValue() == 10;
    }

    @Test
    public void helloExceptionTest() {
        HelloRequest helloRequest = HelloRequest.newBuilder().setAge(20).setName("xiaoming").build();
        try{
           greeterService.helloException(helloRequest);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
