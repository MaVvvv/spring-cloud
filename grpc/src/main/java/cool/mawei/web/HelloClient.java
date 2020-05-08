package cool.mawei.web;

import cool.mawei.dto.HelloRequest;
import cool.mawei.dto.HelloResponse;
import cool.mawei.service.HelloRpcServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-04 16:41
 */
public class HelloClient {

    private static final Logger log = LoggerFactory.getLogger(HelloClient.class);

    public static void main(String[] args) {
        // 通道
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8899).usePlaintext().build();
        // 同步请求实例
        HelloRpcServiceGrpc.HelloRpcServiceBlockingStub blockingStub = HelloRpcServiceGrpc.newBlockingStub(channel);
        //HelloRpcServiceGrpc.HelloRpcServiceStub serviceStub = HelloRpcServiceGrpc.newStub(channel);
        // 定义请求参数
        HelloRequest request = HelloRequest.newBuilder().setId(7).setUserName("小明").build();
        HelloResponse response = null;
        try {
            // 发送请求
            response = blockingStub.sayHello(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        if (response != null) {
            log.info(response.getMessage());
        }
    }
}
