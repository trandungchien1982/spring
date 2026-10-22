package com.example.demo.controller;

import com.example.demo.model.QueueMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

  private static final DateTimeFormatter TIME_FORMAT =
          DateTimeFormatter.ofPattern("yyyy/MMM/dd HH:mm:ss", Locale.ENGLISH);

  private final RabbitTemplate rabbitTemplate;
  private final String mainQueue;

  public RabbitMQController(
          RabbitTemplate rabbitTemplate,
          @Value("${app.rabbitmq.main-queue}") String mainQueue) {
    this.rabbitTemplate = rabbitTemplate;
    this.mainQueue = mainQueue;
  }

  @GetMapping("/ok-message")
  public ResponseEntity<QueueMessage> sendOkMessage() {
    QueueMessage msg = new QueueMessage(
            "message1",
            "The OK message at time: " + LocalDateTime.now().format(TIME_FORMAT));
    // Default exchange: routing key == queue name
    rabbitTemplate.convertAndSend(mainQueue, msg);
    return ResponseEntity.ok(msg);
  }
}
