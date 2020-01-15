package com.ame.challenge.planet.service;

import com.ame.challenge.planet.model.Planet;

import java.util.Optional;

public interface IPlanetService {

    Optional<Planet> findById(long id);

    Optional<Planet> findByName(String name);

    void deleteById(long id);
}
