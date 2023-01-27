package com.github.tasklist.backendspringboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tasklist.backendspringboot.entity.Priority;
import com.github.tasklist.backendspringboot.repo.PriorityRepository;

@RestController
@RequestMapping("/priority")
public class PriorityController {
    
    private final PriorityRepository priorityRepository;
    
    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }
    
    @GetMapping("/test")
    public List<Priority> test() {
        
        return priorityRepository.findAll();
        
    }

    
}
