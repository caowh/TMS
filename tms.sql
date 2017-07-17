/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : tms

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-17 16:41:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('4', '/main/index.do', '权限列表');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '系统管理员', '888888');
INSERT INTO `t_role` VALUES ('3', '权限角色', '100003');
INSERT INTO `t_role` VALUES ('4', '用户中心', '100002');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID',
  KEY `foreignKey_rp_r` (`rid`),
  KEY `foreignKey_rp_p` (`pid`),
  CONSTRAINT `foreignKey_rp_p` FOREIGN KEY (`pid`) REFERENCES `t_permission` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `foreignKey_rp_r` FOREIGN KEY (`rid`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('3', '4');
INSERT INTO `t_role_permission` VALUES ('1', '4');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  `username` varchar(20) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '1240597349@qq.com', '47a584b728e69533311a76ff05e8dc01', '2016-06-16 11:15:33', '2017-07-17 10:53:13', '1', 'admin', '老大');
INSERT INTO `t_user` VALUES ('11', '曹文松', '12405973491@qq.com', '47a584b728e69533311a76ff05e8dc01', '2016-05-26 20:50:54', '2017-07-15 23:45:29', '1', 'caowh', '测试一部');
INSERT INTO `t_user` VALUES ('12', '曹文豪', '12405973492@qq.com', '47a584b728e69533311a76ff05e8dc01', '2016-05-27 22:34:19', '2017-07-15 23:45:29', '1', 'caows', '测试二部');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  KEY `foreignKey_ur_u` (`uid`),
  KEY `foreignKey_ur_r` (`rid`),
  CONSTRAINT `foreignKey_ur_r` FOREIGN KEY (`rid`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `foreignKey_ur_u` FOREIGN KEY (`uid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('12', '4');
INSERT INTO `t_user_role` VALUES ('11', '4');
INSERT INTO `t_user_role` VALUES ('1', '1');
