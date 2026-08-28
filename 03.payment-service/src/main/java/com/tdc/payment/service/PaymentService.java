package com.tdc.payment.service;

import com.tdc.payment.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PaymentService {
  Logger logger = LoggerFactory.getLogger(getClass());
  private final Map<Long, Payment> users = new ConcurrentHashMap<>();
  private final AtomicLong seq = new AtomicLong();

  @Autowired
  Payment01Service payment01Service;

  public Collection<Payment> findAll() {
    logger.warn("Ben trong PaymentService.findAll() ... ");
    logger.info(" -- Get from message of PaymentService: trace_id: {}, span_id: {}", MDC.get("trace_id"), MDC.get("span_id"));
    logger.info(" -- Get from message of PaymentService: traceID: {}, spanID: {}", MDC.get("traceID"), MDC.get("spanID"));
    logger.info(" -- Get from message of PaymentService: traceId: {}, spanId: {}", MDC.get("traceId"), MDC.get("spanId"));


    payment01Service.callSomething();
    return users.values();
  }

  public Payment findById(Long id) {
    var u = users.get(id);
    if (u == null) throw new IllegalArgumentException("User not found: " + id);
    return u;
  }

  public Payment create(Payment in) {
    long id = seq.incrementAndGet();
    var u = new Payment(id, in.username(), in.email());
    users.put(id, u);
    return u;
  }

  public Payment update(Long id, Payment in) {
    if (!users.containsKey(id)) throw new IllegalArgumentException("User not found: " + id);
    var u = new Payment(id, in.username(), in.email());
    users.put(id, u);
    return u;
  }

  public void delete(Long id) {
    if (users.remove(id) == null) throw new IllegalArgumentException("User not found: " + id);
  }
}
