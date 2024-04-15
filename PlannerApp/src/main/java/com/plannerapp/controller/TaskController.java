package com.plannerapp.controller;

import com.plannerapp.model.LoggedUser;
import com.plannerapp.model.dto.TaskAddDTO;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskController extends BaseController{

    private final TaskService taskService;
    private final LoggedUser loggedUser;

    public TaskController(TaskService taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/add")
    public ModelAndView getAdd() {

        if (!this.loggedUser.isLogged()) {
            return super.redirect("/home");
        }

        return super.view("task-add");
    }

    @PostMapping("/add")
    public ModelAndView postAdd(@Validated TaskAddDTO taskAddDTO, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("taskAddDTO", taskAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.taskAddDTO", result);

            return super.redirect("/tasks/add");
        }

        taskService.addTask(taskAddDTO);

        return super.redirect("/home");
    }

    @GetMapping("/assign-to-logged-user/{taskId}")
    public ModelAndView getAssignTaskToLoggedUser(@PathVariable Long taskId){

        if (!this.loggedUser.isLogged()) {
            return super.redirect("/home");
        }

        taskService.assignTaskToLoggedUser(taskId);

        return super.redirect("/home");
    }

    @GetMapping("/done-remove/{taskId}")
    public ModelAndView getDoneAndRemoveTask(@PathVariable Long taskId){
        if (!this.loggedUser.isLogged()) {
            return super.redirect("/home");
        }

        taskService.finishTaskAndRemove(taskId);

        return super.redirect("/home");
    }

    @GetMapping("/return/{taskId}")
    public ModelAndView getReturnTask(@PathVariable Long taskId){

        if (!this.loggedUser.isLogged()) {
            return super.redirect("/home");
        }

        taskService.returnTask(taskId);

        return super.redirect("/home");
    }




    @ModelAttribute
    public TaskAddDTO initTaskAddDTO (){
        return new TaskAddDTO();
    }

}
