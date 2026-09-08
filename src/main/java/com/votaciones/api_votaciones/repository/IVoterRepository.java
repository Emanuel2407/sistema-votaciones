package com.votaciones.api_votaciones.repository;

import com.votaciones.api_votaciones.model.Voter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoterRepository extends JpaRepository<Voter, Long> {

    boolean existsByEmail(String email);

    Page<Voter> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
}
