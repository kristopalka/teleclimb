package com.teleclimb.dto.model.competition_result;

import lombok.Data;

import java.util.List;

@Data
public class ParticipantDataDto {
    private Integer id;

    private Integer place;

    private Integer topRoundNumber;

    // participant data
    private String name;

    private String lastName;

    private String clubName;

    private String startNumber;

    private List<String> results;

}
