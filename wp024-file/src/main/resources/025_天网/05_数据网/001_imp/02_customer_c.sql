/*
Navicat MySQL Data Transfer

Source Server         : mysql5.7localhost
Source Server Version : 50717
Source Host           : localhost:3308
Source Database       : lifedata

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-05-03 17:09:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer_c
-- ----------------------------
DROP TABLE IF EXISTS `customer_c`;
CREATE TABLE `customer_c` (
  `id` int(11) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL COMMENT '0 女 1 男 ',
  `birthday` int(11) DEFAULT NULL,
  `sponsor_money` double DEFAULT NULL,
  `shop_money` double DEFAULT NULL,
  `shop_type` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `black_white` int(11) DEFAULT NULL COMMENT '0 黑名单     1 普通	2  白名单',
  `vip_level` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_dept` varchar(40) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_c
-- ----------------------------
INSERT INTO `customer_c` VALUES ('1', 'xm', '1', '19900714', '0', '0', '0', '0', '0', '0', '0', '2019-12-07 16:16:39', '1', '0', '0');
