package com.votaciones.api_votaciones.exception.dto;

import java.time.LocalDateTime;

/**
 * Da estructura al body de las
 * respuestas de error
 */
public record ErrorResponseBody(
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {
}
