package com.nnk.springboot.services;

import com.nnk.springboot.domain.Privilege;
import com.nnk.springboot.repositories.PrivilegeRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Privilege service.
 */
@Service
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    /**
     * Instantiates a new Privilege service.
     *
     * @param privilegeRepository the privilege repository
     */
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    /**
     * Gets authority by role.
     *
     * @param role the role
     * @return the authority by role
     */
    public List<GrantedAuthority> getAuthorityByRole(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> "ROLE_" + role);
        Collection<Privilege> privileges = privilegeRepository.findByRole(role);
        for (Privilege privilege : privileges) {
            authorities.add((GrantedAuthority) privilege::getName);
        }
        return authorities;
    }

    /**
     * Create if not found.
     *
     * @param role the role
     * @param name the name
     */
    public void createIfNotFound(String role, String name) {
        if (!privilegeRepository.existsByRoleAndName(role, name)) {
            privilegeRepository.save(new Privilege(role, name));
        }
    }

    /**
     * Find privilege by id privilege.
     *
     * @param i the
     * @return the privilege
     */
    public Privilege findPrivilegeById(int i) {
        return privilegeRepository.findById(i).orElse(null);
    }

    /**
     * Save privilege.
     *
     * @param privilege the privilege
     */
    public void savePrivilege(Privilege privilege) {
        privilegeRepository.save(privilege);
    }

    /**
     * Delete privilege by id.
     *
     * @param i the
     */
    public void deletePrivilegeById(int i) {
        privilegeRepository.deleteById(i);
    }

    /**
     * Find all privileges list.
     *
     * @return the list
     */
    public List<Privilege> findAllPrivileges() {
        return privilegeRepository.findAll();
    }

    /**
     * Update privilege.
     *
     * @param privilege the privilege
     */
    public void updatePrivilege(Privilege privilege) {
        privilegeRepository.save(privilege);
    }
}
