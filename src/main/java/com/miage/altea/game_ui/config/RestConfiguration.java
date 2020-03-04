package com.miage.altea.game_ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestConfiguration {

    private String username;

    private String password;

    @Bean
    RestTemplate trainerApiRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        BasicAuthenticationInterceptor interceptor = new BasicAuthenticationInterceptor(username, password);
        restTemplate.setInterceptors(Arrays.asList(interceptor));

        return restTemplate;
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Value("${trainer.service.username}")
    void setUsername(String username) {
        this.username = username;
    }

    @Value("${trainer.service.password}")
    void setPassword(String password) {
        this.password = password;
    }

}
