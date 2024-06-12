package com.br.bird_service.repository;

import com.br.bird_service.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {
    Mono<UserEntity> findByCpf(String cpf);
}
