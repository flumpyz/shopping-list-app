package com.example.shoppinglistapp.controller;

import com.example.shoppinglistapp.database.DBException;
import com.example.shoppinglistapp.model.Note;
import com.example.shoppinglistapp.service.ShoppingService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {

    private final ShoppingService shoppingService;

    public MainController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping(path = "/index")
    public String showForm(@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionKey,
                           Model model) throws DBException {
        List<Note> notes = shoppingService.getAllNotes(sessionKey);
        System.out.println(sessionKey);
        model.addAttribute("notes", notes);

        return "index";
    }

    @PostMapping(path = "/index")
    public RedirectView addModel(@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionKey,
                                 @RequestParam HashMap<String, String> formData) throws DBException {
        String text = formData.get("inputItem");
        shoppingService.create(text, false, sessionKey);

        return new RedirectView("index");
    }

    @PostMapping(path = "/delete")
    public RedirectView deleteModel(@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionKey,
                                    @RequestBody HashMap<String, String> body) throws DBException {
        Long number = Long.parseLong(body.get("purchaseId"));
        shoppingService.delete(number);
        return new RedirectView("index");
    }

}
