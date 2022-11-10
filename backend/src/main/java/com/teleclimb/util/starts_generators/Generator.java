package com.teleclimb.util.starts_generators;

import com.teleclimb.dto.model.Participant;
import com.teleclimb.dto.model.RefereePosition;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.Start;

import java.util.List;

public interface Generator {
    List<Start> generate(Round round, List<Participant> participants, List<RefereePosition> positions);
}
