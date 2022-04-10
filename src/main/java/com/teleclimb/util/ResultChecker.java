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

        if (result.getTries() == null) throw new RuntimeException("tries can not be null");
        if (result.getBonus() == null) throw new RuntimeException("bonus can not be null");
        if (result.getTop() == null) throw new RuntimeException("top can not be null");
        if (result.getTop()) {
            if (result.getTriesToTop() == null) throw new RuntimeException("if top, tries to top can not be null");
            if (result.getTriesToTop() <= 0) throw new RuntimeException("if top, tries to top can not be <= 0");
            if (result.getTriesToTop() > result.getTries())
                throw new RuntimeException("if top, tries to top can not be > than all tries");
        }
        if (result.getBonus()) {
            if (result.getTriesToBonus() == null)
                throw new RuntimeException("if bonus, tries to bonus can not be null");
            if (result.getTriesToBonus() <= 0) throw new RuntimeException("if bonus, tries to bonus can not be <= 0");
            if (result.getTriesToBonus() > result.getTries())
                throw new RuntimeException("if bonus, tries to bonus can not be > than all tries");
        }
        if (result.getTop() && result.getBonus()) {
            if (result.getTriesToBonus() > result.getTriesToTop())
                throw new RuntimeException("if bonus and top, tries to bonus can not be > than tries to top");
        }
        if (!result.getBonus() && result.getTop()) throw new RuntimeException("can not be top true, and bonus false");

    }

    private static void checkSpeed(String resultJson) {
        ResultSpeed result = gson.fromJson(resultJson, ResultSpeed.class);

        if (result.getTime() == null || result.getFalseStart() == null || result.getIsFinished() == null)
            throw new RuntimeException("none of fields can be null");
    }
}
