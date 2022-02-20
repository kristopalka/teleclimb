package com.teleclimb.rest.dto.results;

import lombok.Data;

import java.time.Duration;

@Data
public class LeadResult {
    private Integer value;
    private Boolean plus;
    private Duration duration;
}
