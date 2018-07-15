-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema campeonato_volley
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema campeonato_volley
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `campeonato_volley` DEFAULT CHARACTER SET utf8 ;
USE `campeonato_volley` ;

-- -----------------------------------------------------
-- Table `campeonato_volley`.`Colegio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Colegio` (
  `idColegio` INT NOT NULL AUTO_INCREMENT,
  `NombreColegio` VARCHAR(450) NOT NULL,
  `NombreRepres` VARCHAR(150) NULL,
  PRIMARY KEY (`idColegio`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Persona` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `Nombres` VARCHAR(45) NULL,
  `Apellidos` VARCHAR(45) NULL,
  `Cedula` VARCHAR(15) NULL,
  `Edad` INT NULL,
  `Genero` VARCHAR(15) NULL,
  PRIMARY KEY (`idPersona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Jugador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Jugador` (
  `idJugador` INT NOT NULL AUTO_INCREMENT,
  `Estatura` DOUBLE NULL,
  `Peso` DOUBLE NULL,
  `TipoSangre` VARCHAR(10) NULL,
  `Posicion` VARCHAR(45) NULL,
  `NumDorsal` INT NULL,
  `idPersona` INT NOT NULL,
  `idColegio` INT NOT NULL,
  `Estado` INT NULL,
  PRIMARY KEY (`idJugador`, `idPersona`, `idColegio`),
  INDEX `fk_Jugador_Colegio1_idx` (`idColegio` ASC),
  INDEX `fk_Jugador_Persona1_idx` (`idPersona` ASC),
  CONSTRAINT `fk_Jugador_Colegio1`
    FOREIGN KEY (`idColegio`)
    REFERENCES `campeonato_volley`.`Colegio` (`idColegio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Jugador_Persona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `campeonato_volley`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Equipo` (
  `idEquipo` INT NOT NULL AUTO_INCREMENT,
  `NombreEquipo` VARCHAR(450) NOT NULL,
  `Categoria` VARCHAR(45) NULL,
  `idColegio` INT NOT NULL,
  PRIMARY KEY (`idEquipo`, `idColegio`),
  INDEX `fk_Equipo_Colegio1_idx` (`idColegio` ASC),
  CONSTRAINT `fk_Equipo_Colegio1`
    FOREIGN KEY (`idColegio`)
    REFERENCES `campeonato_volley`.`Colegio` (`idColegio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Campeonato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Campeonato` (
  `idCampeonato` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(200) NOT NULL,
  `Representante` VARCHAR(150) NOT NULL,
  `Anio` INT NULL,
  PRIMARY KEY (`idCampeonato`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Campeonato_Equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Campeonato_Equipo` (
  `Campeonato_Equipo_id` INT NOT NULL AUTO_INCREMENT,
  `idEquipo` INT NOT NULL,
  `idCampeonato` INT NOT NULL,
  PRIMARY KEY (`Campeonato_Equipo_id`, `idEquipo`, `idCampeonato`),
  INDEX `fk_Campeonato_Equipo_Equipo1_idx` (`idEquipo` ASC),
  INDEX `fk_Campeonato_Equipo_Campeonato1_idx` (`idCampeonato` ASC),
  CONSTRAINT `fk_Campeonato_Equipo_Equipo1`
    FOREIGN KEY (`idEquipo`)
    REFERENCES `campeonato_volley`.`Equipo` (`idEquipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Campeonato_Equipo_Campeonato1`
    FOREIGN KEY (`idCampeonato`)
    REFERENCES `campeonato_volley`.`Campeonato` (`idCampeonato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Inscripcion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Inscripcion` (
  `idInscripcion` INT NOT NULL AUTO_INCREMENT,
  `idEquipo` INT NOT NULL,
  `idJugador` INT NOT NULL,
  `idCampeonato` INT NOT NULL,
  `Fecha` VARCHAR(10) NULL,
  PRIMARY KEY (`idInscripcion`, `idEquipo`, `idJugador`, `idCampeonato`),
  INDEX `fk_Inscripcion_Jugador_idx` (`idJugador` ASC),
  INDEX `fk_Inscripcion_Campeonato1_idx` (`idCampeonato` ASC),
  INDEX `fk_Inscripcion_Equipo1_idx` (`idEquipo` ASC),
  CONSTRAINT `fk_Inscripcion_Jugador`
    FOREIGN KEY (`idJugador`)
    REFERENCES `campeonato_volley`.`Jugador` (`idJugador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Inscripcion_Campeonato1`
    FOREIGN KEY (`idCampeonato`)
    REFERENCES `campeonato_volley`.`Campeonato` (`idCampeonato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Inscripcion_Equipo1`
    FOREIGN KEY (`idEquipo`)
    REFERENCES `campeonato_volley`.`Equipo` (`idEquipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Partido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Partido` (
  `idPartido` INT NOT NULL AUTO_INCREMENT,
  `Categoria` VARCHAR(45) NOT NULL,
  `Fecha` VARCHAR(10) NOT NULL,
  `Hora` VARCHAR(45) NULL,
  `NumCancha` INT NULL,
  `idCampeonato` INT NOT NULL,
  PRIMARY KEY (`idPartido`, `idCampeonato`),
  INDEX `fk_Partido_Campeonato1_idx` (`idCampeonato` ASC),
  CONSTRAINT `fk_Partido_Campeonato1`
    FOREIGN KEY (`idCampeonato`)
    REFERENCES `campeonato_volley`.`Campeonato` (`idCampeonato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Arbitro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Arbitro` (
  `idArbitro` INT NOT NULL AUTO_INCREMENT,
  `TipoArbitro` VARCHAR(45) NULL,
  `Confederacion` VARCHAR(45) NULL,
  `idPersona` INT NOT NULL,
  PRIMARY KEY (`idArbitro`, `idPersona`),
  INDEX `fk_Arbitro_Persona1_idx` (`idPersona` ASC),
  CONSTRAINT `fk_Arbitro_Persona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `campeonato_volley`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `Usuario` VARCHAR(15) NULL,
  `Contraseña` VARCHAR(150) NULL,
  `Tipo` VARCHAR(15) NULL,
  `idArbitro` INT NOT NULL,
  PRIMARY KEY (`idUsuario`, `idArbitro`),
  INDEX `fk_Usuario_Arbitro1_idx` (`idArbitro` ASC),
  CONSTRAINT `fk_Usuario_Arbitro1`
    FOREIGN KEY (`idArbitro`)
    REFERENCES `campeonato_volley`.`Arbitro` (`idArbitro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Arbitro_Partido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Arbitro_Partido` (
  `Arbitro_Partido_id` INT NOT NULL AUTO_INCREMENT,
  `idArbitro` INT NOT NULL,
  `idPartido` INT NOT NULL,
  `Observacion` VARCHAR(45) NULL,
  PRIMARY KEY (`Arbitro_Partido_id`, `idArbitro`, `idPartido`),
  INDEX `fk_Arbitro_Partido_Arbitro1_idx` (`idArbitro` ASC),
  INDEX `fk_Arbitro_Partido_Partido1_idx` (`idPartido` ASC),
  CONSTRAINT `fk_Arbitro_Partido_Arbitro1`
    FOREIGN KEY (`idArbitro`)
    REFERENCES `campeonato_volley`.`Arbitro` (`idArbitro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Arbitro_Partido_Partido1`
    FOREIGN KEY (`idPartido`)
    REFERENCES `campeonato_volley`.`Partido` (`idPartido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Resultados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Resultados` (
  `idResultados` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NOT NULL,
  `Puntos` INT NULL,
  `Equipos` INT NULL,
  `idPartido` INT NOT NULL,
  PRIMARY KEY (`idResultados`, `idPartido`),
  INDEX `fk_Resultados_Partido1_idx` (`idPartido` ASC),
  CONSTRAINT `fk_Resultados_Partido1`
    FOREIGN KEY (`idPartido`)
    REFERENCES `campeonato_volley`.`Partido` (`idPartido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campeonato_volley`.`Equipos_Partidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campeonato_volley`.`Equipos_Partidos` (
  `Equipo_Partido_id` INT NOT NULL AUTO_INCREMENT,
  `idEquipo1` INT NOT NULL,
  `idEquipo2` INT NOT NULL,
  `idPartido` INT NOT NULL,
  PRIMARY KEY (`Equipo_Partido_id`),
  INDEX `fk_Equipos_Partidos_Partido1_idx` (`idPartido` ASC),
  INDEX `fk_Equipos_Partidos_Equipo1_idx` (`idEquipo2` ASC),
  INDEX `fk_Equipos_Partidos_Equipo2_idx` (`idEquipo1` ASC),
  CONSTRAINT `fk_Equipos_Partidos_Partido1`
    FOREIGN KEY (`idPartido`)
    REFERENCES `campeonato_volley`.`Partido` (`idPartido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipos_Partidos_Equipo1`
    FOREIGN KEY (`idEquipo2`)
    REFERENCES `campeonato_volley`.`Equipo` (`idEquipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipos_Partidos_Equipo2`
    FOREIGN KEY (`idEquipo1`)
    REFERENCES `campeonato_volley`.`Equipo` (`idEquipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
