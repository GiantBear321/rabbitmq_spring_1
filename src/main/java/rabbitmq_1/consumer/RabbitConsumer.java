package rabbitmq_1.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);

    @RabbitListener(queues = {"${rabbit_queue_name}"})
    public void consume(String message) {
        LOGGER.info(String.format("Message was consumed: -> %s", message));
    }
}
