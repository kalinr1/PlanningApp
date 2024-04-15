package com.exam1.controller;


import com.exam1.model.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController{

    private final LoggedUser loggedUser;

    public HomeController(LoggedUser loggedUser) {
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


        return super.view("home", modelAndView);
    }

}
