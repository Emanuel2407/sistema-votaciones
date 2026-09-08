package com.votaciones.api_votaciones.service;

import com.votaciones.api_votaciones.dto.VoterRequestDto;
import com.votaciones.api_votaciones.dto.VoterResponseDto;
import com.votaciones.api_votaciones.exception.EmailAlreadyExistsException;
import com.votaciones.api_votaciones.exception.VoterAlreadyVotedException;
import com.votaciones.api_votaciones.exception.VoterNotFoundException;
import com.votaciones.api_votaciones.model.Voter;
import com.votaciones.api_votaciones.repository.IVoterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoterService implements IVoterService{

    private final IVoterRepository voterRepo;
    public VoterService(IVoterRepository voterRepo) {
        this.voterRepo = voterRepo;
    }

    /**
     * Transforma un votante en objeto DTO de respuesta
     */
    private VoterResponseDto buildVoterResponse(Voter objVoter){
        return new VoterResponseDto(
                objVoter.getId(),
                objVoter.getName(),
                objVoter.getEmail(),
                objVoter.isHasVoted()
        );
    }

    /**
     * Convierte los votantes de la página en sus respectivos DTO de respuesta
     */
    private Page<VoterResponseDto> buildVotersResponse(Page<Voter> voters) {
        return voters.map(this::buildVoterResponse);
    }

    /**
     * Busca votante por su ID o lanza excepción si no
     * existe
     */
    public Voter findVoter(Long id){
        return voterRepo.findById(id)
                .orElseThrow(
                        () -> new VoterNotFoundException("No existe votante con id: " + id)
                );
    }

    @Transactional(readOnly = true)
    @Override
    public Page<VoterResponseDto> findAllVoters(Pageable pageable) {
        return buildVotersResponse(
                voterRepo.findAll(pageable)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public VoterResponseDto findVoterById(Long voterId) {
        return buildVoterResponse(
                findVoter(voterId)
        );
    }

    @Transactional
    @Override
    public VoterResponseDto saveVoter(VoterRequestDto newVoter) {

        if(voterRepo.existsByEmail(newVoter.email())){
            throw new EmailAlreadyExistsException(
                    "Ya existe un votante registrado con el email: " + newVoter.email()
            );
        }

        Voter objVoter = new Voter(
                null, //Auto-incrementable
                newVoter.name(),
                newVoter.email(),
                false
        );

        voterRepo.save(objVoter);

        return buildVoterResponse(objVoter);
    }

    @Transactional
    @Override
    public void deleteVoter(Long id) {
        Voter objVoter = findVoter(id);

        if(objVoter.isHasVoted()){
            throw new VoterAlreadyVotedException(
                    "Para mantener la trazabilidad, no se puede eliminar un votante que ya emitió su voto"
            );
        }

        voterRepo.delete(objVoter);
    }
}
