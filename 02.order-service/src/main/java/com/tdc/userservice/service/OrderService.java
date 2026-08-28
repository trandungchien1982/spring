package com.tdc.userservice.service;

import com.tdc.userservice.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {
  Logger logger = LoggerFactory.getLogger(getClass());
  private final Map<Long, Order> users = new ConcurrentHashMap<>();
  private final AtomicLong seq = new AtomicLong();

  public Collection<Order> findAll() {
    logger.warn("Ben trong OrderService.findAll() ... ");
    logger.info(" -- Get from message of OrderService: trace_id: {}, span_id: {}", MDC.get("trace_id"), MDC.get("span_id"));
    logger.info(" -- Get from message of OrderService: traceID: {}, spanID: {}", MDC.get("traceID"), MDC.get("spanID"));
    logger.info(" -- Get from message of OrderService: traceId: {}, spanId: {}", MDC.get("traceId"), MDC.get("spanId"));


    return users.values();
  }

  public Order findById(Long id) {
    var u = users.get(id);
    if (u == null) throw new IllegalArgumentException("User not found: " + id);
    return u;
  }

  public Order create(Order in) {
    long id = seq.incrementAndGet();
    var u = new Order(id, in.username(), in.email());
    users.put(id, u);
    return u;
  }

  public Order update(Long id, Order in) {
    if (!users.containsKey(id)) throw new IllegalArgumentException("User not found: " + id);
    var u = new Order(id, in.username(), in.email());
    users.put(id, u);
    return u;
  }

  public void delete(Long id) {
    if (users.remove(id) == null) throw new IllegalArgumentException("User not found: " + id);
  }
}
