package com.abdulquadir.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String getHomePage(){
        System.out.println("what is this thing???");
        return "index";
    }

}
