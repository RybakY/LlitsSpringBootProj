package com.lits.springboot.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
     private int id;

    @Column(name = "username", unique = true)
     private String username;

    @Column(name = "password")
     private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
               name = "user_roles",
               joinColumns ={@JoinColumn(name = "user_id", referencedColumnName = "id")},
               inverseJoinColumns ={ @JoinColumn(name = "role_id", referencedColumnName = "id")})
    List<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
