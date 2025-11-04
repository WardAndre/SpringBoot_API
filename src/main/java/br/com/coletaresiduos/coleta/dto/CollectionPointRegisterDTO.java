package br.com.coletaresiduos.coleta.dto;

import br.com.coletaresiduos.coleta.model.Material;
import jakarta.validation.constraints.*;

public record CollectionPointRegisterDTO(

        @NotBlank(message = "Address is mandatory")
        String address,

        @NotBlank(message = "Material is mandatory")
        Material material

) {
}
