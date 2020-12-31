package com.mxw.hxb.springboot.web;

import com.mxw.hxb.springboot.service.ASyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步任务接口控制器
 *
 * @author Ma_wei
 * @since 2020/12/23 19:14
 */
@Slf4j
@RestController
@RequestMapping(value = "/async-task")
public class ASyncTaskController {

    @Autowired
    private ASyncTaskService aSyncTaskServiceImpl;

    @GetMapping(value = "/do")
    public String doHandAsyncTask() {
        try {
            aSyncTaskServiceImpl.doWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sucess";
    }
}
