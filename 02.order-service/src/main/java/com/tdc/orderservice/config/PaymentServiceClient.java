package com.tdc.orderservice.config;

import com.tdc.orderservice.model.Payment;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange(
        url = "/api/payments",
        accept = "application/json"
)
public interface PaymentServiceClient {
  @GetExchange
  List<Payment> getPayments();
}