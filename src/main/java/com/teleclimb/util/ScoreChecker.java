package com.teleclimb.util;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.controller.responses.error.exception.BadRequestException;
import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.dto.model.score.ScoreBouldering;
import com.teleclimb.dto.model.score.ScoreLead;
import com.teleclimb.dto.model.score.ScoreSpeed;

public class ScoreChecker {
    private static final Gson gson = GsonConfig.get();

    public static void check(String scoreJson, Discipline discipline) {
        try {
            chooseDiscipline(scoreJson, discipline);
        } catch (RuntimeException e) {
            throw new BadRequestException("Given score is wrong: " + e.getMessage());
        }
    }

    private static void chooseDiscipline(String scoreJson, Discipline discipline) {
        switch (discipline) {
            case LEAD -> checkLead(scoreJson);
            case BOULDERING -> checkBouldering(scoreJson);
            case SPEED -> checkSpeed(scoreJson);
        }
    }


    private static void checkLead(String scoreJson) {
        ScoreLead score = gson.fromJson(scoreJson, ScoreLead.class);

        if (score.getPlus() == null) throw new BadRequestException("plus can not be null");
        if (score.getValue() == null) throw new BadRequestException("value can not be null");
        if (score.getTime() == null) throw new BadRequestException("time can not be null");

        if (score.getValue() < 0) throw new BadRequestException("value can not be negative");
    }

    private static void checkBouldering(String scoreJson) {
        ScoreBouldering score = gson.fromJson(scoreJson, ScoreBouldering.class);

        if (score.getTries() < 0) throw new BadRequestException("tries can not be < 0");

        if (score.getTries() == null) throw new BadRequestException("tries can not be null");
        if (score.getBonus() == null) throw new BadRequestException("bonus can not be null");
        if (score.getTop() == null) throw new BadRequestException("top can not be null");
        if (score.getTop()) {
            if (score.getTriesToTop() == null) throw new BadRequestException("if top, tries to top can not be null");
            if (score.getTriesToTop() <= 0) throw new BadRequestException("if top, tries to top can not be <= 0");
            if (score.getTriesToTop() > score.getTries())
                throw new BadRequestException("if top, tries to top can not be > than all tries");
        }
        if (score.getBonus()) {
            if (score.getTriesToBonus() == null)
                throw new BadRequestException("if bonus, tries to bonus can not be null");
            if (score.getTriesToBonus() <= 0) throw new BadRequestException("if bonus, tries to bonus can not be <= 0");
            if (score.getTriesToBonus() > score.getTries())
                throw new BadRequestException("if bonus, tries to bonus can not be > than all tries");
        }
        if (score.getTop() && score.getBonus()) {
            if (score.getTriesToBonus() > score.getTriesToTop())
                throw new BadRequestException("if bonus and top, tries to bonus can not be > than tries to top");
        }
        if (!score.getBonus() && score.getTop()) throw new BadRequestException("can not be top true, and bonus false");

    }

    private static void checkSpeed(String scoreJson) {
        ScoreSpeed score = gson.fromJson(scoreJson, ScoreSpeed.class);

        if (score.getTime() == null && !(score.getFellOff() || score.getDisqualifyingFalseStart()))
            throw new BadRequestException("time can not be null if no fell off and no false start");
        if (score.getFellOff() == null) throw new BadRequestException("fell off can not be null");
        if (score.getDisqualifyingFalseStart() == null) throw new BadRequestException("false start can not be null");

        if (score.getFellOff() && score.getDisqualifyingFalseStart())
            throw new BadRequestException("can not be both: false start and fell off");
    }
}
