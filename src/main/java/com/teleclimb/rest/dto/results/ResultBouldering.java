package com.teleclimb.rest.dto.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultBouldering extends Result {
    private boolean bonus;
    private int bonusNumberOfTries;
    private boolean top;
    private int topNumberOfTries;
}
