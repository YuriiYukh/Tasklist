package com.github.tasklist.backendspringboot.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.tasklist.backendspringboot.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT p FROM Task p where " + "(:text is null or lower(p.title) like lower(concat('%', :text,'%'))) and"
            + "(:completed is null or p.completed=:completed) and "
            + "(:priorityId is null or p.priority.id=:priorityId) and "
            + "(:categoryId is null or p.category.id=:categoryId)")
    Page<Task> findByParams(@Param("text") String text, 
            @Param("completed") Integer completed,
            @Param("priorityId") Long priorityId, 
            @Param("categoryId") Long categoryId, 
            PageRequest pageRequest
            );

}
