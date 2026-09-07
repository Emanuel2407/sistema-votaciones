package com.votaciones.api_votaciones.repository;

import com.votaciones.api_votaciones.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoteRepository extends JpaRepository<Vote, Long> {
}
