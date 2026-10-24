package com.example.mongodemo.repository;
import com.example.mongodemo.model.Product;
import java.util.List;
public interface ProductFilterRepository {
    List<Product> search(String productId, Integer version, String status);
}
