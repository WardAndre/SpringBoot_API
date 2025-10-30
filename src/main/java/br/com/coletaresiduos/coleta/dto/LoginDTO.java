package br.com.coletaresiduos.coleta.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "E-mail is mandatory")
        @Email(message = "E-mail is invalid")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        String password
) {
}
