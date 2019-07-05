package com.lits.springboot.service.mapper;

import com.lits.springboot.dtos.FootballTeamDto;
import com.lits.springboot.dtos.UserDto;
import com.lits.springboot.entity.FootballTeam;
import com.lits.springboot.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FootballteamMapper {

//    public FootballTeam toEntity(FootballTeamDto footballTeamDto){
//        FootballTeam footballTeam = new FootballTeam();
//        footballTeam.setName(footballTeamDto.getName());
//        footballTeam.setTrophies(footballTeamDto.getTrophies());
//        footballTeam.setCountry(footballTeamDto.getCountry());
//        return footballTeam;
//    }
//
//    public FootballTeamDto toDto (FootballTeam footballTeam){
//        FootballTeamDto footballTeamDto =new FootballTeamDto();
//        footballTeamDto.setName(footballTeam.getName());
//        footballTeamDto.setTrophies(footballTeam.getTrophies());
//        footballTeamDto.setCountry(footballTeam.getCountry());
//        return footballTeamDto;
//    }

    @Autowired
    ModelMapper mapper;

    public FootballTeam toEntity(FootballTeamDto footballTeamDto) {
        return mapper.map(footballTeamDto,FootballTeam.class);
    }

    public FootballTeamDto toDto (FootballTeam footballTeam){
        return mapper.map(footballTeam, FootballTeamDto.class);
    }

    public User toEntity(UserDto userDto){
        return  mapper.map(userDto, User.class);
    }

    public UserDto toDto(User user){
        return mapper.map(user, UserDto.class);
    }

}
