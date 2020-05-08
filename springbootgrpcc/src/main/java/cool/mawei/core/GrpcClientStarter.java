package cool.mawei.core;

import cool.mawei.dto.Book;
import cool.mawei.dto.BookRequest;
import cool.mawei.dto.BookResponse;
import cool.mawei.dto.BookResponseCommon;
import cool.mawei.service.BookRpcServiceGrpc;
import cool.mawei.service.BookRpcServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-03-13 15:19
 */
//@Component
public class GrpcClientStarter {

    //@PostConstruct
    public void startMain(){
        System.out.println("GrpcClientStarter........");
        // 通道
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8899).usePlaintext().build();
        // 同步请求实例
        BookRpcServiceGrpc.BookRpcServiceBlockingStub blockingStub = BookRpcServiceGrpc.newBlockingStub(channel);
        //HelloRpcServiceGrpc.HelloRpcServiceStub serviceStub = HelloRpcServiceGrpc.newStub(channel);
        // 定义请求参数
        BookRequest request = BookRequest.newBuilder().setId(7).setName("小明").build();
        BookResponseCommon response = null;
        try {
            // 发送请求
            response = blockingStub.addBook(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response != null) {
        }
        System.out.println(response);
    }

}
