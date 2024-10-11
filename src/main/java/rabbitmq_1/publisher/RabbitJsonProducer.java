package rabbitmq_1.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rabbitmq_1.dto.Order;

@Service
public class RabbitJsonProducer {
    @Value("${rabbit_json_routing_key}")
    private String jsonRoutingKey;
    @Value("${rabbit_exchange_name}")
    private String rabbitExchange;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitJsonProducer.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Order order) {
        LOGGER.info(String.format("Json message sent -> %s", order.toString()));
        rabbitTemplate.convertAndSend(rabbitExchange, jsonRoutingKey, order);
    }
}
