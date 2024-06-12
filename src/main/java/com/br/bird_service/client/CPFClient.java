package com.br.bird_service.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class CPFClient {

    private final WebClient devClient;

    public Mono<String> generateCpf() {
        return devClient.post()
                .uri("/ferramentas_online.php")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("acao", "gerar_cpf")
                        .with("pontuacao", "N"))
                .retrieve()
                .bodyToMono(String.class);
    }
}
