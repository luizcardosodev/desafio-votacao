package com.br.bird_service.service;

import com.br.bird_service.dto.MeetingDTO;
import com.br.bird_service.dto.MeetingRequestDTO;
import com.br.bird_service.entity.MeetingEntity;
import com.br.bird_service.exception.BusinessServiceException;
import com.br.bird_service.interfaces.MeetingService;
import com.br.bird_service.interfaces.MeetingSessionService;
import com.br.bird_service.mapper.MeetingMapper;
import com.br.bird_service.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.br.bird_service.exception.enumeration.ErrorApiCode.MEETING_NOT_FOUND;
import static com.br.bird_service.helper.MessageHelper.getMessage;
import static com.br.bird_service.utils.DateUtils.convertToISOString;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository repository;
    private final MeetingSessionService sessionService;
    private final MeetingMapper meetingMapper;

    @Override
    public Flux<MeetingDTO> getAllMeetings() {
        return repository.findAll()
                .map(meetingMapper::map)
                .doOnError(error -> log.error("Error getting registered meetings: {}", error.getMessage()))
                .doOnComplete(() -> log.info("Meeting consultation completed"));
    }

    @Override
    public Mono<MeetingDTO> getMeeting(String meetingId) {
        return repository.findById(meetingId)
                .switchIfEmpty(Mono.error(new BusinessServiceException(NOT_FOUND,
                        getMessage(MEETING_NOT_FOUND, meetingId))))
                .doOnError(error -> log.error("Error getting registered meeting: {}", error.getMessage()))
                .doOnSuccess(it -> log.info("Meeting successfully obtained with id: {}", meetingId))
                .map(meetingMapper::map);
    }

    @Override
    public Mono<MeetingDTO> createMeeting(MeetingRequestDTO dto) {
        return sessionService.create(dto)
                .flatMap(session -> repository.save(new MeetingEntity()
                                .withVoting(new ArrayList<>())
                                .withDescription(dto.getDescription())
                                .withTitle(dto.getTitle())
                                .withSession(session)
                                .withCreatedBy(dto.getAssociatedId())
                                .withDate(convertToISOString(LocalDateTime.now())))
                        .map(it -> meetingMapper.map(it, dto.getAssociatedId())))
                .doOnSuccess(it -> log.info("Meeting created successfully with id: {}", it.getId()))
                .doOnError(error -> log.info("Error when create meeting: {}", error.getMessage()));
    }
}
