package com.br.bird_service.repository;

import com.br.bird_service.entity.MeetingEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MeetingRepository extends ReactiveMongoRepository<MeetingEntity, String> {
    @Query("{ '_id' : ?1, 'voting.associatedId' : ?0 }")
    Mono<MeetingEntity> existsVoteByAssociatedId(String associatedId, String meetingId);

    @Query(value = "{ '_id' : ?0, 'session.endTime' : { $gt : ?1 } }")
    Mono<MeetingEntity> findMeetingByIdAndEndTimeAfter(String id, String currentTime);
}
