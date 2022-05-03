package com.teleclimb.dto.model.results;


import com.teleclimb.dto.model.Competition;
import com.teleclimb.dto.model.ParticipantWithMeta;
import com.teleclimb.dto.model.Round;
import lombok.Data;

import java.util.List;

@Data
public class CompetitionResults {
    private Competition competition;

    private List<Round> rounds;

    private List<ParticipantWithMeta> participantsData;
}
