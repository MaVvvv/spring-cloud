package com.hxb.jdk8.testjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hxb.jdk8.dto.TestDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 测试json
 *
 * @author Ma_wei
 * @since 2020/8/13 20:55
 */
public class TestJson {

    @Test
    public void test1(){
        String json = "{\"msg\":\"123\",\"code\":1,\"data\":{\"id\":1,\"name\":\"张三\",\"age\":false}}";
        //MsgDTO msg = new JsonUtil().parseJson(json,MsgDTO.class);
        //Person p = JsonUtil.toJavaObject(JsonUtil.toJson(msg.getData()),Person.class);
        //System.out.println(p.toString());
        //System.out.println(msg.toString());
    }

    @Test
    public void test2(){
        String jsonStr = "[{\"skuId\":\"1007228\",\"status\":0},{\"skuId\":\"1037763\",\"status\":0},{\"skuId\":\"1044335\",\"status\":0},{\"skuId\":\"1045286\",\"status\":0},{\"skuId\":\"1171287\",\"status\":0},{\"skuId\":\"1261762\",\"status\":0}]";
        List<TestDTO> skuStatusModel = JSONObject.parseObject(jsonStr,new TypeReference<List<TestDTO>>(){});
        List<TestDTO> skuStatusModel1 = JSON.parseArray(jsonStr,TestDTO.class);
        skuStatusModel.forEach(dto ->{
            System.out.println(dto.toString());
        });
        skuStatusModel1.forEach(testDTO -> {
            System.out.println(testDTO.toString());
        });

    }
}
