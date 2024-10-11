package rabbitmq_1.conf;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private final static String HEADER_NAME = "name";
    private final static String HEADER_VALUE_FRUIT = "apple";
    private final static String HEADER_VALUE_CAR = "skoda";
    @Value("${rabbit_queue_name}")
    private String queue;
    @Value("${rabbit_json_queue_name}")
    private String jsonQueue;
    @Value("${rabbit_json_car_queue_name}")
    private String jsonCarQueue;
    @Value("${rabbit_routing_key}")
    private String routingKey;
    @Value("${rabbit_json_routing_key}")
    private String jsonRoutingKey;
    @Value("${rabbit_exchange_name}")
    private String rabbitExchange;

    @Value("${rabbit_header_exchange_name}")
    private String rabbitHeaderExchange;

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    @Bean
    public Queue jsonCarQueue() {
        return new Queue(jsonCarQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(rabbitExchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(rabbitHeaderExchange);
    }

    @Bean
    public Binding headerBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(headersExchange())
                .where(HEADER_NAME)
                .matches(HEADER_VALUE_FRUIT);
    }

    @Bean
    public Binding headerCarBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(headersExchange())
                .where(HEADER_NAME)
                .matches(HEADER_VALUE_CAR);
    }
}
