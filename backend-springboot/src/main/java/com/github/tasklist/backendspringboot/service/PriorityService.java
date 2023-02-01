package com.github.tasklist.backendspringboot.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.tasklist.backendspringboot.entity.Priority;
import com.github.tasklist.backendspringboot.repo.PriorityRepository;

@Service
@Transactional
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }

    public Priority add(Priority priority) {
        return priorityRepository.save(priority);
    }

    public Priority update(Priority priority) {
        return priorityRepository.save(priority);
    }

    public List<Priority> findByTitle(String title) {
        return priorityRepository.findByTitle(title);
    }

    public Priority findById(Long id) {
        return priorityRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Priority> findAllByOrderByIdAsc() {
        return priorityRepository.findAllByOrderByIdAsc();
    }

    public void deleteById(Long id) {
        priorityRepository.deleteById(id);
    }
}
