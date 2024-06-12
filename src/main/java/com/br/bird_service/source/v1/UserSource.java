package com.br.bird_service.source.v1;

import com.br.bird_service.dto.UserCreateDTO;
import com.br.bird_service.dto.UserDTO;
import com.br.bird_service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserSource {

    private UserService userService;

    @Operation(summary = "Cria um novo usuario associado",
            description = "Cria um usuário para que possa efetuar votação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado",
                    content = { @Content(mediaType = "application/json")})
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserDTO> create(@RequestBody() @Valid UserCreateDTO dto){
        return userService.create(dto);
    }

    @Operation(summary = "Retorna os usuários criados",
            description = "Retorna todos os associados que podem votar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json")}),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<UserDTO> getAll(){
        return userService.findAll();
    }
}
