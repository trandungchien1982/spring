package demo.helloworld.services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class CustomMetricsService {

  private final Counter transactionCounter;
  private final Counter threadCounter;

  public CustomMetricsService(MeterRegistry meterRegistry) {

    this.transactionCounter = Counter.builder("tdc_count_transaction")
            .description("Total number of TDC transactions")
            .register(meterRegistry);

    this.threadCounter = Counter.builder("tdc_count_threads")
            .description("Total number of TDC threads")
            .register(meterRegistry);
  }

  public void increaseTransaction() {
    transactionCounter.increment();
  }

  public void increaseTransaction(double amount) {
    transactionCounter.increment(amount);
  }

  public void increaseThreads() {
    threadCounter.increment();
  }

  public void increaseThreads(double amount) {
    threadCounter.increment(amount);
  }

  public double getTransactionCount() {
    return transactionCounter.count();
  }

  public double getThreadCount() {
    return threadCounter.count();
  }
}