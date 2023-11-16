package mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(getClass());



    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Start running CLI for SpringMVC + Interceptors ...");

        for (int i = 0; i < 1; i++) {
            String greetings = executeGET("http://localhost:5810/greeting");
            log.info(" ---> Result GET: " + greetings);
        }

        log.info(" >> We will stop the JavaApp now. Bye bye ...");

        System.exit(0);
    }

    private String executeGET(String url) {
        RestTemplate rest = new RestTemplate();
        String responseData = rest.getForObject(url, String.class);
        return responseData;
    }

}
