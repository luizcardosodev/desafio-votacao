package com.br.bird_service.dto;

import com.br.bird_service.validation.annotation.Cpf;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    @Schema(description = "Nome do associado")
    @NotNull(message = "Field 'name' is required")
    private String name;
    @Schema(description = "Email do associado")
    @NotNull(message = "Field 'email' is required")
    @Cpf()
    @NotNull
    private String cpf;
}
