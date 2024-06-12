package com.br.bird_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class UserModel {
    @Id
    private String id;
    private String name;
    private String cpf;
}
