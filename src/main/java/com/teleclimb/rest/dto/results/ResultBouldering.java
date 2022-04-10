package com.teleclimb.rest.dto.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBouldering {
    private Boolean bonus;
    private Integer bonusNumberOfTries;
    private Boolean top;
    private Integer topNumberOfTries;
}
