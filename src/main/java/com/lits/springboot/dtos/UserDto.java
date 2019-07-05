package com.lits.springboot.dtos;

import com.lits.springboot.entity.Role;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    private int id;
    private String username;
    private String password;
    private List<Role> role;

}


