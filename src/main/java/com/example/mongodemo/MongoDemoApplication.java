package com.example.mongodemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoDemoApplication {
  static final Logger logger = LoggerFactory.getLogger(MongoDemoApplication.class);
  public static void main(String[] args) {

    logger.info(" --- MONGODB_URI: {}",System.getenv("MONGODB_URI"));

    SpringApplication.run(MongoDemoApplication.class, args);
  }
}
