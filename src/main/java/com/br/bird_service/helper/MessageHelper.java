package com.br.bird_service.helper;

import com.br.bird_service.exception.interfaces.ErrorCode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

@RequiredArgsConstructor
public class MessageHelper {

    private final MessageSource messageSource;
    private static MessageSourceAccessor accessor;

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor( messageSource, Locale.getDefault() );
    }

    public static String getMessage(ErrorCode code, Object ... args) {
        return accessor.getMessage(code.getMessageKey(), args);
    }

    public static String getMessage(MessageCode code, Object ... args) {
        return accessor.getMessage(code.getMessageKey(), args);
    }
}