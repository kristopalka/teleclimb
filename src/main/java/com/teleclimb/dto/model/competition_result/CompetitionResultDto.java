package com.teleclimb.dto.model.competition_result;


import com.teleclimb.dto.model.Competition;
import com.teleclimb.dto.model.Round;
import lombok.Data;

import java.util.List;

@Data
public class CompetitionResultDto {
    private Competition competition;

    private List<Round> rounds;

    private List<ParticipantDataDto> participantsData;
}
