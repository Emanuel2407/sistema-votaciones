package com.votaciones.api_votaciones.dto;

public record CandidateStatisticsDto(
        Long id,
        String name,
        String party,
        int totalVotes,
        double votePercentage
) {
}
