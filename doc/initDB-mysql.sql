CREATE SCHEMA `kai` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `kai`.`t_user` (
  `id` VARCHAR(32) NOT NULL,
  `type` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '用户类型',
  `username` VARCHAR(45) NOT NULL COMMENT '用户名',
  `nick_name` VARCHAR(45) NULL COMMENT '昵称',
  `password` VARCHAR(128) NOT NULL COMMENT '密码',
  `salt` VARCHAR(64) NULL COMMENT '盐值',
  `mobile` INT NULL COMMENT '手机号',
  `gender` TINYINT(2) NULL COMMENT '性别',
  `avatar` VARCHAR(200) NULL COMMENT '虚拟形象',
  `email` VARCHAR(45) NULL COMMENT '电子邮件地址',
  `remark` VARCHAR(200) NULL COMMENT '备注',
  `status` TINYINT(2) NOT NULL DEFAULT 1 COMMENT '用户状态 0 禁用, 1 启用, 2 锁定',
  `last_login_ts` DATETIME NULL COMMENT '上次登陆时间',
  `created_by` VARCHAR(32) NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` VARCHAR(32) NULL COMMENT '更新人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT(1) NULL DEFAULT 0 COMMENT '是否逻辑删除 0 未删除, 1 已删除',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC) VISIBLE);

CREATE TABLE `kai`.`t_role` (
  `id` VARCHAR(32) NOT NULL,
  `code` VARCHAR(45) NOT NULL COMMENT '编码',
  `name` VARCHAR(45) NOT NULL COMMENT '名称',
  `description` VARCHAR(45) NULL COMMENT '描述',
  `status` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '角色状态 0 失效 1 有效',
  `created_by` VARCHAR(32) NULL COMMENT '创建人',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` VARCHAR(32) NULL COMMENT '修改人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` TINYINT(1) NULL COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);

INSERT INTO `kai`.`t_role` (`id`, `code`, `name`, `created_by`, `deleted`) VALUES ('1', 'root', 'root', 'init', '0');

CREATE TABLE `kai`.`rel_user_role` (
  `id` INT NOT NULL,
  `user_id` VARCHAR(32) NOT NULL,
  `role_id` VARCHAR(32) NOT NULL,
  `created_by` VARCHAR(45) NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` VARCHAR(45) NULL,
  `update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

