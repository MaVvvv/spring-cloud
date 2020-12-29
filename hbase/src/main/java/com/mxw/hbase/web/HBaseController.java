package com.mxw.hbase.web;

import com.mxw.hbase.service.HBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * HBase控制器层
 *
 * @author Ma_wei
 * @since 2020/11/12 17:16
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class HBaseController {

    @Autowired
    private HBaseService hBaseServiceImpl;

    @GetMapping(value = "/test1")
    public String testHbase() throws IOException {
        log.info("HBaseController.testHbase()");
        return hBaseServiceImpl.test1();
    }
}
