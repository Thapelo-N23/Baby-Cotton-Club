/*
Category.java
CategoryService POJO class
Author: Olwethu Nene(230277845)
Date: 25 May 2025
 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
