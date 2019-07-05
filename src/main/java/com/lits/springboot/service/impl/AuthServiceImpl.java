package com.lits.springboot.service.impl;

import com.lits.springboot.entity.User;
import com.lits.springboot.repository.UserRepository;
import com.lits.springboot.security.jwt.JwtTokenProvider;
import com.lits.springboot.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

//    @Autowired
//    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String auth(String username, String pass) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        pass
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(username);
        return jwtTokenProvider.createToken(user.getId());
    }

}
