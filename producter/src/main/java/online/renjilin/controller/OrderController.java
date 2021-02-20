package online.renjilin.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("order")
    @ResponseBody
    public String getOrder() {
//        for (int i = 0; i < 10; i++) {
//            this.rabbitTemplate.convertAndSend("exchange_topics_inform", "inform.test.sms.1", i);
//        }

        MessageProperties messageProperties=new MessageProperties();
        messageProperties.set
        Message message=new Message("hello".getBytes(),)

        this.rabbitTemplate.convertAndSend("exchange_topics_inform", "inform.test.dead.1", "hello");
        return "OK";
    }
}
