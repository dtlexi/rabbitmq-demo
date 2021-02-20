package online.renjilin.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;


@Component
public class Listener {
    @RabbitListener(queues = "queue_inform_sms",ackMode = "MANUAL")
    public void listenerEmail(String msg, Message message, Channel channel) throws IOException {
        System.out.println("queue_inform_sms_1:"+msg);
        if (isSuccess())
        {
            System.out.println("消费成功！");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }else {
            System.out.println("消费失败！消息退回");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }

    }

    @RabbitListener(queues = "queue_inform_sms",ackMode = "MANUAL")
    public void listenerSms(String msg, Message message, Channel channel) throws IOException {
        System.out.println("queue_inform_sms_2:"+msg);
        if (isSuccess())
        {
            System.out.println("消费成功！");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }else {
            System.out.println("消费失败！消息退回");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
    }


    public boolean isSuccess()
    {
        Random random=new Random();
        return random.nextBoolean();
    }
}
