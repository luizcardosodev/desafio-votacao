package com.br.bird_service.interfaces;

import com.br.bird_service.dto.MeetingRequestDTO;
import com.br.bird_service.entity.MeetingEntity;
import com.br.bird_service.model.MeetingSessionModel;
import reactor.core.publisher.Mono;

public interface MeetingSessionService {
    Mono<MeetingSessionModel> create(MeetingRequestDTO dto);
    Mono<MeetingEntity> checkSessionExpired(String meetingId);
}
