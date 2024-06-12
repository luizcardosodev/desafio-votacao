package com.br.bird_service.interfaces;

import com.br.bird_service.dto.UserCreateDTO;
import com.br.bird_service.dto.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDTO> create(UserCreateDTO dto);
    Mono<UserDTO> findById(String id);
    Flux<UserDTO> findAll();
}
