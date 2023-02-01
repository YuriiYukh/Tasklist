package com.github.tasklist.backendspringboot.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.tasklist.backendspringboot.entity.Category;
import com.github.tasklist.backendspringboot.repo.CategoryRepository;

@Service
@Transactional
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
    public Category add(Category category) {
        return categoryRepository.save(category);
    }
    
    public Category update(Category category) {
        return categoryRepository.save(category);
    }
    
    public List<Category> findByTitle(String title){
        return categoryRepository.findByTitle(title);
    }
    
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    
    public List<Category> findByOrderByTitleDesc() {
        return categoryRepository.findAllByOrderByTitleDesc();
    }
    
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
