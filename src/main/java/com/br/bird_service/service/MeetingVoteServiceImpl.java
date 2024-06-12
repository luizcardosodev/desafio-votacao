package com.br.bird_service.service;

import com.br.bird_service.dto.MeetingDTO;
import com.br.bird_service.dto.MeetingVoteRequestDTO;
import com.br.bird_service.entity.MeetingEntity;
import com.br.bird_service.exception.BusinessServiceException;
import com.br.bird_service.interfaces.MeetingService;
import com.br.bird_service.interfaces.MeetingSessionService;
import com.br.bird_service.interfaces.UserService;
import com.br.bird_service.mapper.MeetingMapper;
import com.br.bird_service.repository.MeetingRepository;
import com.br.bird_service.repository.MeetingVoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

import static com.br.bird_service.exception.enumeration.ErrorApiCode.MEETING_SESSION_EXPIRED;
import static com.br.bird_service.exception.enumeration.ErrorApiCode.USER_HAS_ALREADY_VOTED;
import static com.br.bird_service.helper.MessageHelper.getMessage;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@Slf4j
@Service
@AllArgsConstructor
public class MeetingVoteServiceImpl implements MeetingVoteService {

    private final MeetingSessionService meetingSessionService;
    private final MeetingService meetingService;
    private final UserService userService;
    private final MeetingRepository repository;
    private final MeetingMapper mapper;

    @Override
    public Mono<MeetingDTO> vote(MeetingVoteRequestDTO dto) {
        return meetingService.getMeeting(dto.getMeetingId())
                .filterWhen(s -> checkVoteExist(dto.getAssociatedId(), dto.getMeetingId()))
                .flatMap(it -> userService.findById(dto.getAssociatedId())
                        .zipWith(Mono.just(dto), (user, voteRequest) -> {
                            voteRequest.setAssociatedName(user.getName());
                            return voteRequest;
                        }))
                .flatMap(it -> meetingSessionService.checkSessionExpired(dto.getMeetingId()))
                .map(it -> it.withVoting(Optional.ofNullable(it.getVoting()).orElse(new ArrayList<>())))
                .zipWhen(meeting -> Mono.just(dto)
                        .map(voteRequest -> {
                            meeting.getVoting().add(mapper.map(voteRequest));
                            return meeting.getVoting();
                        }))
                .map(tuple -> tuple.getT1().withVoting(tuple.getT2()))
                .map(it -> it.withTotalVotes(it.countTotalVotes()))
                .map(it -> it.withTotalVotedYes(it.countTotalVotesTrue()))
                .map(it -> it.withTotalVotedNo(it.countTotalVotesFalse()))
                .flatMap(repository::save)
                .map(mapper::map);
    }

    @Override
    public Mono<Boolean> checkVoteExist(String associatedId, String meetingId) {
        return repository.existsVoteByAssociatedId(associatedId, meetingId)
                .flatMap(it -> Mono.error(new BusinessServiceException(BAD_REQUEST,
                        getMessage(USER_HAS_ALREADY_VOTED, meetingId))))
                .defaultIfEmpty(true)
                .hasElement();
    }
}
