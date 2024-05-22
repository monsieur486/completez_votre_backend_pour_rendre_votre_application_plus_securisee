package com.nnk.springboot.controllers;

import com.nnk.springboot.configuration.ApplicationConfiguration;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import com.nnk.springboot.tools.PasswordValidation;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        validatePassword(user, result);

        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.save(user);
            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id,
                             @Valid User user,
                             BindingResult result,
                             Principal principal,
                             Model model) {
        validatePassword(user, result);

        if (result.hasErrors()) {
            return "user/update";
        }

        if (principal.getName().equals(user.getUsername())) {
            model.addAttribute("errorMessage",
                    "You can't change your own role.");
            model.addAttribute("users", userService.findAll());
            return "user/list";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable("id") Integer id,
                             Model model,
                             Principal principal) {
        User user = userService.findById(id);

        if (principal.getName().equals(user.getUsername())) {
            model.addAttribute("errorMessage",
                    "You can't delete your own account.");
            model.addAttribute("users", userService.findAll());
            return "user/list";
        }

        userService.delete(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    private void validatePassword(@Valid User user, BindingResult result) {
        if(!PasswordValidation.hasMinimumLength(user.getPassword())) {
            result.rejectValue("password", "password", "Password must be at least " + ApplicationConfiguration.MINIMUM_PASSWORD_LENGTH + " characters.");
        }

        if(!PasswordValidation.hasMaximumLength(user.getPassword())) {
            result.rejectValue("password", "password", "Password must be less than " + ApplicationConfiguration.MAXIMUM_PASSWORD_LENGTH + " characters.");
        }

        if(!PasswordValidation.containsCapitalLetter(user.getPassword())) {
            result.rejectValue("password", "password", "Password must contain at least one capital letter.");
        }

        if(!PasswordValidation.containsLowercaseLetter(user.getPassword())) {
            result.rejectValue("password", "password", "Password must contain at least one lowercase letter.");
        }

        if(!PasswordValidation.containsDigit(user.getPassword())) {
            result.rejectValue("password", "password", "Password must contain at least one digit.");
        }

        if(!PasswordValidation.containsSpecialCharacter(user.getPassword())) {
            result.rejectValue("password", "password", "Password must contain at least one special character.");
        }
    }
}
