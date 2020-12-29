package com.channelsoft;

import com.cool.constant.TargetTypeEnum;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
public class SpringTest {

    @Test
    public void testEnumValueOf(){
        Integer value = 3;
        TargetTypeEnum targetTypeEnum = TargetTypeEnum.typeOf(3);
        try {
            TargetTypeEnum targetTypeEnum1 = TargetTypeEnum.valueOf("1234");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        log.info(targetTypeEnum.toString());
        System.out.println(targetTypeEnum.toString());
    }

    @Test
    public void testSplit(){
        String packageStr = "com.sunyur.www.dubbo.Test1";
        String className = packageStr.substring(packageStr.lastIndexOf(".") + 1);
        String packageName = packageStr.substring(0,packageStr.lastIndexOf("."));
        System.out.println("className = " + className);
        System.out.println("packageName = " + packageName);
    }
}
