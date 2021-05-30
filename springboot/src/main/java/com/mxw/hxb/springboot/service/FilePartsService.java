package com.mxw.hxb.springboot.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件分片业务逻辑处理接口
 *
 * @author Ma_wei
 * @since 2021/1/14 17:59
 */
public interface FilePartsService {

    void filePartsStream(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void run() throws IOException;

    void fileDownloadByParts();
}
