package com.lits.springboot.service;

import com.lits.springboot.dtos.UserDto;
import com.lits.springboot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto registerNewUserAccount(UserDto userDto);

    User register(User user);
    List<User> getAll();

    User findByUsername(String username);
    Optional<User> findById(Integer id);
    void deleteUser(Integer id);


}
