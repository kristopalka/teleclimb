package com.teleclimb.dto.model.lead_competition_results;


import com.teleclimb.dto.model.Competition;
import com.teleclimb.dto.model.Round;
import lombok.Data;

import java.util.List;

@Data
public class CompetitionResults {
    private Competition competition;

    private List<Round> rounds;

    private List<ParticipantData> participantsData;
}
