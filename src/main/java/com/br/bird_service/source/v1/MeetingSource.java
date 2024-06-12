package com.br.bird_service.source.v1;

import com.br.bird_service.dto.MeetingDTO;
import com.br.bird_service.dto.MeetingRequestDTO;
import com.br.bird_service.dto.MeetingVoteRequestDTO;
import com.br.bird_service.interfaces.MeetingService;
import com.br.bird_service.repository.MeetingVoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/meetings")
@AllArgsConstructor
public class MeetingSource {

    private final MeetingService meetingService;
    private final MeetingVoteService meetingVoteService;


    @Operation(summary = "Retorna todas as pautas criadas")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            content = { @Content(mediaType = "application/json")})})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<MeetingDTO> getAll(){
        return meetingService.getAllMeetings();
    }

    @Operation(summary = "Realiza a criação de uma nova pauta",
            description = "Cria uma nova pauta e abre uma sessão para votação")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MeetingDTO.class))})})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<MeetingDTO> create(@RequestBody() @Valid MeetingRequestDTO dto){
        return meetingService.createMeeting(dto);
    }

    @Operation(summary = "Realiza votação de uma pauta")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MeetingDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Sessão de votação expirada", content = @Content),
            @ApiResponse(responseCode = "404", description = "Sessão de votação não encontrada", content = @Content),
            @ApiResponse(responseCode = "409", description = "O associado já efetuou a votação", content = @Content),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado", content = @Content)})
    @PostMapping(value = "/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<MeetingDTO> vote(@RequestBody() @Valid MeetingVoteRequestDTO dto){
        return meetingVoteService.vote(dto);
    }
}
