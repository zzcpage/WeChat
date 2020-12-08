/*
 Navicat MySQL Data Transfer

 Source Server         : admins
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : wechat

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 28/11/2020 01:15:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `id1` int(20) NOT NULL,
  `id2` int(20) NOT NULL,
  `group1` int(10) NOT NULL,
  `group2` int(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_index`(`id1`, `id2`) USING BTREE,
  INDEX `id2_foregin`(`id2`) USING BTREE,
  CONSTRAINT `id1_foregin` FOREIGN KEY (`id1`) REFERENCES `user` (`Uid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `id2_foregin` FOREIGN KEY (`id2`) REFERENCES `user` (`Uid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Uid` int(20) NOT NULL,
  `pos` int(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `group_index`(`Uid`) USING BTREE,
  CONSTRAINT `group_index` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dates` datetime(0) NOT NULL,
  `id1` int(20) NOT NULL,
  `id2` int(20) NOT NULL,
  `message` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `msg_index`(`id1`, `id2`) USING BTREE,
  INDEX `Mid2_index`(`id2`) USING BTREE,
  CONSTRAINT `Mid1` FOREIGN KEY (`id1`) REFERENCES `user` (`Uid`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `Mid2_index` FOREIGN KEY (`id2`) REFERENCES `user` (`Uid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for unsent
-- ----------------------------
DROP TABLE IF EXISTS `unsent`;
CREATE TABLE `unsent`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `dates` datetime(0) NOT NULL,
  `id1` int(20) NOT NULL,
  `id2` int(20) NOT NULL,
  `message` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `umId_index`(`id1`, `id2`) USING BTREE,
  INDEX `unId2`(`id2`) USING BTREE,
  CONSTRAINT `unId1` FOREIGN KEY (`id1`) REFERENCES `user` (`Uid`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `unId2` FOREIGN KEY (`id2`) REFERENCES `user` (`Uid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `Uid` int(20) NOT NULL,
  `amount` int(18) NOT NULL,
  `password` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `sex` int(1) UNSIGNED NOT NULL,
  `birth` date NOT NULL,
  `signature` varchar(150) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `bImage` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `imgSrc` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`Uid`) USING BTREE,
  UNIQUE INDEX `amount_index`(`amount`, `Uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
