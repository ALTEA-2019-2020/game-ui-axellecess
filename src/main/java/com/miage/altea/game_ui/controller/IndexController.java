package com.miage.altea.game_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/registerTrainer")
    ModelAndView registerNewTrainer(@RequestBody String trainerName){
        var modelAndView = new ModelAndView("register");
        modelAndView.addObject("name", trainerName);
        return modelAndView;
    }

}
