package com.channelsoft.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.channelsoft.easyexcel.tcm.TCMFileDTO;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 单元个内容读取监听类
 *
 * @author Ma_wei
 * @since 2020-04-23 10:42
 */
public class CellTCMFileListener extends AnalysisEventListener<TCMFileDTO> {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(CellTCMFileListener.class);

    private List<TCMFileDTO> list = new ArrayList<>();

    @Override
    public void invoke(TCMFileDTO tcmFileDTO, AnalysisContext analysisContext) {
        Set<String> tcms = new HashSet<>();
        Set<String> wtcms = new HashSet<>();
        // 解析
        String tcmMethod = tcmFileDTO.getTcmMethod();
        String wtcmMethod = tcmFileDTO.getWestern();
        List<String> tcmMethods = Splitter.on(",").splitToList(tcmMethod);
        tcmMethods.forEach(tcm -> {
            if (tcm.contains("克")) {
                tcms.add(tcm);
            } else {
                wtcms.add(tcm);
            }
        });
        tcmFileDTO.setTcmMethod(Joiner.on(",").join(tcms));
        if (!wtcms.isEmpty()) {
            String newWtc = Joiner.on(",").join(wtcms);
            tcmFileDTO.setWestern(StringUtils.isNotBlank(wtcmMethod) ? wtcmMethod + newWtc : newWtc);
        }

        list.add(tcmFileDTO);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        this.printlnData();
        System.out.println("所有数据解析完成!共解析到：" + list.size() + "条！");
    }

    private void printlnData() {
        String PATH = "D:\\doc\\user\\java\\ot\\";
        String fileName = PATH + "3湿疮3320+含中医证型.xlsx";
        // 写入到新的excel中
        EasyExcel.write(fileName, TCMFileDTO.class).sheet().doWrite(list);
    }
}
