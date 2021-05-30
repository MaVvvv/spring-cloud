package com.mxw.hxb.springboot.web;

import com.mxw.hxb.springboot.service.FilePartsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试我的响应结果控制器
 *
 * @author Ma_wei
 * @since 2020/12/28 16:48
 */
@Slf4j
@RestController
@RequestMapping(value = "/file-parts")
public class FilePartsController {

    @Autowired
    private FilePartsService filePartsServiceImpl;

    @GetMapping("/download")
    public void doDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        filePartsServiceImpl.filePartsStream(request,response);
    }

    @GetMapping("/run")
    public void run() throws IOException {
        filePartsServiceImpl.run();
    }

    @GetMapping("/download/parts")
    public void fileDownloadByParts(){
        filePartsServiceImpl.fileDownloadByParts();
    }

}
