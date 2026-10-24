package com.example.mongodemo.repository;
import com.example.mongodemo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
public interface ProductRepository extends MongoRepository<Product, String>, ProductFilterRepository {
    // Derived query
    List<Product> findByProductIdAndStatus(String productId, String status);
    // BSON query using positional parameters
    @Query("{ 'productId': ?0, 'version': ?1, 'status': ?2 }")
    List<Product> findUsingQuery(String productId, Integer version, String status);

}
