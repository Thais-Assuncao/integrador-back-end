-- -----------------------------------------------------
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
  `creation_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `modification_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ag_conta_unique` (`ag` ASC, `conta` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
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
  `creation_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `modification_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
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
  `tipo` VARCHAR(15) NULL DEFAULT NULL,
  `creation_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `modification_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_correntista` (`id_correntista` ASC) VISIBLE,
  CONSTRAINT `transacao_ibfk_1`
    FOREIGN KEY (`id_correntista`)
    REFERENCES `icarros_correntistas`.`correntista` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 38
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `icarros_correntistas`;

DELIMITER $$

USE `icarros_correntistas`$$
DROP TRIGGER IF EXISTS `icarros_correntistas`.`transacao_insert_transacao_before_insert` $$
USE `icarros_correntistas`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `icarros_correntistas`.`transacao_insert_transacao_before_insert`
BEFORE INSERT ON `icarros_correntistas`.`transacao`
FOR EACH ROW
BEGIN
	IF(NEW.saldo_atualizado > NEW.saldo_anterior)
    THEN
		SET  new.tipo := 'ENTRADA';
	ELSE
		SET  new.tipo := 'SAIDA';
    END IF;
END$$


DELIMITER ;

INSERT INTO gerente (email, senha) VALUES ("gerente@icarros.com.br", "123456");