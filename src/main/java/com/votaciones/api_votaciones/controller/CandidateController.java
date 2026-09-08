package com.votaciones.api_votaciones.controller;

import com.votaciones.api_votaciones.dto.CandidateRequestDto;
import com.votaciones.api_votaciones.dto.CandidateResponseDto;
import com.votaciones.api_votaciones.service.ICandidateService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final ICandidateService candidateService;
    public CandidateController(ICandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<Page<CandidateResponseDto>> findAllCandidates(
            @RequestParam(required = false) String name,
            @ParameterObject Pageable pageable
    ){
        return ResponseEntity.ok(
                candidateService.findAllCandidates(name, pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDto> findCandidateById(@PathVariable Long id){
        return ResponseEntity.ok(
                candidateService.findCandidateById(id)
        );
    }

    @PostMapping
    public ResponseEntity<CandidateResponseDto> saveCandidate(@RequestBody @Valid CandidateRequestDto newCandidate){
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                candidateService.saveCandidate(newCandidate)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id){
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }
}
