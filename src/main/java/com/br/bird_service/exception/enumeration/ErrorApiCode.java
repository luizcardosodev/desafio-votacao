package com.br.bird_service.exception.enumeration;

import com.br.bird_service.exception.interfaces.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorApiCode implements ErrorCode {

    MEETING_SESSION_EXPIRED("messages.error.meeting-session-expired"),
    MEETING_NOT_FOUND("messages.error.meeting.not-found"),
    USER_HAS_ALREADY_VOTED("messages.error.user-has-already-voted"),
    USER_EXIST("messages.error.user-exist"),
    USER_NOT_FOUND("messages.error.user-not-found");

    private final String messageKey;

}
