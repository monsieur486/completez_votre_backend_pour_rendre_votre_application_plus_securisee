package com.nnk.springboot.fixture;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The type Install rule name fixture.
 */
@Component
@Slf4j
public class InstallRuleNameFixture {
    private final RuleNameService ruleNameService;

    /**
     * Instantiates a new Install rule name fixture.
     *
     * @param ruleNameService the rule name service
     */
    public InstallRuleNameFixture(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    /**
     * Execute.
     */
    public void execute() {
        log.warn("Creating fixtures for RuleName");
        ruleNameService.saveRuleName(new RuleName("Rule Name Test", "Description Test", "Json Test", "Template Test", "SQL Test", "SQL Part Test"));
        ruleNameService.saveRuleName(new RuleName("Rule Name Test 2", "Description Test 2", "Json Test 2", "Template Test 2", "SQL Test 2", "SQL Part Test 2"));
    }

}
