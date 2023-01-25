package com.github.tasklist.backendspringboot.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@Table(name = "category")
public class Category {
    private Long id;
    private String title;
    private Long completedCount;
    private Long uncompletedCount;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    
    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    
    @Basic
    @Column(name = "completed_count")
    public Long getCompletedCount() {
        return completedCount;
    }
    
    @Basic
    @Column(name = "uncompleted_count")
    public Long getUncompletedCount() {
        return uncompletedCount;
    }
    
    
}
