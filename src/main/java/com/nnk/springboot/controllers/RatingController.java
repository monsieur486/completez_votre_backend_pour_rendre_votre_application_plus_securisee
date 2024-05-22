package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
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
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }


    @RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingService.findAllRatings());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            ratingService.saveRating(rating);
            model.addAttribute("ratings", ratingService.findAllRatings());
            return "redirect:/rating/list";
        }

        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Rating rating = ratingService.findRatingById(id);
        model.addAttribute("rating", rating);

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "rating/update";
        }

        rating.setId(id);
        ratingService.saveRating(rating);
        model.addAttribute("ratings", ratingService.findAllRatings());


        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE')")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {

        ratingService.deleteRatingById(id);
        model.addAttribute("ratings", ratingService.findAllRatings());

        return "redirect:/rating/list";
    }
}
