package com.ame.challenge.planet;

import com.ame.challenge.planet.model.Planet;
import com.ame.challenge.planet.model.SwapiPlanet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataBuilder {

    public static List<Planet> planets = buildPlanet();

    private static List<Planet> buildPlanet() {

        List<Planet> planets = new ArrayList<>();
        Planet planetAlderaan = new Planet();
        planetAlderaan.setNome("Alderaan");
        planetAlderaan.setClima("temperate");
        planetAlderaan.setTerreno("grasslands, mountains");

        Planet planetYavin = new Planet();
        planetYavin.setNome("Yavin IV");
        planetYavin.setClima("temperate, tropical");
        planetYavin.setTerreno("jungle, rainforests");

        Planet planetHoth = new Planet();
        planetHoth.setNome("Hoth");
        planetHoth.setClima("frozen");
        planetHoth.setTerreno("tundra, ice caves, mountain ranges");

        planets.add(planetAlderaan);
        planets.add(planetYavin);
        planets.add(planetHoth);

        return planets;
    }

    public static String getJsonCadastroPlanet(Planet Planet) {

        return "{  \n" +
                "   \"Planet\":{  \n" +
                "      \"nome\":\""+ Planet.getNome() +"\",\n" +
                "      \"clima\": \""+ Planet.getClima() +"\",\n" +
                "      \"terreno\": \""+ Planet.getTerreno() +"\"\n" +
                "   }\n" +
                "}";
    }

    public static String getJsonCadastroPlanetBadRequest(Planet Planet) {

        return "{  \n" +
                "   \"Planet\":{  \n" +
                "      \"nome\":\""+ Planet.getNome() +"\",\n" +
                "      \"climas\": \""+ Planet.getClima() +"\",\n" +
                "      \"terreno\": \""+ Planet.getTerreno() +"\"\n" +
                "   }\n" +
                "}";
    }

    public static SwapiPlanet getSwapiPlanetDeUmPlanet(Planet Planet) {

        List<Object> filmes = new ArrayList<>();
        filmes.add("filme 1");
        filmes.add("filme 2");
        filmes.add("filme 3");

        SwapiPlanet swapiPlanet = new SwapiPlanet();
        swapiPlanet.setName(Planet.getNome());
        swapiPlanet.setClimate(Planet.getClima());
        swapiPlanet.setTerrain(Planet.getTerreno());
        swapiPlanet.setFilms(filmes);

        return swapiPlanet;
    }
}
