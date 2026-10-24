package com.example.mongodemo.repository;
import com.example.mongodemo.model.Product;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class ProductFilterRepositoryImpl implements ProductFilterRepository {
    private final MongoTemplate template;
    public ProductFilterRepositoryImpl(MongoTemplate template) { this.template = template; }
    @Override public List<Product> search(String productId, Integer version, String status) {
        Query query = new Query();
        if (productId != null && !productId.isBlank()) query.addCriteria(Criteria.where("productId").is(productId));
        if (version != null) query.addCriteria(Criteria.where("version").is(version));
        if (status != null && !status.isBlank()) query.addCriteria(Criteria.where("status").is(status));
        query.limit(100);
        return template.find(query, Product.class);
    }
}
