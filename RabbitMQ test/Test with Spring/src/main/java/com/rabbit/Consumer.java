package com.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${rabbitmq.queue}")
public class Consumer{
//    @RabbitHandler
//    public void receive(String in) {
//        System.out.println(" [x] Received '" + in + "'");
//    }

    @RabbitHandler
    public void handleDelta(final byte[] message) {
        String messageBody = new String(message);
        System.out.println(" [x] Received '" + messageBody + "'");
    }
}
