package com.teleclimb.util;

import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.entities.Participant;
import com.teleclimb.rest.entities.Round;
import com.teleclimb.rest.entities.Route;
import com.teleclimb.rest.entities.Start;

import java.util.ArrayList;
import java.util.List;

public class StartsGenerator {
    private final Round round;
    private final List<Participant> participants;
    private final List<Route> routes;
    private final List<Start> starts;

    public StartsGenerator(Round round, List<Participant> participants, List<Route> routes) {
        this.round = round;
        this.participants = participants;
        this.routes = routes;
        this.starts = new ArrayList<>();
    }

    public List<Start> generate() {
        StartsGenerationMethod method = round.getStartsGenerationMethod();

        switch (method) {
            case LEAD_CLASSIC:
                generateLeadClassic();
                break;
            default:
                throw new RuntimeException("wrong formula identifier");
        }

        return starts;
    }

    private void generateLeadClassic() {
        //todo generowanko :)
    }
}
