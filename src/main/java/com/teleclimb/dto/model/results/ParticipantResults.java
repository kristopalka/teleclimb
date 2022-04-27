package com.teleclimb.dto.model.results;

import com.teleclimb.dto.model.Participant;
import lombok.Data;

import java.util.List;

@Data
public class ParticipantResults {
    private Participant participant;

    private Integer place;

    private Integer topRoundNumber;

    private List<String> results;

}
