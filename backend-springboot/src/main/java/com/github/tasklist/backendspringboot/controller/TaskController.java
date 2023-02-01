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

import com.github.tasklist.backendspringboot.entity.Task;
import com.github.tasklist.backendspringboot.repo.TaskRepository;
import com.github.tasklist.backendspringboot.search.TaskSearchValues;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @PostMapping("/search")
    public ResponseEntity<List<Task>> findByTitle(@RequestBody TaskSearchValues taskSearchValues) {
        
      String text = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;

      Integer completed = taskSearchValues.getCompleted() != null ?  taskSearchValues.getCompleted() : null;

      Long priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
      Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;

      return ResponseEntity.ok(taskRepository.findByParams(text, completed, priorityId, categoryId));
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {

        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("title must be filled", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskRepository.save(task));
    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {

        if (task.getId() == null && task.getId() != 0) {
            return new ResponseEntity("id must be present", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("title must be filled", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskRepository.save(task));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(taskRepository.findById(id).orElseThrow(NoSuchElementException::new));

        } catch (NoSuchElementException e) {
            return new ResponseEntity("No element found with " + id + " id found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> deleteById(@PathVariable Long id) {
        try {
            taskRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("No element found with " + id + " id found", HttpStatus.NOT_FOUND);
        }
    }
}