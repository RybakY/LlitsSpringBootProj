package com.lits.springboot.service.mapper;

import com.lits.springboot.dtos.UserDto;
import com.lits.springboot.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    ModelMapper mapper;

    public User toEntity(UserDto userDto){
        return  mapper.map(userDto, User.class);
    }

    public UserDto toDto(User user){
        return mapper.map(user, UserDto.class);
    }
}
