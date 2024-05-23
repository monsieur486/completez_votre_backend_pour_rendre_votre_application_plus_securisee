package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RuleNameServiceTest {

    @Mock
    RuleNameRepository ruleNameRepository;

    @InjectMocks
    RuleNameService ruleNameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRuleName() {
        RuleName ruleName = new RuleName();
        ruleNameService.saveRuleName(ruleName);
        verify(ruleNameRepository, times(1)).save(any(RuleName.class));
    }

    @Test
    void findRuleNameById() {
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        when(ruleNameRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(ruleName));

        RuleName result = ruleNameService.findRuleNameById(1);

        assertEquals(1, result.getId());
        verify(ruleNameRepository, times(1)).findById(any(Integer.class));
    }

    @Test
    void findAllRuleNames() {
        when(ruleNameRepository.findAll()).thenReturn(Collections.emptyList());

        Iterable<RuleName> result = ruleNameService.findAllRuleNames();

        assertEquals(0, ((List<RuleName>) result).size());
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    void deleteRuleNameById() {
        ruleNameService.deleteRuleNameById(1);
        verify(ruleNameRepository, times(1)).deleteById(any(Integer.class));
    }

    @Test
    void existsById() {
        when(ruleNameRepository.existsById(any(Integer.class))).thenReturn(true);

        boolean result = ruleNameService.existsById(1);

        assertTrue(result);
        verify(ruleNameRepository, times(1)).existsById(any(Integer.class));
    }
}