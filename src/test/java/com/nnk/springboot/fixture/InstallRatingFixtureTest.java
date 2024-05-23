package com.nnk.springboot.fixture;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class InstallRatingFixtureTest {

    @Mock
    RatingService ratingService;

    @InjectMocks
    InstallRatingFixture installRatingFixture;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute() {
        installRatingFixture.execute();
        verify(ratingService, times(2)).saveRating(any(Rating.class));
    }
}