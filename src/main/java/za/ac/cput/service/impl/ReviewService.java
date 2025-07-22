package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.ac.cput.domain.Review;

import za.ac.cput.repository.ReviewRepository;
import za.ac.cput.service.IReviewService;

import java.util.List;
@Service
public class ReviewService implements IReviewService {
    private ReviewRepository repository;
    @Autowired
    ReviewService (ReviewRepository repository){
        this.repository = repository;
    }

    @Override
    public Review create(Review review) {
        return this.repository.save(review);
    }

    @Override
    public Review read(Integer integer) {
        return this.repository.findById(integer).orElse(null);
    }

    @Override
    public Review update(Review review) {
        return this.repository.save(review);
    }
    @Override
    public List<Review> getAll() {
        return this.repository.findAll();
    }
}
