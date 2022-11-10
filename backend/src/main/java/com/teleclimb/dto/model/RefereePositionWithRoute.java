package com.teleclimb.dto.model;

import lombok.Data;

@Data
public class RefereePositionWithRoute {
    private Integer id;

    private String hash;

    private Route route;

    private Integer roundId;
}
