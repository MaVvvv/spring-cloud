package com.channelsoft.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 单元个内容读取监听类
 *
 * @author Ma_wei
 * @since 2020-04-23 10:42
 */
public class SopMallOrderHeadDataListener extends AnalysisEventListener<SopMallOrderPO> {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(SopMallOrderHeadDataListener.class);

    private static final int BATCH_COUNT = 100;

    private int total;

    private final List<SopMallOrderPO> list = new ArrayList<>();

    public Set<String> orderPOs = Sets.newHashSet();

    @Override
    public void invoke(SopMallOrderPO sopMallOrderPO, AnalysisContext analysisContext) {
        list.add(sopMallOrderPO);
        if (list.size() >= BATCH_COUNT) {
            this.printlnData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        this.printlnData();
        System.out.println("所有数据解析完成!共解析到：" + total + "条！");
    }

    private void printlnData() {
        list.forEach(sopMallOrderPO -> {
            total ++;
            orderPOs.add(sopMallOrderPO.getOrderNo());
        });
    }

    public List<SopMallOrderPO> getList() {
        return list;
    }
}
