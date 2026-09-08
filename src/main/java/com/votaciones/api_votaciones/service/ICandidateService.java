package com.votaciones.api_votaciones.service;

import com.votaciones.api_votaciones.dto.CandidateRequestDto;
import com.votaciones.api_votaciones.dto.CandidateResponseDto;

import java.util.List;

public interface ICandidateService {

    List<CandidateResponseDto> findAllCandidates();

    CandidateResponseDto findCandidateById(Long candidateId);

    CandidateResponseDto saveCandidate(CandidateRequestDto newCandidate);

    void deleteCandidate(Long id);

}
