package online.renjilin.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";

    public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";

    // 测试死信交换机队列
    public static final String QUEUE_INFORMATION_TEST_DEAD="queue_information_test_dead";

    // 死信交换机
    public static final String EXCHANGE_DEAD="exchange_dead";

    // 死信交换机绑定的队列
    public static final String QUEUE_DEAD="queue_dead";

    //声明队列
    @Bean(QUEUE_INFORM_SMS)
    public Queue QUEUE_INFORM_SMS() {
        Queue queue = new Queue(QUEUE_INFORM_SMS);
        return queue;
    }

    //声明队列
    @Bean(QUEUE_INFORM_EMAIL)
    public Queue QUEUE_INFORM_EMAIL() {
        Queue queue = new Queue(QUEUE_INFORM_EMAIL);
        return queue;
    }

    /**
     * 交换机配置
     * ExchangeBuilder提供了fanout、direct、topic、header交换机类型的配置
     *
     * @return the exchange
     */
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        //durable(true)持久化，消息队列重启后交换机仍然存在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
    }

    /**
     * channel.queueBind(INFORM_QUEUE_SMS,"inform_exchange_topic","inform.#.sms.#");
     * 绑定队列到交换机 .
     *
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_INFORM_SMS) Queue queue,
                                            @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.sms.#").noargs();
    }

    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.email.#").noargs();
    }

    @Autowired
    public void initRabbitTemplate(RabbitTemplate rabbitTemplate)
    {
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.out.println("");
//                System.out.println("---------------confirm-call-back--------------");
//                System.out.println(correlationData);
//                System.out.println(ack);
//                System.out.println(cause);
//                System.out.println("---------------------end----------------------");
//            }
//        });
//
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            System.out.println("");
//            System.out.println("---------------return-call-back--------------");
//            System.out.println(message);
//            System.out.println(replyCode);
//            System.out.println(replyText);
//            System.out.println(exchange);
//            System.out.println(routingKey);
//            System.out.println("---------------------end----------------------");
//        });
    }

    @Bean(EXCHANGE_DEAD)
    public Exchange EXCHANGE_DEAD()
    {
        //durable(true)持久化，消息队列重启后交换机仍然存在
        return ExchangeBuilder.topicExchange(EXCHANGE_DEAD).durable(true).build();
    }

    @Bean(QUEUE_DEAD)
    public Queue QUEUE_DEAD()
    {
        Queue queue=new Queue(QUEUE_DEAD);
        return queue;
    }

    @Bean
    public Binding BIND_EXCHANGE_DEAD(@Qualifier(EXCHANGE_DEAD) Exchange exchange,@Qualifier(QUEUE_DEAD) Queue queue)
    {
        return BindingBuilder.bind(queue).to(exchange).with("info.dead.#").noargs();
    }

    @Bean(QUEUE_INFORMATION_TEST_DEAD)
    public Queue QUEUE_INFORMATION_TEST_DEAD()
    {
        Map<String,Object> map = new HashMap<>();
        //设置附带的死信交换机
        map.put("x-dead-letter-exchange",EXCHANGE_DEAD);
        map.put("x-dead-letter-routing-key","inform.test.dead.test");
        //指定重定向的路由建 消息作废之后可以决定需不需要更改他的路由建 如map.put("x-dead-letter-routing-key","dead.order");
        return new Queue(QUEUE_INFORMATION_TEST_DEAD, true,false,false,map);
    }

    @Bean
    public Binding BIND_QUEUE_INFORMATION_TEST_DEAD(@Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange,@Qualifier(QUEUE_INFORMATION_TEST_DEAD) Queue queue)
    {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.dead.#").noargs();
    }
}
