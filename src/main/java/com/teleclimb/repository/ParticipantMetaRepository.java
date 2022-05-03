package com.teleclimb.repository;

import com.teleclimb.entitie.ParticipantMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantMetaRepository extends JpaRepository<ParticipantMetaEntity, Integer> {
    List<ParticipantMetaEntity> findAllByParticipantIdAndKey(Integer participantId, String key);

    List<ParticipantMetaEntity> findAllByParticipantId(Integer participantId);

    void removeAllByParticipantId(Integer participantId);
}
