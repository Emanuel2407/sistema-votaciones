package com.votaciones.api_votaciones.controller;

import com.votaciones.api_votaciones.dto.VoteListResponseDto;
import com.votaciones.api_votaciones.dto.VoteRequestDto;
import com.votaciones.api_votaciones.dto.VoteResponseDto;
import com.votaciones.api_votaciones.dto.VotingStatisticsDto;
import com.votaciones.api_votaciones.service.IVoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private final IVoteService voteService;
    public VoteController(IVoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public ResponseEntity<List<VoteListResponseDto>> findAllVotes(){
        return ResponseEntity.ok(
                voteService.findAllVotes()
        );
    }

    @PostMapping
    public ResponseEntity<VoteResponseDto> saveVote(@RequestBody @Valid VoteRequestDto newVote){
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                voteService.saveVote(newVote)
        );
    }

    @GetMapping("/statistics")
    public ResponseEntity<VotingStatisticsDto> getStatistics(){
        return ResponseEntity.ok(
                voteService.getStatistics()
        );
    }
}
