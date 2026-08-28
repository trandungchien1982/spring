package com.tdc.userservice.service;
import com.tdc.userservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class UserService {
  Logger logger = LoggerFactory.getLogger(getClass());
 private final Map<Long,User> users=new ConcurrentHashMap<>(); private final AtomicLong seq=new AtomicLong();
 public Collection<User> findAll(){
   logger.warn(" -- Inside UserService.findAll() ... nowTime: {}", new Date());
   return users.values();
 }
 public User findById(Long id){var u=users.get(id); if(u==null) throw new IllegalArgumentException("User not found: "+id); return u;}
 public User create(User in){long id=seq.incrementAndGet(); var u=new User(id,in.username(),in.email()); users.put(id,u); return u;}
 public User update(Long id,User in){if(!users.containsKey(id)) throw new IllegalArgumentException("User not found: "+id); var u=new User(id,in.username(),in.email()); users.put(id,u); return u;}
 public void delete(Long id){if(users.remove(id)==null) throw new IllegalArgumentException("User not found: "+id);}
}
