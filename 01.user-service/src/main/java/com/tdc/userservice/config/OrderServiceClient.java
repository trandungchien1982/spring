package com.tdc.userservice.config;

import com.tdc.userservice.model.Order;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange(
        url = "/api/orders",
        accept = "application/json"
)
public interface OrderServiceClient {
  @GetExchange
  List<Order> getOrders();
}