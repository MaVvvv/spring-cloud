package com.hxb.jdk8.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hxb.jdk8.dto.TestDTO;
import com.hxb.jdk8.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-12-19 15:26
 */
public class Test {

    private static Integer n = 0;

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    private static AtomicInteger an = new AtomicInteger(0);

    @org.junit.jupiter.api.Test
    public void test01() throws InterruptedException {
        for (int k = 0; k < 1000;k ++) {
            // n = 0;
            an = new AtomicInteger(0);
            for (int i = 0 ; i < 2; i ++) {
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 100; j ++) {
                        an.incrementAndGet();
                    }
                }).start();
            }
            Thread.sleep(3000);
            System.out.println("an = " + an);
        }
    }

    @org.junit.jupiter.api.Test
    public void test02() {
        List<String> skillIds = Lists.newArrayList("12","13","14");
        StringBuilder s = new StringBuilder();
        /*s.append("select * from table where skill_group_id in ('");
        String skillIdStr = Joiner.on("','").appendTo(s,skillIds).append("')").toString();
        System.out.println(skillIdStr);*/
        String skill = "";
        for(String skillId:skillIds){
            skill+= "'" + skillId + "',";
        }
        String skillId= skill.substring(0,skill.length() - 1);
        s.append(" and skill_group_id in ("+skillId+")");
        System.out.println(s.toString());
    }

    @org.junit.jupiter.api.Test
    public void test03() {
        String s = "abc";
        Optional<String> so = Optional.ofNullable(s);
        System.out.println(so.isPresent());
        so.ifPresent(x -> {
            int a = 0;
            a++;
            System.out.println(a + x);
        });
    }

    @org.junit.jupiter.api.Test
    public void test04() {
        Map<String,String> gMap = Maps.newHashMap();
        gMap.put("a","A");
        gMap.put("b","B");
        gMap.put("c","C");
        gMap.put("d","D");
        String _val = "value = ";
        List<String> gList = Lists.newArrayList("3","2","1","4","7","6","5","6","1");
        String[] arr = (String[])gList.toArray();
        //log.info(String.valueOf(gList.stream().peek(log::debug).limit(4).mapToInt(Integer::parseInt).sum()));
        gList.stream().peek(e -> log.info("元素{}被消费",e)).collect(Collectors.toSet()).forEach(log::info);
        ActionListener one = event -> System.out.println("button click!");

    }

    @org.junit.jupiter.api.Test
    public void test05() {
        List<String> gList = Lists.newArrayList("ma","xiao","wei","he","xiao","bei");
        int sum = gList.parallelStream().map(String::length).reduce(Integer::sum).get();
        System.out.println("sum = " + sum);
    }

    @org.junit.jupiter.api.Test
    public void test06() {
        TestDTO testDTO = new TestDTO();
        testDTO.setName("jack");
        testDTO.setAge(10);
        String str = JsonUtil.toJson(testDTO);
        System.out.println(str);
        String str1 = "{\"name\":\"jack\",\"age\":10,\"sex\":2}";
        TestDTO dto = JsonUtil.toJavaObject(str1,TestDTO.class);
        System.out.println(dto.toString());
    }

    @org.junit.jupiter.api.Test
    public void test07() {
        Map<String,String> map = new HashMap<>();
        Map<String,String> cMap = new ConcurrentHashMap<>();
    }

    @org.junit.jupiter.api.Test
    public void test08() {
        String r = "09";
        int r1 = Integer.parseInt(r);
        System.out.println(r1);
    }
}
