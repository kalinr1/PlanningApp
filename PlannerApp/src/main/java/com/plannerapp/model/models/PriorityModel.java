package com.plannerapp.model.models;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.enums.PriorityEnum;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriorityModel {

    private Long id;

    private PriorityEnum priorityName;

    private String description;

    private List<TaskModel> tasks;
}
