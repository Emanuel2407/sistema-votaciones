package com.votaciones.api_votaciones.dto;

import java.util.List;

public record VotingStatisticsDto(
        List<CandidateStatisticsDto> candidates,
        long totalVotersVoted
) {
}
