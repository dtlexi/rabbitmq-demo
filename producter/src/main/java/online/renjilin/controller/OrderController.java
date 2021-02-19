package online.renjilin.controller;

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
    public String getOrder()
    {
        for (int i = 0; i < 100; i++) {
            this.rabbitTemplate.convertAndSend("exchange_topics_inform","inform.test.sms.1",i);
        }

        for (int i = 0; i < 100; i++) {
            this.rabbitTemplate.convertAndSend("exchange_topics_inform","inform.test.email.1",i);
        }
        return "OK";
    }
}
