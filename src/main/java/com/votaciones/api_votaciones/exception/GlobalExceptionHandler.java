package com.votaciones.api_votaciones.exception;

import com.votaciones.api_votaciones.exception.dto.ErrorResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponseBody buildErrorResponse(HttpStatus status, String message){
        return new ErrorResponseBody(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
        );
    }

    @ExceptionHandler(VoterNotFoundException.class)
    public ResponseEntity<ErrorResponseBody> voterNotFound(VoterNotFoundException ex){
        return ResponseEntity.status(
                HttpStatus.NOT_FOUND
        ).body(
                buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage())
        );
    }

    @ExceptionHandler(VoterAlreadyVotedException.class)
    public ResponseEntity<ErrorResponseBody> voterAlreadyVoted(VoterAlreadyVotedException ex){
        return ResponseEntity.status(
                HttpStatus.CONFLICT
        ).body(
                buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage())
        );
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseBody> emailAlreadyExists(EmailAlreadyExistsException ex){
        return ResponseEntity.status(
                HttpStatus.CONFLICT
        ).body(
                buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage())
        );
    }

}
