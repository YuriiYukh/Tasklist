package com.github.tasklist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.tasklist.backendspringboot.entity.Stat;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {

}
