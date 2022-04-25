package com.teleclimb.dto.model.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreLead {
    private Integer value;
    private Boolean plus;
    private LocalTime time;
}