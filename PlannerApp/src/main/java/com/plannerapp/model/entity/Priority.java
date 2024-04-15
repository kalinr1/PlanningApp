package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table (name = "priorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Priority extends BaseEntity{

    @Enumerated (EnumType.ORDINAL)
    @Column(unique = true, nullable = false)
    private PriorityEnum priorityName;

    @Column(nullable = false)
    private String description;

    @OneToMany (mappedBy = "priority", fetch = FetchType.EAGER)
    private List<Task> tasks;
}
