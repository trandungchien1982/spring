package web_socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

  private Logger log = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Override
  public void run(String... args) {
    log.info("Start running CLI for Spring WebSocket with 3 Chat Room ...");
  }
}
