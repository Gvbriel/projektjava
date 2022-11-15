package com.mrdp.taskcreator.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private boolean isFinished;
//    private UserDto owner;
}
