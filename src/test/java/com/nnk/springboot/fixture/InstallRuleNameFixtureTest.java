package com.nnk.springboot.fixture;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class InstallRuleNameFixtureTest {

    @Mock
    RuleNameService ruleNameService;

    @InjectMocks
    InstallRuleNameFixture installRuleNameFixture;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute() {
        installRuleNameFixture.execute();
        verify(ruleNameService, times(2)).saveRuleName(any(RuleName.class));
    }
}