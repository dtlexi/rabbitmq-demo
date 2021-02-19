package online.renjilin.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class Listener {
    @RabbitListener(queues = "queue_inform_email")
    public void listenerEmail(String msg, Message message, Channel channel)
    {
        System.out.println("--------------queue_inform_email---------------");
        System.out.println(msg);
        System.out.println("--------------end---------------");
        System.out.println("");
    }

    @RabbitListener(queues = "queue_inform_sms")
    public void listenerSms(String msg, Message message, Channel channel)
    {
        System.out.println("--------------queue_inform_sms---------------");
        System.out.println(msg);
        System.out.println("--------------end---------------");
        System.out.println("");
    }
}
