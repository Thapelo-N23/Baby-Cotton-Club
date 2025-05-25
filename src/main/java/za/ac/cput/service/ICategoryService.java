/*
Category.java
ICategoryService POJO class
Author: Olwethu Nene(230277845)
Date: 25 May 2025
 */

package za.ac.cput.service;

import za.ac.cput.domain.Category;

import java.util.List;

public interface ICategoryService extends IService<Category, Integer> {
    List<Category> getAll();

}
