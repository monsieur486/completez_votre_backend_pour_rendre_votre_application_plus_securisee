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

/**
 * The type Curve controller.
 */
@Controller
public class CurveController {

    private final CurvePointService curvePointService;

    /**
     * Instantiates a new Curve controller.
     *
     * @param curvePointService the curve point service
     */
    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }


    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "curvePoint/list";
    }

    /**
     * Add bid form string.
     *
     * @param bid the bid
     * @return the string
     */
    @GetMapping("/curvePoint/add")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    /**
     * Validate string.
     *
     * @param curvePoint the curve point
     * @param result     the result
     * @param model      the model
     * @return the string
     */
    @PostMapping("/curvePoint/validate")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "curvePoint/add";
        }

        curvePointService.saveCurvePoint(curvePoint);

        model.addAttribute("curvePoints", curvePointService.findAll());
        return "curvePoint/list";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/curvePoint/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    /**
     * Update bid string.
     *
     * @param id         the id
     * @param curvePoint the curve point
     * @param result     the result
     * @param model      the model
     * @return the string
     */
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

    /**
     * Delete bid string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/curvePoint/delete/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointService.deleteCurvePointById(id);
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }
}
