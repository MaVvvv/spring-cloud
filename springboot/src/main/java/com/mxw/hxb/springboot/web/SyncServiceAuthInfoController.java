package com.mxw.hxb.springboot.web;

import com.mxw.hxb.springboot.service.SyncServiceAuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 同步服务授权表信息
 *
 * @author Ma_wei
 * @since 2020/11/25 13:49
 */
@RestController
@RequestMapping(value = "/sync")
public class SyncServiceAuthInfoController {

    /**
     * TODO
     */
    @Autowired
    private SyncServiceAuthInfoService syncServiceAuthInfoServiceImpl;

    @GetMapping
    public String sync(){
        syncServiceAuthInfoServiceImpl.sync();
        return "ok";
    }
}
