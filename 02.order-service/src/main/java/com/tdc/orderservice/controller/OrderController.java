package com.tdc.orderservice.controller;

import com.tdc.orderservice.config.PaymentServiceClient;
import com.tdc.orderservice.model.Order;
import com.tdc.orderservice.model.Payment;
import com.tdc.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  Logger logger = LoggerFactory.getLogger(getClass());
  private final OrderService service;

  @Autowired
  PaymentServiceClient paymentServiceClient;

  public OrderController(OrderService service) {
    this.service = service;
  }

  @GetMapping
  public Collection<Order> findAll() {
    logger.info("Ben trong findAll() - OrderController: nowTime: {}", new Date());
    logger.info(" -- Get from message of OrderController: trace_id: {}, span_id: {}", MDC.get("trace_id"), MDC.get("span_id"));
    logger.info("Chuan bi call paymentService.getPayments() ...");
    List<Payment> payments = paymentServiceClient.getPayments();
    logger.info("Danh sach payments: {}", payments);

    return service.findAll();
  }

  @GetMapping("/{id}")
  public Order findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Order create(@RequestBody Order user) {
    return service.create(user);
  }

  @PutMapping("/{id}")
  public Order update(@PathVariable Long id, @RequestBody Order user) {
    return service.update(id, user);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
