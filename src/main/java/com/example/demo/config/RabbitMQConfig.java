package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final int MAX_RETRY = 5;
  public static final String DLQ_SUFFIX = ".dlq";

  @Bean
  public Queue mainQueue(@Value("${app.rabbitmq.main-queue}") String queueName) {
    return new Queue(queueName, true);
  }

  @Bean
  public Queue simulateFailedQueue(@Value("${app.rabbitmq.simulate-failed-queue:tdc-simulate-failed-queue}") String queueName) {
    return new Queue(queueName, true);
  }

  @Bean
  public Queue simulateFailedDlq(@Value("${app.rabbitmq.simulate-failed-queue:tdc-simulate-failed-queue}") String queueName) {
    return new Queue(queueName + DLQ_SUFFIX, true);
  }

  // Retry is attached only to this factory, so only listeners that select it are affected.
  // Boot's spring.rabbitmq.listener.simple.retry.* would apply to every listener.
  @Bean
  public SimpleRabbitListenerContainerFactory simulateFailedContainerFactory(
          SimpleRabbitListenerContainerFactoryConfigurer configurer,
          ConnectionFactory connectionFactory,
          RabbitTemplate rabbitTemplate,
          @Value("${app.rabbitmq.simulate-failed-queue:tdc-simulate-failed-queue}") String queueName) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    factory.setAdviceChain(RetryInterceptorBuilder.stateless()
            .maxAttempts(MAX_RETRY + 1) // first delivery + MAX_RETRY retries
            .backOffOptions(1000, 2.0, 10000)
            // After the retries are exhausted, publish the message to the DLQ via the default exchange
            .recoverer(new RepublishMessageRecoverer(rabbitTemplate, "", queueName + DLQ_SUFFIX))
            .build());
    return factory;
  }

  // Spring Boot wires this converter into the auto-configured RabbitTemplate
  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
