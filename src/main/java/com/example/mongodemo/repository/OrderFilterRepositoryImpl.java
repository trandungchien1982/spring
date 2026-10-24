package com.example.mongodemo.repository;
import com.example.mongodemo.model.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class OrderFilterRepositoryImpl implements OrderFilterRepository {
    private final MongoTemplate template;
    public OrderFilterRepositoryImpl(MongoTemplate template) { this.template = template; }
    @Override public List<Order> search(String customerId, Integer version, String status) {
        Query query = new Query();
        if (customerId != null && !customerId.isBlank()) query.addCriteria(Criteria.where("customerId").is(customerId));
        if (version != null) query.addCriteria(Criteria.where("version").is(version));
        if (status != null && !status.isBlank()) query.addCriteria(Criteria.where("status").is(status));
        query.limit(100);
        return template.find(query, Order.class);
    }
}
