package com.github.tasklist.backendspringboot.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class TaskSearchValues {
    
    private String title;
    private Long categoryId;
    private Long priorityId;
    private Integer completed;

}
