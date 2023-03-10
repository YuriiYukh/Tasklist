package com.github.tasklist.backendspringboot.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tasklist.backendspringboot.entity.Stat;
import com.github.tasklist.backendspringboot.service.StatService;

@RestController
@RequestMapping("/stat")
public class StatController {

    private static final Long DEFAULT_STAT_ID = 1l;
    private final StatService statService;
    
    public StatController(StatService statService) {
        this.statService = statService;
    }
    
    @GetMapping
    public ResponseEntity<Stat> showStat() {
        
        try {
        return ResponseEntity.ok(statService.findById(DEFAULT_STAT_ID));
        
        } catch(NoSuchElementException e) {
            return new ResponseEntity("No stat found", HttpStatus.NOT_FOUND);
        }
    }
}
