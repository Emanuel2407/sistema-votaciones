package com.votaciones.api_votaciones.service;

import com.votaciones.api_votaciones.dto.VoteListResponseDto;
import com.votaciones.api_votaciones.dto.VoteRequestDto;
import com.votaciones.api_votaciones.dto.VoteResponseDto;
import com.votaciones.api_votaciones.dto.VotingStatisticsDto;

import java.util.List;

public interface IVoteService {

    List<VoteListResponseDto> findAllVotes();

    VoteResponseDto saveVote(VoteRequestDto newVote);

    VotingStatisticsDto getStatistics();

}
