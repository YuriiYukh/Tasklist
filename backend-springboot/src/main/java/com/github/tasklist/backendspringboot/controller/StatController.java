package com.github.tasklist.backendspringboot.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tasklist.backendspringboot.entity.Stat;
import com.github.tasklist.backendspringboot.repo.StatRepository;

@RestController
@RequestMapping("/stat")
public class StatController {

    private static final Long DEFAULT_STAT_ID = 1l;
    private final StatRepository statRepository;
    
    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }
    
    @GetMapping("")
    public ResponseEntity<Stat> showStat() {
        
        try {
        return ResponseEntity.ok(statRepository.findById(DEFAULT_STAT_ID).orElseThrow(NoSuchElementException::new));
        
        } catch(NoSuchElementException e) {
            return new ResponseEntity("No stat found", HttpStatus.NOT_FOUND);
        }
    }
}
