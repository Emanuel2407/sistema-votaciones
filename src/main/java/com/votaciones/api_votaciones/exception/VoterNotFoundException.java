package com.votaciones.api_votaciones.exception;

public class VoterNotFoundException extends RuntimeException {

    public VoterNotFoundException(String message) {
        super(message);
    }
}
