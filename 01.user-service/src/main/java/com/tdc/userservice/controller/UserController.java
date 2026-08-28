package com.tdc.userservice.controller;
//import com.tdc.userservice.config.OrderServiceClient;
import com.tdc.userservice.config.OrderServiceClient;
import com.tdc.userservice.model.Order;
import com.tdc.userservice.model.User;
import com.tdc.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
@RestController @RequestMapping("/api/users")
public class UserController {
  Logger logger = LoggerFactory.getLogger(getClass());
  private final UserService service;
  @Autowired
  OrderServiceClient orderServiceClient;

  public UserController(UserService service){this.service=service;}
  @GetMapping public Collection<User> findAll(){
    // Execute order-service:
    logger.info(" -- Try to call the UserController ... ");
    Collection<Order> listOrders = orderServiceClient.getOrders();
    logger.info(" -- listOrders: {}", listOrders);
    return service.findAll();
  }
  @GetMapping("/{id}") public User findById(@PathVariable Long id){return service.findById(id);}
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public User create(@RequestBody User user){return service.create(user);}
  @PutMapping("/{id}") public User update(@PathVariable Long id,@RequestBody User user){return service.update(id,user);}
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
}
