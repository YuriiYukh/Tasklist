package com.github.tasklist.backendspringboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @PostMapping("/post")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("title must be filled", HttpStatus.NOT_ACCEPTABLE);
        }
            return ResponseEntity.ok(priorityRepository.save(priority));
    }

    
}
