package cool.mawei.constant.interceptor;

import cool.mawei.utils.UuidUtil;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-07 15:22
 */
public class GrpcClientInterceptor implements ClientInterceptor {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(GrpcClientInterceptor.class);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        //创建client
        log.debug("创建gRPCClient...");
        ClientCall<ReqT,RespT> clientCall = channel.newCall(methodDescriptor,callOptions);
        return new ForwardingClientCall<ReqT, RespT>() {
            protected ClientCall<ReqT, RespT> delegate() {
                return clientCall;
            }
            public void start(Listener<RespT> responseListener, Metadata headers) {
                Metadata.Key<String> token = Metadata.Key.of("token",Metadata.ASCII_STRING_MARSHALLER);
                String _token = UuidUtil.getUUIDfor24Bit();
                log.info("客户端生成token串为：{}",_token);
                headers.put(token,_token);
                Listener<RespT> forwardListener = new ForwardingClientCallListener.
                        SimpleForwardingClientCallListener<RespT>(responseListener) {
                    @Override
                    public void onHeaders(Metadata headers) {
                        Metadata.Key<String> token = Metadata.Key.of("token",Metadata.ASCII_STRING_MARSHALLER);
                        if (!_token.equals(headers.get(token))){
                            log.warn("返回参数无token,关闭该链接");
                            super.onClose(Status.DATA_LOSS,headers);
                        }
                        super.onHeaders(headers);
                    }
                };
                super.start(forwardListener, headers);
            }
        };
    }
}
