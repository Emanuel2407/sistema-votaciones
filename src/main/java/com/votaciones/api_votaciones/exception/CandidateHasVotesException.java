package com.votaciones.api_votaciones.exception;

public class CandidateHasVotesException extends RuntimeException {

    public CandidateHasVotesException(String message) {
        super(message);
    }
}
