package cool.mawei.service;

import cool.mawei.dto.HelloRequest;
import cool.mawei.dto.HelloResponse;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-04 15:55
 */
public class HelloServiceImpl extends HelloRpcServiceGrpc.HelloRpcServiceImplBase{

    private static final Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        log.debug("进入HelloServiceImpl.sayHello()方法...");
        int _id = request.getId();
        String _userName = request.getUserName();
        log.info("服务器端接收到请求信息为：id = [{}],userNme = [{}]",_id,_userName);
        String _uuid = UUID.randomUUID().toString();
        HelloResponse response = HelloResponse.newBuilder().setMessage("hello " + _userName + _uuid).build();
        log.info("response message = {}",response.getMessage());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void sayHelloAgain(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        log.debug("进入HelloServiceImpl.sayHelloAgain()方法...");
        int _id = request.getId();
        String _userName = request.getUserName();
        log.info("服务器端接收到请求信息为：id = [{}],userNme = [{}]",_id,_userName);
        String _uuid = UUID.randomUUID().toString();
        HelloResponse response = HelloResponse.newBuilder().setMessage("hello again" + _userName + _uuid).build();
        log.info("response message = {}",response.getMessage());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
