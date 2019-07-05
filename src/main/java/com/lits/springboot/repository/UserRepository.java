package com.lits.springboot.repository;

import com.lits.springboot.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    Optional<User> findById(Integer id);
    void deleteById(Integer id);
}
