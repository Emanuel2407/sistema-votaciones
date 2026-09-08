package com.votaciones.api_votaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CandidateRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        String name,

        // Permite null, pero rechaza cadenas vacías o compuestas solo por espacios
        @Pattern(
                regexp = ".*\\S.*",
                message = "El partido no puede contener únicamente espacios"
        )
        String party
) {
}