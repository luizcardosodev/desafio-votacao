package com.br.bird_service.entity;

import com.br.bird_service.model.MeetingSessionModel;
import com.br.bird_service.model.MeetingVoteModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "meetings")
public class MeetingEntity {
    @Id
    private String id;
    private String title;
    private String description;
    private Long totalVotes;
    private Long totalVotedYes;
    private Long totalVotedNo;
    private MeetingSessionModel session;
    private List<MeetingVoteModel> voting;
    private String date;
    private String createdBy;

    public Long countTotalVotes() {
        if (voting == null || voting.isEmpty()) {
            return 0L;
        }

        long totalVotes = 0;

        for (MeetingVoteModel vote : voting) {
            totalVotes++;
        }

        return totalVotes;
    }

    public Long countTotalVotesFalse() {
        if (voting == null || voting.isEmpty()) {
            return 0L;
        }

        long totalVotesFalse = 0;

        for (MeetingVoteModel vote : voting) {
            if (!vote.getVote()) {
                totalVotesFalse++;
            }
        }

        return totalVotesFalse;
    }

    public Long countTotalVotesTrue() {
        if (voting == null || voting.isEmpty()) {
            return 0L;
        }

        long totalVotesTrue = 0;

        for (MeetingVoteModel vote : voting) {
            if (vote.getVote()) {
                totalVotesTrue++;
            }
        }

        return totalVotesTrue;
    }
}
