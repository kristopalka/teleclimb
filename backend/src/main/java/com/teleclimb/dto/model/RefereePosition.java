package com.teleclimb.dto.model;

import lombok.Data;

@Data
public class RefereePosition {
    private Integer id;

    private String hash;

    private Integer routeId;

    private Integer roundId;
}
