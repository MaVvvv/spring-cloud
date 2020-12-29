package com.channelsoft;

import com.alibaba.fastjson.JSONObject;
import com.channelsoft.dto.KdniaoQueryDTO;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-06-02 16:37
 */
@Slf4j
public class Test2 {

    @Test
    public void test01() {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock1) {
                    System.out.println("A");
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"a");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock1) {
                    System.out.println("B");
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"b");
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock1) {
                    System.out.println("C");
                    lock1.notifyAll();
                }
            }
        },"c");

        t1.start();
        t2.start();
        t3.start();
    }

    @Test
    public void test3(){
        Map<String,String> map = Maps.newHashMapWithExpectedSize(1);
        map.put("ClientId","11111");
        map.put("ClientSecret","###########");
        String jsonString = JSONObject.toJSONString(map);
        System.out.println(jsonString);

        String str1 = Joiner.on(":").join("\"clientId\"","\"" + 11111 + "\"");
        System.out.println(str1);
        int index = jsonString.indexOf(str1);
        System.out.println("index = " + index);
    }

    @Test
    public void test4(){
        String jsonStr = "";
    }

    @Test
    public void test5(){
        KdniaoQueryDTO queryDTO = new KdniaoQueryDTO();
        queryDTO.setEBusinessID("202011041525");
        queryDTO.setOrderCode("154811531");
        System.out.println(JSONObject.toJSONString(queryDTO));
    }

    @Test
    public void test6() {
        String str = "SopUserCode=KDNIAO&userType=APP&RequestData={\"PushTime\":\"2020-11-04 21:05:36\",\"EBusinessID\":\"1683526\",\"Data\":[{\"CallBack\":\"purchaserId=3557|tag=SEND_ORDER:RECEIVE_LOGISTICS_MESSAGE\",\"LogisticCode\":\"YT4910124060155\",\"ShipperCode\":\"YTO\",\"Traces\":[{\"AcceptStation\":\"【浙江省金华市义乌市廿三里镇公司】 已收件 取件人: 朱会敏 (18256862026)\",\"AcceptTime\":\"2020-11-03 22:41:03\",\"Remark\":\"\"},{\"AcceptStation\":\"【浙江省金华市义乌市廿三里镇】 已发出\",\"AcceptTime\":\"2020-11-04 11:42:04\",\"Remark\":\"\"},{\"AcceptStation\":\"【浙江省金华市义乌市廿三里镇公司】 已打包\",\"AcceptTime\":\"2020-11-04 15:28:32\",\"Remark\":\"\"},{\"AcceptStation\":\"【义乌转运中心公司】 已收入\",\"AcceptTime\":\"2020-11-04 18:21:18\",\"Remark\":\"\"},{\"AcceptStation\":\"【义乌转运中心】 已发出 下一站 【华北转运中心公司】\",\"AcceptTime\":\"2020-11-04 18:23:24\",\"Remark\":\"\"}],\"State\":\"2\",\"OrderCode\":\"purchaserId=3557|tag=SEND_ORDER:RECEIVE_LOGISTICS_MESSAGE\",\"EBusinessID\":\"1683526\",\"Success\":true}],\"Count\":\"1\"}&DataSign=YTEzMDlkNTc2MGYyZTUxNDlmODlmMjBhYmQwYzE1NjE&RequestType=101";
        List<String> params = Splitter.on("&").splitToList(str);
        params.forEach(param -> {
            System.out.println(param);
        });
        Map<String,String> paramMap = this.parseFormUrlencodedParamMap(str);//Splitter.on("&").withKeyValueSeparator("=").split(str);
        paramMap.forEach((key,value) -> {
            System.out.println(key + "------>" + value);
        });

    }

    @Test
    public void test7(){
        String regexp = "((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        String date = "2020-11-26 10:00:11";
        boolean matches = Pattern.matches(regexp, date);
        System.out.println(matches);
    }

    /**
     * 解析{@linkplain MediaType.APPLICATION_FORM_URLENCODED_VALUE}请求ContentType类型数据
     *
     * @param param 请求完整参数 aa=AA&bb=BB&cc=CC
     * @return Map<String,String>
     * @author Ma_wei
     * @since 2020/10/27
     */
    public Map<String,String> parseFormUrlencodedParamMap(String param){
        // 转化为json
        Map<String,String> paramMap = Maps.newHashMapWithExpectedSize(8);
        // 分隔 处理accessToken中存在==的问题
        String[] accJsons = param.split("&");
        for (String accJson : accJsons) {
            int index = accJson.indexOf("=");
            paramMap.put(accJson.substring(0,index),accJson.substring(index + 1));
        }
        return paramMap;
    }


}
