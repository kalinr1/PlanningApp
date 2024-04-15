package com.plannerapp.service;

import com.plannerapp.model.LoggedUser;
import com.plannerapp.model.dto.TaskAddDTO;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.models.PriorityModel;
import com.plannerapp.model.models.TaskModel;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;
    private final UserService userService;
    private final PriorityService priorityService;


    private final ModelMapper modelMapper;

    private final LoggedUser loggedUser;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, UserService userService, PriorityService priorityService, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.priorityService = priorityService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public void addTask (TaskAddDTO taskAddDTO){

        PriorityModel priorityModel = priorityService.findPriorityModelByName(taskAddDTO.getPriorityName());

        TaskModel taskModel = modelMapper.map(taskAddDTO, TaskModel.class);

        taskModel.setPriority(priorityModel);

        taskRepository.save(modelMapper.map(taskModel, Task.class));
    }

    public void assignTaskToLoggedUser (Long taskId){

        Task taskToAssign = taskRepository.findById(taskId).orElseThrow();

        User user = userRepository.findById(loggedUser.getId()).orElseThrow();

        taskToAssign.setUser(user);

        user.getAssignedTasks().add(taskToAssign);

        taskRepository.save(taskToAssign);
    }

    public void finishTaskAndRemove (Long taskId){

        Task taskToRemove = taskRepository.findById(taskId).orElseThrow();

        taskRepository.delete(taskToRemove);
    }

    public void returnTask (Long taskId){

        Task taskToReturn = taskRepository.findById(taskId).orElseThrow();

        taskToReturn.setUser(null);

        taskRepository.save(taskToReturn);
    }

    public List<TaskModel> findAllAvailableTasks_Models (){

        List<Task> allByUserIdNot = taskRepository.findAllByUserIsNull();

        return allByUserIdNot.stream()
                .map(task -> modelMapper.map(task, TaskModel.class))
                .collect(Collectors.toList());
    }

    public List<TaskModel> findAllUserTasks_Models (){

        List<Task> allByUserIdNot = taskRepository.findAllByUser_Id(loggedUser.getId());

        return allByUserIdNot.stream()
                .map(task -> modelMapper.map(task, TaskModel.class))
                .collect(Collectors.toList());
    }
}
