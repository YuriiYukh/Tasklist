package com.github.tasklist.backendspringboot.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Table(name = "stat")
@EqualsAndHashCode
public class Stat {
    private Long id;
    private Long uncompletedTotal;
    private Long completedTotal;
    
    @Id
    @Column(name = "id")
    public Long gedId() {
        return id;
    }
    
    @Basic
    @Column(name = "uncompleted_total")
    public Long getUncompletedTotal() {
        return uncompletedTotal;
    }
    
    @Basic
    @Column(name = "completed_total")
    public Long getCompletedTotal() {
        return completedTotal;
    }
}
