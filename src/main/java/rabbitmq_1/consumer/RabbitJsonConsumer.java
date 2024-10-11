package rabbitmq_1.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import rabbitmq_1.dto.Order;

@Service
public class RabbitJsonConsumer {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitJsonConsumer.class);

    @RabbitListener(queues = {"${rabbit_json_queue_name}"})
    public void consume(Order order) {
        LOGGER.info(String.format("Message was consumed: -> %s", order.toString()));
    }
}