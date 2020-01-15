package com.ame.challenge.planet.service;

import com.ame.challenge.planet.model.SwapiPlanet;
import com.ame.challenge.planet.model.SwapiPlanetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@PropertySource("classpath:application.properties")
public class SwapiPlanetService {

    @Value("${swapi.base.url}")
    private String url;

    @Value("${swapi.resource}")
    private String resource;

    @Value("${swapi.resource.search}")
    private String resourceSearch;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * Retorna a URL de pesquisa de um planeta pelo nome em Star Wars API
     * @return String
     */
    private String getUrlPlanet() {
        return this.url + this.resource;
    }

    /**
     * Retorna a URL de pesquisa de um planeta pelo nome em Star Wars API
     * @return String
     */
    private String getUrlPlanetSearch() {
        return this.url + this.resource + this.resourceSearch;
    }

    public SwapiPlanetResponse findAll(){
        return restTemplate.getForObject(this.getUrlPlanet(), SwapiPlanetResponse.class);
    }

    public Optional<SwapiPlanet> findByName(String name){

        SwapiPlanetResponse response = restTemplate.getForObject(this.getUrlPlanetSearch() + name, SwapiPlanetResponse.class);
        return response.getResults().stream().findFirst();
    }
}