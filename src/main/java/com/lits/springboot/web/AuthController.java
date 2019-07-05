package com.lits.springboot.web;

import com.lits.springboot.dtos.AuthenticationRequestDto;
import com.lits.springboot.entity.User;
import com.lits.springboot.security.jwt.JwtTokenProvider;
import com.lits.springboot.service.AuthService;
import com.lits.springboot.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    @Autowired
    private AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {

            String username = requestDto.getUsername();
            String password = requestDto.getPassword();

            final Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userService.findByUsername(username);
            String token = jwtTokenProvider.createToken(user.getId());
            return ResponseEntity.ok(authService.auth(requestDto.getUsername(), requestDto.getPassword()));

//            Map<Object, Object> response = new HashMap<>();
//            response.put("username", username);
//            response.put("token", token);
//            return ResponseEntity.ok(response);


//        String token1 = authService.auth(username, password);
//        return ResponseEntity.ok(token1);

    }


}
