package com.mxw.hxb.springboot.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.NameFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Ma_wei
 * @since 2021/3/8 18:06
 */
@Slf4j
@Component
public class JSONObjectKeyFilter implements NameFilter {

    @Override
    public String process(Object o, String name, Object o1) {
        log.info(JSONObject.toJSONString(o));
        log.info("name = {}",name);
        log.info("o1 = {}",JSONObject.toJSONString(o1));
        return name;
    }
}
