package com.example.mongodemo.repository;
import com.example.mongodemo.model.Inventory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class InventoryFilterRepositoryImpl implements InventoryFilterRepository {
    private final MongoTemplate template;
    public InventoryFilterRepositoryImpl(MongoTemplate template) { this.template = template; }
    @Override public List<Inventory> search(String productId, Integer version, String status) {
        Query query = new Query();
        if (productId != null && !productId.isBlank()) query.addCriteria(Criteria.where("productId").is(productId));
        if (version != null) query.addCriteria(Criteria.where("version").is(version));
        if (status != null && !status.isBlank()) query.addCriteria(Criteria.where("status").is(status));
        query.limit(100);
        return template.find(query, Inventory.class);
    }
}
