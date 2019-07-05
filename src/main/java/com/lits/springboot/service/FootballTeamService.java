package com.lits.springboot.service;

import com.lits.springboot.dtos.FootballTeamDto;

import java.util.List;

public interface FootballTeamService {


    FootballTeamDto getById(Integer id);
    List<FootballTeamDto> getAllFootballTeam();
    FootballTeamDto save(FootballTeamDto footballTeam);

    List<FootballTeamDto> findByNameAndCountry(String country, String name);


}
