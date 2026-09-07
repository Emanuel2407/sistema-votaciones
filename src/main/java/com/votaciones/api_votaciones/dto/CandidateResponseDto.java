package com.votaciones.api_votaciones.dto;

public record CandidateResponseDto(
        Long id,
        String name,
        String party,
        Integer votes
) {
}
