package com.ame.challenge.planet.controller;

import com.ame.challenge.planet.DataBuilder;
import com.ame.challenge.planet.PlanetApplication;
import com.ame.challenge.planet.model.Planet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlanetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PlanetControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private Planet planet;

    public PlanetControllerTest() {
        this.planet = new Planet();
        planet.setNome("Dagobah");
        planet.setClima("murky");
        planet.setTerreno("swamp, jungles");
    }

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/ame/planet";
    }

    /**
     * Cria o registro na base e confirma se ocorreu tudo bem
     * @param planet
     */
    private void createRegister(Planet planet){
        ResponseEntity<Planet> postResponse = restTemplate.postForEntity(getRootUrl(), planet, Planet.class);
        Assert.assertNotNull(postResponse);
        Assert.assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    }

    private void removeRegister(Planet value){
        Planet planet = this.restTemplate.getForObject(getRootUrl() + "/name/" + value.getNome(), Planet.class);
        if(planet != null) {
            this.restTemplate.delete(getRootUrl() + "/" + planet.getId());
        }
    }


    @Before
    public void before() {
        this.removeRegister(this.planet);
        for (Planet planetBase : DataBuilder.planets) {
            this.removeRegister(planetBase);
        }
        this.createRegister(this.planet);
    }

    @Test
    public void create() {
        for (Planet planet : DataBuilder.planets) {
            this.createRegister(planet);
        }
    }

    @Test
    public void findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<List> response = restTemplate.exchange(getRootUrl(),
                HttpMethod.GET, entity, List.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void getAllPlanetFromApiStarWars() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<List> response = this.restTemplate.exchange(getRootUrl() + "/swapi",
                HttpMethod.GET, entity, List.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void findById() {
        Planet planet = this.restTemplate.getForObject(getRootUrl() + "/name/Dagobah", Planet.class);
        Assert.assertNotNull(planet);
        planet = this.restTemplate.getForObject(getRootUrl() + "/" + planet.getId(), Planet.class);
        Assert.assertNotNull(planet);
        Assert.assertEquals("Dagobah", planet.getNome());
    }

    @Test
    public void findByName() {
        Planet planet = this.restTemplate.getForObject(getRootUrl() + "/name/Dagobah", Planet.class);
        Assert.assertNotNull(planet);
        Assert.assertEquals("murky", planet.getClima());
    }

    @Test
    public void delete() {
        this.create();
        Planet planet = this.restTemplate.getForObject(getRootUrl() + "/name/Alderaan", Planet.class);
        Assert.assertNotNull(planet);
        this.restTemplate.delete(getRootUrl() + "/" + planet.getId());

        try {
            restTemplate.getForObject(getRootUrl() + "/" + planet.getId(), Planet.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}