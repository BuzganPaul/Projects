package com.example.demo;

import com.rabbit.Consumer;
import com.rabbit.Producer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
@EnableRabbit
public class DemoApplication {


    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(DemoApplication.class, args);
        Producer producer = new Producer();
        //producer.produceMsg("MESAJ");
        Thread.sleep(1000);
        //producer.produceMsg("MESAJ");

    }

}
