package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Rating service.
 */
@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    /**
     * Instantiates a new Rating service.
     *
     * @param ratingRepository the rating repository
     */
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Save rating.
     *
     * @param rating the rating
     */
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    /**
     * Find rating by id rating.
     *
     * @param id the id
     * @return the rating
     */
    public Rating findRatingById(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }

    /**
     * Find all ratings list.
     *
     * @return the list
     */
    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    /**
     * Delete rating by id.
     *
     * @param id the id
     */
    public void deleteRatingById(Integer id) {
        ratingRepository.deleteById(id);
    }

    /**
     * Update rating.
     *
     * @param rating the rating
     */
    public void updateRating(Rating rating) {
        ratingRepository.save(rating);
    }
}
