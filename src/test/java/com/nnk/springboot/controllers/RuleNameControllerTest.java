package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RuleNameControllerTest {
    @Mock
    RuleNameService ruleNameService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @Mock
    Principal principal;

    @InjectMocks
    RuleNameController ruleNameController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void home() {
        when(ruleNameService.findAllRuleNames()).thenReturn(Collections.emptyList());
        String result = ruleNameController.home(model);
        assertEquals("ruleName/list", result);
        verify(ruleNameService, times(1)).findAllRuleNames();
    }

    @Test
    void addRuleNameForm() {
        RuleName ruleName = new RuleName();
        String result = ruleNameController.addRuleForm(ruleName);
        assertEquals("ruleName/add", result);
    }

    @Test
    void validate() {
        RuleName ruleName = new RuleName();
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = ruleNameController.validate(ruleName, bindingResult, model);
        assertEquals("ruleName/list", result);
    }

    @Test
    void showUpdateForm() {
        RuleName ruleName = new RuleName();
        when(ruleNameService.findRuleNameById(any(Integer.class))).thenReturn(ruleName);
        String result = ruleNameController.showUpdateForm(1, model);
        assertEquals("ruleName/update", result);
        verify(ruleNameService, times(1)).findRuleNameById(any(Integer.class));
    }

    @Test
    void updateRuleName() {
        RuleName ruleName = new RuleName();
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = ruleNameController.updateRuleName(1, ruleName, bindingResult, model);
        assertEquals("redirect:/ruleName/list", result);
    }

    @Test
    void deleteRuleName() {
        String result = ruleNameController.deleteRuleName(1, model);
        assertEquals("redirect:/ruleName/list", result);
        verify(ruleNameService, times(1)).deleteRuleNameById(any(Integer.class));
    }

}