package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.trainers.bo.Pokemon;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface PokemonTypeService {
    List<PokemonType> listPokemonsTypes();
    PokemonType getPokemonType(int id);
    List<PokemonType> getPokemonType(List<Pokemon> pokemons);
    void setRestTemplate(RestTemplate restTemplate);
}
