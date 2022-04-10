package com.teleclimb.util;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.enums.Discipline;
import com.teleclimb.rest.dto.results.ResultBouldering;
import com.teleclimb.rest.dto.results.ResultLead;
import com.teleclimb.rest.dto.results.ResultSpeed;

public class ResultChecker {
    private static final Gson gson = GsonConfig.gson();

    public static void check(String resultJson, Discipline discipline) {
        switch (discipline) {
            case LEAD -> checkLead(resultJson);
            case BOULDERING -> checkBouldering(resultJson);
            case SPEED -> checkSpeed(resultJson);
        }
    }

    private static void checkLead(String resultJson) {
        ResultLead result = gson.fromJson(resultJson, ResultLead.class);

        if (result.getPlus() == null || result.getTime() == null || result.getValue() == null)
            throw new RuntimeException("none of fields can be null");
    }

    private static void checkBouldering(String resultJson) {
        ResultBouldering result = gson.fromJson(resultJson, ResultBouldering.class);

        if (result.getBonusNumberOfTries() == null || result.getTopNumberOfTries() == null || result.getBonus() == null || result.getTop() == null)
            throw new RuntimeException("none of fields can be null");
    }

    private static void checkSpeed(String resultJson) {
        ResultSpeed result = gson.fromJson(resultJson, ResultSpeed.class);

        if (result.getTime() == null || result.getFalseStart() == null || result.getIsFinished() == null)
            throw new RuntimeException("none of fields can be null");
    }
}
