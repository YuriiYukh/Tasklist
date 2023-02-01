package com.github.tasklist.backendspringboot.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tasklist.backendspringboot.entity.Category;
import com.github.tasklist.backendspringboot.search.CategorySearchValues;
import com.github.tasklist.backendspringboot.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> findByTitle(@RequestBody CategorySearchValues categorySearchValues) {

        return ResponseEntity.ok(categoryService.findByTitle(categorySearchValues.getTitle()));
    }

    @GetMapping("/all")
    public List<Category> findAll() {

        return categoryService.findByOrderByTitleDesc();
    }

    @PostMapping("/post")
    public ResponseEntity<Category> add(@RequestBody Category category) {

        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("title must be filled", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {

        if (category.getId() == null && category.getId() != 0) {
            return new ResponseEntity("id must be present", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("title must be filled", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.update(category));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(categoryService.findById(id));

        } catch (NoSuchElementException e) {
            return new ResponseEntity("No element found with " + id + " id found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("No element found with " + id + " id found", HttpStatus.NOT_FOUND);
        }
    }
}
