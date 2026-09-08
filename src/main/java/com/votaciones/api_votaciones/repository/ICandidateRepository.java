package com.votaciones.api_votaciones.repository;

import com.votaciones.api_votaciones.model.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICandidateRepository extends JpaRepository<Candidate, Long> {

    Page<Candidate> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
}
