package com.br.bird_service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class RestClientConfiguration {
    public static final String WEBCLIENT_URL = "Creating WebClient in URL: {}";
    @Value("${client.4devs.url}")
    private String devUrl;

    @Bean
    @Lazy
    public WebClient devClient(final WebClient.Builder webClientBuilder) {
        log.debug(WEBCLIENT_URL, devUrl);
        return webClientBuilder
                .filter(logRequest())
                .baseUrl(devUrl)
                .build();
    }
    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }
}
