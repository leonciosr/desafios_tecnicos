package com.ame.challenge.planet.model;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * Entidade obtida diretamente da API Star Wars
 */
public class SwapiPlanet {

    /**
     * The name of this planet
     */
    public String name;

    /**
     * The diameter of this planet in kilometers.
     */
    public String diameter;

    /**
     * The number of standard hours it takes for this planet to complete a single rotation on its axis.
     */
    public String rotation_period;

    /**
     * The number of standard days it takes for this planet to complete a single orbit of its local star.
     */
    public String orbital_period;

    /**
     * A number denoting the gravity of this planet. Where 1 is normal.
     */
    public String gravity;

    /**
     * The average populationof sentient beings inhabiting this planet.
     */
    public String population;

    /**
     * The climate of this planet. Comma seperated if diverse.
     */
    public String climate;

    /**
     * the terrain of this planet. Comma seperated if diverse.
     */
    public String terrain;

    /**
     * The percentage of the planet surface that is naturally occuring water or bodies of water.
     */
    public String surface_water;

    /**
     * An array of People URL Resources that live on this planet.
     */
    public List<Object> residents = null;

    /**
     * An array of Film URL Resources that this planet has appeared in.
     */
    public List<Object> films = null;

    /**
     * The hypermedia URL of this resource.
     */
    public URI url;

    /**
     * The ISO 8601 date format of the time that this resource was created.
     */
    public Date created;

    /**
     * the ISO 8601 date format of the time that this resource was edited.
     */
    public Date edited;

    public SwapiPlanet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getRotation_period() {
        return rotation_period;
    }

    public void setRotation_period(String rotation_period) {
        this.rotation_period = rotation_period;
    }

    public String getOrbital_period() {
        return orbital_period;
    }

    public void setOrbital_period(String orbital_period) {
        this.orbital_period = orbital_period;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurface_water() {
        return surface_water;
    }

    public void setSurface_water(String surface_water) {
        this.surface_water = surface_water;
    }

    public List<Object> getResidents() {
        return residents;
    }

    public void setResidents(List<Object> residents) {
        this.residents = residents;
    }

    public List<Object> getFilms() {
        return films;
    }

    public void setFilms(List<Object> films) {
        this.films = films;
    }

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getEdited() {
        return edited;
    }

    public void setEdited(Date edited) {
        this.edited = edited;
    }

}
