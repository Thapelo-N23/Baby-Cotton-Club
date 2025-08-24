/*
CategoryController.java
Author: Olwethu Nene(230277845)
Date: 25 May 2025
*/
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Category;
import za.ac.cput.service.impl.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.service = categoryService;
    }

    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category){
        return service.create(category);
    }

    @GetMapping("/read/{id}")
    public Category readCategory(@PathVariable ("id") Integer id){
        return service.read(id);
    }

    @PutMapping("/update")
    public Category updateCategory(@RequestBody Category category){
        return service.update(category);
    }

    @GetMapping("/getall")
    public List<Category> getAllCategories(){
        return service.getAll();
    }
}
