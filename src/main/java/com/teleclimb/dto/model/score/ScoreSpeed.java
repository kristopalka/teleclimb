package com.teleclimb.dto.model.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreSpeed {
    private LocalTime time;
    private Boolean fellOff;
    private Boolean disqualifyingFalseStart;
}
