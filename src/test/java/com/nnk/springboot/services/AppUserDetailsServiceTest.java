package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AppUserDetailsServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PrivilegeService privilegeService;

    @InjectMocks
    AppUserDetailsService appUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("TestPassword123!");
        user.setRole("ROLE_USER");
        when(userRepository.findByUsername(any(String.class))).thenReturn(user);
        when(privilegeService.getAuthorityByRole(any(String.class))).thenReturn(Collections.emptyList());

        UserDetails result = appUserDetailsService.loadUserByUsername("TestUser");

        assertEquals("TestUser", result.getUsername());
        assertEquals("TestPassword123!", result.getPassword());
    }

    @Test
    void loadUserByUsername_NotFound() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> appUserDetailsService.loadUserByUsername("TestUser"));
    }
}