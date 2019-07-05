package com.lits.springboot.web;

import com.lits.springboot.dtos.FootballTeamDto;
import com.lits.springboot.entity.FootballTeam;
import com.lits.springboot.service.FootballTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/api")
public class FootballteamController {



    @Autowired
    @Qualifier("small")
    private FootballTeamService smallfootballTeamService;



    @PostMapping
    public FootballTeamDto saveFootballteam(@Validated @RequestBody FootballTeamDto footballTeamDto){
        return smallfootballTeamService.save(footballTeamDto);
    }

    @GetMapping(value = "/footballTeam")
    public FootballTeamDto getFootballteamById(@RequestParam Integer id){
        return smallfootballTeamService.getById(id);
    }


//    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/allFootballTeam")
    public List <FootballTeamDto> getAllFootballTeam(){
        return smallfootballTeamService.getAllFootballTeam();
    }

    @GetMapping(value = "/footballTeamlist")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<FootballTeamDto>findByCountryAndName(@RequestParam String name, @RequestParam String country ){
        return smallfootballTeamService.findByNameAndCountry(name, country);
    }

}
