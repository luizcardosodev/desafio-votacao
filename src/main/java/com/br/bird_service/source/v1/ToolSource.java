package com.br.bird_service.source.v1;

import com.br.bird_service.client.CPFClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/tools")
@AllArgsConstructor
public class ToolSource {
    private final CPFClient cpfClient;

    @Operation(summary = "Gera um cpf válido aleatório")
    @PostMapping(value = "/generateCpf", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> generateCpf() {
        return cpfClient.generateCpf();
    }
}
