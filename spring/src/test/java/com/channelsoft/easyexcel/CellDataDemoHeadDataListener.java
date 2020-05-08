package com.channelsoft.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 单元个内容读取监听类
 *
 * @author Ma_wei
 * @since 2020-04-23 10:42
 */
public class CellDataDemoHeadDataListener extends AnalysisEventListener<Person> {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(CellDataDemoHeadDataListener.class);

    private static final int BATCH_COUNT = 100;

    private int total;

    private List<Person> list = new ArrayList<>();

    @Override
    public void invoke(Person person, AnalysisContext analysisContext) {
        list.add(person);
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
        list.forEach(person -> {
            total ++;
            System.out.println(person.toString());
        });
    }
}
