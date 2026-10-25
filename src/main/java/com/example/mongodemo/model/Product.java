package com.example.mongodemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import java.math.BigDecimal;
import java.util.List;

@Document(collection = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    @NotBlank
    private String productId;
    @NotNull @Min(1)
    private Integer version;
    @NotBlank
    private String status;
    @NotBlank
    private String name;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    // Reverse lookup: inventories.productId == this product's productId; not persisted.
    @ReadOnlyProperty
    @DocumentReference(lookup = "{ 'productId': ?#{#self.productId} }", lazy = true)
    //@JsonIgnoreProperties("product")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Inventory> inventories;
}
