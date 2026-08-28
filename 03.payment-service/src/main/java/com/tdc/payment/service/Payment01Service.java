package com.tdc.payment.service;

import com.tdc.payment.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class Payment01Service {
  Logger logger = LoggerFactory.getLogger(getClass());
  private final Map<Long, Payment> users = new ConcurrentHashMap<>();
  private final AtomicLong seq = new AtomicLong();

  public void callSomething() {
    logger.warn("Ben trong Payment01Service.callSomething() ... ");
    logger.info("Tiep theo ben trong callSomething() ...");
  }
}
