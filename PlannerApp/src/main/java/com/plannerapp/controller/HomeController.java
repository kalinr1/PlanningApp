package com.plannerapp.controller;


import com.plannerapp.model.LoggedUser;
import com.plannerapp.model.models.TaskModel;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController extends BaseController{

    private final TaskService taskService;
    private final LoggedUser loggedUser;

    public HomeController(TaskService taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public ModelAndView getIndex (){
        if (!loggedUser.isLogged()){
            return super.view("index");
        }

        return getHome(new ModelAndView());
    }


    @GetMapping("/home")
    public ModelAndView getHome (ModelAndView modelAndView){

        if (!loggedUser.isLogged()){
            return super.redirect("/");
        }

        List<TaskModel> allAvailableTasksModels = taskService.findAllAvailableTasks_Models();
        List<TaskModel> allUserTasksModels = taskService.findAllUserTasks_Models();

        modelAndView.addObject("allAvailableTasksModels", allAvailableTasksModels);
        modelAndView.addObject("allUserTasksModels", allUserTasksModels);

        return super.view("home", modelAndView);
    }

}
