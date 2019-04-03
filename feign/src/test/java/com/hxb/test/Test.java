package com.hxb.test;

import com.hxb.feign.FeignApplication;
import com.hxb.feign.service.ApiService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-03 15:49
 */
@SpringBootTest(classes = FeignApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    @Autowired
    private ApiService apiService;

    @org.junit.Test
    public void test(){
        try {
            System.out.println(apiService.index());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
