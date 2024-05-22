package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.UserDto;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUsername() {
        User user = new User();
        user.setUsername("username");
        when(userRepository.findByUsername(any(String.class))).thenReturn(user);

        User result = userService.findByUsername("username");

        assertEquals("username", result.getUsername());
        verify(userRepository, times(1)).findByUsername(any(String.class));
    }

    @Test
    void save() {
        User user = new User();
        userService.save(user);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void saveUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setPassword("password");
        userService.saveUserDto(userDto);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void findAll() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userService.findAll();

        assertEquals(0, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(user));

        User result = userService.findById(1);

        assertEquals(1, result.getId());
        verify(userRepository, times(1)).findById(any(Integer.class));
    }

    @Test
    void delete() {
        User user = new User();
        userService.delete(user);
        verify(userRepository, times(1)).delete(any(User.class));
    }
}