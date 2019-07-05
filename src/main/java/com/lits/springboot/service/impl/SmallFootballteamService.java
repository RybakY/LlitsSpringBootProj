package com.lits.springboot.service.impl;

import com.lits.springboot.dtos.FootballTeamDto;
import com.lits.springboot.entity.FootballTeam;
import com.lits.springboot.repository.FootballTeamRepository;
import com.lits.springboot.service.FootballTeamService;
import com.lits.springboot.service.PersonNotFoundException;
import com.lits.springboot.service.mapper.FootballteamMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.jws.Oneway;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "small")
@Slf4j
public class SmallFootballteamService implements FootballTeamService {

    @Autowired
    private FootballTeamRepository footballTeamRepository;
    @Autowired
    private FootballteamMapper footballteamMapper;

    @Autowired
    private ModelMapper mapper;


    @Override
    public FootballTeamDto getById(Integer id) {
        FootballTeam footballTeam = footballTeamRepository.getById(id);

        if (footballTeam == null) {
            throw new PersonNotFoundException("user with id: " + id + " not found");
//            throw  new RuntimeException("user not found1");
        }
        FootballTeamDto footballTeamDto = footballteamMapper.toDto(footballTeam);

        if (footballTeamDto == null) {
            throw new RuntimeException("user not found2");
        }

        return footballTeamDto;
    }

//    @Override
    public List<FootballTeamDto> getAllFootballTeam() {
//        return null;
//        @Override
//        public List<FootballTeamDto> getAllFootballTeam() {
//            List<FootballTeam> list = footballTeamRepository.findAll();
//            return list.stream().map(team ->footballteamMapper.toDto(team)).collect(Collectors.toList());
//
//
        return null;
        }



    @Override
    public FootballTeamDto save( FootballTeamDto footballTeamDto ) {
        FootballTeam footballTeam = footballteamMapper.toEntity(footballTeamDto);
        return footballteamMapper.toDto(footballTeamRepository.save(footballTeam));
    }

//    @Override
//    public List<FootballTeamDto> findByCountryAndName(String name,String country) {
//        List<FootballTeam> list = footballTeamRepository.findAll();
//        return list.stream()
//                .filter(t ->t.getName()==name)
//                .filter(c ->c.getCountry()==country)
//                .map(team ->footballteamMapper.toDto(team))
//                .collect(Collectors.toList());
//    }

        @Override
        public List<FootballTeamDto> findByNameAndCountry (String name, String country){
            List<FootballTeam> list = footballTeamRepository.findByNameAndCountry(name, country);
            return list.stream()
                    .map(team -> footballteamMapper.toDto(team))
                    .collect(Collectors.toList());
        }


    }

