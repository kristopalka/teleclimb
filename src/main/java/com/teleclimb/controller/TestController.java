package com.teleclimb.controller;

import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.dto.enums.Gender;
import com.teleclimb.dto.model.CompetitionPost;
import com.teleclimb.dto.model.Participant;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.Route;
import com.teleclimb.service.*;
import com.teleclimb.service.round.RoundManagementService;
import com.teleclimb.service.round.RoundService;
import com.teleclimb.service.round.RoundsGeneratingService;
import com.teleclimb.service.start.StartService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "test")
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "ok";
    }


    private final RoundService roundService;
    private final CompetitionService competitionService;
    private final FormulaService formulaService;
    private final RouteService routeService;
    private final RefereePositionService positionService;
    private final ParticipantService participantService;
    private final StartService startService;
    private final RoundsGeneratingService roundsGeneratingService;
    private final RoundManagementService roundManagementService;


    @PutMapping("/generate-competition-participants-rounds-starts")
    public void generate() {
        Route routeA = routeService.add(new Route(null, "Męska eliminacje A", "eliminacyjna", Discipline.LEAD, null));
        Route routeB = routeService.add(new Route(null, "Męska eliminacje B", "eliminacyjna", Discipline.LEAD, null));
        Route routeF = routeService.add(new Route(null, "Męska finał", "finałowa", Discipline.LEAD, null));


        CompetitionPost competition = competitionService.add(new CompetitionPost(null, 4, 1, Gender.MALE, "Puchar Polski"));

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

    }
}
