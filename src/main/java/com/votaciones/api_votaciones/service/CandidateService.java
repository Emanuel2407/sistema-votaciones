package com.votaciones.api_votaciones.service;

import com.votaciones.api_votaciones.dto.CandidateRequestDto;
import com.votaciones.api_votaciones.dto.CandidateResponseDto;
import com.votaciones.api_votaciones.exception.CandidateHasVotesException;
import com.votaciones.api_votaciones.exception.CandidateNotFoundException;
import com.votaciones.api_votaciones.model.Candidate;
import com.votaciones.api_votaciones.repository.ICandidateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateService implements ICandidateService{

    private final ICandidateRepository candidateRepo;
    public CandidateService(ICandidateRepository candidateRepo) {
        this.candidateRepo = candidateRepo;
    }

    /**
     * Transforma un candidato en objeto DTO de respuesta
     */
    private CandidateResponseDto buildCandidateResponse(Candidate objCandidate){
        return new CandidateResponseDto(
                objCandidate.getId(),
                objCandidate.getName(),
                objCandidate.getParty(),
                objCandidate.getVotes()
        );
    }

    /**
     * Transforma una lista de candidatos en objetos DTO
     * de respuesta
     */
    private List<CandidateResponseDto> buildCandidatesResponse(List<Candidate> candidates){
        List<CandidateResponseDto> candidatesResponse = new ArrayList<>();

        for(Candidate objCandidate: candidates){
            candidatesResponse.add(
                    buildCandidateResponse(objCandidate)
            );
        }

        return candidatesResponse;
    }

    /**
     * Busca candidato por su ID o lanza excepción si no
     * existe
     */
    private Candidate findCandidate(Long id){
        return candidateRepo.findById(id)
                .orElseThrow(
                        () -> new CandidateNotFoundException("No existe candidato con id: " + id)
                );
    }

    @Transactional(readOnly = true)
    @Override
    public List<CandidateResponseDto> findAllCandidates() {
        return buildCandidatesResponse(
                candidateRepo.findAll()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public CandidateResponseDto findCandidateById(Long candidateId) {
        return buildCandidateResponse(
                findCandidate(candidateId)
        );
    }

    @Transactional
    @Override
    public CandidateResponseDto saveCandidate(CandidateRequestDto newCandidate) {
        Candidate objCandidate = new Candidate(
                null,
                newCandidate.name(),
                newCandidate.party(),
                0
        );

        candidateRepo.save(objCandidate);

        return buildCandidateResponse(
                objCandidate
        );
    }

    @Transactional
    @Override
    public void deleteCandidate(Long id) {
        Candidate objCandidate = findCandidate(id);

        if(objCandidate.getVotes() > 0){
            throw new CandidateHasVotesException(
                    "Para mantener la trazabilidad, no se puede eliminar un candidato que ya ha recibido votos"
            );
        }

        candidateRepo.delete(objCandidate);
    }
}
