package com.teleclimb.unit;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.dto.model.score.ScoreBouldering;
import com.teleclimb.dto.model.score.ScoreLead;
import com.teleclimb.dto.model.score.ScoreSpeed;
import com.teleclimb.util.ScoreChecker;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertThrows;

public class ScoreCheckerTest {
    private final Gson gson = GsonConfig.get();

    @Test
    public void lead() {
        Discipline discipline = Discipline.LEAD;

        String empty = gson.toJson(new ScoreLead(null, null, LocalTime.now()));
        String wrongObject = gson.toJson(new ScoreSpeed(LocalTime.of(1, 1, 1), false, false));
        String correct = gson.toJson(new ScoreLead(17, true, LocalTime.of(0, 3, 1)));
        String incorrect = gson.toJson(new ScoreLead(-2, true, LocalTime.of(0, 3, 1)));


        assertThrows(RuntimeException.class, () -> ScoreChecker.check(empty, discipline));
        assertThrows(RuntimeException.class, () -> ScoreChecker.check(wrongObject, discipline));
        ScoreChecker.check(correct, discipline);
        assertThrows(RuntimeException.class, () -> ScoreChecker.check(incorrect, discipline));
    }

    @Test
    public void bouldering() {
        Discipline discipline = Discipline.BOULDERING;

        String empty = gson.toJson(new ScoreBouldering(null, null, null, null, null));
        String wrongObject = gson.toJson(new ScoreSpeed(LocalTime.of(1, 1, 1), false, false));
        String correct = gson.toJson(new ScoreBouldering(5, true, 4, true, 5));
        String correct2 = gson.toJson(new ScoreBouldering(14, true, 1, false, null));
        String incorrect = gson.toJson(new ScoreBouldering(14, true, 15, true, null));
        String incorrect2 = gson.toJson(new ScoreBouldering(14, false, -1, true, 1));


        assertThrows(RuntimeException.class, () -> ScoreChecker.check(empty, discipline));
        assertThrows(RuntimeException.class, () -> ScoreChecker.check(wrongObject, discipline));
        ScoreChecker.check(correct, discipline);
        ScoreChecker.check(correct2, discipline);
        assertThrows(RuntimeException.class, () -> ScoreChecker.check(incorrect, discipline));
        assertThrows(RuntimeException.class, () -> ScoreChecker.check(incorrect2, discipline));
    }

    @Test
    public void speed() {
        Discipline discipline = Discipline.SPEED;

        String empty = gson.toJson(new ScoreSpeed(null, null, null));
        String wrongObject = gson.toJson(new ScoreLead(17, true, LocalTime.of(0, 3, 1)));
        String correct = gson.toJson(new ScoreSpeed(LocalTime.of(0, 0, 12, 234000000), false, false));
        String correct2 = gson.toJson(new ScoreSpeed(null, true, false));
        String incorrect = gson.toJson(new ScoreSpeed(null, false, false));


        assertThrows(RuntimeException.class, () -> ScoreChecker.check(empty, discipline));
        assertThrows(RuntimeException.class, () -> ScoreChecker.check(wrongObject, discipline));
        ScoreChecker.check(correct, discipline);
        ScoreChecker.check(correct2, discipline);
        assertThrows(RuntimeException.class, () -> ScoreChecker.check(incorrect, discipline));
    }
}
