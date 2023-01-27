package com.github.tasklist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.tasklist.backendspringboot.entity.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

}
