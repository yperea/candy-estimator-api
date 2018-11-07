#!/usr/bin/env bash

set -e

mysql -umadjava -pmadjava -D directory <<EOF

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pktydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS pktydb ;

-- -----------------------------------------------------
-- Schema pktydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS pktydb DEFAULT CHARACTER SET utf8 ;
USE pktydb ;

-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------
DROP TABLE IF EXISTS users ;

CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  first_name NVARCHAR(45) NULL,
  last_name NVARCHAR(45) NULL,
  user_name NVARCHAR(45) NOT NULL,
  password NVARCHAR(45) NULL,
  api_key NVARCHAR(45) NULL,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id))
ENGINE = InnoDB;

CREATE UNIQUE INDEX user_name_UNIQUE ON users (user_name ASC);


-- -----------------------------------------------------
-- Table roles
-- -----------------------------------------------------
DROP TABLE IF EXISTS roles ;

CREATE TABLE IF NOT EXISTS roles (
  id INT NOT NULL AUTO_INCREMENT,
  role_name NVARCHAR(45) NULL,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id))
ENGINE = InnoDB;

CREATE UNIQUE INDEX role_name_UNIQUE ON roles (role_name ASC);


-- -----------------------------------------------------
-- Table users_roles
-- -----------------------------------------------------
DROP TABLE IF EXISTS users_roles ;

CREATE TABLE IF NOT EXISTS users_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_users_has_roles_users
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_users_has_roles_roles1
    FOREIGN KEY (role_id)
    REFERENCES roles (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX fk_users_has_roles_roles1_idx ON users_roles (role_id ASC);

CREATE INDEX fk_users_has_roles_users_idx ON users_roles (user_id ASC);


-- -----------------------------------------------------
-- Table estimates_history
-- -----------------------------------------------------
DROP TABLE IF EXISTS estimates_history ;

CREATE TABLE IF NOT EXISTS estimates_history (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NULL,
  candy_per_children DECIMAL NULL DEFAULT 0,
  children_population INT NULL DEFAULT 0,
  address NVARCHAR(215) NOT NULL,
  country NVARCHAR(3) NOT NULL,
  latitude DECIMAL NULL,
  longitude DECIMAL NULL,
  created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT fk_estimates_history_users1
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX fk_estimates_history_users1_idx ON estimates_history (user_id ASC);

USE pktydb ;

-- -----------------------------------------------------
-- Placeholder table for view v_users_roles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS v_users_roles (user_name INT, role_name INT);

-- -----------------------------------------------------
-- View v_users_roles
-- -----------------------------------------------------
DROP TABLE IF EXISTS v_users_roles;
DROP VIEW IF EXISTS v_users_roles ;
USE pktydb;
CREATE OR REPLACE VIEW v_users_roles AS
  SELECT u.user_name, r.role_name
  FROM users u INNER JOIN users_roles ur ON u.id = ur.user_id
               INNER JOIN roles r ON r.id = ur.role_id;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table users
-- -----------------------------------------------------
START TRANSACTION;
USE pktydb;
INSERT INTO users (id, first_name, last_name, user_name, password, api_key, created) VALUES (DEFAULT, 'Tiresa', 'Dombrowski', 'tdombrowski', 'supersecret1', 'supersecret1', DEFAULT);
INSERT INTO users (id, first_name, last_name, user_name, password, api_key, created) VALUES (DEFAULT, 'Paul', 'Norby', 'pnorby', 'supersecret2', 'supersecret2', DEFAULT);
INSERT INTO users (id, first_name, last_name, user_name, password, api_key, created) VALUES (DEFAULT, 'Kyle', 'Maitland', 'kmaitland', 'supersecret3', 'supersecret3', DEFAULT);
INSERT INTO users (id, first_name, last_name, user_name, password, api_key, created) VALUES (DEFAULT, 'Yesid', 'Perea', 'yperea', 'supersecret4', 'supersecret4', DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table roles
-- -----------------------------------------------------
START TRANSACTION;
USE pktydb;
INSERT INTO roles (id, role_name, created) VALUES (DEFAULT, 'administrator', DEFAULT);
INSERT INTO roles (id, role_name, created) VALUES (DEFAULT, 'user', DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table users_roles
-- -----------------------------------------------------
START TRANSACTION;
USE pktydb;
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table estimates_history
-- -----------------------------------------------------
START TRANSACTION;
USE pktydb;
INSERT INTO estimates_history (id, user_id, candy_per_children, children_population, address, country, latitude, longitude, created) VALUES (DEFAULT, 1, 5, 274, '2935 Broadbridge Ave, Stratford, CT', 'USA', NULL, NULL, DEFAULT);
INSERT INTO estimates_history (id, user_id, candy_per_children, children_population, address, country, latitude, longitude, created) VALUES (DEFAULT, 2, 10, 190, '1701 Wright Street, Madison, Wisconsin 53704', 'USA', NULL, NULL, DEFAULT);
INSERT INTO estimates_history (id, user_id, candy_per_children, children_population, address, country, latitude, longitude, created) VALUES (DEFAULT, 3, 15, 40, '223 W Main St, Madison, WI 53703', 'USA', NULL, NULL, DEFAULT);
INSERT INTO estimates_history (id, user_id, candy_per_children, children_population, address, country, latitude, longitude, created) VALUES (DEFAULT, 4, 5, 240, '201 S Gammon Rd, Madison, WI 53717', DEFAULT, NULL, NULL, DEFAULT);

COMMIT;

EOF