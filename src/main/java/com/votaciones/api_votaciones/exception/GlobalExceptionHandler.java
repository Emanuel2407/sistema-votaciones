package com.votaciones.api_votaciones.exception;

import com.votaciones.api_votaciones.exception.dto.ErrorResponseBody;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

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

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<ErrorResponseBody> candidateNotFound(CandidateNotFoundException ex){
        return ResponseEntity.status(
                HttpStatus.NOT_FOUND
        ).body(
                buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage())
        );
    }

    @ExceptionHandler(CandidateHasVotesException.class)
    public ResponseEntity<ErrorResponseBody> candidateHasVotes(CandidateHasVotesException ex){
        return ResponseEntity.status(
                HttpStatus.CONFLICT
        ).body(
                buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage())
        );
    }

    /**
     * Maneja los errores de validación de los DTOs y devuelve
     * los mensajes definidos en las anotaciones de validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> methodArgumentNotValid(
            MethodArgumentNotValidException ex
    ) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        buildErrorResponse(
                                HttpStatus.BAD_REQUEST,
                                message
                        )
                );
    }

}
