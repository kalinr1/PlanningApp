package com.exam1.controller;

import com.exam1.model.LoggedUser;
import com.exam1.model.dto.UserLoginDTO;
import com.exam1.model.dto.UserRegisterDTO;
import com.exam1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{

    private final UserService userService;
    private final LoggedUser loggedUser;

    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {

        if (this.loggedUser.isLogged()) {
            return super.redirect("/home");
        }

        return super.view("login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@Validated UserLoginDTO userLoginDTO, BindingResult result, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginDTO", userLoginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", result);

            return super.redirect("/users/login");
        }

        userService.loginUser(userLoginDTO);

        return super.redirect("/home");
    }

    @GetMapping("/register")
    public ModelAndView getRegister() {

        if (this.loggedUser.isLogged()) {
            return super.redirect("/home");
        }

        return super.view("register");
    }

    @PostMapping("/register")
    public ModelAndView postRegister(@Validated UserRegisterDTO userRegisterDTO, BindingResult result, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterDTO", userRegisterDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", result);

            return super.redirect("/users/register");
        }

        userService.registerUser(userRegisterDTO);

        return super.redirect("/home");
    }

    @GetMapping("/logout")
    public ModelAndView getLogout(){

        if (!this.loggedUser.isLogged()) {
            return super.redirect("/");
        }

        userService.logoutUser();
        return super.redirect("/");
    }

    @ModelAttribute
    public UserLoginDTO initLoginDTO (){
        return new UserLoginDTO();
    }

    @ModelAttribute
    public UserRegisterDTO initRegisterDTO (){
        return new UserRegisterDTO();
    }

}
