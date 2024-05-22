package com.nnk.springboot.database;

import com.nnk.springboot.domain.Privilege;
import com.nnk.springboot.repositories.PrivilegeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PrivilegeTests {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Test
    void privilegeTest() {
        Privilege privilege = new Privilege("Role", "Name");

        // Save
        privilege = privilegeRepository.save(privilege);
        assertNotNull(privilege.getId());
        assertTrue(privilege.getRole().equals("Role"));

        // Update
        privilege.setRole("Role Update");
        privilege = privilegeRepository.save(privilege);
        assertTrue(privilege.getRole().equals("Role Update"));

        // Find
        privilegeRepository.findAll();
        assertFalse(privilegeRepository.findAll().isEmpty());

        // Delete
        Integer id = privilege.getId();
        privilegeRepository.delete(privilege);
        Optional<Privilege> privilegeList = privilegeRepository.findById(id);
        assertFalse(privilegeList.isPresent());
    }
}