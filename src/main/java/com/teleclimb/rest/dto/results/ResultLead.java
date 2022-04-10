package com.teleclimb.rest.dto.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultLead {
    private Integer value;
    private Boolean plus;
    private LocalTime time;
}
