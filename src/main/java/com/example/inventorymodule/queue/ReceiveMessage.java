package com.example.inventorymodule.queue;

import com.example.inventorymodule.dto.OrderDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.inventorymodule.queue.Config.*;

@Component
public class ReceiveMessage {
    @Autowired
    ConsumerService service;

    @RabbitListener(queues = {QUEUE_INVENT})
    public void getInfo(OrderDto orderDto){
        System.out.println("Nhận được rồi tư từ rồi xử lý");
        service.handleMessage(orderDto);
        System.out.println("Xong");

    }
}
