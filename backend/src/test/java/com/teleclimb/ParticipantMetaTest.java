package com.teleclimb;

import com.teleclimb.dto.enums.Gender;
import com.teleclimb.dto.model.CompetitionPost;
import com.teleclimb.dto.model.Participant;
import com.teleclimb.service.CompetitionService;
import com.teleclimb.service.ParticipantMetaService;
import com.teleclimb.service.ParticipantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
class ParticipantMetaTest {
    @Autowired
    private ParticipantMetaService metaService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private ParticipantService participantService;


    @Test
    public void generatingStarts() {
        CompetitionPost competition = competitionService.add(new CompetitionPost(0, 4, 1, Gender.MALE, "Puchar Polski"));

        Participant participant = participantService.add(new Participant(null, competition.getId(), null, null, "Kinga", "Ociepka", null, "0006", "Korona", LocalDate.of(2000, 8, 26)));

        metaService.addOrUpdate(participant.getId(), "key", "value");
        metaService.addOrUpdate(participant.getId(), "key2", "value2");
        Assertions.assertEquals(metaService.getValueByParticipantIdAndKey(participant.getId(), "key"), "value");
        Assertions.assertEquals(metaService.getValueByParticipantIdAndKey(participant.getId(), "key2"), "value2");
        Assertions.assertEquals(metaService.getAllByParticipantId(participant.getId()).size(), 2);
        Assertions.assertThrows(RuntimeException.class, () -> metaService.getValueByParticipantIdAndKey(10000, "key"));
        Assertions.assertThrows(RuntimeException.class, () -> metaService.getValueByParticipantIdAndKey(participant.getId(), "wrongKey"));

        metaService.addOrUpdate(participant.getId(), "key", "nextValue");
        Assertions.assertEquals(metaService.getValueByParticipantIdAndKey(participant.getId(), "key"), "nextValue");

        metaService.deleteAllByParticipantId(participant.getId());
        Assertions.assertEquals(metaService.getAllByParticipantId(participant.getId()).size(), 0);
    }
}
