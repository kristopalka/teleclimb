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
public class StartWithParticipant {
    private Integer id;

    private Integer refereePositionId;

    private String refereePositionHash;

    private RoundState roundState;

    private Integer roundSequenceNumber;

    private Integer positionSequenceNumber;

    private Participant participant;

    private String score;

    private Discipline discipline;
}
