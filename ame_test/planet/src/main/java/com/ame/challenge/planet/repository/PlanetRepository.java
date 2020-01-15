package com.ame.challenge.planet.repository;

import com.ame.challenge.planet.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Givantha Kalansuriya
 */
@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    @Query("select p from Planet p where lower(p.nome) = lower(:name)")
    Optional<Planet> findByName(@Param("name")String name);

}