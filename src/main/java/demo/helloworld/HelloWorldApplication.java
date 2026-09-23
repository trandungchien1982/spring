package demo.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.prometheus.PrometheusMeterRegistry;

@SpringBootApplication
public class HelloWorldApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

}

// A controller that having /metrics with information
// (Actually NO NEEDED, we already have /actuator/prometheus)
@RestController
class MetricsController {

	private final PrometheusMeterRegistry registry;

	public MetricsController(PrometheusMeterRegistry registry) {
		this.registry = registry;
	}


        @GetMapping(
          value = "/metrics",
          produces = "application/openmetrics-text; version=1.0.0; charset=utf-8"
        )
	public String getMetrics() {
		return registry.scrape();
	}
}
