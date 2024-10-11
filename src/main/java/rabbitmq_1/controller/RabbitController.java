package rabbitmq_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rabbitmq_1.dto.Order;
import rabbitmq_1.publisher.RabbitHeaderProducer;
import rabbitmq_1.publisher.RabbitJsonProducer;
import rabbitmq_1.publisher.RabbitProducer;

@RestController
@RequestMapping("/api")
public class RabbitController {
    @Autowired
    private RabbitProducer rabbitProducer;
    @Autowired
    private RabbitJsonProducer rabbitJsonProducer;
    @Autowired
    private RabbitHeaderProducer rabbitHeaderProducer;

    @GetMapping
    public ResponseEntity<String> publishMessage(@RequestParam String message) {
        rabbitProducer.produce(message);
        return ResponseEntity.ok("Message sent to Rabbit queue");
    }

    @PostMapping
    public ResponseEntity<String> publishMessage(@RequestBody Order order) {
        rabbitJsonProducer.sendJsonMessage(order);
        return ResponseEntity.ok("Message sent to Rabbit queue");
    }

    @PostMapping("/header")
    public ResponseEntity<String> publishHeaderMessage(@RequestBody Order order) {
        rabbitHeaderProducer.produce(order);
        return ResponseEntity.ok("Message sent to Rabbit queue");
    }
}
