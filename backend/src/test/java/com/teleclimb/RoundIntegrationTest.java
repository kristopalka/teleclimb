package com.teleclimb;

import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.dto.enums.Gender;
import com.teleclimb.dto.model.Competition;
import com.teleclimb.dto.model.CompetitionPost;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.Route;
import com.teleclimb.service.*;
import com.teleclimb.service.round.RoundManagementService;
import com.teleclimb.service.round.RoundService;
import com.teleclimb.service.round.RoundsGeneratingService;
import com.teleclimb.service.start.StartService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class RoundIntegrationTest {
    Route routeA, routeB, routeF;
    Competition competition;

    @Autowired
    private RoundService roundService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private FormulaService formulaService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private RefereePositionService positionService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private StartService startService;
    @Autowired
    private RoundsGeneratingService roundsGeneratingService;
    @Autowired
    private RoundManagementService roundManagementService;


    @Test
    public void generatingStarts() {
        routeA = routeService.add(new Route(null, "Męska eliminacje A", "eliminacyjna", Discipline.LEAD, null));
        routeB = routeService.add(new Route(null, "Męska eliminacje B", "eliminacyjna", Discipline.LEAD, null));
        routeF = routeService.add(new Route(null, "Męska finał", "finałowa", Discipline.LEAD, null));


        CompetitionPost competition = competitionService.add(new CompetitionPost(0, 4, 1, Gender.MALE, "Puchar Polski"));

        // generate rounds
        roundsGeneratingService.generateRounds(competition.getId());
        List<Round> round = roundService.getAllByCompetitionId(competition.getId());

        // add routes to rounds
        positionService.addPosition(round.get(0).getId(), routeA.getId());
        positionService.addPosition(round.get(0).getId(), routeB.getId());
        positionService.addPosition(round.get(1).getId(), routeF.getId());

        // generate starts
        roundManagementService.startRound(round.get(0).getId());
        roundManagementService.finishRound(round.get(0).getId());

        roundManagementService.startRound(round.get(1).getId());
        roundManagementService.finishRound(round.get(1).getId());
    }
}
