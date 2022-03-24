package com.teleclimb.rest.services.upperlevel;

import com.teleclimb.enums.Discipline;
import com.teleclimb.enums.Gender;
import com.teleclimb.rest.dto.Competition;
import com.teleclimb.rest.dto.Participant;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.dto.Route;
import com.teleclimb.rest.services.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public record TestService(RoundService roundService, CompetitionService competitionService,
                          FormulaService formulaService, RouteService routeService,
                          RefereePositionService positionServoce, ParticipantService participantService,
                          StartService startService, GeneratingService operationsService) {
    public void testWeird() {
        Route e1 = routeService.add(new Route(null, "Męska eliminacje A", "eliminacyjna", Discipline.LEAD, null));
        Route e2 = routeService.add(new Route(null, "Męska eliminacje B", "eliminacyjna", Discipline.LEAD, null));
        Route f = routeService.add(new Route(null, "Męska finał", "finałowa", Discipline.LEAD, null));

        Competition competition = competitionService.add(new Competition(0, 4, 1, null, Gender.MALE, "Puchar Polski"));

        participantService.add(new Participant(null, competition.getId(), null, "Krzysztof", "Pałka", 1, "0001", "Oblak", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competition.getId(), null, "Kinga", "Pałka", 6, "0002", "Oblak", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competition.getId(), null, "Andrzej", "Krzywda", 3, "0003", "Korona", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competition.getId(), null, "Mikołaj", "Cheretyk", 234, "0004", "Tarnovia", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competition.getId(), null, "Aleksandra", "Kotwas", null, "0005", "Skarpa Bytom", LocalDate.of(2000, 8, 26)));
        participantService.add(new Participant(null, competition.getId(), null, "Kinga", "Ociepka", null, "0006", "Korona", LocalDate.of(2000, 8, 26)));

        // generowanie rund
        operationsService.generateRounds(competition.getId());
        List<Round> round = roundService.getAllByCompetitionId(competition.getId());

        // dodawanie dróg do rund
        positionServoce.addPosition(round.get(0).getId(), e1.getId());
        positionServoce.addPosition(round.get(0).getId(), e2.getId());
        positionServoce.addPosition(round.get(1).getId(), f.getId());

        // generowanie startów
        operationsService.generateStarts(round.get(0).getId());

    }

}
