package rabbitmq_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rabbitmq_1.publisher.RabbitProducer;

@RestController
@RequestMapping("/api")
public class RabbitController {
    @Autowired
    private RabbitProducer rabbitProducer;

    @GetMapping
    public ResponseEntity<String> publishMessage(@RequestParam String message) {
        rabbitProducer.produce(message);
        return ResponseEntity.ok("Message sent to Rabbit queue");
    }
}
