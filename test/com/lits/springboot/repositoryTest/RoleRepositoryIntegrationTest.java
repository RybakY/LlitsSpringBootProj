package com.lits.springboot.repositoryTest;

import com.lits.springboot.entity.Role;
import com.lits.springboot.entity.User;
import com.lits.springboot.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    RoleRepository roleRepository;

    List<User> users =new ArrayList<>();

    @Test
    public void  whenfindByName_thenReturnRole(){

        Role role = new Role("ROLE_USER", users);
        entityManager.persist(role);
        entityManager.flush();

        Role found = roleRepository.findByName("ROLE_USER");

        assertThat(found.getName())
                .isEqualTo(role.getName());


    }


}



