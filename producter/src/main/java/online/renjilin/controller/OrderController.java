package online.renjilin.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
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
        for (int i = 0; i < 10000; i++) {
            this.rabbitTemplate.convertAndSend("exchange_topics_inform", "inform.test.email.1", i);
        }

//        for (int i = 10; i >0 ; i--) {
//            Integer index=i;
//            Integer time=i * 1000;
//            Message message = MessageBuilder.withBody(index.toString().getBytes()).setExpiration(time.toString()).build();
//
//            this.rabbitTemplate.send("exchange_topics_inform", "inform.test.dead.1", message);
//
//        }


//        this.rabbitTemplate.convertAndSend("exchange_dead","info.dead.test","hello");
        return "OK";
    }
}
