package com.teleclimb.rest.dto.custom;

import com.teleclimb.rest.dto.Competition;
import com.teleclimb.rest.dto.Participant;
import lombok.Data;

import java.util.List;

@Data
public class CompetitionWithParticipantsList {
    private Competition competition;

    private List<Participant> participants;
}
