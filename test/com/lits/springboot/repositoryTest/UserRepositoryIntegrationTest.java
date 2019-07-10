package com.lits.springboot.repositoryTest;

import com.lits.springboot.entity.Role;
import com.lits.springboot.entity.User;
import com.lits.springboot.repository.UserRepository;
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
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    List<Role> roles  =new ArrayList<>();


    @Test
    public void whenfindByUsername_thenReturnUser() {

        // given
        User user1 = new User("Ivan", "test", roles);
        entityManager.persist(user1);
        entityManager.flush();

        // when
        User found = userRepository.findByUsername(user1.getUsername());

        // then
        assertThat(found.getUsername())
                .isEqualTo(user1.getUsername());


    }

}
