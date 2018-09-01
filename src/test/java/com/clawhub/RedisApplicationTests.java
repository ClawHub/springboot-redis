package com.clawhub;

import com.clawhub.redis.RedisTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        System.out.println("------------");
        System.out.println(redisTemplate.set("test", "123456"));
        System.out.println(redisTemplate.get("test"));
        System.out.println(redisTemplate.del("test"));
    }

}
