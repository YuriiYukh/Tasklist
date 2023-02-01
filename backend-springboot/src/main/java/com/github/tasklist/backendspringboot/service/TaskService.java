package com.github.tasklist.backendspringboot.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.github.tasklist.backendspringboot.entity.Task;
import com.github.tasklist.backendspringboot.repo.TaskRepository;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task add(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Task task) {
        return taskRepository.save(task);
    }

    public Page<Task> findByParams(String text, Integer completed, Long priorityId, Long categoryId, PageRequest paging){
        return taskRepository.findByParams(text, completed, priorityId, categoryId, paging);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
