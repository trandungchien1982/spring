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

@Document(collection = "inventories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    private String id;
    @NotBlank
    private String productId;
    @NotNull @Min(1)
    private Integer version;
    private String warehouseId;
    @NotNull @Min(0)
    private Integer quantity;

    // Resolved from products.productId == this.productId; not persisted.
    @ReadOnlyProperty
    @DocumentReference(lookup = "{ 'productId': ?#{#target} }")
    //@JsonIgnoreProperties("inventories")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Product product;
}
