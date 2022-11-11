package com.jiuzhang.seckill;

import com.jiuzhang.seckill.services.SeckillActivityService;
import com.jiuzhang.seckill.util.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class RedisDemoTest {
    @Resource
    private RedisService redisService;

    @Test
    public void stockTest(){
        redisService.setValue("stock:19", 1L);
    }

    @Test
    public void getStockTest(){
        String stock = redisService.getValue("stock:19");
        System.out.println(stock);
    }

    @Test
    public void stockDeuctValidatorTest(){
        boolean result = redisService.stockDeductValidator("stock:19");
        System.out.println("result:" + result);
        String stock = redisService.getValue("stock:19");
        System.out.println("stock:" + stock);
    }

    @Autowired
    SeckillActivityService seckillActivityService;

    @Test
    public void pushSeckillInfoToRedisTest(){
        seckillActivityService.pushSeckillInfoToRedis(19);
    }

}
