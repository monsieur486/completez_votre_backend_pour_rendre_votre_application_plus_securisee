package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RuleNameController {
    private final RuleNameService ruleNameService;

    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameService.findAllRuleNames());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

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

    @GetMapping("/ruleName/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ruleName", ruleNameService.findRuleNameById(id));
        return "ruleName/update";
    }

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

    @GetMapping("/ruleName/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE')")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRuleNameById(id);
        return "redirect:/ruleName/list";
    }
}
