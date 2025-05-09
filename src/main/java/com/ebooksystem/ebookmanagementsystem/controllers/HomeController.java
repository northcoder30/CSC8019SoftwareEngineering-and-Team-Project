package com.ebooksystem.ebookmanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller of the home of the api. Not utilised in the frontend, so there's a little easter egg. Says Hello World!
 * Every programmer starts the same way.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Test");

        return "index";
    }
}