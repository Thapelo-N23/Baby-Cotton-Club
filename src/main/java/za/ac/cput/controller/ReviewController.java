/*
Category.java
ReviewController POJO class
Author: Olwethu Nene(230277845)
Date: 25 May 2025
 */

package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.service.impl.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private ReviewService service;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.service = reviewService;
    }
    @PostMapping("/create")
    public Review createReview(@RequestBody Review review){
        return service.create(review);
    }
    @GetMapping("/read")
    public Review readReview(@PathVariable Integer id){
        return service.read(id);

    }
    @PutMapping("/update")
    public Review updateReview(@RequestBody Review review){
        return service.update(review);
    }
    @GetMapping("/getall")
    public List<Review> getAllReviews(){
        return service.getAll();
    }

}
