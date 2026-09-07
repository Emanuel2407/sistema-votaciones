package com.votaciones.api_votaciones.dto;

import jakarta.validation.constraints.NotBlank;

public record CandidateRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        String name,
        String party
) {
}
