package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class TrainerController {

    private TrainerService trainerService;
    private PokemonTypeService pokemonTypeService;

    @GetMapping("/trainers")
    public ModelAndView trainers(Principal principal){
        // Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Trainer> trainerList = trainerService.getOtherTrainers(principal.getName());

        var modelAndView = new ModelAndView("trainers");
        modelAndView.addObject("trainers", trainerList);

        for(Trainer t : trainerList){
            t.setPokemonTypes(pokemonTypeService.getPokemonType(t.getTeam()));
        }

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

    @GetMapping("/profile")
    public ModelAndView getProfile(Principal principal){
        // Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Trainer trainer = trainerService.getTrainer(principal.getName());
        trainer.setPokemonTypes(pokemonTypeService.getPokemonType(trainer.getTeam()));

        var modelAndView = new ModelAndView("trainer");
        modelAndView.addObject("trainer", trainer);

        return modelAndView;
    }


    @GetMapping("/fight/{opponent}")
    public ModelAndView fight(Principal principal, @PathVariable String opponent){
        var modelAndView = new ModelAndView("fight");

        modelAndView.addObject("trainerName", principal.getName());
        modelAndView.addObject("opponentName", opponent);

        return modelAndView;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService){
        this.pokemonTypeService = pokemonTypeService;
    }


}
