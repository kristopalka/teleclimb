package com.teleclimb.rest.dto.results;

import lombok.Data;

@Data
public class BoulderingResult {
    private boolean bonus;
    private int bonusNumberOfTries;
    private boolean top;
    private int topNumberOfTries;
}
