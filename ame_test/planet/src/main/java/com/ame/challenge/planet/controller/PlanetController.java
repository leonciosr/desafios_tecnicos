package com.ame.challenge.planet.controller;

import com.ame.challenge.planet.exception.SwapiException;
import com.ame.challenge.planet.model.Planet;
import com.ame.challenge.planet.service.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ame/planet")
public class PlanetController {

    private final PlanetService planetService;

    PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    /**
     * Adicionar um planeta
     * @param planet
     * @return
     */
    @PostMapping
    public ResponseEntity<Planet> create(@Valid @RequestBody Planet planet) {
        try {
            Planet savedPlanet = this.planetService.create(planet);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPlanet.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (SwapiException e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Listar planetas do banco de dados
     * @return
     */
    @GetMapping
    public List<Planet> findAll() {
        return this.planetService.findAll();
    }

    /**
     * Listar planetas da API do Star Wars
     * @return the list
     */
    @GetMapping("/swapi")
    public List<Planet> getAllPlanetFromApiStarWars() {
        return this.planetService.findAllFromSwapi();
    }

    /**
     * Buscar por ID no banco de dados
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Planet> findById(@PathVariable long id){
        return this.planetService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Buscar por nome no banco de dados
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Planet> findByName(@PathVariable String name){
        return this.planetService.findByName(name)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remover planeta
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return this.planetService.findById(id)
                .map(record -> {
                    this.planetService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
