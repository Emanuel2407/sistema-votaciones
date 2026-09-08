package com.votaciones.api_votaciones.dto;

import jakarta.validation.constraints.NotNull;

public record VoteRequestDto(
        @NotNull(message = "El id del votante no puede ser null")
        Long voterId,

        @NotNull(message = "El id del candidato no puede ser null")
        Long candidateId
) {
}
