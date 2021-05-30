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
public class SupOrderHeadDataListener extends AnalysisEventListener<SupOrderPO> {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(SupOrderHeadDataListener.class);

    private static final int BATCH_COUNT = 100;

    private int total;

    private final List<SupOrderPO> list = new ArrayList<>();

    public Set<String> orderPOs = Sets.newHashSet();

    @Override
    public void invoke(SupOrderPO supOrderPO, AnalysisContext analysisContext) {
        list.add(supOrderPO);
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
        list.forEach(supOrderPO -> {
            total ++;
            orderPOs.add(supOrderPO.getSupOrderNo());
        });
    }

    public List<SupOrderPO> getList() {
        return list;
    }
}
