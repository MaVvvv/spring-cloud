package cool.mawei.service;

import cool.mawei.constant.funci.FIBookDTOToBookResponse;
import cool.mawei.constant.funci.FIBookRequestToBookDTO;
import cool.mawei.constant.interceptor.GrpcServerInterceptor;
import cool.mawei.dto.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-04 18:01
 */
@Service
@GrpcService(interceptors = GrpcServerInterceptor.class)
public class BookRpcServiceImpl extends BookRpcServiceGrpc.BookRpcServiceImplBase {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(BookRpcServiceImpl.class);

    /** 数据队列集合*/
    private static final ConcurrentHashMap<Integer,BookDTO> bookInfoMap = new ConcurrentHashMap<>();

    /** 书籍编号*/
    private static int bookId = 0;

    /**
     * <pre>
     * 更新数据信息接口
     * </pre>
     *
     * @param responseObserver
     */
    @Override
    public StreamObserver<BookRequest> updateBook(StreamObserver<BookResponseCommon> responseObserver) {
        log.debug("进入BookRpcServiceImpl.updateBook()方法...");
        return new StreamObserver<BookRequest>() {
            @Override
            public void onNext(BookRequest bookRequest) {
                int _id = bookRequest.getId();
                log.info("接收到书籍编号为：{}",_id);
                Function<BookRequest, BookDTO> func = new FIBookRequestToBookDTO(_id);
                BookDTO bookDTO = func.apply(bookRequest);
                log.info("更新书籍信息为：{}",bookDTO.toString());

                // TODO 构建响应信息
                BookResponse bookResponse = BookResponse.newBuilder().setId(_id).build();
                BookResponseCommon response = null;
                try {
                    bookInfoMap.put(_id,bookDTO);
                    log.debug("书籍更新成功");
                    response = BookResponseCommon.newBuilder().setCode(0).setMsg("书籍更新信息成功！").setBook(bookResponse).build();
                } catch (Exception e){
                    log.error("移除书籍信息异常！{}",e.getMessage());
                    e.printStackTrace();
                    response = BookResponseCommon.newBuilder().setCode(-1).setMsg("书籍更新信息失败！").setBook(bookResponse).build();
                }
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable throwable) {
                log.error(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                log.warn("onCompleted");
            }
        };
    }

    /**
     * <pre>
     * 查询书籍详情
     * </pre>
     *
     * @param responseObserver
     */
    @Override
    public StreamObserver<BookRequestById> getBookInfoById(StreamObserver<BookResponseCommon> responseObserver) {
        log.debug("进入BookRpcServiceImpl.addBook()方法...");
        return new StreamObserver<BookRequestById>() {
            @Override
            public void onNext(BookRequestById bookRequestById) {
                int _id = bookRequestById.getId();
                log.info("查询书籍信息编号为：{}",_id);

                // TODO 构建响应信息
                BookResponseCommon response = null;
                BookResponse bookResponse = null;
                BookDTO bookDTO = bookInfoMap.get(_id);
                if (bookDTO != null) {
                    log.debug("书籍信息查看成功");
                    log.info(bookDTO.toString());
                    Function<BookDTO,BookResponse> func = new FIBookDTOToBookResponse();
                    bookResponse = func.apply(bookDTO);
                    response = BookResponseCommon.newBuilder().setCode(0).setMsg("获取书籍信息成功！").setBook(bookResponse).build();
                } else {
                    bookResponse = BookResponse.newBuilder().setId(_id).build();
                    response = BookResponseCommon.newBuilder().setCode(-1).setMsg("获取书籍信息失败！").setBook(bookResponse).build();
                }
                responseObserver.onNext(response);
                // 加入第二个返回值
                response = BookResponseCommon.newBuilder().setCode(-1).setMsg("获取书籍信息失败！").setBook(bookResponse).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }

    /**
     * <pre>
     * 添加书籍接口
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void addBook(BookRequest request, StreamObserver<BookResponseCommon> responseObserver) {
        log.debug("进入BookRpcServiceImpl.addBook()方法...");
        Function<BookRequest, BookDTO> func = new FIBookRequestToBookDTO(++bookId);
        BookDTO bookDTO = func.apply(request);
        log.info("新添加书籍信息为：{}",bookDTO.toString());

        // TODO 构建响应信息
        BookResponse bookResponse = BookResponse.newBuilder().setId(bookId).build();
        BookResponseCommon response = null;
        try {
            bookInfoMap.put(bookId,bookDTO);
            log.debug("书籍加入队列成功");
            response = BookResponseCommon.newBuilder().setCode(0).setMsg("书籍添加成功！").setBook(bookResponse).build();
        } catch (Exception e){
            log.error("添加书籍信息异常！{}",e.getMessage());
            e.printStackTrace();
            response = BookResponseCommon.newBuilder().setCode(-1).setMsg("书籍添加失败！").setBook(bookResponse).build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /**
     * <pre>
     * 通过数据编号删除书籍接口
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void delBookById(BookRequestById request, StreamObserver<BookResponseCommon> responseObserver) {
        log.debug("进入BookRpcServiceImpl.addBook()方法...");
        int _id = request.getId();
        log.info("删除书籍编号为：{}",_id);

        // TODO 构建响应信息
        BookResponse bookResponse = BookResponse.newBuilder().setId(_id).build();
        BookResponseCommon response = null;
        try {
            bookInfoMap.remove(_id);
            log.debug("书籍移除队列成功");
            response = BookResponseCommon.newBuilder().setCode(0).setMsg("书籍移除成功！").setBook(bookResponse).build();
        } catch (Exception e){
            log.error("移除书籍信息异常！{}",e.getMessage());
            e.printStackTrace();
            response = BookResponseCommon.newBuilder().setCode(-1).setMsg("书籍移除失败！").setBook(bookResponse).build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
