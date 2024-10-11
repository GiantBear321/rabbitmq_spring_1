package rabbitmq_1.dto;

import lombok.Data;

@Data
public class Order {
    private int id;
    private String name;
    private Double price;
}
