package com.teleclimb.rest.dto.results;

import lombok.Data;

import java.time.Duration;

@Data
public class SpeedResult {
    private boolean isFinished;
    private Duration duration;
}
