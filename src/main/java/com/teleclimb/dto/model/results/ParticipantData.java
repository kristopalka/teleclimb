package com.teleclimb.dto.model.results;

import lombok.Data;

import java.util.List;

@Data
public class ParticipantData {
    private Integer id;

    private String name;

    private String lastName;

    private String clubName;

    private String startNumber;

    //
    private Integer place;

    private Integer topRoundNumber;

    private List<String> results;

}
