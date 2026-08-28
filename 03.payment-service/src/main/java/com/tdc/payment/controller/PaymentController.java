package com.tdc.payment.controller;

import com.tdc.payment.model.Payment;
import com.tdc.payment.service.PaymentService;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
  Logger logger = LoggerFactory.getLogger(getClass());
  private final PaymentService service;

  public PaymentController(PaymentService service) {
    this.service = service;
  }

  @GetMapping
  public Collection<Payment> findAll() {
    logger.info("Ben trong findAll() - PaymentController: nowTime: {}", new Date());
    logger.info(" -- Get from message of PaymentController: trace_id: {}, span_id: {}", MDC.get("trace_id"), MDC.get("span_id"));
    showTraceIdFromContext();

    logger.info("Xu ly cap do 01 - Controller ... ");
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Payment findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Payment create(@RequestBody Payment user) {
    logger.info("Ben trong create() - PaymentController: nowTime: {}", new Date());
    return service.create(user);
  }

  @PutMapping("/{id}")
  public Payment update(@PathVariable Long id, @RequestBody Payment user) {
    return service.update(id, user);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }

  private void showTraceIdFromContext() {
    SpanContext context =
            Span.current().getSpanContext();

    if (context.isValid()) {
      String traceId = context.getTraceId();
      String spanId = context.getSpanId();

      logger.info(
              "CUATUI - Current traceId={}, spanId={}",
              traceId,
              spanId
      );
    }
  }
}
