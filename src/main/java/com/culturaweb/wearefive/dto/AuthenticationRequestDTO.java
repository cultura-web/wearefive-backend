package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AuthenticationRequestDTO {

    @Size(min = 1, max = 20, message = "username debe tener mínimo 1 carácter y máximo 20")
    private String username;

    @Size(min = 8, message = "la contraseña debe tener mínimo 8 caracteres")
    private String password;
}
