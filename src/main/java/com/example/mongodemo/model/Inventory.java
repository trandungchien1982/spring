package com.example.mongodemo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "inventories")
public record Inventory(@Id String id, @jakarta.validation.constraints.NotBlank String productId, @jakarta.validation.constraints.NotNull @jakarta.validation.constraints.Min(1) Integer version, @jakarta.validation.constraints.NotBlank String status, String warehouseId, @jakarta.validation.constraints.NotNull @jakarta.validation.constraints.Min(0) Integer quantity) { }
