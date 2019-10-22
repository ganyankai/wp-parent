/*
 Navicat Premium Data Transfer

 Source Server         : 119.23.172.36-newshop
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : 119.23.172.36:3306
 Source Schema         : logistics

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 17/10/2019 11:11:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父Id',
  `english_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `logo` int(11) NULL DEFAULT NULL COMMENT '分类图标',
  `unit_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `seq_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `category_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型：产品名称，产品分类',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 623 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, 0, '', '成品油', 234, 'ton', 1, 'product_category', 1, '2018-08-20 11:55:59', NULL, '2019-10-17 10:37:15');
INSERT INTO `product_category` VALUES (2, 0, '', '半成品油', 1, 'ton', 2, 'product_category', 1, '2018-08-20 12:19:35', NULL, '2019-10-17 10:37:18');
INSERT INTO `product_category` VALUES (3, 0, '', '化工品', 236, 'ton', 3, 'product_category', 1, '2018-08-20 11:56:23', NULL, '2018-11-09 17:25:09');
INSERT INTO `product_category` VALUES (4, 0, '', 'LNG', 238, 'ton', 4, 'product_category', 1, '2018-08-20 11:56:46', NULL, '2018-11-09 17:25:09');
INSERT INTO `product_category` VALUES (5, 0, '', '物资设备', 240, 'ton', 5, 'product_category', 1, '2018-08-20 11:58:40', NULL, '2018-11-09 17:25:10');
INSERT INTO `product_category` VALUES (12, 1, '', '汽油', 1, 'ton', 1, 'product_category', 1, '2018-08-21 20:17:47', 1, '2019-10-17 10:39:01');
INSERT INTO `product_category` VALUES (13, 1, '', '柴油', 1, 'ton', 2, 'product_category', 1, '2018-08-21 20:17:56', 1, '2019-10-17 10:44:16');
INSERT INTO `product_category` VALUES (14, 2, '', '半成品油', 2, 'ton', 1, 'product_category', 1, '2018-08-20 12:19:35', NULL, '2019-10-17 10:42:24');
INSERT INTO `product_category` VALUES (15, 3, '', '化工品', 238, 'ton', 1, 'product_category', 1, '2018-08-20 11:56:23', NULL, '2019-10-17 10:42:26');
INSERT INTO `product_category` VALUES (16, 4, '', 'LNG', 238, 'ton', 1, 'product_category', 1, '2018-08-20 11:56:46', NULL, '2019-10-17 10:44:27');
INSERT INTO `product_category` VALUES (56, 5, '', '原煤', 1, 'ton', 1, 'product_category', 1, '2018-08-22 16:26:53', NULL, '2018-08-22 16:26:53');
INSERT INTO `product_category` VALUES (57, 5, '', '钢材', 1, 'ton', 2, 'product_category', 1, '2018-08-22 16:27:05', NULL, '2018-08-22 16:27:05');
INSERT INTO `product_category` VALUES (58, 5, '', '设备备件', 1, 'ton', 3, 'product_category', 1, '2018-08-22 16:27:29', NULL, '2018-08-22 16:27:29');
INSERT INTO `product_category` VALUES (59, 5, '', '化工辅料', 1, 'ton', 4, 'product_category', 1, '2018-08-22 16:27:42', 1, '2018-08-22 16:27:50');
INSERT INTO `product_category` VALUES (60, 5, '', '仪表', 1, 'ton', 5, 'product_category', 1, '2018-08-22 16:28:06', 1, '2019-10-14 17:34:11');
INSERT INTO `product_category` VALUES (61, 5, '', '化验仪器', 1, 'ton', 6, 'product_category', 1, '2018-08-22 16:28:28', NULL, '2018-08-22 16:28:28');
INSERT INTO `product_category` VALUES (70, 5, '', '电器', 1, 'ton', 7, 'product_category', 1, '2018-08-24 10:41:12', NULL, '2018-08-24 10:41:12');
INSERT INTO `product_category` VALUES (71, 5, '', '安全消防', 1, 'ton', 8, 'product_category', 1, '2018-08-24 10:41:32', NULL, '2018-08-24 10:41:32');
INSERT INTO `product_category` VALUES (72, 5, '', '易耗品', 1, 'ton', 9, 'product_category', 1, '2018-08-24 10:42:03', NULL, '2018-08-24 10:42:03');
INSERT INTO `product_category` VALUES (101, 12, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:05:40');
INSERT INTO `product_category` VALUES (102, 12, '', '98#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:30:03', NULL, '2019-10-17 11:05:42');
INSERT INTO `product_category` VALUES (103, 12, '', '92#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:30:14', NULL, '2019-10-17 11:05:43');
INSERT INTO `product_category` VALUES (104, 13, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:05:45');
INSERT INTO `product_category` VALUES (105, 14, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:05:47');
INSERT INTO `product_category` VALUES (106, 15, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:05:50');
INSERT INTO `product_category` VALUES (607, 16, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:05:52');
INSERT INTO `product_category` VALUES (608, 56, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:05:55');
INSERT INTO `product_category` VALUES (609, 57, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:05:57');
INSERT INTO `product_category` VALUES (610, 58, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:00');
INSERT INTO `product_category` VALUES (611, 59, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:02');
INSERT INTO `product_category` VALUES (612, 60, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:05');
INSERT INTO `product_category` VALUES (613, 61, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:07');
INSERT INTO `product_category` VALUES (614, 70, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:09');
INSERT INTO `product_category` VALUES (615, 71, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:12');
INSERT INTO `product_category` VALUES (616, 72, '', '95#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:14');
INSERT INTO `product_category` VALUES (617, 15, '', '92#', 1, 'ton', 1, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:16');
INSERT INTO `product_category` VALUES (618, 15, '', '93#', 1, 'ton', 2, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:20');
INSERT INTO `product_category` VALUES (619, 15, '', '94#', 1, 'ton', 3, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:22');
INSERT INTO `product_category` VALUES (620, 15, '', '97#', 1, 'ton', 4, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:25');
INSERT INTO `product_category` VALUES (621, 15, '', '90#', 1, 'ton', 5, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:27');
INSERT INTO `product_category` VALUES (622, 15, '', '99#', 1, 'ton', 6, 'product_category', 1, '2018-08-22 15:29:56', NULL, '2019-10-17 11:06:30');

SET FOREIGN_KEY_CHECKS = 1;
