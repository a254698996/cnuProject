-- MySQL Script generated by MySQL Workbench
-- 04/13/17 18:10:38
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
  `createtime` DATETIME NULL,
  `updatetime` DATETIME NULL,
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
  `t_goods_category_sub_code` INT NULL,
  `t_goodscol` VARCHAR(45) NULL,
  `state` INT NULL,
  `user_id` INT NULL,
  `title_url` VARCHAR(45) NULL,
  `adminGrounding` INT NULL,
  `send_date` DATETIME NULL,
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
  `username` VARCHAR(45) NULL,
  `state` INT NULL,
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
  `t_exchange_group_id_main` INT NULL,
  `t_exchange_group_id` INT NULL,
  `success_date` DATETIME NULL,
  `main_success_flag` INT NULL,
  `success_flag` INT NULL,
  `exchange_state` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_t_exchange_order_t_exchange_group1_idx` (`t_exchange_group_id_main` ASC),
  INDEX `fk_t_exchange_order_t_exchange_group2_idx` (`t_exchange_group_id` ASC),
  CONSTRAINT `fk_t_exchange_order_t_exchange_group1`
    FOREIGN KEY (`t_exchange_group_id_main`)
    REFERENCES `cnu`.`t_exchange_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_exchange_order_t_exchange_group2`
    FOREIGN KEY (`t_exchange_group_id`)
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
  `ename` VARCHAR(45) NULL,
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
  PRIMARY KEY (`id`))
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
-- Table `cnu`.`t_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `remark` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
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


-- -----------------------------------------------------
-- Table `cnu`.`t_permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `remark` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NULL,
  `role_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_role_permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_role_permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` VARCHAR(45) NULL,
  `permission_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cnu`.`t_notice_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cnu`.`t_notice_activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` INT NULL COMMENT '公告或活动',
  `name` VARCHAR(45) NULL,
  `conent` VARCHAR(45) NULL,
  `send_date` DATE NULL,
  `end_date` DATE NULL,
  `state` INT NULL,
  `img_url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
