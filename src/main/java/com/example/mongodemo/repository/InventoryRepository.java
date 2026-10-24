package com.example.mongodemo.repository;
import com.example.mongodemo.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
public interface InventoryRepository extends MongoRepository<Inventory, String>, InventoryFilterRepository {
    // Derived query
    List<Inventory> findByProductIdAndStatus(String productId, String status);
    // BSON query using positional parameters
    @Query("{ 'productId': ?0, 'version': ?1, 'status': ?2 }")
    List<Inventory> findUsingQuery(String productId, Integer version, String status);
    List<Inventory> findByProductId(String productId);
}
