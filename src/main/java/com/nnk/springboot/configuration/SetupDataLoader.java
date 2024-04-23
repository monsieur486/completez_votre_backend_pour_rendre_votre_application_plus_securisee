package com.nnk.springboot.configuration;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    @Value("${ADMIN_USERNAME}")
    public String adminUsername;

    @Value("${ADMIN_FULLNAME}")
    public String adminFullname;

    @Value("${ADMIN_PASSWORD}")
    public String adminPassword;

    @Value("${USER_USERNAME}")
    public String userUsername;

    @Value("${USER_FULLNAME}")
    public String userFullname;

    @Value("${USER_PASSWORD}")
    public String userPassword;
    private boolean alreadySetup = false;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    public SetupDataLoader(UserService userService) {
        this.userService = userService;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        createUserIfNotFound(adminUsername, adminFullname, adminPassword, "ADMIN");


        if (activeProfile.equals("dev")) {
            createUserIfNotFound(userUsername, userFullname, userPassword, "USER");
        }

        alreadySetup = true;
    }

    private void createUserIfNotFound(String username, String fullname, String password, String role) {
        if (activeProfile.equals("dev")) {
            log.warn("Creating user username: {}", username);
            log.warn("Creating user fullname: {}", fullname);
            log.warn("Creating user role    : {}", role);
            log.warn("Password user         : {}", password);
        }
        User user = userService.findByUsername(username);
        if (user == null) {
            UserDto userDto = new UserDto(username, fullname, password, role);
            userService.saveUserDto(userDto);
        }
    }

}
