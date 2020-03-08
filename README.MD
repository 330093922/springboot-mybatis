### 后端服务通用框架

<p align="center">
<a href="http://www.oracle.com/technetwork/java/javase/overview/index.html"><img src="https://img.shields.io/badge/language-java%208.0-orange.svg"></a>
<a href="https://www.jetbrains.com/idea/"><img src="https://img.shields.io/badge/platform-jetbrains-66FF99.svg"></a>
<a href="http://www.eclipse.org/"><img src="https://img.shields.io/badge/platform-eclipse-46aae6.svg"></a>
<a href="http://projects.spring.io/spring-boot/"><img src="https://img.shields.io/badge/SpringBoot-2.1.1-621066.svg"></a>
<a href="http://www.mybatis.org/mybatis-3/"><img src="https://img.shields.io/badge/mybatis-3.4.6-358451.svg"></a>
<img src="https://img.shields.io/badge/release-1.0.0-brightgreen.svg">
</p>

本项目是雷数信息科技通用后端框架，采用`springboot-zuul-mybatis-grpc`架构，各模块以微服务方式部署，禁止使用有状态的cookie和session污染微服务无状态的分布式环境。

#### 项目重命名
执行`bash rename.sh` 可对项目进行重命名。将重命名module模块和包名，将backend替换成你设置的项目名称，重新生成项目架构后，请提交到新的代码仓库上，禁止直接使用本项目仓库作为
你的项目仓库！！！！。

#### 架构图
<img src="http://thundersdata-public.oss-cn-hangzhou.aliyuncs.com/eurasian_train/image/1560148849994.png?Expires=1875508850&OSSAccessKeyId=LTAIDlo0QYzFR2tc&Signature=JYEGRQJd2dmRreVEtoMpD51JWP0%3D"/>

#### 架构图解析
1. 微服务提供两种服务，一个Grpc服务，一个http服务，即需对内容器需要开放两个端口。
   - Http服务仅供前端调用，主要支持通用业务的处理，包含且不限于数据的增删查改操作。http服务包含了大部分的接口需求。
   - Grpc服务供微服务模块之间调用，仅支持局域网的调用。 接口数较少，根据业务调用需求而定。

```java
@SpringBootApplication
@ComponentScan("com.thundersdata.backend")
public class ServiceApplication implements CommandLineRunner {
    @Autowired
    private ServiceServer server;

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    //grpc服务
    public void run(String[] args) {
        server.start();
    }
}
```

2. `zuul`服务网关。
  因为微服务化，服务相对独立，前端无法一一对接各个服务，所以需求服务网关进行统一的对接转发处理。服务网关里支持统一的filter操作以及一些简单的通用处理。
```properties
zuul.routes.basic.path=/basic/**  #配置转发规则
zuul.routes.basic.url=http://localhost:8081/ #转发路径
```

3. Grpc调用方式
`facade`模块定义了各个模块grpc的调用方式即grpc的路径，参数和返回等。`main/proto`下的`protobuf`文件是`Grpc`的声明文件，通过声明文件，各个模块提供的`Grpc`服务以及调用方式一目了然。
不同的微服务在proto文件夹下分不同的文件存储。

```proto
syntax = "proto3";

import "google/protobuf/wrappers.proto"; //引入基本数据类型

option java_multiple_files = true;
option java_package = "com.thundersdata.backend.facade.grpc";

service GreeterService {
  rpc SayHello (HelloRequest) returns (HelloReply) {}
  rpc SayTest(google.protobuf.Int32Value) returns (google.protobuf.Int32Value) {}
}

message HelloRequest {
   string name = 1;
   int32 age = 2;
}

// The response message containing the greetings
message HelloReply {
   string message = 1;
}
```

`protobuf`是google研发的新的数据格式，生命文件`protobuf`和`grpc`应用的结合。

grpc代码文件的生成融合在mvn的生命周期里面，执行`mvn package -Dmaven.test.skip=true` 在打包的同时生成Grpc代码（不需要提交到代码仓库，可以减少冲突）本地运行之前可以装`protobuf support`插件，或者将
`target/generated-sources/protobuf`下的两个文件夹右键标记为`generated sources root`即可将Grpc代码引入到项目中。

server端启动
- 将facade模块作为项目依赖放到pom.xml下
- 启动类

