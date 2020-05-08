package cool.mawei.service.impl;

import cool.mawei.helloworld.GreeterGrpc;
import cool.mawei.helloworld.HelloReply;
import cool.mawei.helloworld.HelloRequest;
import cool.mawei.service.IHelloWorldService;
import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-03-15 15:18
 */
@Service
public class HelloWorldServiceImpl implements IHelloWorldService {

    @GrpcClient(value = "hello-grpc-server")
    private Channel channel;

    private GreeterGrpc.GreeterBlockingStub blockingStub;

    @Override
    public String sayHello() {
        blockingStub = GreeterGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder().setName("小明").build();
        try {
            HelloReply reply = blockingStub.sayHello(request);
            if (reply == null) {
                System.out.println("null");
                return "null";
            } else {
                System.out.println(reply.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
