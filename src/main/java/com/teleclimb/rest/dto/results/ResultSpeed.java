package com.teleclimb.rest.dto.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultSpeed extends Result {
    private boolean isFinished;
    private boolean falseStart;
    private Duration duration;
}
