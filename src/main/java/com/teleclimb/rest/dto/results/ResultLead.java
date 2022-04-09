package com.teleclimb.rest.dto.results;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ResultLead {
    private Integer value;
    private Boolean plus;
    private LocalTime time;
}
