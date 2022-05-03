package com.teleclimb.scores;

import com.teleclimb.dto.model.score.ScoreLead;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class ScoreLeadUnitTest {
    @Test
    public void unitTest() {
        List<ScoreLead> scores = new ArrayList<>();
        scores.add(new ScoreLead(12, true, LocalTime.of(0, 2, 12, 0)));
        scores.add(new ScoreLead(12, false, LocalTime.of(0, 2, 12, 0)));
        scores.add(new ScoreLead(13, false, LocalTime.of(0, 2, 12, 0)));
        scores.add(new ScoreLead(15, false, LocalTime.of(0, 2, 12, 0)));
        scores.add(null);

        scores.sort(ScoreLead::compareNullSafe);

        Assertions.assertTrue(scores.get(0).getValue() > scores.get(1).getValue());
        Assertions.assertTrue(scores.get(2).getPlus() && !scores.get(3).getPlus());

        System.out.println(scores);

    }
}
