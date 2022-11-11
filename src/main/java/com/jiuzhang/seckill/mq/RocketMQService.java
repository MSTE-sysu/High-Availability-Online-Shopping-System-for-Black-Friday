package com.jiuzhang.seckill.mq;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RocketMQService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息
     * @param topic
     * @param body
     * @throws Exception
     */
    public void sendMessage(String topic,String body) throws Exception{
        Message message = new Message(topic,body.getBytes());
        rocketMQTemplate.getProducer().send(message);
    }
    /**
     * 发送延时消息
     *
     * @param topic
     * @param body
     * @param delayTimeLevel
     * @throws Exception
     */
    public void sendDelayMessage(String topic, String body, int delayTimeLevel)
            throws Exception {
        Message message = new Message(topic, body.getBytes());
        message.setDelayTimeLevel(delayTimeLevel);
        rocketMQTemplate.getProducer().send(message);
    }
}
