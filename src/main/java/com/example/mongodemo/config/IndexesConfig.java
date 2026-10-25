package com.example.mongodemo.config;

import com.example.mongodemo.model.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
public class IndexesConfig {
  @Bean
  ApplicationRunner ensureIndexes(MongoTemplate mongo) {
    return args -> {
      mongo.indexOps(Order.class).ensureIndex(new Index().on("customerId", Sort.Direction.ASC).on("version", Sort.Direction.ASC).on("status", Sort.Direction.ASC));
      mongo.indexOps(Product.class).ensureIndex(new Index().on("productId", Sort.Direction.ASC).on("version", Sort.Direction.ASC).on("status", Sort.Direction.ASC));
      mongo.indexOps(Inventory.class).ensureIndex(new Index().on("productId", Sort.Direction.ASC).on("version", Sort.Direction.ASC).on("status", Sort.Direction.ASC));
    };
  }
}
