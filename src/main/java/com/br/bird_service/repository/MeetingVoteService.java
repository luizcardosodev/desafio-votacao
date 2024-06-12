package com.br.bird_service.repository;

import com.br.bird_service.dto.MeetingDTO;
import com.br.bird_service.dto.MeetingVoteRequestDTO;
import reactor.core.publisher.Mono;

public interface MeetingVoteService {
    Mono<MeetingDTO> vote(MeetingVoteRequestDTO dto);
    Mono<Boolean> checkVoteExist(String associatedId, String meetingId);
}
