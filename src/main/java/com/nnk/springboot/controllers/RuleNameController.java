package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Rule name controller.
 */
@Controller
public class RuleNameController {
    private final RuleNameService ruleNameService;

    /**
     * Instantiates a new Rule name controller.
     *
     * @param ruleNameService the rule name service
     */
    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameService.findAllRuleNames());
        return "ruleName/list";
    }

    /**
     * Add rule form string.
     *
     * @param bid the bid
     * @return the string
     */
    @GetMapping("/ruleName/add")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     * Validate string.
     *
     * @param ruleName the rule name
     * @param result   the result
     * @param model    the model
     * @return the string
     */
    @PostMapping("/ruleName/validate")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "ruleName/add";
        }

        ruleNameService.saveRuleName(ruleName);
        model.addAttribute("ruleNames", ruleNameService.findAllRuleNames());
        return "ruleName/list";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/ruleName/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ruleName", ruleNameService.findRuleNameById(id));
        return "ruleName/update";
    }

    /**
     * Update rule name string.
     *
     * @param id       the id
     * @param ruleName the rule name
     * @param result   the result
     * @param model    the model
     * @return the string
     */
    @PostMapping("/ruleName/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }

        ruleName.setId(id);
        ruleNameService.saveRuleName(ruleName);
        model.addAttribute("ruleNames", ruleNameService.findAllRuleNames());

        return "redirect:/ruleName/list";
    }

    /**
     * Delete rule name string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/ruleName/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE')")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRuleNameById(id);
        return "redirect:/ruleName/list";
    }
}
