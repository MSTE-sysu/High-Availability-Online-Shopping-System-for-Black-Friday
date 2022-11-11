package com.jiuzhang.seckill.services;


import com.jiuzhang.seckill.db.dao.SeckillActivityDao;
import com.jiuzhang.seckill.db.po.SeckillActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillOverSellService {
    @Autowired
    SeckillActivityDao seckillActivityDao;

    public String processSeckill(long activityId){
        SeckillActivity seckillActivity = seckillActivityDao.querySeckillActivityById(activityId);
        long availableStock = seckillActivity.getAvailableStock();
        String result;
        if (availableStock > 0){
            result = "恭喜， 抢购成功！";
            System.out.println(result);
            availableStock = availableStock - 1;
            seckillActivity.setAvailableStock(new Integer("" + availableStock));
            seckillActivityDao.updateSeckillActivity(seckillActivity);
        }
        else{
            result = "抱歉，抢购失败，商品已经售完";
            System.out.println(result);
        }
        return result;
    }

}
