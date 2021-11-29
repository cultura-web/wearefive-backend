package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegistroClienteDTO {

    @Size(min = 1, max = 20, message = "username debe tener mínimo 1 carácter y máximo 20")
    private String username;

    @Size(min = 8, message = "la contraseña debe tener mínimo 8 caracteres")
    private String contrasena;

    @NotEmpty(message = "Debe ingresar apellidos")
    private String apellidos;

    @NotEmpty(message = "Debe ingresar nombres")
    private String nombres;

    @Email(message = "Ingrese un correo correcto")
    private String correo;

    @NotEmpty(message = "Debe ingresar dirección de envío")
    private String direccion;

    @Size(min = 10, max = 10, message = "Ingrese un numero de celular colombiano de 10 dígitos")
    private String celular;
}
