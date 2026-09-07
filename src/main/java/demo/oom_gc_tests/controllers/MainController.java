package demo.oom_gc_tests.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.unwrap;


@Controller
@RequestMapping(path="/main") // This means URL's start with /user (after Application path)
@Transactional // Transactional for CustomSQL
@Slf4j
public class MainController {

    private static List<String> MAIN_LIST = new LinkedList<>();

    @GetMapping(path = "/increaseRAM")
    @ResponseBody
    public String increaseRAM(@RequestParam Optional<Integer> loopTimes) {

      // TODO: Try to increase RAM a lot ...
      ExecutorService executor = Executors.newFixedThreadPool(1);

      List<CompletableFuture<Void>> futures = new ArrayList<>(1);
      CompletableFuture<Void> future = CompletableFuture
              .supplyAsync(() -> {
                log.info(" -- MAIN - Current process ... ");
                loop(loopTimes.orElse(100));
                return null;
              }, executor)
              .thenAccept(item -> {
                log.info("MAIN - CompletableFuture completed -- : {}", item);
              })
              .exceptionally(exception -> {
                log.error("MAIN - CompletableFuture failed for ", exception);
                return null;
              });

      futures.add(future);

      log.info(" --- MAIN - Before allOf() : " + new Date());
      CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
      log.info(" --- MAIN - After allOf() : " + new Date());

      return "MAIN - InCreaseRAM ok ... " + new Date();
    }

  @GetMapping(path = "/clearRAM")
  @ResponseBody
  public String clearRAM() {

    // TODO: Try to increase RAM a lot ...

    log.info(" --- MAIN - Try to remove all elements : " + new Date());
    MAIN_LIST.clear();
    log.info(" --- MAIN - After clear() : " + new Date());

    return "Clear RAM ok ... " + new Date();
  }

    private void loop(int loopTimes) {
      log.info(" -- MAIN - Process loop with loopTimes: {} - with about the same MB of RAM has been grabbed. ", loopTimes);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      for (int i = 0; i < loopTimes; i++) {
        if (i % 50 == 0) log.info(" -- MAIN - Processing value : {}", i);

        String randomStr = RandomStringUtils.randomAlphanumeric(1024*1024);
        MAIN_LIST.add(randomStr);
      }
    }

}