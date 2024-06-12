package com.br.bird_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MeetingRequestDTO {
    @Schema(description = "Título da pauta que será criada")
    @NotNull(message = "Field 'title' is required")
    private String title;
    @Schema(description = "Descrição da pauta")
    @NotNull(message = "Field 'description' is required")
    private String description;
    @Schema(description = "Associado que cria a pauta")
    @NotNull(message = "Field 'associatedId' is required")
    private String associatedId;
    @Schema(description = "Duração em segundos EX.: 300s = 5m")
    private Integer durationSeconds;
}
