package com.br.bird_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingVoteRequestDTO {
    @Schema(description = "Identificação da pauta para efetuar votação")
    @NotNull(message = "Field 'meetingId' is required")
    private String meetingId;
    @Schema(description = "Identificação do associado que vota")
    @NotNull(message = "Field 'associatedId' is required")
    private String associatedId;
    @JsonIgnore
    private String associatedName;
    @Schema(description = "Motivo da votação")
    private String reason;
    private Boolean vote;
}
