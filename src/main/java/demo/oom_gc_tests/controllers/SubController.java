package demo.oom_gc_tests.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Controller
@RequestMapping(path="/sub") // This means URL's start with /user (after Application path)
@Transactional // Transactional for CustomSQL
@Slf4j
public class SubController {

    private static List<String> SUB_LIST = new LinkedList<>();

    @GetMapping(path = "/increaseRAM")
    @ResponseBody
    public String increaseRAM(@RequestParam Optional<Integer> loopTimes) {

      // TODO: Try to increase RAM a lot ...
      ExecutorService executor = Executors.newFixedThreadPool(1);

      List<CompletableFuture<Void>> futures = new ArrayList<>(1);
      CompletableFuture<Void> future = CompletableFuture
              .supplyAsync(() -> {
                log.info(" -- SUB - Current process ... ");
                loop(loopTimes.orElse(100));
                return null;
              }, executor)
              .thenAccept(item -> {
                log.info("SUB - CompletableFuture completed -- : {}", item);
              })
              .exceptionally(exception -> {
                log.error("SUB - CompletableFuture failed for ", exception);
                return null;
              });

      futures.add(future);

      log.info(" --- SUB - Before allOf() : {}", new Date());
      CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
      log.info(" --- SUB - After allOf() : {}", new Date());

      return "SUB - InCreaseRAM ok ... " + new Date();
    }

    private void loop(int loopTimes) {
      log.info(" -- SUB - Process loop with loopTimes: {} - with about the same MB of RAM has been grabbed. ", loopTimes);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      for (int i = 0; i < loopTimes; i++) {
        if (i % 50 == 0) log.info(" -- SUB - Processing value for subList : {} <=> MB of RAM ... ", i);

        String randomStr = RandomStringUtils.randomAlphanumeric(1024*1024);
        SUB_LIST.add(randomStr);
      }
    }

}