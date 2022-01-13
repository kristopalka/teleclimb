package com.teleclimb.rest.entity;

import com.teleclimb.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class Contestant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String lastName;

    private String startNumber;

    private String clubName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;
}
