package com.plannerapp.model.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {

    private Long id;

    private String description;

    private LocalDate dueDate;

    private PriorityModel priority;

    private UserModel user;
}
