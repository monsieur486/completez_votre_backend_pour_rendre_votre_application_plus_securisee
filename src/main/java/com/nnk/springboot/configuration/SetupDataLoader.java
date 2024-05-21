package com.nnk.springboot.configuration;

import com.nnk.springboot.configuration.fixture.*;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.service.PrivilegeService;
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
    private final PrivilegeService privilegeService;
    private final InstallBidListFixture installBidListFixture;
    private final InstallCurvePointFixture installCurvePointFixture;
    private final InstallRatingFixture installRatingFixture;
    private final InstallRuleNameFixture installRuleNameFixture;
    private final InstallTradeFixture installTradeFixture;

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

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private boolean alreadySetup = false;

    public SetupDataLoader(UserService userService, PrivilegeService privilegeService, InstallBidListFixture installBidListFixture, InstallCurvePointFixture installCurvePointFixture, InstallRatingFixture installRatingFixture, InstallRuleNameFixture installRuleNameFixture, InstallTradeFixture installTradeFixture) {
        this.userService = userService;
        this.privilegeService = privilegeService;
        this.installBidListFixture = installBidListFixture;
        this.installCurvePointFixture = installCurvePointFixture;
        this.installRatingFixture = installRatingFixture;
        this.installRuleNameFixture = installRuleNameFixture;
        this.installTradeFixture = installTradeFixture;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        createUserIfNotFound(adminUsername, adminFullname, adminPassword, "ADMIN");

        createPrivilegeIfNotFound("ADMIN", "ADD_PRIVILEGE");
        createPrivilegeIfNotFound("ADMIN", "UPDATE_PRIVILEGE");
        createPrivilegeIfNotFound("ADMIN", "DELETE_PRIVILEGE");
        createPrivilegeIfNotFound("ADMIN", "USER_MANAGEMENT");


        if (activeProfile.equals("dev")) {
            createUserIfNotFound(userUsername, userFullname, userPassword, "USER");
            createUserIfNotFound("manager", "Manager", userPassword, "MANAGER");
            createPrivilegeIfNotFound("MANAGER", "USER_MANAGEMENT");

            installBidListFixture.execute();
            installCurvePointFixture.execute();
            installRatingFixture.execute();
            installRuleNameFixture.execute();
            installTradeFixture.execute();
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

    private void createPrivilegeIfNotFound(String role, String name) {
        privilegeService.createIfNotFound(role, name);
    }
}
