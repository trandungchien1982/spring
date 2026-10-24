package com.example.mongodemo.repository;
import com.example.mongodemo.model.Order;
import java.util.List;
public interface OrderFilterRepository {
    List<Order> search(String customerId, Integer version, String status);
}
