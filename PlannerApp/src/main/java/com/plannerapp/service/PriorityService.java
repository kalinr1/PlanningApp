package com.plannerapp.service;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.enums.PriorityEnum;
import com.plannerapp.model.models.PriorityModel;
import com.plannerapp.repo.PriorityRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriorityService {

    private final PriorityRepository priorityRepository;
    private final ModelMapper modelMapper;

    public PriorityService(PriorityRepository priorityRepository, ModelMapper modelMapper) {
        this.priorityRepository = priorityRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void postConstruct(){

        if (priorityRepository.count() == 0){

            List<PriorityModel> priorityModels = List.of(
                    PriorityModel.builder()
                            .priorityName(PriorityEnum.URGENT)
                            .description("An urgent problem that blocks the system use until the issue is resolved.")
                            .tasks(new ArrayList<>()).build(),

                    PriorityModel.builder()
                            .priorityName(PriorityEnum.IMPORTANT)
                            .description("A core functionality that your product is explicitly supposed to perform is compromised.")
                            .tasks(new ArrayList<>()).build(),

                    PriorityModel.builder()
                            .priorityName(PriorityEnum.LOW)
                            .description("Should be fixed if time permits but can be postponed.")
                            .tasks(new ArrayList<>()).build()
            );


            priorityRepository.saveAllAndFlush(priorityModels.stream()
                    .map(priorityModel -> modelMapper.map(priorityModel, Priority.class))
                    .toList());
        }
    }

    public PriorityModel findPriorityModelByName (PriorityEnum priorityEnum){
        return modelMapper.map(priorityRepository.findByPriorityName(priorityEnum), PriorityModel.class);
    }
}
