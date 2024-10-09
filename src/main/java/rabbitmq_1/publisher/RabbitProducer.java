package rabbitmq_1.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {
    @Value("${rabbit_routing_key}")
    private String routingKey;
    @Value("${rabbit_exchange_name}")
    private String rabbitExchange;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitProducer.class);
    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(String message) {
        rabbitTemplate.convertAndSend(rabbitExchange, routingKey, message);

        LOGGER.info(String.format("Message was produced: -> %s", message));
    }
}
