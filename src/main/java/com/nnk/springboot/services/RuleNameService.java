package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

@Service
public class RuleNameService {

    private final RuleNameRepository ruleNameRepository;

    public RuleNameService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    public void saveRuleName(RuleName ruleName) {
        ruleNameRepository.save(ruleName);
    }

    public RuleName findRuleNameById(Integer id) {
        return ruleNameRepository.findById(id).orElse(null);
    }

    public Iterable<RuleName> findAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    public void deleteRuleNameById(Integer id) {
        ruleNameRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return ruleNameRepository.existsById(id);
    }
}
