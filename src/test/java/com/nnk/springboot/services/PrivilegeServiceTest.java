package com.nnk.springboot.services;

import com.nnk.springboot.domain.Privilege;
import com.nnk.springboot.repositories.PrivilegeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PrivilegeServiceTest {
    @Mock
    PrivilegeRepository privilegeRepository;

    @InjectMocks
    PrivilegeService privilegeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findPrivilegeById() {
        Privilege privilege = new Privilege();
        privilege.setId(1);
        when(privilegeRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(privilege));

        Privilege result = privilegeService.findPrivilegeById(1);

        assertEquals(1, result.getId());
        verify(privilegeRepository, times(1)).findById(any(Integer.class));
    }

    @Test
    void savePrivilege() {
        Privilege privilege = new Privilege();
        privilegeService.savePrivilege(privilege);
        verify(privilegeRepository, times(1)).save(any(Privilege.class));
    }

    @Test
    void deletePrivilegeById() {
        privilegeService.deletePrivilegeById(1);
        verify(privilegeRepository, times(1)).deleteById(any(Integer.class));
    }

    @Test
    void findAllPrivileges() {
        when(privilegeRepository.findAll()).thenReturn(Collections.emptyList());

        List<Privilege> result = privilegeService.findAllPrivileges();

        assertEquals(0, result.size());
        verify(privilegeRepository, times(1)).findAll();
    }

    @Test
    void updatePrivilege() {
        Privilege privilege = new Privilege();
        privilegeService.updatePrivilege(privilege);
        verify(privilegeRepository, times(1)).save(any(Privilege.class));
    }

}