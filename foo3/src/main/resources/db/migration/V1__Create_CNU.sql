-- MySQL Script generated by MySQL Workbench
-- 03/31/17 19:21:40
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cnu
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cnu
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cnu` DEFAULT CHARACTER SET utf8 ;
USE `cnu` ;

-- -----------------------------------------------------
-- Table `cnu`.`t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `email` VARCHAR(20) NULL,
  `phone` VARCHAR(20) NULL,
  `memo` VARCHAR(200) NULL,
  `sno` VARCHAR(45) NULL,
  `sname` VARCHAR(45) NULL,
  `passwordanswer` VARCHAR(45) NULL,
  `passwordask` VARCHAR(45) NULL,
  `state` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_exchange_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_exchange_group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `total` MEDIUMTEXT NULL,
  `t_user_id` INT NULL,
  `intention` VARCHAR(100) NULL,
  `state` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_goods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_goods` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `t_exchange_group_id` INT NULL,
  `t_goods_category_code` INT NULL,
  `t_goodscol` VARCHAR(45) NULL,
  `state` INT NULL,
  `t_goods_category_sub_code` INT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_t_goods_t_exchange_group_idx` (`t_exchange_group_id` ASC),
  CONSTRAINT `fk_t_goods_t_exchange_group`
    FOREIGN KEY (`t_exchange_group_id`)
    REFERENCES `cnu`.`t_exchange_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_goods_score`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_goods_score` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `score` VARCHAR(45) NULL,
  `t_goods_id` VARCHAR(45) NULL,
  `t_user_id` INT NULL,
  `comment` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_t_goods_score_t_goods1_idx` (`t_goods_id` ASC),
  INDEX `fk_t_goods_score_t_user1_idx` (`t_user_id` ASC),
  CONSTRAINT `fk_t_goods_score_t_goods1`
    FOREIGN KEY (`t_goods_id`)
    REFERENCES `cnu`.`t_goods` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_goods_score_t_user1`
    FOREIGN KEY (`t_user_id`)
    REFERENCES `cnu`.`t_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_exchange_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_exchange_order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `t_exchange_ordercol` VARCHAR(45) NULL,
  `t_exchange_group_id_a` INT NULL,
  `t_exchange_group_id_b` INT NULL,
  `success_date` DATETIME NULL,
  `a_success_flag` VARCHAR(1) NULL,
  `b_success_flag` VARCHAR(1) NULL,
  `exchange_state` VARCHAR(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_t_exchange_order_t_exchange_group1_idx` (`t_exchange_group_id_a` ASC),
  INDEX `fk_t_exchange_order_t_exchange_group2_idx` (`t_exchange_group_id_b` ASC),
  CONSTRAINT `fk_t_exchange_order_t_exchange_group1`
    FOREIGN KEY (`t_exchange_group_id_a`)
    REFERENCES `cnu`.`t_exchange_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_exchange_order_t_exchange_group2`
    FOREIGN KEY (`t_exchange_group_id_b`)
    REFERENCES `cnu`.`t_exchange_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_goods_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_goods_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `pcode` VARCHAR(45) NULL,
  `is_sub` VARCHAR(1) NULL DEFAULT 0,
  `state` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_goods_pic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_goods_pic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  `t_goods_id` VARCHAR(45) NULL,
  `size` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_t_goods_pic_t_goods1`
    FOREIGN KEY ()
    REFERENCES `cnu`.`t_goods` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `url` VARCHAR(100) NULL,
  `state` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_exchange_intention`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_exchange_intention` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `memo` VARCHAR(200) NULL,
  `t_exchange_order_id` INT NULL,
  `t_user_id` INT NULL,
  `state` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_t_exchange_intention_t_exchange_order1_idx` (`t_exchange_order_id` ASC),
  INDEX `fk_t_exchange_intention_t_user1_idx` (`t_user_id` ASC),
  CONSTRAINT `fk_t_exchange_intention_t_exchange_order1`
    FOREIGN KEY (`t_exchange_order_id`)
    REFERENCES `cnu`.`t_exchange_order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_exchange_intention_t_user1`
    FOREIGN KEY (`t_user_id`)
    REFERENCES `cnu`.`t_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_menu_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_menu_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_role` (
  `id` INT NOT NULL,
  `role_code` VARCHAR(45) NULL,
  `role_name` VARCHAR(45) NULL,
  `t_rolecol` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_menu_has_t_menu_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_menu_has_t_menu_role` (
  `t_menu_id` INT NULL AUTO_INCREMENT,
  `t_menu_role_id` INT NULL,
  INDEX `fk_t_menu_has_t_menu_role_t_menu_role1_idx` (`t_menu_role_id` ASC),
  INDEX `fk_t_menu_has_t_menu_role_t_menu1_idx` (`t_menu_id` ASC),
  CONSTRAINT `fk_t_menu_has_t_menu_role_t_menu1`
    FOREIGN KEY (`t_menu_id`)
    REFERENCES `cnu`.`t_menu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_menu_has_t_menu_role_t_menu_role1`
    FOREIGN KEY (`t_menu_role_id`)
    REFERENCES `cnu`.`t_menu_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_menu_role_has_t_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_menu_role_has_t_role` (
  `t_menu_role_id` INT NULL,
  `t_role_id` INT NULL,
  INDEX `fk_t_menu_role_has_t_role_t_role1_idx` (`t_role_id` ASC),
  INDEX `fk_t_menu_role_has_t_role_t_menu_role1_idx` (`t_menu_role_id` ASC),
  CONSTRAINT `fk_t_menu_role_has_t_role_t_menu_role1`
    FOREIGN KEY (`t_menu_role_id`)
    REFERENCES `cnu`.`t_menu_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_menu_role_has_t_role_t_role1`
    FOREIGN KEY (`t_role_id`)
    REFERENCES `cnu`.`t_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_role_has_t_admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_role_has_t_admin` (
  `t_role_id` INT NULL,
  `t_admin_id` INT NULL,
  INDEX `fk_t_role_has_t_admin_t_admin1_idx` (`t_admin_id` ASC),
  INDEX `fk_t_role_has_t_admin_t_role1_idx` (`t_role_id` ASC),
  CONSTRAINT `fk_t_role_has_t_admin_t_role1`
    FOREIGN KEY (`t_role_id`)
    REFERENCES `cnu`.`t_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_role_has_t_admin_t_admin1`
    FOREIGN KEY (`t_admin_id`)
    REFERENCES `cnu`.`t_admin` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_dictionary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_dictionary` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `pcode` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
