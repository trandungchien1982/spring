package com.example.mongodemo.repository;
import com.example.mongodemo.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
public interface InventoryRepository extends MongoRepository<Inventory, String>, InventoryFilterRepository {
    // BSON query using positional parameters
    @Query("{ 'productId': ?0, 'version': ?1 }")
    List<Inventory> findUsingQuery(String productId, Integer version);
    List<Inventory> findByProductId(String productId);
}
