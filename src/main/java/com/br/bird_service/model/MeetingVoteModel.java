package com.br.bird_service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeetingVoteModel {
    private String associatedId;
    private String associatedName;
    private String date;
    private String reason;
    private Boolean vote;
}
