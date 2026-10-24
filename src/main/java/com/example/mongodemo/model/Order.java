package com.example.mongodemo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "orders")
public record Order(@Id String id, @jakarta.validation.constraints.NotBlank String customerId, @jakarta.validation.constraints.NotNull @jakarta.validation.constraints.Min(1) Integer version, @jakarta.validation.constraints.NotBlank String status, @org.springframework.data.mongodb.core.mapping.Field(targetType = org.springframework.data.mongodb.core.mapping.FieldType.DECIMAL128) java.math.BigDecimal totalAmount, java.time.Instant createdAt) { }
