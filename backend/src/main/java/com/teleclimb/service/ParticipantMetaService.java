package com.teleclimb.service;

import com.teleclimb.dto.model.Meta;
import com.teleclimb.entitie.ParticipantEntity;
import com.teleclimb.entitie.ParticipantMetaEntity;
import com.teleclimb.repository.ParticipantMetaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipantMetaService {
    private final ModelMapper mapper;
    private final ParticipantMetaRepository metaRepo;

    // --------------------------------- GET ---------------------------------

    public String getValueByParticipantIdAndKey(Integer participantId, String key) {
        return getByParticipantIdAndKey(participantId, key).getValue();
    }

    public List<Meta> getAllByParticipantId(Integer participantId) {
        return metaRepo.findAllByParticipantId(participantId).stream().map(m -> mapper.map(m, Meta.class)).toList();
    }

    private ParticipantMetaEntity getByParticipantIdAndKey(Integer participantId, String key) {
        List<ParticipantMetaEntity> meta = metaRepo.findAllByParticipantIdAndKey(participantId, key);
        if (meta.size() == 0)
            throw new RuntimeException("there is no value fo key: " + key + " for participant with id: " + participantId);
        if (meta.size() > 1)
            throw new RuntimeException("there is too much values fo key: " + key + " for participant with id: " + participantId);
        return meta.get(0);
    }

    // --------------------------------- ADD ---------------------------------

    public void addOrUpdate(Integer participantId, String key, String value) {
        try {
            ParticipantMetaEntity participantMeta = getByParticipantIdAndKey(participantId, key);
            participantMeta.setValue(value);
            metaRepo.save(participantMeta);
        } catch (Exception e) {
            ParticipantMetaEntity newMeta = new ParticipantMetaEntity();

            ParticipantEntity participant = new ParticipantEntity();
            participant.setId(participantId);
            newMeta.setParticipant(participant);
            newMeta.setKey(key);
            newMeta.setValue(value);

            metaRepo.save(newMeta);
        }
    }

    // --------------------------------- DELETE ---------------------------------

    public void deleteAllByParticipantId(Integer participantId) {
        metaRepo.removeAllByParticipantId(participantId);
    }
}
