package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public Rating findRatingById(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }

    public Iterable<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    public void deleteRatingById(Integer id) {
        ratingRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return ratingRepository.existsById(id);
    }
}
