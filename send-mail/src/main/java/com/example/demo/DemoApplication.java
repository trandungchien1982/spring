package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    EmailService emailService;

	public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication
                .run(DemoApplication.class);
        EmailService emailService = applicationContext
                .getBean(EmailService.class);
        for (int i = 1; i < 1000; i++) {
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
