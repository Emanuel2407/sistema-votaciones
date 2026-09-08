package com.votaciones.api_votaciones.service;

import com.votaciones.api_votaciones.dto.CandidateRequestDto;
import com.votaciones.api_votaciones.dto.CandidateResponseDto;
import com.votaciones.api_votaciones.model.Candidate;

import java.util.List;

public interface ICandidateService {

    List<CandidateResponseDto> findAllCandidates();

    Candidate findCandidate(Long id);

    CandidateResponseDto findCandidateById(Long candidateId);

    CandidateResponseDto saveCandidate(CandidateRequestDto newCandidate);

    void deleteCandidate(Long id);

}
