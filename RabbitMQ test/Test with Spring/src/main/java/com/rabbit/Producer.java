package com.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Producer {

    private AmqpTemplate amqpTemplate = rabbitTemplate(connectionFactory());

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    public Producer() throws IOException {
    }

    public void open(String msg){
        this.amqpTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("Send msg = " + msg);
    }

    public void produceMsg(String msg){
        this.amqpTemplate.convertAndSend(exchange, routingKey, msg);
        //this.amqpTemplate.
        System.out.println("Send msg = " + msg);
    }


    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) throws IOException {
        //Channel channel = connectionFactory.createChannel();
        //channel.queueDeclare(queueName, false, false, false, null);
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        amqpAdmin(connectionFactory).declareQueue(queue());

        return rabbitTemplate;
    }

    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    Queue queue(){
        return new Queue("MyRabbitQueue2", true);
    }
}
