package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TrainerController {

    private TrainerService trainerService;

    @GetMapping("/trainers")
    public ModelAndView trainers(){
        List<Trainer> trainerList = trainerService.getAllTrainers();

        var modelAndView = new ModelAndView("trainers");
        modelAndView.addObject("trainers", trainerList);

        return modelAndView;
    }

    @GetMapping("/trainer/{name}")
    public ModelAndView trainer(@PathVariable String name){
        System.out.println("Find trainer with name = " + name);
        Trainer trainer = trainerService.getTrainer(name);

        var modelAndView = new ModelAndView("trainer");
        modelAndView.addObject("trainer", trainer);

        return modelAndView;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

}
