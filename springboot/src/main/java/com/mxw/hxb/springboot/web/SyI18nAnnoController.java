package com.mxw.hxb.springboot.web;

import com.mxw.hxb.springboot.annotations.SyI18nApi;
import com.mxw.hxb.springboot.model.OrderModel;
import com.sunyur.common.pagination.ModelResult;
import com.sunyur.common.pagination.ModelResultUtil;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(value = "/sy-i18n")
public class SyI18nAnnoController {

    @SyI18nApi
    @GetMapping(value = "/anno")
    public ModelResult<OrderModel> doHandAsyncTask() {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(1);
        orderModel.setName("测试订单1");
        return ModelResultUtil.success(orderModel);
    }
}
