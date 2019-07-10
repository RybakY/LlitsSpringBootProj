package com.lits.springboot.repositoryTest;

import com.lits.springboot.entity.FootballTeam;
import com.lits.springboot.entity.User;
import com.lits.springboot.repository.FootballTeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FootballTeamRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    FootballTeamRepository footballTeamRepository;

    @Test
    public void testFindByNameAndCountry(){
        FootballTeam footballTeam = new FootballTeam("TestTeam", 3, "Ukraine");
        entityManager.persist(footballTeam);
        entityManager.flush();

        FootballTeam found = footballTeamRepository.findByNameAndCountry(footballTeam.getName(),footballTeam.getCountry());

        assertThat(found.getName())
                .isEqualTo(footballTeam.getName());
    }

}
