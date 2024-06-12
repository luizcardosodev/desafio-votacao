package com.br.bird_service.service;

import com.br.bird_service.dto.MeetingRequestDTO;
import com.br.bird_service.entity.MeetingEntity;
import com.br.bird_service.exception.BusinessServiceException;
import com.br.bird_service.interfaces.MeetingSessionService;
import com.br.bird_service.interfaces.UserService;
import com.br.bird_service.mapper.MeetingMapper;
import com.br.bird_service.model.MeetingSessionModel;
import com.br.bird_service.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.br.bird_service.exception.enumeration.ErrorApiCode.*;
import static com.br.bird_service.helper.MessageHelper.getMessage;
import static com.br.bird_service.utils.DateUtils.convertToISOString;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@Service
@AllArgsConstructor
public class MeetingSessionServiceImpl implements MeetingSessionService {

    private final MeetingRepository repository;
    private final UserService userService;

    @Override
    public Mono<MeetingSessionModel> create(MeetingRequestDTO dto) {
        return userService.findById(dto.getAssociatedId())
                .flatMap(it -> Mono.just(new MeetingSessionModel()
                        .withStartTime(convertToISOString(LocalDateTime.now()))
                        .withEndTime(LocalDateTime.now()
                                .plus(Optional.ofNullable(dto.getDurationSeconds())
                                        .map(Duration::ofSeconds)
                                        .orElse(Duration.ofMinutes(1)))
                                .format(DateTimeFormatter.ISO_DATE_TIME))))
                .doOnSuccess(it -> log.info("Meeting session created"))
                .doOnError(error -> log.error("Error when creating session for meeting: {}", error.getMessage()));
    }

    @Override
    public Mono<MeetingEntity> checkSessionExpired(String meetingId) {
        return repository.findMeetingByIdAndEndTimeAfter(meetingId, convertToISOString(LocalDateTime.now()))
                .switchIfEmpty(Mono.error(new BusinessServiceException(BAD_REQUEST,
                        getMessage(MEETING_SESSION_EXPIRED, meetingId))))
                .doOnError(s -> log.info("Meeting session expired with id: {}", meetingId));
    }
}
