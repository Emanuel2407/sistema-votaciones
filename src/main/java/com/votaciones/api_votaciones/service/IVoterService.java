package com.votaciones.api_votaciones.service;

import com.votaciones.api_votaciones.dto.VoterRequestDto;
import com.votaciones.api_votaciones.dto.VoterResponseDto;
import com.votaciones.api_votaciones.model.Voter;

import java.util.List;

public interface IVoterService {

    List<VoterResponseDto> findAllVoters();

    Voter findVoter(Long id);

    VoterResponseDto findVoterById(Long voterId);

    VoterResponseDto saveVoter(VoterRequestDto newVoter);

    void deleteVoter(Long id);
}
