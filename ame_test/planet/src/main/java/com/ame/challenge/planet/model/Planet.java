package com.ame.challenge.planet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "planeta")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "{nome.not.blank}")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank(message = "{clima.not.blank}")
    @Column(name = "clima", nullable = false)
    private String clima;

    @NotBlank(message = "{terreno.not.blank}")
    @Column(name = "terreno", nullable = false)
    private String terreno;

    @Column(name = "qtde_aparicoes_filmes", nullable = false)
    private Integer qtdeAparicoesFilmes;

}