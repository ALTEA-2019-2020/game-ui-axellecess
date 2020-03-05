package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private RestTemplate restTemplate;
    private String trainerServiceUrl;

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainerList;

        trainerList = Arrays.asList(restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class));
        return trainerList;
    }

    @Override
    public List<Trainer> getOtherTrainers(String name) {
        List<Trainer> trainerList = Arrays.asList(restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class));

        List<Trainer> trainers = new ArrayList<>();
        for(Trainer t : trainerList){
            if(!t.getName().equals(name)){
                trainers.add(t);
            }
        }
        return trainers;
    }

    @Override
    public Trainer getTrainer(String name){
        Trainer trainer = restTemplate.getForObject(trainerServiceUrl + "/trainers/" + name, Trainer.class);
        return trainer;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setTrainerServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl = trainerServiceUrl;
    }
}
