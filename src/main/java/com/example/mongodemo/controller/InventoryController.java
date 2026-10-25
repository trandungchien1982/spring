package com.example.mongodemo.controller;
import com.example.mongodemo.model.Inventory;
import com.example.mongodemo.repository.InventoryRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {
    private final InventoryRepository repository;
    public InventoryController(InventoryRepository repository) { this.repository = repository; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory create(@Valid @RequestBody Inventory body) {
        String id = body.id() == null ? UUID.randomUUID().toString() : body.id();
        if (repository.existsById(id)) throw new ResponseStatusException(HttpStatus.CONFLICT, "ID already exists");
        return repository.save(new Inventory(id, body.productId(), body.version(), body.warehouseId(), body.quantity()));
    }

    @GetMapping("/{id}")
    public Inventory get(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found"));
    }

    @PutMapping("/{id}")
    public Inventory update(@PathVariable String id, @Valid @RequestBody Inventory body) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        if (body.id() != null && !body.id().equals(id)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch");
        return repository.save(new Inventory(id, body.productId(), body.version(), body.warehouseId(), body.quantity()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found");
        repository.deleteById(id);
    }

    @GetMapping
    public List<Inventory> search(@RequestParam(required = false) String productId,
                             @RequestParam(required = false) Integer version,
                             @RequestParam(required = false) String status) {
        return repository.search(productId, version, status);
    }

    @GetMapping("/annotated")
    public List<Inventory> annotated(@RequestParam String productId, @RequestParam Integer version) {
        return repository.findUsingQuery(productId, version);
    }
    @GetMapping("/by-product/{productId}")
    public List<Inventory> byProduct(@PathVariable String productId) {
        return repository.findByProductId(productId);
    }
}
