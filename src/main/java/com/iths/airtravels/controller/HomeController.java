package com.iths.airtravels.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(value = "/403")
    public String accessDenied(Model model) {
        return "403";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }


}
