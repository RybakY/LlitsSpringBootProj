package com.lits.springboot.security.jwt;

import com.lits.springboot.entity.Role;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

//   @Value("${jwt.token.secret}")
//    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    private final byte[] secret;
    private final String userIdClaim = "userIdClaim";
    private final Long allowedClockSkewSeconds = 30L;
    private final JwtParser jwtParser;

    public JwtTokenProvider(@Value("${jwt.token.secret}") String secret) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
        jwtParser = Jwts.parser()
                .setSigningKey(this.secret)
                .setAllowedClockSkewSeconds(this.allowedClockSkewSeconds);
    }

    @Qualifier("jwt")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

//    @PostConstruct
//    protected void init() {
//        secret = Base64.getEncoder().encodeToString(secret.getBytes());
//    }
//
//    public String createToken(String username, List<Role> roles) {
//
//        Claims claims = Jwts.claims().setSubject(username);
//        claims.put("roles", getRoleNames(roles));
//
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(io.jsonwebtoken.SignatureAlgorithm.ES256, secret)//
//                .compact();
//    }


    public String createToken(Integer id) {
        return Jwts.builder()
                .claim(userIdClaim, id)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret).compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

    private List<String> getRoleNames(List<Role> userRoles) {
        List<String> result = new ArrayList<>();

        userRoles.forEach(role -> {
            result.add(role.getName());
        });

        return result;
    }
}
