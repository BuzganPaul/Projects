-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema assignment1bank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema assignment1bank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `assignment1bank` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `assignment1bank` ;

-- -----------------------------------------------------
-- Table `assignment1bank`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment1bank`.`administrator` (
  `idAdministrator` INT(11) NOT NULL,
  `user` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAdministrator`),
  UNIQUE INDEX `user_UNIQUE` (`user` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment1bank`.`clientinformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment1bank`.`clientinformation` (
  `idclient` INT(11) NOT NULL,
  `nameClient` VARCHAR(45) NOT NULL,
  `idNumber` BIGINT(15) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idclient`),
  UNIQUE INDEX `nameClient_UNIQUE` (`nameClient` ASC) VISIBLE,
  UNIQUE INDEX `idNumber_UNIQUE` (`idNumber` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment1bank`.`clientaccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment1bank`.`clientaccount` (
  `idclient` INT(11) NOT NULL,
  `accountType` VARCHAR(45) NOT NULL,
  `money` INT(10) NOT NULL,
  `creationDate` DATE NOT NULL,
  `idAccount` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAccount`),
  UNIQUE INDEX `idAccount_UNIQUE` (`idAccount` ASC) VISIBLE,
  INDEX `idClient_idx` (`idclient` ASC) VISIBLE,
  CONSTRAINT `idClient`
    FOREIGN KEY (`idclient`)
    REFERENCES `assignment1bank`.`clientinformation` (`idclient`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment1bank`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment1bank`.`employee` (
  `idEmployee` INT(11) NOT NULL,
  `nameEmployee` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEmployee`),
  UNIQUE INDEX `idEmployee_UNIQUE` (`idEmployee` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment1bank`.`operation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment1bank`.`operation` (
  `idOperation` INT(11) NOT NULL,
  `dataOperation` DATE NOT NULL,
  `idEmployee` INT(11) NOT NULL,
  `operationdetail` VARCHAR(90) NOT NULL,
  `idAccount` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idOperation`),
  UNIQUE INDEX `idOperation_UNIQUE` (`idOperation` ASC) VISIBLE,
  INDEX `idEmployee_idx` (`idEmployee` ASC) VISIBLE,
  INDEX `idAccount_idx` (`idAccount` ASC) VISIBLE,
  CONSTRAINT `idAccount`
    FOREIGN KEY (`idAccount`)
    REFERENCES `assignment1bank`.`clientaccount` (`idAccount`),
  CONSTRAINT `idEmployee`
    FOREIGN KEY (`idEmployee`)
    REFERENCES `assignment1bank`.`employee` (`idEmployee`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
