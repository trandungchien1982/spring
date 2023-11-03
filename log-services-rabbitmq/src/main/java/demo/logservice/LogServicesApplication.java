package demo.logservice;

import com.fasterxml.jackson.databind.DatabindException;
import demo.logservice.producer.RabbitMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class LogServicesApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	RabbitMQProducer rabbitMQProducer;

	public static void main(String[] args) {
		SpringApplication.run(LogServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		rabbitMQProducer.sendMessage("log-service, Try to send 3 sample logs :) to make sure RabbitMQ is working properly :)");
		for (int i = 0; i < 3; i++) {
			rabbitMQProducer.sendMessage("log-service, Sample message idx " + i);
		}
	}
}
