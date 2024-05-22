package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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
public class CurveController {

    private final CurvePointService curvePointService;

    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }


    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "curvePoint/add";
        }

        curvePointService.saveCurvePoint(curvePoint);

        model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "curvePoint/update";
        }

        curvePointService.updateCurvePoint(curvePoint);

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointService.deleteCurvePointById(id);
        model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
        return "redirect:/curvePoint/list";
    }
}
