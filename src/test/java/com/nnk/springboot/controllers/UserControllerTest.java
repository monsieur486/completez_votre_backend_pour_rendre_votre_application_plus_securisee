package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.security.Principal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserControllerTest {
    @Mock
    UserService userService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @Mock
    Principal principal;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void home() {
        when(userService.findAll()).thenReturn(Collections.emptyList());
        String result = userController.home(model);
        assertEquals("user/list", result);
        verify(userService, times(1)).findAll();
    }

    @Test
    void addUserForm() {
        User user = new User();
        String result = userController.addUser(user);
        assertEquals("user/add", result);
    }

    @Test
    void validate() {
        User user = new User();
        user.setPassword("TestPassword123!");
        when(bindingResult.hasErrors()).thenReturn(false);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        String result = userController.validate(user, bindingResult, model);
        assertEquals("redirect:/user/list", result);
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void validateWithErrors() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("**");
        when(bindingResult.hasErrors()).thenReturn(true);
        String result = userController.validate(user, bindingResult, model);
        assertEquals("user/add", result);
    }

    @Test
    void showUpdateForm() {
        User user = new User();
        when(userService.findById(any(Integer.class))).thenReturn(user);
        String result = userController.showUpdateForm(1, model);
        assertEquals("user/update", result);
        verify(userService, times(1)).findById(any(Integer.class));
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("TestPassword123!");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(principal.getName()).thenReturn("AnotherUser");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        String result = userController.updateUser(1, user, bindingResult, principal, model);
        assertEquals("redirect:/user/list", result);
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void updateUserWithErrors() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("**");
        when(bindingResult.hasErrors()).thenReturn(true);
        String result = userController.updateUser(1, user, bindingResult, principal, model);
        assertEquals("user/update", result);
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("TestPassword123!");
        when(userService.findById(any(Integer.class))).thenReturn(user);
        when(principal.getName()).thenReturn("AnotherUser");
        String result = userController.deleteUser(1, model, principal);
        assertEquals("redirect:/user/list", result);
        verify(userService, times(1)).delete(any(User.class));
    }

}