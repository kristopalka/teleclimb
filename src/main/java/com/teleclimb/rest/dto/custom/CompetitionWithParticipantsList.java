package com.teleclimb.rest.dto.custom;

import com.teleclimb.rest.dto.CompetitionDto;
import com.teleclimb.rest.dto.ParticipantRawDto;
import lombok.Data;

import java.util.List;

@Data
public class CompetitionWithParticipantsList {
    private CompetitionDto competition;

    private List<ParticipantRawDto> participants;
}
