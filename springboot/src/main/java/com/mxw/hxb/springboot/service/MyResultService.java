package com.mxw.hxb.springboot.service;

import com.mxw.hxb.springboot.model.MyResult;
import com.sunyur.common.pagination.ModelResult;

/**
 * 响应结果业务逻辑处理接口
 *
 * @author Ma_wei
 * @since 2020/12/28 16:49
 */
public interface MyResultService {

    MyResult doMyResult();

    ModelResult<Boolean> doModelResult();
}
