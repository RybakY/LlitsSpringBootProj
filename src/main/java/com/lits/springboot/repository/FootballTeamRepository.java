package com.lits.springboot.repository;

import com.lits.springboot.dtos.FootballTeamDto;
import com.lits.springboot.entity.FootballTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FootballTeamRepository extends CrudRepository<FootballTeam, Integer> {

    List<FootballTeam> findByNameAndCountry(String name, String country);
    FootballTeam getById(Integer Id);

}
