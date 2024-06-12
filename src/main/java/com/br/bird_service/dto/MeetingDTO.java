package com.br.bird_service.dto;

import com.br.bird_service.model.MeetingSessionModel;
import com.br.bird_service.model.MeetingVoteModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDTO {
    private String id;
    @Schema(description = "Título da pauta")
    private String title;
    @Schema(description = "Descrição da pauta")
    private String description;
    @Schema(description = "Total de votos efetuados")
    private Long totalVotes;
    @Schema(description = "Total de votos SIM")
    private Long totalVotedYes;
    @Schema(description = "Total de votos NÃO")
    private Long totalVotedNo;
    @Schema(description = "Sessão de votação da pauta")
    private MeetingSessionModel session;
    @Schema(description = "Votações realizadas na pauta")
    private List<MeetingVoteModel> voting;
    @Schema(description = "Associado que criou a pauta")
    private String createdBy;
}
