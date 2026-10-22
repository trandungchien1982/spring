package com.example.demo.config;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnleashConfig {

  @Bean(destroyMethod = "shutdown")
  public Unleash unleash(
          @Value("${unleash.api-url}") String apiUrl,
          @Value("${unleash.api-token}") String apiToken,
          @Value("${unleash.app-name}") String appName,
          @Value("${unleash.instance-id}") String instanceId,
          @Value("${unleash.fetch-toggles-interval-seconds}") long fetchInterval) {
    io.getunleash.util.UnleashConfig config = new Builder()
            .appName(appName)
            .instanceId(instanceId)
            .unleashAPI(apiUrl)
            .apiKey(apiToken)
            .fetchTogglesInterval(fetchInterval)
            .synchronousFetchOnInitialisation(false)
            .build();
    return new DefaultUnleash(config);
  }
}
