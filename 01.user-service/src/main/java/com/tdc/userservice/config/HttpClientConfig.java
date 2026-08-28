package com.tdc.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfig {

  @Bean
  public OrderServiceClient orderServiceClient(
          @Value("${order.service.url}") String orderServiceUrl
  ) {

    RestClient restClient = RestClient.builder()
            .baseUrl(orderServiceUrl)
            .build();

    RestClientAdapter adapter =
            RestClientAdapter.create(restClient);

    HttpServiceProxyFactory factory =
            HttpServiceProxyFactory
                    .builderFor(adapter)
                    .build();

    return factory.createClient(OrderServiceClient.class);
  }
}