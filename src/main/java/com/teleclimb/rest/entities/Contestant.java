package com.teleclimb.rest.entities;

import com.teleclimb.rest.dto.ContestantDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Contestant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private String name;

    private String lastName;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;

    public ContestantDto toDto() {
        ContestantDto dto = new ContestantDto();

        dto.setId(this.getId());
        dto.setCompetition(this.getCompetition());
        dto.setName(this.getName());
        dto.setLastName(this.getLastName());
        dto.setStartNumber(this.getStartNumber());
        dto.setClubName(this.getClubName());
        dto.setBirthDate(this.getBirthDate());

        return dto;
    }
}
