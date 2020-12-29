package com.mxw.hxb.springboot.service.impl;

import com.mxw.hxb.springboot.model.MyResult;
import com.mxw.hxb.springboot.service.MyResultService;
import com.sunyur.common.pagination.ModelResult;
import com.sunyur.common.pagination.ModelResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 响应接口业务逻辑处理接口实现类
 *
 * @author Ma_wei
 * @since 2020/12/28 16:52
 */
@Slf4j
@Service
public class MyResultServiceImpl implements MyResultService {

    @Override
    public MyResult doMyResult() {
        return new MyResult("00000","error msg");
    }

    @Override
    public ModelResult<Boolean> doModelResult() {
        return ModelResultUtil.error("22222222");
    }
}
