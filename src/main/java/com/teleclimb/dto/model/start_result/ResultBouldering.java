package com.teleclimb.dto.model.start_result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBouldering {
    private Integer tries;
    private Boolean bonus;
    private Integer triesToBonus;
    private Boolean top;
    private Integer triesToTop;
}
