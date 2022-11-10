package com.teleclimb.dto.model;

import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.dto.enums.RoundState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Start {
    private Integer id;

    private Integer refereePositionId;

    private String refereePositionHash;

    private RoundState roundState;

    private Integer roundSequenceNumber;

    private Integer positionSequenceNumber;

    private Integer participantId;

    private String score;

    private Discipline discipline;
}
