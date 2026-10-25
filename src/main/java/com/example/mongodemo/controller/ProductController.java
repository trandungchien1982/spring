package com.example.mongodemo.controller;
import com.example.mongodemo.model.Inventory;
import com.example.mongodemo.model.Product;
import com.example.mongodemo.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
    private final ProductRepository repository;
    public ProductController(ProductRepository repository) { this.repository = repository; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product body) {
        String id = body.getId() == null ? UUID.randomUUID().toString() : body.getId();
        if (repository.existsById(id)) throw new ResponseStatusException(HttpStatus.CONFLICT, "ID already exists");
        body.setId(id);
        body.setInventories(null);
        return repository.save(body);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable String id) {
      Product result = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
      List<Inventory> inventories = result.getInventories();
      log.info("-- Total inventory: {}", inventories);
      return result;
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable String id, @Valid @RequestBody Product body) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        if (body.getId() != null && !body.getId().equals(id)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch");
        body.setId(id);
        body.setInventories(null);
        return repository.save(body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        repository.deleteById(id);
    }

    @GetMapping
    public List<Product> search(@RequestParam(required = false) String productId,
                             @RequestParam(required = false) Integer version,
                             @RequestParam(required = false) String status) {
        return repository.search(productId, version, status);
    }

    @GetMapping("/derived")
    public List<Product> derived(@RequestParam String productId, @RequestParam String status) {
        return repository.findByProductIdAndStatus(productId, status);
    }

    @GetMapping("/annotated")
    public List<Product> annotated(@RequestParam String productId, @RequestParam Integer version,
                                @RequestParam String status) {
        return repository.findUsingQuery(productId, version, status);
    }

}
