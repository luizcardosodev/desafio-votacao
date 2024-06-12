package com.br.bird_service.interfaces;

import com.br.bird_service.dto.MeetingDTO;
import com.br.bird_service.dto.MeetingRequestDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MeetingService {
    Flux<MeetingDTO> getAllMeetings();
    Mono<MeetingDTO> getMeeting(String meetingId);
    Mono<MeetingDTO> createMeeting(MeetingRequestDTO dto);

}
