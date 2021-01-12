package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Users;
import com.iths.airtravels.service.IUsersService;
import com.iths.airtravels.service.UsersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    private IUsersService usersService;

    public HomeController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "403";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "login";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        model.addAttribute("currentUser", getUserData());
        return "profile";
    }

    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("currentUser", getUserData());
        return "register";

    }

    @PostMapping("/register")
    public String toRegister(@RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "re_user_password") String rePassword,
                             @RequestParam(name = "user_fullname") String fullName){

        if(password.equals(rePassword)){

            Users newUser = new Users();
            newUser.setFullName(fullName);
            newUser.setPassword(password);
            newUser.setEmail(email);

            if(usersService.creatUser(newUser)!=null){
                return "redirect:/register?success";
            }

        }
        return "redirect:/register?error";
    }

    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            Users myUser = usersService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }

}
