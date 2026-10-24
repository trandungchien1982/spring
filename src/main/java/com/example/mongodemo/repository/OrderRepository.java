package com.example.mongodemo.repository;
import com.example.mongodemo.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
public interface OrderRepository extends MongoRepository<Order, String>, OrderFilterRepository {
    // Derived query
    List<Order> findByCustomerIdAndStatus(String customerId, String status);
    // BSON query using positional parameters
    @Query("{ 'customerId': ?0, 'version': ?1, 'status': ?2 }")
    List<Order> findUsingQuery(String customerId, Integer version, String status);

}
