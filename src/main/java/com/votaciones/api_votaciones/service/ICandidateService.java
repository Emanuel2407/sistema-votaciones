package com.votaciones.api_votaciones.service;

import com.votaciones.api_votaciones.dto.CandidateRequestDto;
import com.votaciones.api_votaciones.dto.CandidateResponseDto;
import com.votaciones.api_votaciones.model.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICandidateService {

    Page<CandidateResponseDto> findAllCandidates(Pageable pageable);

    Candidate findCandidate(Long id);

    CandidateResponseDto findCandidateById(Long candidateId);

    CandidateResponseDto saveCandidate(CandidateRequestDto newCandidate);

    void deleteCandidate(Long id);

}
