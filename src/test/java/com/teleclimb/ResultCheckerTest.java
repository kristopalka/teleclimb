package com.teleclimb;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.enums.Discipline;
import com.teleclimb.rest.dto.results.ResultLead;
import com.teleclimb.rest.dto.results.ResultSpeed;
import com.teleclimb.util.ResultChecker;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertThrows;

public class ResultCheckerTest {
    private final Gson gson = GsonConfig.gson();

    @Test
    public void lead() {
        String empty = gson.toJson(new ResultLead(null, null, LocalTime.now()));
        String wrongObject = gson.toJson(new ResultSpeed(true, false, LocalTime.of(1, 1, 1)));
        String correct = gson.toJson(new ResultLead(17, true, LocalTime.of(0, 3, 1)));


        assertThrows(RuntimeException.class, () -> ResultChecker.check(empty, Discipline.LEAD));
        assertThrows(RuntimeException.class, () -> ResultChecker.check(wrongObject, Discipline.LEAD));
        ResultChecker.check(correct, Discipline.LEAD);
    }
}
