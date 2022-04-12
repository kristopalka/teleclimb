package com.teleclimb.dto.start_results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultSpeed {
    private LocalTime time;
    private Boolean fellOff;
    private Boolean disqualifyingFalseStart;
}
