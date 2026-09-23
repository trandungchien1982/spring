package demo.helloworld.controllers;

import demo.helloworld.services.CustomMetricsService;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// A controller that having /metrics with information
// (Actually NO NEEDED, we already have /actuator/prometheus)
@RestController
class MetricsController {

  private final PrometheusMeterRegistry registry;

  @Autowired
  private CustomMetricsService metricsService;

  public MetricsController(PrometheusMeterRegistry registry) {
    this.registry = registry;
  }

  @GetMapping("/metrics")
  public String getMetrics() {
    StringBuilder builder = new StringBuilder();
    builder.append("<html><body><pre>");
    builder.append(registry.scrape());
    builder.append("</pre></body></html>");
    return builder.toString().replace("tdc_", "<span style='color:red; font-size: 30px; font-weight: bold'>tdc_</span>");
  }

  @GetMapping("/transaction")
  public Map<String, Object> transaction() {

    metricsService.increaseTransaction();

    return Map.of(
            "message", "Transaction processed",
            "count", metricsService.getTransactionCount()
    );
  }

  @GetMapping("/thread")
  public Map<String, Object> thread() {

    metricsService.increaseThreads();

    return Map.of(
            "message", "Thread processed",
            "count", metricsService.getThreadCount()
    );
  }
}
