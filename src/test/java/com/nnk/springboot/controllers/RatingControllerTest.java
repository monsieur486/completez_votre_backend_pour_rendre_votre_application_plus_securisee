package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RatingControllerTest {
    @Mock
    RatingService ratingService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @Mock
    Principal principal;

    @InjectMocks
    RatingController ratingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void home() {
        when(ratingService.findAllRatings()).thenReturn(Collections.emptyList());
        String result = ratingController.home(model);
        assertEquals("rating/list", result);
        verify(ratingService, times(1)).findAllRatings();
    }

    @Test
    void addRatingForm() {
        Rating rating = new Rating();
        String result = ratingController.addRatingForm(rating);
        assertEquals("rating/add", result);
    }

    @Test
    void validate() {
        Rating rating = new Rating();
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = ratingController.validate(rating, bindingResult, model);
        assertEquals("redirect:/rating/list", result);
    }

    @Test
    void showUpdateForm() {
        Rating rating = new Rating();
        when(ratingService.findRatingById(any(Integer.class))).thenReturn(rating);
        String result = ratingController.showUpdateForm(1, model);
        assertEquals("rating/update", result);
        verify(ratingService, times(1)).findRatingById(any(Integer.class));
    }

    @Test
    void updateRating() {
        Rating rating = new Rating();
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = ratingController.updateRating(1, rating, bindingResult, model);
        assertEquals("redirect:/rating/list", result);
    }

    @Test
    void deleteRating() {
        String result = ratingController.deleteRating(1, model);
        assertEquals("redirect:/rating/list", result);
        verify(ratingService, times(1)).deleteRatingById(any(Integer.class));
    }

}