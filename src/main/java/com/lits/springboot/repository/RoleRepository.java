package com.lits.springboot.repository;

import com.lits.springboot.entity.Role;
import javafx.beans.property.ReadOnlyListWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role,Integer> {

    Role findByName(String name);
}
