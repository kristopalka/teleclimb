package com.teleclimb;

import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.dto.enums.Gender;
import com.teleclimb.dto.model.*;
import com.teleclimb.service.FormulaService;
import com.teleclimb.service.ParticipantService;
import com.teleclimb.service.RefereePositionService;
import com.teleclimb.service.RouteService;
import com.teleclimb.service.competition.CompetitionService;
import com.teleclimb.service.round.RoundManagementService;
import com.teleclimb.service.round.RoundService;
import com.teleclimb.service.round.RoundsGeneratingService;
import com.teleclimb.service.start.StartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class GeneratingStartsIntegrationTests {
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
    public void fullTest() {
        routeA = routeService.add(new Route(null, "Męska eliminacje A", "eliminacyjna", Discipline.LEAD, null));
        routeB = routeService.add(new Route(null, "Męska eliminacje B", "eliminacyjna", Discipline.LEAD, null));
        routeF = routeService.add(new Route(null, "Męska finał", "finałowa", Discipline.LEAD, null));

        Category category = new Category();
        category.setId(4);

        Formula formula = new Formula();
        formula.setId(1);

        Competition competition = competitionService.add(new Competition(0, category, formula, null, Gender.MALE, "Puchar Polski"));

        participantService.add(new Participant(null, competition.getId(), null, "Krzysztof", "Pałka", 1, "0001", "Oblak", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competition.getId(), null, "Kinga", "Pałka", 6, "0002", "Oblak", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competition.getId(), null, "Andrzej", "Krzywda", 3, "0003", "Korona", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competition.getId(), null, "Mikołaj", "Cheretyk", 234, "0004", "Tarnovia", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competition.getId(), null, "Aleksandra", "Kotwas", null, "0005", "Skarpa Bytom", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competition.getId(), null, "Kinga", "Ociepka", null, "0006", "Korona", LocalDate.of(2000, 8, 26)));


        // generate rounds
        roundsGeneratingService.generateRounds(competition.getId());
        List<Round> round = roundService.getAllByCompetitionId(competition.getId());

        // add routes to rounds
        positionService.addPosition(round.get(0).getId(), routeA.getId());
        positionService.addPosition(round.get(0).getId(), routeB.getId());
        positionService.addPosition(round.get(1).getId(), routeF.getId());

        // start round
        roundManagementService.startRound(round.get(0).getId());

        // check
        List<Start> starts = startService.getAll();
        Assertions.assertEquals(12, starts.size());
    }


}
