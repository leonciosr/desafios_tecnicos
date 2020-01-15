package com.ame.challenge.planet.service;

import com.ame.challenge.planet.exception.SwapiException;
import com.ame.challenge.planet.model.Planet;
import com.ame.challenge.planet.model.SwapiPlanet;
import com.ame.challenge.planet.model.SwapiPlanetResponse;
import com.ame.challenge.planet.repository.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetService implements IPlanetService {

    private final PlanetRepository planetRepository;

    private final SwapiPlanetService swapiPlanetService;

    public PlanetService(PlanetRepository planetRepository, SwapiPlanetService swapiPlanetService) {
        this.planetRepository = planetRepository;
        this.swapiPlanetService = swapiPlanetService;
    }

    /**
     * Busca de todos os Planets cadastrados
     *
     * @return List<Planet> Planets
     */
    public List<Planet> findAll() {
        return this.planetRepository.findAll();
    }

    /**
     * Busca de todos os planetas cadastrados na API do Star Wars
     *
     * @return List<Planet> Planets
     */
    public List<Planet> findAllFromSwapi() {
        List<Planet> planets = new ArrayList<>();
        SwapiPlanetResponse response = this.swapiPlanetService.findAll();

        for (SwapiPlanet swapiPlanet : response.getResults()) {
            Planet planet = new Planet();
            planet.setNome(swapiPlanet.getName());
            planet.setTerreno(swapiPlanet.getTerrain());
            planet.setClima(swapiPlanet.getClimate());
            planet.setQtdeAparicoesFilmes(swapiPlanet.getFilms().size());
            planets.add(planet);
        }

        return planets;
    }

    /**
     * Busca de Planet pelo ID
     *
     * @param id
     * @return Optional<Planet> Planet
     */
    public Optional<Planet> findById(long id) {
        return this.planetRepository.findById(id);
    }

    /**
     * Busca de Planet pelo Nome
     *
     * @param nome
     * @return
     */
    public Optional<Planet> findByName(String nome) {
        return this.planetRepository.findByName(nome);
    }


    /**
     * Remove do banco de dados o planeta recebido por parâmetro
     *
     * @param Planet
     */
    public void delete(Planet Planet) {
        this.planetRepository.delete(Planet);
    }

    @Override
    public void deleteById(long id) {
        this.planetRepository.deleteById(id);
    }

    /**
     * Adicionar um Planet (com nome, clima e terreno)
     *
     * @param planet
     * @return Planet Planet
     */
    public Planet create(Planet planet) throws SwapiException {
        planet.setQtdeAparicoesFilmes(this.getNumberOfTimesThatAppearsInMovies(planet.getNome()));
        return this.planetRepository.save(planet);
    }

    /**
     * Retorna o Total de aparicoes do Planet nos filmes
     *
     * @param nome
     * @return
     * @throws SwapiException
     */
    public Integer getNumberOfTimesThatAppearsInMovies(String nome) throws SwapiException {

        Optional<SwapiPlanet> swapiPlanet = this.swapiPlanetService.findByName(nome);
        return swapiPlanet.map(item -> item.getFilms().size()).orElse(0);
    }
}
