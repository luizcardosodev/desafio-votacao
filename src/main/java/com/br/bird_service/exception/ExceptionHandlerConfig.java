package com.br.bird_service.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ControllerAdvice
public class ExceptionHandlerConfig {
    private static final String DEFAULT_KEY_STATUS = "status";
    private static final String DEFAULT_KEY_ERROR = "error";
    private static final String DEFAULT_KEY_MESSAGE = "message";
    private static final String DEFAULT_KEY_PATH = "path";

    public static final String KEY_STATUS = "status";
    public static final String KEY_ERROR = "error";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TIMESTAMP = "timestamp";

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
                Map<String ,Object> defaultMap = super.getErrorAttributes( request ,options );
                Map<String ,Object> errorAttributes = new LinkedHashMap<>();
                errorAttributes.put( KEY_TIMESTAMP, LocalDateTime.now());
                errorAttributes.put( DEFAULT_KEY_PATH, defaultMap.get( DEFAULT_KEY_PATH ) );
                errorAttributes.put( KEY_STATUS, defaultMap.get( DEFAULT_KEY_STATUS ) );
                errorAttributes.put( KEY_ERROR, defaultMap.get( DEFAULT_KEY_ERROR ) );
                errorAttributes.put( KEY_MESSAGE ,defaultMap.get( DEFAULT_KEY_MESSAGE ) );
                return errorAttributes;
            }
        };
    }

    private record ValidationError(String field, String message) {}

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<List<ValidationError>> handleValidationExceptions(WebExchangeBindException ex) {
        List<ValidationError> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new ValidationError(fieldName, errorMessage));
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
