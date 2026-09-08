package com.votaciones.api_votaciones.dto;

public record VoteListResponseDto(
        Long voteId,
        Long voterId,
        Long candidateId
) {
}
