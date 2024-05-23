package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

/**
 * The type Rule name service.
 */
@Service
public class RuleNameService {

    private final RuleNameRepository ruleNameRepository;

    /**
     * Instantiates a new Rule name service.
     *
     * @param ruleNameRepository the rule name repository
     */
    public RuleNameService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    /**
     * Save rule name.
     *
     * @param ruleName the rule name
     */
    public void saveRuleName(RuleName ruleName) {
        ruleNameRepository.save(ruleName);
    }

    /**
     * Find rule name by id rule name.
     *
     * @param id the id
     * @return the rule name
     */
    public RuleName findRuleNameById(Integer id) {
        return ruleNameRepository.findById(id).orElse(null);
    }

    /**
     * Find all rule names iterable.
     *
     * @return the iterable
     */
    public Iterable<RuleName> findAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    /**
     * Delete rule name by id.
     *
     * @param id the id
     */
    public void deleteRuleNameById(Integer id) {
        ruleNameRepository.deleteById(id);
    }

    /**
     * Exists by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean existsById(Integer id) {
        return ruleNameRepository.existsById(id);
    }
}
