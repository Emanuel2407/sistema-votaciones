package com.votaciones.api_votaciones.exception;


public class VoterAlreadyVotedException extends RuntimeException {

    public VoterAlreadyVotedException(String message) {
        super(message);
    }
}
