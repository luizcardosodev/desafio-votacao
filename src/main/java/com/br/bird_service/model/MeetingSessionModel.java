package com.br.bird_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class MeetingSessionModel {
    private String startTime;
    private String endTime;
}
