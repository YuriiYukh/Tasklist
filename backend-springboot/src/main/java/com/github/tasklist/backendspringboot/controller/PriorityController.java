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

import com.github.tasklist.backendspringboot.entity.Priority;
import com.github.tasklist.backendspringboot.repo.PriorityRepository;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private final PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/all")
    public List<Priority> findAll() {
        return priorityRepository.findAllByOrderByIdAsc();
    }

    @PostMapping("/post")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("title must be filled", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("color must be filled", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {

        if (priority.getId() == null && priority.getId() == 0) {
            return new ResponseEntity("id must be present", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("title must be filled", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("color must be filled", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(priorityRepository.findById(id).orElseThrow(NoSuchElementException::new));

        } catch (NoSuchElementException e) {
            return new ResponseEntity("No element found with " + id + " id found", HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Priority> deleteById(@PathVariable Long id){
        try {
            priorityRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
            
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("No element found with " + id + " id found", HttpStatus.NOT_FOUND);
        }
    }
}
