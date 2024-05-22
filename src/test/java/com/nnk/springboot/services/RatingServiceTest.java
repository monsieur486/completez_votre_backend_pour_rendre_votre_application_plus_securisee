package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RatingServiceTest {

    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    RatingService ratingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findRatingById() {
        Rating rating = new Rating();
        rating.setId(1);
        when(ratingRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(rating));

        Rating result = ratingService.findRatingById(1);

        assertEquals(1, result.getId());
        verify(ratingRepository, times(1)).findById(any(Integer.class));
    }

    @Test
    void saveRating() {
        Rating rating = new Rating();
        ratingService.saveRating(rating);
        verify(ratingRepository, times(1)).save(any(Rating.class));
    }

    @Test
    void deleteRatingById() {
        ratingService.deleteRatingById(1);
        verify(ratingRepository, times(1)).deleteById(any(Integer.class));
    }

    @Test
    void findAllRatings() {
        when(ratingRepository.findAll()).thenReturn(Collections.emptyList());

        List<Rating> result = ratingService.findAllRatings();

        assertEquals(0, result.size());
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void updateRating() {
        Rating rating = new Rating();
        ratingService.updateRating(rating);
        verify(ratingRepository, times(1)).save(any(Rating.class));
    }
}