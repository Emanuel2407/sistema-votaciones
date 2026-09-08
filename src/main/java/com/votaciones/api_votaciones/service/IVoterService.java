package com.votaciones.api_votaciones.service;

import com.votaciones.api_votaciones.dto.VoterRequestDto;
import com.votaciones.api_votaciones.dto.VoterResponseDto;

import java.util.List;

public interface IVoterService {

    List<VoterResponseDto> findAllVoters();

    VoterResponseDto findVoterById(Long voterId);

    VoterResponseDto saveVoter(VoterRequestDto newVoter);

    void deleteVoter(Long id);
}
