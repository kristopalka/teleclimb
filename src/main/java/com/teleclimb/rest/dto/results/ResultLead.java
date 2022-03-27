package com.teleclimb.rest.dto.results;

import lombok.Data;

import java.time.Duration;

@Data
public class ResultLead {
    private Integer value;
    private Boolean plus;
    private Duration duration;
}
