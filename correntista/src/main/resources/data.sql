-- Schema icarros_correntistas
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `icarros_correntistas` ;

-- -----------------------------------------------------
-- Schema icarros_correntistas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `icarros_correntistas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `icarros_correntistas` ;

-- -----------------------------------------------------
-- Table `icarros_correntistas`.`correntista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `icarros_correntistas`.`correntista` ;

CREATE TABLE IF NOT EXISTS `icarros_correntistas`.`correntista` (
  `ag` INT NOT NULL,
  `conta` INT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `telefone` CHAR(11) NOT NULL,
  `saldo` DECIMAL(8,2) NOT NULL,
  `endereco` VARCHAR(255) NOT NULL,
  `cep` CHAR(8) NOT NULL,
  `bairro` VARCHAR(255) NOT NULL,
  `cidade` VARCHAR(255) NOT NULL,
  `UF` CHAR(2) NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `senha` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ag_conta_unique` (`ag` ASC, `conta` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `icarros_correntistas`.`gerente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `icarros_correntistas`.`gerente` ;

CREATE TABLE IF NOT EXISTS `icarros_correntistas`.`gerente` (
  `senha` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `icarros_correntistas`.`transacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `icarros_correntistas`.`transacao` ;

CREATE TABLE IF NOT EXISTS `icarros_correntistas`.`transacao` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_correntista` BIGINT NOT NULL,
  `fluxo` DECIMAL(8,2) NOT NULL,
  `saldo_anterior` DECIMAL(8,2) NULL DEFAULT NULL,
  `saldo_atualizado` DECIMAL(8,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_correntista` (`id_correntista` ASC) VISIBLE,
  CONSTRAINT `transacao_ibfk_1`
    FOREIGN KEY (`id_correntista`)
    REFERENCES `icarros_correntistas`.`correntista` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;