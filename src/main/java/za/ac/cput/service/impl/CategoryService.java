/*
Category.java
CategoryService POJO class
Author: Olwethu Nene(230277845)
Date: 25 May 2025
 */
package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Category;
import za.ac.cput.repository.CategoryRepository;
import za.ac.cput.service.ICategoryService;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private CategoryRepository repository;
    @Autowired
    CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    @Override
    public Category create(Category category) {
        return this.repository.save(category);
    }

    @Override
    public Category read(Integer integer) {
        return this.repository.findById(integer).orElse(null);
    }

    @Override
    public Category update(Category category) {
        return this.repository.save(category);
    }
    @Override
    public List<Category> getAll() {
        return this.repository.findAll();
    }
}
