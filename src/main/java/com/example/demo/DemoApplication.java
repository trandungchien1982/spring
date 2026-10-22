package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

//    static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
    System.out.println(
            "KEY01 = " + System.getenv("KEY01")
    );
    System.out.println(
            "RABBITMQ_HOST = " + System.getenv("RABBITMQ_HOST")
    );

    System.out.println(
            "UNLEASH_API_URL = " + System.getenv("UNLEASH_API_URL")
    );

    System.out.println(
            "UNLEASH_API_TOKEN = " + System.getenv("UNLEASH_API_TOKEN")
    );

    SpringApplication.run(DemoApplication.class);
  }
}
