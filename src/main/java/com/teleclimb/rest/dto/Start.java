package com.teleclimb.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Start {
    private Integer id;

    private Integer roundId;

    private Integer routeId;

    private Integer routeSequenceNumber;

    private Integer participantId;

    private String result;
}
