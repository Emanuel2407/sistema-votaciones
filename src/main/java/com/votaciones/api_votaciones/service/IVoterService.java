package com.votaciones.api_votaciones.service;

import com.votaciones.api_votaciones.dto.VoterRequestDto;
import com.votaciones.api_votaciones.dto.VoterResponseDto;
import com.votaciones.api_votaciones.model.Voter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IVoterService {

    Page<VoterResponseDto> findAllVoters(Pageable pageable);

    Voter findVoter(Long id);

    VoterResponseDto findVoterById(Long voterId);

    VoterResponseDto saveVoter(VoterRequestDto newVoter);

    void deleteVoter(Long id);
}
