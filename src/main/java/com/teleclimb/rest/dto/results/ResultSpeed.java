package com.teleclimb.rest.dto.results;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ResultSpeed {
    private boolean isFinished;
    private boolean falseStart;
    private LocalTime time;
}
