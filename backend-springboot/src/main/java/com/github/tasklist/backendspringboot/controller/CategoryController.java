package com.github.tasklist.backendspringboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tasklist.backendspringboot.entity.Category;
import com.github.tasklist.backendspringboot.repo.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @GetMapping("/test")
    public List<Category> test() {
        
        return categoryRepository.findAll();
    }
    
    @PostMapping("/post")
    public Category add(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
