package com.example.mongodemo.controller;
import com.example.mongodemo.model.Order;
import com.example.mongodemo.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repository;
    public OrderController(OrderRepository repository) { this.repository = repository; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody Order body) {
        String id = body.id() == null ? UUID.randomUUID().toString() : body.id();
        if (repository.existsById(id)) throw new ResponseStatusException(HttpStatus.CONFLICT, "ID already exists");
        return repository.save(new Order(id, body.customerId(), body.version(), body.status(), body.totalAmount(), body.createdAt()));
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable String id, @Valid @RequestBody Order body) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        if (body.id() != null && !body.id().equals(id)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch");
        return repository.save(new Order(id, body.customerId(), body.version(), body.status(), body.totalAmount(), body.createdAt()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        repository.deleteById(id);
    }

    @GetMapping
    public List<Order> search(@RequestParam(required = false) String customerId,
                             @RequestParam(required = false) Integer version,
                             @RequestParam(required = false) String status) {
        return repository.search(customerId, version, status);
    }

    @GetMapping("/derived")
    public List<Order> derived(@RequestParam String customerId, @RequestParam String status) {
        return repository.findByCustomerIdAndStatus(customerId, status);
    }

    @GetMapping("/annotated")
    public List<Order> annotated(@RequestParam String customerId, @RequestParam Integer version,
                                @RequestParam String status) {
        return repository.findUsingQuery(customerId, version, status);
    }

}
