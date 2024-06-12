package com.br.bird_service.mapper;

import com.br.bird_service.dto.MeetingDTO;
import com.br.bird_service.dto.MeetingVoteRequestDTO;
import com.br.bird_service.entity.MeetingEntity;
import com.br.bird_service.model.MeetingVoteModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.br.bird_service.utils.DateUtils.convertToISOString;

@Component
@AllArgsConstructor
public class MeetingMapper {

    private final ModelMapper modelMapper;
    public MeetingDTO map(MeetingEntity source){
        return modelMapper.map(source, MeetingDTO.class);
    }

    public MeetingDTO map(MeetingEntity source, String associatedId) {
        return MeetingDTO.builder()
                .id(source.getId())
                .title(source.getTitle())
                .session(source.getSession())
                .voting(source.getVoting())
                .createdBy(associatedId)
                .description(source.getDescription())
                .build();
    }

    public MeetingVoteModel map(MeetingVoteRequestDTO source){
        return MeetingVoteModel.builder()
                .date(convertToISOString(LocalDateTime.now()))
                .associatedId(source.getAssociatedId())
                .associatedName(source.getAssociatedName())
                .vote(source.getVote())
                .reason(source.getReason())
                .build();
    }
}
