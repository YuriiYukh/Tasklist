package com.github.tasklist.backendspringboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tasklist.backendspringboot.entity.Stat;
import com.github.tasklist.backendspringboot.repo.StatRepository;

@RestController
@RequestMapping("/stat")
public class StatController {

    private final StatRepository statRepository;
    
    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }
    
    @GetMapping("")
    public List<Stat> showStat() {
        return statRepository.findAll();
    }
}
