package com.teleclimb.rest.dto.results;

import lombok.Data;

@Data
public class ResultBouldering {
    private boolean bonus;
    private int bonusNumberOfTries;
    private boolean top;
    private int topNumberOfTries;
}
