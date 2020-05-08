package com.hxb.webflux.domain.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-06-17 15:19
 */
@Component
public class RedisTest1 {

    /** 操作字符串的template，StringRedisTemplate是RedisTemplate的一个子集*/
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void getInfo () {
        this.changeDatabase(stringRedisTemplate,2);
        Set<String> result = stringRedisTemplate.opsForZSet().range("getAgentCallInfo:zset:getAgentList:9876:19",0,-1);
        for (String str:result) {
            System.out.println(str);
        }
    }

    /**
     * method_name: changeDatabase
     * param: [redisTemplate, number]
     * describe: 动态修改redis的数据库号
     * author: guojin
     * create_time: 2018/5/26 15:01
     **/
    protected void changeDatabase(RedisTemplate redisTemplate, int number){
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(number);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
    }
}
