package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    public void deleteRatingById(Integer id) {
        ratingRepository.deleteById(id);
    }

    public void updateRating(Rating rating) {
        ratingRepository.save(rating);
    }
}
