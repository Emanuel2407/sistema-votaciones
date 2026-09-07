package com.votaciones.api_votaciones.dto;

public record VoterResponseDto(
        Long id,
        String name,
        String email,
        Boolean hasVoted
) {
}