```java
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

    @Value("${grpc.port}")  //在配置文件里面配置端口号
    private int port;

    //提供grpc服务的service
    @Autowired
    private GreeterServiceImpl greeterService;

    private Server server;

    public void start() {
        try{
            server = ServerBuilder.forPort(port)
                    .addService(greeterService)  //添加grpc service
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

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
```
- service实现
```java
//必须继承base类
@Service
public class GreeterServiceImpl extends GreeterServiceGrpc.GreeterServiceImplBase implements GreeterService {
    
    //rpc实现
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted(); //结束调用
    }

    public void sayTest(Int32Value id, StreamObserver<Int32Value> responseObserver) {
        responseObserver.onNext(id);
        responseObserver.onCompleted();
    }


    public String sayHello(String hello){
        return hello;
    }
}

```

client端调用方式
每个微服务定义一个client，一个client对应多个servcie服务。
spring配置
```java
@Configuration
public class ClientConfig {
    @Value("${service.host}")
    private String host;
    @Value("${service.port}")
    private int port;

    @Bean("serviceClient")
    public ServiceClient serviceClient() {
        return new ServiceClient(host, port);
    }
}

```
client实现
```java
@Data
public class ServiceClient {

    private final ManagedChannel channel;

    private final GreeterServiceGrpc.GreeterServiceBlockingStub greeterService;

    public ServiceClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        greeterService = GreeterServiceGrpc.newBlockingStub(channel); //初始化service对象，供后面直接调用rpc方法
    }

}
```

调用测试类
```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class GrpcTest {
    @Autowired
    private ServiceClient serviceClient;

    private GreeterServiceGrpc.GreeterServiceBlockingStub greeterService;

    @Before
    public void before(){
        //从client获取service
        greeterService = serviceClient.getGreeterService();
    }

    @Test
    public void helloTest() {
        HelloRequest helloRequest = HelloRequest.newBuilder().setAge(20).setName("xiaoming").build();
        HelloReply helloReply = greeterService.sayHello(helloRequest);  //直接通过service调用
        Assert.assertEquals("Hello: xiaoming", helloReply.getMessage());
    }

    @Test
    public void sayTest(){
        Int32Value id = Int32Value.of(10);
        Int32Value int32Value = greeterService.sayTest(id);//直接通过service调用
        assert  int32Value.getValue() == 10;
    }
}

```

##### 微服务端
微服务端是一个完整的springboot项目，使用springmvc支持http接口，service调用直接使用成员变量进行调用，不需要跟原来一样构造
thrift服务参数，单独接口也不再跨模块调用。

```java
@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private GreeterService greeterService;

    @RequestMapping("index")
    public String index(String message) {
        int i = 1 / 0;
        return "Hello:" + greeterService.sayHello(message);
    }
}
```
##### 异常处理
http异常处理，脚手架对http接口异常以及grpc接口异常都有统一的异常处理，见gateway模块ResponseWrapperFilter，不过这里，我们依然有一些约定俗成的异常处理方法。
1. service里面非grpc的method，手动抛出的异常，请抛出BaseException，http接口会将异常信息返回给前端， `throw new BaseException(code, message)`。
2. grpc method，抛出异常依然抛出BaseException, grpc接口将异常返回给前端，暂时无法返回状态码。`responseObserver.onError(new BaseException("测试grpc抛出异常信息"));`

## 主要功能

|    功能特性    |   新框架                        |  原框架      |
|------------|-----------------------------------|-------------|
|   rpc支持     |   grpc支持                      | thrift支持   |
|   springboot+mybatis     |   支持      | 支持   |    
|   http接口、rpc接口拆分    |   支持，http接口写起来更加高效     | 不支持，所有的服务都采用rpc的调用
|                          | controller直接调用内部service。         |方式,部分接口先走了前端的http，再走rpc调用，大部分的rpc调用是冗余的|                          
|  微服务rpc接口暴露 | 支持，通过声明文件可知道内部暴露的服务|不支持，完全是黑盒，需要去看代码|
| rpc调用方法化   |  支持，直接通过service调用方法， | 不支持，需要以字符串的方式写方法名和入参，|   
 |               | 完全跟调用内部方法无异 |              不能直接调用，调用起来方式像http |    
| 前端统一入口 |  支持， 使用zuul服务网关，将controller |  支持，需要写单独的web层，controller的调用需要跨模块|
|             |层移到微服务中，代码更加简洁   |所有模块的controller层都在web层，微服务无法提供http接口，移植的话要把web层一起移走或者重写|