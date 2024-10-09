package rabbitmq_1.conf;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${rabbit_queue_name}")
    private String queue;
    @Value("${rabbit_routing_key}")
    private String routingKey;
    @Value("${rabbit_exchange_name}")
    private String rabbitExchange;

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(rabbitExchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }
}
