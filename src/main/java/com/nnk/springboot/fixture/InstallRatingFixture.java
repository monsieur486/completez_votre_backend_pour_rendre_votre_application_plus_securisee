package com.nnk.springboot.fixture;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The type Install rating fixture.
 */
@Component
@Slf4j
public class InstallRatingFixture {
    private final RatingService ratingService;

    /**
     * Instantiates a new Install rating fixture.
     *
     * @param ratingService the rating service
     */
    public InstallRatingFixture(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    /**
     * Execute.
     */
    public void execute() {
        log.warn("Creating fixtures for Rating");
        ratingService.saveRating(new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10));
        ratingService.saveRating(new Rating("Moodys Rating 2", "Sand PRating 2", "Fitch Rating 2", 20));
    }

}
