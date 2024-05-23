package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The interface Privilege repository.
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

    /**
     * Find by role list.
     *
     * @param role the role
     * @return the list
     */
    List<Privilege> findByRole(String role);

    /**
     * Exists by role and name boolean.
     *
     * @param role the role
     * @param name the name
     * @return the boolean
     */
    Boolean existsByRoleAndName(String role, String name);
}
