package com.channelsoft.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.channelsoft.easyexcel.tcm.TCMFileDTO;
import com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 单元个内容读取监听类
 *
 * @author Ma_wei
 * @since 2020-04-23 10:42
 */
public class CellParseTCMFileListener extends AnalysisEventListener<TCMFileDTO> {

    private List<String> nameList = new ArrayList<>();

    private List<String> sexList = new ArrayList<>();

    private List<Integer> ageList = new ArrayList<>();

    private List<String> typeList = new ArrayList<>();

    private List<String> tcmMethodList = new ArrayList<>();

    private List<String> westernList = new ArrayList<>();

    /**
     * excel数据解析
     * 
     * @method invoke
     * @param tcmFileDTO
     * @param analysisContext
     * @author Ma_wei
     * @since 2021/4/19 14:09
     */
    @Override
    public void invoke(TCMFileDTO tcmFileDTO, AnalysisContext analysisContext) {
        nameList.add(tcmFileDTO.getName());
        sexList.add(tcmFileDTO.getSex());
        ageList.add(tcmFileDTO.getAge());
        typeList.add(tcmFileDTO.getType());

        // 中药
        String tcmMethod = tcmFileDTO.getTcmMethod();
        if (StringUtils.isNotBlank(tcmMethod)) {
            List<String> tcmMethods = Splitter.on(",").splitToList(tcmMethod);
            tcmMethods.forEach(tcmM -> {
                if (StringUtils.isNotBlank(tcmM)) {
                    String reg = "\\d";
                    String regStr = tcmM.replaceAll(reg,"hxb");
                    // 正则替换数字为hxb 截取0-hxb ---> 防风hxbhxb克  -> 防风
                    String newRegStr = regStr.substring(0,regStr.indexOf("hxb"));
                    newRegStr = newRegStr.replace("L","");
                    newRegStr = newRegStr.replace("-","");
                    tcmMethodList.add(newRegStr);
                }
            });
        }

        // 西药
        String western = tcmFileDTO.getWestern();
        if (StringUtils.isNotBlank(western)) {
            List<String> westerns = Splitter.on(",").splitToList(western);
            westerns.forEach(weter -> {
                if (StringUtils.isNotBlank(weter)) {
                    String reg = "\\d";
                    String regStr = weter.replaceAll(reg,"hxb");
                    // 正则替换数字为hxb 截取0-hxb ---> 防风hxbhxb克  -> 防风
                    String newRegStr = regStr.substring(0,regStr.indexOf("hxb"));
                    newRegStr = newRegStr.replace(" z","");
                    newRegStr = newRegStr.replace(" ◇","");
                    newRegStr = newRegStr.replace("☆","");
                    newRegStr = newRegStr.replace("(","");
                    newRegStr = newRegStr.replace("α（","");
                    newRegStr = newRegStr.replace(")","");
                    newRegStr = newRegStr.replace("）","");
                    newRegStr = newRegStr.replace("◇","");
                    newRegStr = newRegStr.replace(" ","");
                    westernList.add(newRegStr);
                }
            });
        }

    }

    /**
     * 解析完成后数据分析
     * 
     * @method doAfterAllAnalysed
     * @param analysisContext
     * @author Ma_wei
     * @since 2021/4/19 14:10
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("sheet:" + analysisContext.getCurrentSheet().getSheetName());

        Map<String,List<String>> nameMap = new HashMap<>();
        nameList = nameList.stream().sorted().collect(Collectors.toList());
        // 分组
        nameList.forEach(name -> {
            if (nameMap.containsKey(name)) {
                nameMap.get(name).add(name);
            } else {
                List<String> names = new ArrayList<>();
                names.add(name);
                nameMap.put(name,names);
            }
        });
        System.out.println(">>>>>>>>>>>>>>>>>医生姓名：" + nameList.size() + "个");
        nameMap.forEach((name,names) -> System.out.println("医生姓名：" + name + "，共出现" + names.size() +"次"));

        System.out.println(">>>>>>>>>>>>>>>>>性别共：" + sexList.size() + "个");
        Map<String,List<String>> sexMap = new HashMap<>();
        // 分组
        sexList.forEach(sex -> {
            if (sexMap.containsKey(sex)) {
                sexMap.get(sex).add(sex);
            } else {
                List<String> sexs = new ArrayList<>();
                sexs.add(sex);
                sexMap.put(sex,sexs);
            }
        });
        sexMap.forEach((sex,sexs) -> System.out.println("性别：" + sex + "，共出现" + sexs.size() +"次"));

        System.out.println(">>>>>>>>>>>>>>>>>年龄共：" + ageList.size() + "个");
        // 年龄排序
        ageList = ageList.stream().sorted().collect(Collectors.toList());
        Map<Integer,List<Integer>> ageMap = new HashMap<>();
        // 分组
        ageList.forEach(age -> {
            if (ageMap.containsKey(age)) {
                ageMap.get(age).add(age);
            } else {
                List<Integer> ages = new ArrayList<>();
                ages.add(age);
                ageMap.put(age,ages);
            }
        });
        ageMap.forEach((age,ages) -> System.out.println("年龄：" + age + "，共出现" + ages.size() +"次"));

        System.out.println(">>>>>>>>>>>>>>>>>症型共：" + typeList.size() + "个");
        Map<String,List<String>> typeMap = new HashMap<>();
        // 分组
        typeList.forEach(type -> {
            if (typeMap.containsKey(type)) {
                typeMap.get(type).add(type);
            } else {
                List<String> types = new ArrayList<>();
                types.add(type);
                typeMap.put(type,types);
            }
        });
        typeMap.forEach((type,types) -> System.out.println("症型：" + type + "，共出现" + types.size() +"次"));

        System.out.println(">>>>>>>>>>>>>>>>>中药共：" + tcmMethodList.size() + "个");
        Map<String,List<String>> tcmMethodMap = new HashMap<>();
        // 分组
        tcmMethodList.forEach(tcmM -> {
            if (tcmMethodMap.containsKey(tcmM)) {
                tcmMethodMap.get(tcmM).add(tcmM);
            } else {
                List<String> tcmMs = new ArrayList<>();
                tcmMs.add(tcmM);
                tcmMethodMap.put(tcmM,tcmMs);
            }
        });
        tcmMethodMap.forEach((tcmM,tcmMs) -> System.out.println("中药：" + tcmM + "，共出现" + tcmMs.size() +"次"));

        System.out.println(">>>>>>>>>>>>>>>>>西药共：" + westernList.size() + "个！");
        Map<String,List<String>> westernMap = new HashMap<>();
        // 分组
        westernList.forEach(western -> {
            if (westernMap.containsKey(western)) {
                westernMap.get(western).add(western);
            } else {
                List<String> westerns = new ArrayList<>();
                westerns.add(western);
                westernMap.put(western,westerns);
            }
        });
        westernMap.forEach((western,westerns) -> System.out.println("西药：" + western + "，共出现" + westerns.size() +"次"));
    }
}
