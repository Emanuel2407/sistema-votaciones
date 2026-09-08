package com.votaciones.api_votaciones.controller;

import com.votaciones.api_votaciones.dto.VoterRequestDto;
import com.votaciones.api_votaciones.dto.VoterResponseDto;
import com.votaciones.api_votaciones.service.IVoterService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voters")
public class VoterController {

    private final IVoterService voterService;
    public VoterController(IVoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping
    public ResponseEntity<Page<VoterResponseDto>> findAllVoters(Pageable pageable){
        return ResponseEntity.ok(
                voterService.findAllVoters(pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoterResponseDto> findVoterById(@PathVariable Long id){
        return ResponseEntity.ok(
                voterService.findVoterById(id)
        );
    }

    @PostMapping
    public ResponseEntity<VoterResponseDto> saveVoter(@RequestBody @Valid VoterRequestDto newVoter){
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                voterService.saveVoter(newVoter)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoter(@PathVariable Long id){
        voterService.deleteVoter(id);
        return ResponseEntity.noContent().build();
    }
}
