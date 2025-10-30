package br.com.coletaresiduos.coleta.dto;
import br.com.coletaresiduos.coleta.model.UserRole;
import jakarta.validation.constraints.*;

public record UserRegisterDTO(
        Long userId,

        @NotBlank(message = "Name is mandatory")
        String name,

        @NotBlank(message = "E-mail is mandatory")
        @Email(message = "E-mail is invalid")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        String password,

        UserRole role
) {
}
