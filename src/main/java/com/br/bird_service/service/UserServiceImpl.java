package com.br.bird_service.service;

import com.br.bird_service.dto.UserCreateDTO;
import com.br.bird_service.dto.UserDTO;
import com.br.bird_service.exception.BusinessServiceException;
import com.br.bird_service.interfaces.UserService;
import com.br.bird_service.mapper.UserMapper;
import com.br.bird_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.br.bird_service.exception.enumeration.ErrorApiCode.USER_EXIST;
import static com.br.bird_service.exception.enumeration.ErrorApiCode.USER_NOT_FOUND;
import static com.br.bird_service.helper.MessageHelper.getMessage;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Mono<UserDTO> create(UserCreateDTO dto) {
        return userRepository.findByCpf(dto.getCpf())
                .flatMap(it -> Mono.error(new BusinessServiceException(CONFLICT, getMessage(USER_EXIST, dto.getCpf()))))
                .doOnError(it -> log.error("User with cpf: {} already exists", dto.getCpf()))
                .switchIfEmpty(Mono.defer(() -> userRepository.save(userMapper.map(dto))))
                .flatMap(it -> userRepository.findByCpf(dto.getCpf()))
                .doOnSuccess(it -> log.info("User created successfully with cpf: {}", it.getCpf()))
                .doOnError(error -> log.error("Error creating user: {}", error.getMessage()))
                .map(userMapper::map);
    }

    @Override
    public Mono<UserDTO> findById(String id) {
        return userRepository.findById(id)
                .flatMap(user -> Mono.just(userMapper.map(user)))
                .switchIfEmpty(Mono.error(new BusinessServiceException(NOT_FOUND, getMessage(USER_NOT_FOUND, id))))
                .doOnSuccess(it -> log.info("User found with id: {}", id))
                .doOnError(it -> log.error("User with id: {} not found", id));
    }

    @Override
    public Flux<UserDTO> findAll() {
        return userRepository.findAll()
                .map(userMapper::map)
                .doOnComplete(() -> log.info("User consultation completed"))
                .doOnError(e -> log.error("Error when obtaining users: {}", e.getMessage()));
    }
}
