package com.teleclimb.dto.model.competition_results;


import com.teleclimb.dto.model.Competition;
import lombok.Data;

import java.util.List;

@Data
public class CompetitionResults {
    private Competition competition;

    private List<ParticipantData> participantsData;
}
