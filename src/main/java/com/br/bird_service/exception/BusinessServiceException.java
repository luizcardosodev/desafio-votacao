package com.br.bird_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BusinessServiceException extends ResponseStatusException {
    public BusinessServiceException(HttpStatus status) {
        super(status);
    }

    public BusinessServiceException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public BusinessServiceException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
