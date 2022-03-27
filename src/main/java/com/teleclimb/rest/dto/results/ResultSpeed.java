package com.teleclimb.rest.dto.results;

import lombok.Data;

import java.time.Duration;

@Data
public class ResultSpeed {
    private boolean isFinished;
    private Duration duration;
}
