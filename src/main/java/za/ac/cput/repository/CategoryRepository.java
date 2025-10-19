/*
Category.java
CategoryRepository POJO class
Author: Olwethu Nene(230277845)
Date: 25 May 2025
 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer >{
    Category findByCategoryName(String categoryName);
}
