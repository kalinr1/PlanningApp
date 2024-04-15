package com.plannerapp.model.dto;



import com.plannerapp.model.enums.PriorityEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskAddDTO {

    @Size (min = 2, max = 50, message = "Content length must be between 2 and 50 characters!")
    private String description;

    @NotNull (message = "The due date can not be empty")
    @FutureOrPresent
    private LocalDate dueDate;

    @NotNull (message = "You must select a priority!")
    private PriorityEnum priorityName;

}
