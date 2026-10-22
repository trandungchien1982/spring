package com.example.demo.consumer;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.model.QueueMessage;
import io.getunleash.Unleash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SimulateFailedConsumer {

  private static final Logger logger = LoggerFactory.getLogger(SimulateFailedConsumer.class);

  private static final String SIMULATE_ERROR_FLAG = "ff-simulate-error";

  private final Unleash unleash;

  public SimulateFailedConsumer(Unleash unleash) {
    this.unleash = unleash;
  }

  @RabbitListener(
          queues = "${app.rabbitmq.simulate-failed-queue:tdc-simulate-failed-queue}",
          containerFactory = "simulateFailedContainerFactory")
  public void consume(QueueMessage msg) throws InterruptedException {

    logger.info("Consumed message [key={}]: {}", msg.getKey(), msg.getMessage());

    if (unleash.isEnabled(SIMULATE_ERROR_FLAG)) {
      // Retry count is 0 on the first delivery, 1 on the first retry, ...
      int retryCount = RetrySynchronizationManager.getContext().getRetryCount();
      logger.warn("Simulating error for message [key={}]: {} (retry {}/{})",
              msg.getKey(), msg.getMessage(), retryCount, RabbitMQConfig.MAX_RETRY);

      Thread.sleep(3000);
      throw new IllegalStateException("Simulated failure for message key=" + msg.getKey());
    }

    logger.info("CONSUME - SUCCESS at time: {}", new Date());

  }
}
