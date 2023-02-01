package com.github.tasklist.backendspringboot.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.tasklist.backendspringboot.entity.Stat;
import com.github.tasklist.backendspringboot.repo.StatRepository;

@Service
@Transactional
public class StatService {

    private final StatRepository statRepository;
    
    public StatService(StatRepository statRepository) {
        this.statRepository = statRepository;
    }
    
    public Stat findById(Long id) {
        return statRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
