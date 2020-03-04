package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;
    private String pokemonTypeServiceUrl;

    public List<PokemonType> listPokemonsTypes() {
        List<PokemonType> pokemonsTypes;

        HttpHeaders headers = new HttpHeaders();
        headers.setAcceptLanguageAsLocales(List.of(LocaleContextHolder.getLocale()));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        restTemplate.exchange(pokemonTypeServiceUrl + "/pokemon-types/", HttpMethod.GET, httpEntity, PokemonType[].class);

        pokemonsTypes = Arrays.asList(restTemplate.getForObject(pokemonTypeServiceUrl + "/pokemon-types/", PokemonType[].class));
        return pokemonsTypes;
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonTypeServiceUrl = pokemonServiceUrl;
    }
}