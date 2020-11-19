package com.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RabbitController {

    //@Autowired
    //Producer producer;

    @GetMapping("/send")
    public String sendMessage(@RequestParam("msg") String msg){
        System.out.println("Sending: " + msg);
        for(int i = 0; i<25; i++)
        {
            //producer.produceMsg(msg);
        }
        return "Successfully message sent";
    }


}
