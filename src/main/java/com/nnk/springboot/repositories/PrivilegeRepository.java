package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

    List<Privilege> findByRole(String role);

    Boolean existsByRoleAndName(String role, String name);
}
