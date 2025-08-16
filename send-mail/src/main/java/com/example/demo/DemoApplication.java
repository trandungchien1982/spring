package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    EmailService emailService;

	public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication
                .run(DemoApplication.class);
        EmailService emailService = applicationContext
                .getBean(EmailService.class);
        logger.info(" >> Start to process sending email to server ...");
        for (int i = 1; i < 100; i++) {
            logger.info("      -- Send mail at pos: {}", i);

            emailService.sendMail(
                    "\"Techmaster 👻\" <ta.van.dung@techmaster.vn>",
                    "itprono3@gmail%d.com".formatted(i),
                    "Hello ✔" + i,
                    "<b>Hello world! %d</b>".formatted(i)
            );
        }

        System.exit(0);
	}

}
