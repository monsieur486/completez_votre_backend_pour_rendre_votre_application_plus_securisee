package com.nnk.springboot.configuration;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.service.*;
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
    private final BidListService bidListService;
    private final RatingService ratingService;
    private final RuleNameService ruleNameService;
    private final TradeService tradeService;
    private final CurvePointService curvePointService;


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

    public SetupDataLoader(UserService userService, PrivilegeService privilegeService, BidListService bidListService, RatingService ratingService, RuleNameService ruleNameService, TradeService tradeService, CurvePointService curvePointService) {
        this.userService = userService;
        this.privilegeService = privilegeService;
        this.bidListService = bidListService;
        this.curvePointService = curvePointService;
        this.ratingService = ratingService;
        this.ruleNameService = ruleNameService;
        this.tradeService = tradeService;
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

            installFixtiures();
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

    private void installFixtiures() {
        log.warn("Installing fixtures");

        log.warn("Creating fixtures for BidList");
        BidList bidList = new BidList("Account Test", "Type Test", 10);
        bidListService.saveBidList(bidList, "ADMIN");
        BidList bidList2 = new BidList("Account Test 2", "Type Test 2", 20);
        bidListService.saveBidList(bidList2, "ADMIN");


    }

}
