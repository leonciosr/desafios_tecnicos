package com.ame.challenge.planet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SwapiPlanetResponse {

    private int count;
    private String next;
    private String previous;
    List<SwapiPlanet> results;
}
