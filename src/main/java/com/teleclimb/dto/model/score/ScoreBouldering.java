package com.teleclimb.dto.model.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreBouldering {
    private Integer tries;
    private Boolean bonus;
    private Integer triesToBonus;
    private Boolean top;
    private Integer triesToTop;
}
