package com.lits.springboot.service.impl;

import com.lits.springboot.dtos.UserDto;
import com.lits.springboot.entity.Role;
import com.lits.springboot.entity.User;
import com.lits.springboot.repository.RoleRepository;
import com.lits.springboot.repository.UserRepository;
import com.lits.springboot.service.mapper.UserMapper;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service(value = "userservice")
public class UserService  implements com.lits.springboot.service.UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    //    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserService() {

    }

    @Override
    public UserDto registerNewUserAccount(UserDto userDto) {

        User user = userMapper.toEntity(userDto);
        List<com.lits.springboot.entity.Role> roles = new ArrayList<>();
        roles.add(roleRepository.getOne(1));

        user.setUsername(userDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRoles(roles);
            return userMapper.toDto(userRepository.save(user));
    }
//    public List<User> getAllUsers(){
//        List<User> list = userRepository.findAll();
//        return list.stream().map(user ->userMapper.toDto(user)).collect(Collectors.toList());
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role:user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getUsername(), grantedAuthorities);
    }

    private boolean usernameExists(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }


    //    proselite
    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User registered = userRepository.save(user);

//        log.info("IN register - user successfully registered");
        return registered;
    }

    @Override
    public List<User> getAll() {
        List<User> users = (List<User>) userRepository.findAll();
        log.info("IN getAll users found" );

        return users;

    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user found by username");
        return result;

    }

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> result = userRepository.findById(id);

        if (result == null) {
            log.warn("IN findById - no user found by id");
            return null;
        }

        log.info("IN findById - user: {} found by id: {}");
        return result;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id successfully deleted");
    }


}
