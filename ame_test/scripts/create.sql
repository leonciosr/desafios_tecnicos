/*
* Utilizado para criar a base de dados de produção
*/
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ame
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ame
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ame` DEFAULT CHARACTER SET utf8 ;
USE `ame` ;

-- -----------------------------------------------------
-- Table `ame`.`planeta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ame`.`planeta` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Identificador do planeta',
  `nome` VARCHAR(256) NOT NULL COMMENT 'The name of this planet.',
  `clima` VARCHAR(256) NOT NULL COMMENT 'The climate of this planet. Comma separated if diverse.',
  `terreno` VARCHAR(256) NOT NULL COMMENT 'The terrain of this planet. Comma separated if diverse.',
  `qtde_aparicoes_filmes` INT NOT NULL COMMENT 'Quantidade de aparições em filmes do Star Wars',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
