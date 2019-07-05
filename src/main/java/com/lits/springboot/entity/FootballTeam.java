package com.lits.springboot.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@ToString(of = {"name"})
@EqualsAndHashCode(exclude = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table
public class FootballTeam {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please provide a name")
    private String name;

    @NotEmpty(message = "Please provide a number of trophies")
    private Integer trophies;

    @NotEmpty(message = "Please provide a country")
    private String country;

//    FootballTeam footballTeam = FootballTeam.builder().id(1).name("aad").trophies(4).country("UK").build();

}
