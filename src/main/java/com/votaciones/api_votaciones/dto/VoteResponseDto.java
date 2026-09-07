package com.votaciones.api_votaciones.dto;

public record VoteResponseDto(
        Long id,
        CandidateResponseDto candidate,
        VoterResponseDto voter
) {
}
