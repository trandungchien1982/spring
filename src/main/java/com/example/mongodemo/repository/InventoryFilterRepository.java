package com.example.mongodemo.repository;
import com.example.mongodemo.model.Inventory;
import java.util.List;
public interface InventoryFilterRepository {
    List<Inventory> search(String productId, Integer version, String status);
}
