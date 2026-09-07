package com.votaciones.api_votaciones.dto;

import jakarta.validation.constraints.NotNull;

public record VoteRequestDto(
        @NotNull Long voterId,
        @NotNull Long candidateId
) {
}
