package cool.mawei.constant.interceptor;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 服务器端拦截器
 *
 * @author Ma_wei
 * @since 2020-02-07 14:53
 */
public class GrpcServerInterceptor implements ServerInterceptor {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(GrpcServerInterceptor.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        log.info("进入GrpcInterceptor服务端拦截器方法...");
        Metadata.Key<String> token = Metadata.Key.of("token",Metadata.ASCII_STRING_MARSHALLER);
        // 获取请求头中token值
        String tokenStr = metadata.get(token);
        log.info("请求中获取到客户端传递参数token = {}",tokenStr);
        if (StringUtils.isEmpty(tokenStr)) {
            log.warn("未收到客户端请求token信息，中断该次请求！");
            serverCall.close(Status.DATA_LOSS,metadata);
        }
        ServerCall<ReqT, RespT> serverCall1 = new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(serverCall){
            @Override
            public void sendHeaders(Metadata headers) {
                log.warn("执行server拦截器2,写入token");
                headers.put(token,tokenStr);
                super.sendHeaders(headers);
            }
        };
        return serverCallHandler.startCall(serverCall1,metadata);
    }

}
