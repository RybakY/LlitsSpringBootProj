package com.lits.springboot.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class FootballTeamDto {

    @NotNull
    private String name;

    private Integer trophies;

    private String country;


}
