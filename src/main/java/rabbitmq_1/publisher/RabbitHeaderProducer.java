package rabbitmq_1.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rabbitmq_1.dto.Order;

@Service
public class RabbitHeaderProducer {
    private final static String HEADER = "name";
    @Value("${rabbit_header_exchange_name}")
    private String exchange;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitHeaderProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitHeaderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(Order order) {
        MessageProperties properties = new MessageProperties();
        properties.setHeader(HEADER, order.getName());

        MessageConverter converter = new Jackson2JsonMessageConverter();
        Message message = converter.toMessage(order, properties);
        rabbitTemplate.convertAndSend(exchange, "", message);
        LOGGER.info(String.format("Json message sent -> %s", order.toString()));
    }

}
