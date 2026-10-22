package com.example.demo.consumer;

import com.example.demo.model.QueueMessage;
import io.getunleash.Unleash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OkMessageConsumer {

  private static final Logger logger = LoggerFactory.getLogger(OkMessageConsumer.class);

  private final Unleash unleash;
  private final String okMessageFlag;

  public OkMessageConsumer(
          Unleash unleash,
          @Value("${app.feature-flags.ok-message}") String okMessageFlag) {
    this.unleash = unleash;
    this.okMessageFlag = okMessageFlag;
  }

  @RabbitListener(queues = "${app.rabbitmq.main-queue}")
  public void consume(QueueMessage msg) {

    if (!unleash.isEnabled(okMessageFlag)) {
      logger.info("Feature flag '{}' is OFF, skip message: {}", okMessageFlag, msg);
      return;
    }

    logger.info("Consumed message [key={}]: {}", msg.getKey(), msg.getMessage());

  }
}
