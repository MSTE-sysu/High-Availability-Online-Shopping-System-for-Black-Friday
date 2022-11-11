package com.jiuzhang.seckill.component;

import com.jiuzhang.seckill.db.dao.SeckillActivityDao;
import com.jiuzhang.seckill.db.po.SeckillActivity;
import com.jiuzhang.seckill.services.SeckillActivityService;
import com.jiuzhang.seckill.util.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisPreheatRunner implements ApplicationRunner {
    @Autowired
    RedisService redisService;

    @Autowired
    SeckillActivityDao seckillActivityDao;

    @Autowired
    SeckillActivityService seckillActivityService;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        List<SeckillActivity> seckillActivities = seckillActivityDao.querySeckillActivitysByStatus(1);
        for (SeckillActivity seckillActivity : seckillActivities){
            redisService.setValue("stock:" + seckillActivity.getId(), (long) seckillActivity.getAvailableStock());
            seckillActivityService.pushSeckillInfoToRedis(seckillActivity.getId());
        }
    }
}
