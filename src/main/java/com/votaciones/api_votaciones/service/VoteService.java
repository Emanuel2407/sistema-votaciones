package com.votaciones.api_votaciones.service;

import com.votaciones.api_votaciones.dto.*;
import com.votaciones.api_votaciones.exception.VoterAlreadyVotedException;
import com.votaciones.api_votaciones.model.Candidate;
import com.votaciones.api_votaciones.model.Vote;
import com.votaciones.api_votaciones.model.Voter;
import com.votaciones.api_votaciones.repository.ICandidateRepository;
import com.votaciones.api_votaciones.repository.IVoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteService implements IVoteService{

    private final IVoteRepository voteRepo;
    private final IVoterService voterService;
    private final ICandidateService candidateService;
    private final ICandidateRepository candidateRepo;

    public VoteService(IVoteRepository voteRepo, IVoterService voterService, ICandidateService candidateService, ICandidateRepository candidateRepo) {
        this.voteRepo = voteRepo;
        this.voterService = voterService;
        this.candidateService = candidateService;
        this.candidateRepo = candidateRepo;
    }


    private List<VoteListResponseDto> buildVotesResponse(List<Vote> votes){
        List<VoteListResponseDto> votesResponse = new ArrayList<>();

        for(Vote objVote: votes){
            votesResponse.add(
                    new VoteListResponseDto(
                            objVote.getId(),
                            objVote.getVoter().getId(),
                            objVote.getCandidate().getId()
                    )
            );
        }

        return votesResponse;
    }

    @Transactional(readOnly = true)
    @Override
    public List<VoteListResponseDto> findAllVotes() {
        return buildVotesResponse(
                voteRepo.findAll()
        );
    }

    @Transactional
    @Override
    public VoteResponseDto saveVote(VoteRequestDto newVote) {
        Candidate objCandidate = candidateService.findCandidate(newVote.candidateId());
        Voter objVoter = voterService.findVoter(newVote.voterId());

        if (objVoter.isHasVoted()){
            throw new VoterAlreadyVotedException(
                    "El votante ya ha emitido su voto"
            );
        }

        Vote objVote = new Vote(
                null,
                objVoter,
                objCandidate
        );

        objCandidate.setVotes(
                objCandidate.getVotes() + 1
        );

        objVoter.setHasVoted(true);

        voteRepo.save(objVote);

        return new VoteResponseDto(
                objVote.getId(),
                new CandidateResponseDto(
                        objCandidate.getId(),
                        objCandidate.getName(),
                        objCandidate.getParty(),
                        objCandidate.getVotes()
                ),
                new VoterResponseDto(
                        objVoter.getId(),
                        objVoter.getName(),
                        objVoter.getEmail(),
                        objVoter.isHasVoted()
                )
        );
    }

    @Transactional(readOnly = true)
    @Override
    public VotingStatisticsDto getStatistics() {
        List<Candidate> candidates = candidateRepo.findAll();
        long totalVotes = voteRepo.count();

        List<CandidateStatisticsDto> candidateStatistics = new ArrayList<>();


        for(Candidate objCandidate: candidates){

            double percentage;
            // Si aún no hay votos, el porcentaje de cada candidato es 0%.
            if (totalVotes==0){
                percentage=0.0;
            }else{
                // Redondea el porcentaje a dos decimales.
                percentage = Math.round(
                        ((double) objCandidate.getVotes() / totalVotes) * 10000
                ) / 100.0;
            }

            candidateStatistics.add(
                    new CandidateStatisticsDto(
                            objCandidate.getId(),
                            objCandidate.getName(),
                            objCandidate.getParty(),
                            objCandidate.getVotes(),
                            percentage
                    )
            );
        }

        return new VotingStatisticsDto(
                candidateStatistics,
                totalVotes
        );
    }
}
