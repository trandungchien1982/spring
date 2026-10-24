package com.example.mongodemo.controller;
import com.example.mongodemo.model.Product;
import com.example.mongodemo.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repository;
    public ProductController(ProductRepository repository) { this.repository = repository; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product body) {
        String id = body.id() == null ? UUID.randomUUID().toString() : body.id();
        if (repository.existsById(id)) throw new ResponseStatusException(HttpStatus.CONFLICT, "ID already exists");
        return repository.save(new Product(id, body.productId(), body.version(), body.status(), body.name(), body.price()));
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable String id, @Valid @RequestBody Product body) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        if (body.id() != null && !body.id().equals(id)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch");
        return repository.save(new Product(id, body.productId(), body.version(), body.status(), body.name(), body.price()));
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
