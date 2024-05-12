package com.nnk.springboot.service;

import com.nnk.springboot.domain.Privilege;
import com.nnk.springboot.repositories.PrivilegeRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public List<GrantedAuthority> getAuthorityByRole(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> "ROLE_" + role);
        Collection<Privilege> privileges = privilegeRepository.findByRole(role);
        for (Privilege privilege : privileges) {
            authorities.add((GrantedAuthority) privilege::getName);
        }
        return authorities;
    }

    public void createIfNotFound(String role, String name) {
        if (!privilegeRepository.existsByRoleAndName(role, name)) {
            privilegeRepository.save(new Privilege(role, name));
        }
    }
}
