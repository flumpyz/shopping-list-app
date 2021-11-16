package com.example.shoppinglistapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(path = "/index")
    public String showForm() {
        return "index";
    }

}
