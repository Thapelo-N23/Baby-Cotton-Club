/*
Category.java
CategoryService POJO class
Author: Olwethu Nene(230277845)
Date: 25 May 2025
 */

package za.ac.cput.service;

import za.ac.cput.domain.Category;
import za.ac.cput.domain.Review;

import java.util.List;

public interface IReviewService extends IService <Review, Integer> {
    List<Review> getAll();
}
