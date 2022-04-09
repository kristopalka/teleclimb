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
public class ResultLead extends Result {
    private Integer value;
    private Boolean plus;
    private Duration duration;
}
