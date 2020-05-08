package cool.mawei.web;

import cool.mawei.web.handle.BookHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-04 22:47
 */
@RestController
@RequestMapping(value = "/grpc/book",produces = "application/json;charset=UTF-8")
public class BookController {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookHandle bookHandle;

    /**
     * 添加书籍
     *
     * @param jsonParam
     * @return String
     * @author Ma_wei
     * @since 2020/2/4
     */
    @PostMapping
    public String addBook(@RequestBody String jsonParam) {
        log.debug("进入BookController.addBook()方法...");
        log.info("接收到请求消息为：{}",jsonParam);
        String returnJsonStr = bookHandle.addBook(jsonParam);
        log.info("添加书籍响应信息为：{}",returnJsonStr);
        return returnJsonStr;
    }

    /**
     * 添加书籍
     *
     * @param jsonParam
     * @return String
     * @author Ma_wei
     * @since 2020/2/4
     */
    @PutMapping
    public String updateBook(@RequestBody String jsonParam) {
        log.debug("进入BookController.updateBook()方法...");
        log.info("接收到请求消息为：{}",jsonParam);
        String returnJsonStr = bookHandle.updateBook(jsonParam);
        log.info("更新书籍响应信息为：{}",returnJsonStr);
        return returnJsonStr;
    }

    @GetMapping(value = "/{id}")
    public String getBookInfoById(@PathVariable(value = "id") Integer id) {
        log.debug("进入BookController.updateBook()方法...");
        log.info("接收到请求消息为：{}",id);
        String returnJsonStr = bookHandle.getBookInfoById(id);
        log.info("更新书籍响应信息为：{}",returnJsonStr);
        return returnJsonStr;
    }

}
