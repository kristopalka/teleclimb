package com.teleclimb.controller;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.dto.enums.Gender;
import com.teleclimb.dto.model.*;
import com.teleclimb.dto.model.score.ScoreLead;
import com.teleclimb.service.*;
import com.teleclimb.service.round.RoundManagementService;
import com.teleclimb.service.round.RoundService;
import com.teleclimb.service.round.RoundsGeneratingService;
import com.teleclimb.service.start.StartService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/testing/")
@Api(tags = "test purpose")
public class TestingPurposeController {
    private final Gson gson = GsonConfig.get();
    private final RoundService roundService;
    private final CompetitionService competitionService;
    private final FormulaService formulaService;
    private final RouteService routeService;
    private final RefereePositionService positionService;
    private final ParticipantService participantService;
    private final StartService startService;
    private final RoundsGeneratingService roundsGeneratingService;
    private final RoundManagementService roundManagementService;
    private final ResultsService resultsService;

    @PutMapping("/generate")
    public List<String> generate() {
        Integer competitionId = competitionService.add(new CompetitionPost(null, 4, 1, Gender.MALE, "Puchar Polski")).getId();

        addParticipants(competitionId);
        List<Round> rounds = generateRoundsAndAddRoutes(competitionId);

        return rounds.stream().map(r -> "{" + r.getName() + ": " + r.getId().toString() + "} ").collect(Collectors.toList());
    }

    @PutMapping("/fill/{roundId}")
    public void results(@PathVariable Integer roundId) {
        List<RefereePosition> positions = positionService.getAllByRoundId(roundId);


        for (RefereePosition position : positions) {
            List<StartWithParticipant> starts = startService.getByRefereePositionHash(position.getHash());

            for (StartWithParticipant start : starts) {
                String score = gson.toJson(generateRandomScore());
                startService.updateScore(start.getId(), score);
            }
        }
    }


    private void addParticipants(Integer competitionId) {
        participantService.add(new Participant(null, competitionId, null, null, "Krzysztof", "Pałka", 1, "0001", "AZS AGH", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competitionId, null, null, "Kinga", "Pałka", 6, "0002", "MKS Pałac Młodzieży w Tarnowie", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competitionId, null, null, "Andrzej", "Krzywda", 3, "0003", "", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competitionId, null, null, "Mikołaj", "Cheretyk", 234, "0004", "Sekcja Wspinaczkowa KS \"Korona\"", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competitionId, null, null, "Aleksandra", "Kotwas", null, "0005", "AZS AWF", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competitionId, null, null, "Kinga", "Ociepka", null, "0006", "Sekcja Wspinaczkowa KS \"Korona\"", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competitionId, null, null, "Julia", "Seweryn", null, "0007", "AZS AGH", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competitionId, null, null, "Paweł", "Bańczyk", null, "0008", "Sakwa", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competitionId, null, null, "Roman", "Kharytonov", null, "0009", "Sakwa", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competitionId, null, null, "Szymon", "Bobrowski", null, "0010", "", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competitionId, null, null, "Wojciech", "Mościński", null, "0011", "", LocalDate.of(2000, 8, 26)));
    }

    private List<Round> generateRoundsAndAddRoutes(Integer competitionId) {
        roundsGeneratingService.generateRounds(competitionId);
        List<Round> rounds = roundService.getAllByCompetitionId(competitionId);

        Route routeA = routeService.add(new Route(null, "Męska eliminacje A", "eliminacyjna", Discipline.LEAD, null));
        Route routeB = routeService.add(new Route(null, "Męska eliminacje B", "eliminacyjna", Discipline.LEAD, null));
        Route routeF = routeService.add(new Route(null, "Męska finał", "finałowa", Discipline.LEAD, null));

        positionService.addPosition(rounds.get(0).getId(), routeA.getId());
        positionService.addPosition(rounds.get(0).getId(), routeB.getId());
        positionService.addPosition(rounds.get(1).getId(), routeF.getId());

        return rounds;
    }

    private ScoreLead generateRandomScore() {
        Random random = new Random();

        ScoreLead score = new ScoreLead();
        score.setValue(random.nextInt(5, 20));
        score.setPlus(random.nextBoolean());
        score.setTime(LocalTime.of(0, 0, 0, random.nextInt(120000)));

        return score;
    }
}
