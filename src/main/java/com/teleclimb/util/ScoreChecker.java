package com.teleclimb.util;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.dto.model.score.ScoreBouldering;
import com.teleclimb.dto.model.score.ScoreLead;
import com.teleclimb.dto.model.score.ScoreSpeed;

public class ScoreChecker {
    private static final Gson gson = GsonConfig.gson();

    public static void check(String scoreJson, Discipline discipline) {
        switch (discipline) {
            case LEAD -> checkLead(scoreJson);
            case BOULDERING -> checkBouldering(scoreJson);
            case SPEED -> checkSpeed(scoreJson);
        }
    }

    private static void checkLead(String scoreJson) {
        ScoreLead score = gson.fromJson(scoreJson, ScoreLead.class);

        if (score.getPlus() == null) throw new RuntimeException("plus can not be null");
        if (score.getValue() == null) throw new RuntimeException("value can not be null");
        if (score.getTime() == null) throw new RuntimeException("time can not be null");

        if (score.getValue() < 0) throw new RuntimeException("value can not be negative");
    }

    private static void checkBouldering(String scoreJson) {
        ScoreBouldering score = gson.fromJson(scoreJson, ScoreBouldering.class);

        if (score.getTries() < 0) throw new RuntimeException("tries can not be < 0");

        if (score.getTries() == null) throw new RuntimeException("tries can not be null");
        if (score.getBonus() == null) throw new RuntimeException("bonus can not be null");
        if (score.getTop() == null) throw new RuntimeException("top can not be null");
        if (score.getTop()) {
            if (score.getTriesToTop() == null) throw new RuntimeException("if top, tries to top can not be null");
            if (score.getTriesToTop() <= 0) throw new RuntimeException("if top, tries to top can not be <= 0");
            if (score.getTriesToTop() > score.getTries())
                throw new RuntimeException("if top, tries to top can not be > than all tries");
        }
        if (score.getBonus()) {
            if (score.getTriesToBonus() == null)
                throw new RuntimeException("if bonus, tries to bonus can not be null");
            if (score.getTriesToBonus() <= 0) throw new RuntimeException("if bonus, tries to bonus can not be <= 0");
            if (score.getTriesToBonus() > score.getTries())
                throw new RuntimeException("if bonus, tries to bonus can not be > than all tries");
        }
        if (score.getTop() && score.getBonus()) {
            if (score.getTriesToBonus() > score.getTriesToTop())
                throw new RuntimeException("if bonus and top, tries to bonus can not be > than tries to top");
        }
        if (!score.getBonus() && score.getTop()) throw new RuntimeException("can not be top true, and bonus false");

    }

    private static void checkSpeed(String scoreJson) {
        ScoreSpeed score = gson.fromJson(scoreJson, ScoreSpeed.class);

        if (score.getTime() == null && !(score.getFellOff() || score.getDisqualifyingFalseStart()))
            throw new RuntimeException("time can not be null if no fell off and no false start");
        if (score.getFellOff() == null) throw new RuntimeException("fell off can not be null");
        if (score.getDisqualifyingFalseStart() == null) throw new RuntimeException("false start can not be null");

        if (score.getFellOff() && score.getDisqualifyingFalseStart())
            throw new RuntimeException("can not be both: false start and fell off");
    }
}
