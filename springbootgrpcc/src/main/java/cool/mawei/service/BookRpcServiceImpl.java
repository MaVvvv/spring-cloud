package cool.mawei.service;

import cool.mawei.constant.funci.FIBookDTOToBookRequest;
import cool.mawei.constant.funci.FIBookDTOToBookResponse;
import cool.mawei.constant.interceptor.GrpcClientInterceptor;
import cool.mawei.dto.*;
import cool.mawei.utils.JsonUtil;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-04 22:51
 */
@Service
public class BookRpcServiceImpl implements IBookService {

    private static final Logger log = LoggerFactory.getLogger(BookRpcServiceImpl.class);

    @GrpcClient(value = "book-grpc-server",interceptors = GrpcClientInterceptor.class)
    private Channel channel;

    /** 同步请求stub示例*/
    private BookRpcServiceGrpc.BookRpcServiceBlockingStub blockingStub;

    /** 异步请求stub示例*/
    private BookRpcServiceGrpc.BookRpcServiceStub stub;

    @Override
    public String addBook(BookDTO bookDTO) {
        // 类型结构转化
        Function<BookDTO,BookRequest> funcR = new FIBookDTOToBookRequest();
        BookRequest request = funcR.apply(bookDTO);
        blockingStub = BookRpcServiceGrpc.newBlockingStub(channel);
        BookResponseCommon response = null;
        Function<BookDTO,BookResponse> funcS = new FIBookDTOToBookResponse();
        BookResponse bookResponse = funcS.apply(bookDTO);
        try {
            response = blockingStub.addBook(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            response = BookResponseCommon.newBuilder().setCode(-1).setMsg("添加书籍信息失败").setBook(bookResponse).build();
        }
        if (response != null) {
            log.info("添加书籍成功，书籍名称：[{}]，编号为：[{}]",bookDTO.getName(),response.getBook().getId());
        }
        return JsonUtil.toJson(response);
    }

    @Override
    public String updateBook(BookDTO bookDTO) throws InterruptedException {
        Map<String,Object> resultMap = new HashMap<>(4);
        // 类型结构转化
        Function<BookDTO,BookRequest> funcR = new FIBookDTOToBookRequest();
        BookRequest request = funcR.apply(bookDTO);
        //stub = BookRpcServiceGrpc.newStub(channel);
        //blockingStub = BookRpcServiceGrpc.newBlockingStub(channel);
        try {
            StreamObserver<BookResponseCommon> responseCommonStreamObserver = new StreamObserver<BookResponseCommon>() {
                @Override
                public void onNext(BookResponseCommon bookResponseCommon) {
                    resultMap.put("code",bookResponseCommon.getCode());
                    resultMap.put("msg",bookResponseCommon.getMsg());
                    resultMap.put("book",bookResponseCommon.getBook());
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {

                }
            };
            //response = blockingStub.addBook(request);
            StreamObserver<BookRequest> req = stub.updateBook(responseCommonStreamObserver);
            req.onNext(request);
            req.onNext(request);
            req.onNext(request);
            req.onNext(request);
            req.onCompleted();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            resultMap.put("code",-1);
            resultMap.put("msg","更新书籍信息失败");
            resultMap.put("book",request);
        }
        Thread.sleep(10000);
        return JsonUtil.toJson(resultMap);
    }

    @Override
    public String getBookInfoById(int id) throws InterruptedException {
        Map<String,Object> resultMap = new HashMap<>(4);
        // 类型结构转化
        Function<BookDTO,BookRequest> funcR = new FIBookDTOToBookRequest();
        BookRequestById request = BookRequestById.newBuilder().setId(id).build();
        //stub = BookRpcServiceGrpc.newStub(channel);
        //blockingStub = BookRpcServiceGrpc.newBlockingStub(channel);
        try {
            StreamObserver<BookResponseCommon> responseCommonStreamObserver = new StreamObserver<BookResponseCommon>() {
                @Override
                public void onNext(BookResponseCommon bookResponseCommon) {
                    resultMap.put("code",bookResponseCommon.getCode());
                    resultMap.put("msg",bookResponseCommon.getMsg());
                    resultMap.put("book",bookResponseCommon.getBook());
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {

                }
            };
            //response = blockingStub.addBook(request);
            StreamObserver<BookRequestById> req = stub.getBookInfoById(responseCommonStreamObserver);
            req.onNext(request);
            req.onCompleted();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            resultMap.put("code",-1);
            resultMap.put("msg","查询书籍信息失败");
            resultMap.put("book",request);
        }
        Thread.sleep(10000);
        return JsonUtil.toJson(resultMap);
    }

}
