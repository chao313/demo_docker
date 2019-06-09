/*
 Navicat Premium Data Transfer

 Source Server         : mysql_local
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : demo_docker

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 10/06/2019 00:25:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_component_common_cmd
-- ----------------------------
DROP TABLE IF EXISTS `t_component_common_cmd`;
CREATE TABLE `t_component_common_cmd` (
  `id` varchar(32) NOT NULL COMMENT '通用cmd组件id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT 'cmd组件的name，eg. vim',
  `redhat_cmd` varchar(128) NOT NULL DEFAULT '' COMMENT 'cmd组件在redhat上的安装',
  `debian_cmd` varchar(128) NOT NULL DEFAULT '' COMMENT 'cmd组件在debian上的安装',
  `bin_cmd` varchar(256) NOT NULL DEFAULT '' COMMENT 'cmd组件二进制安装',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未启用 1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（申请时间）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志: 0未删除，1已删除',
  PRIMARY KEY (`id`,`delete_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用的cmd组件安装命令';

-- ----------------------------
-- Table structure for t_component_common_file
-- ----------------------------
DROP TABLE IF EXISTS `t_component_common_file`;
CREATE TABLE `t_component_common_file` (
  `id` varchar(32) NOT NULL COMMENT '通用文件组件id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '安装文件的name',
  `redhat_file` varchar(128) NOT NULL DEFAULT '' COMMENT '文件组件在redhat上的安装',
  `debian_file` varchar(128) NOT NULL DEFAULT '' COMMENT '文件组件在debian上的安装',
  `bin_file` varchar(256) NOT NULL DEFAULT '' COMMENT '文件组件二进制安装',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未启用 1-启用',
  `location` varchar(20) NOT NULL DEFAULT '' COMMENT '安装文件的安装location',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（申请时间）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志: 0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用文件组件安装';

-- ----------------------------
-- Table structure for t_component_person_cmd
-- ----------------------------
DROP TABLE IF EXISTS `t_component_person_cmd`;
CREATE TABLE `t_component_person_cmd` (
  `id` varchar(32) NOT NULL COMMENT '个人cmd命令id',
  `belong_user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '主机属于用户的ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT 'cmd组件的name',
  `redhat_cmd` varchar(128) NOT NULL DEFAULT '' COMMENT 'cmd组件在redhat上的安装',
  `debian_cmd` varchar(128) NOT NULL DEFAULT '' COMMENT 'cmd组件在debian上的安装',
  `bin_cmd` varchar(256) NOT NULL DEFAULT '' COMMENT 'cmd组件二进制安装',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未启用 1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（申请时间）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志: 0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人cmd组件安装命令';

-- ----------------------------
-- Table structure for t_component_person_file
-- ----------------------------
DROP TABLE IF EXISTS `t_component_person_file`;
CREATE TABLE `t_component_person_file` (
  `id` varchar(32) NOT NULL COMMENT '个人文件组件id',
  `belong_user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '主机属于用户的ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '安装文件的name',
  `redhat_file` varchar(128) NOT NULL DEFAULT '' COMMENT '文件组件在redhat上的安装',
  `debian_file` varchar(128) NOT NULL DEFAULT '' COMMENT '文件组件在debian上的安装',
  `bin_file` varchar(256) NOT NULL DEFAULT '' COMMENT '文件组件二进制安装',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未启用 1-启用',
  `location` varchar(20) NOT NULL DEFAULT '' COMMENT '安装文件的安装location',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（申请时间）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志: 0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人文件组件安装';

-- ----------------------------
-- Table structure for t_dockerfile_common
-- ----------------------------
DROP TABLE IF EXISTS `t_dockerfile_common`;
CREATE TABLE `t_dockerfile_common` (
  `id` varchar(32) NOT NULL COMMENT '通用dockerfile id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT 'dockerfile 的name',
  `dockerfile_base_image` varchar(128) NOT NULL DEFAULT '' COMMENT 'docker的基础image',
  `dockerfile` varchar(256) NOT NULL DEFAULT '' COMMENT 'docker的内容',
  `dockerfile_targert_name` varchar(256) NOT NULL DEFAULT '' COMMENT 'dcokerFile生成目标镜像的名称',
  `dockerfile_git` blob NOT NULL COMMENT 'dcokerFile的.git文件',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未启用 1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（申请时间）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志: 0未删除，1已删除',
  PRIMARY KEY (`id`,`delete_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录dockerfile的文件';

-- ----------------------------
-- Table structure for t_dockerfile_person
-- ----------------------------
DROP TABLE IF EXISTS `t_dockerfile_person`;
CREATE TABLE `t_dockerfile_person` (
  `id` varchar(32) NOT NULL COMMENT '个人dockerfile id',
  `belong_user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '属于的用户ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT 'dockerfile 的name',
  `dockerfile_base_image` varchar(128) NOT NULL DEFAULT '' COMMENT 'docker的基础image',
  `dockerfile` varchar(256) NOT NULL DEFAULT '' COMMENT 'docker的内容',
  `dockerfile_targert_name` varchar(256) NOT NULL DEFAULT '' COMMENT 'dcokerFile生成目标镜像的名称',
  `dockerfile_git` blob NOT NULL COMMENT 'dcokerFile的.git文件',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未启用 1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（申请时间）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志: 0未删除，1已删除',
  PRIMARY KEY (`id`,`delete_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人的dockerfile的文件';

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '用户登录ID',
  `user_name` varchar(10) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `event_type` varchar(32) NOT NULL DEFAULT '' COMMENT '事件类型',
  `event_source` varchar(10) NOT NULL DEFAULT '' COMMENT '触发事件源id',
  `content` varchar(128) NOT NULL DEFAULT '' COMMENT '事件内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志: 0未删除，1已删除',
  PRIMARY KEY (`id`(20)) USING BTREE,
  KEY `idx_ ame` (`user_id`) USING BTREE,
  KEY `idx_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户的操作日志表';

-- ----------------------------
-- Table structure for t_remote_host
-- ----------------------------
DROP TABLE IF EXISTS `t_remote_host`;
CREATE TABLE `t_remote_host` (
  `id` varchar(32) NOT NULL COMMENT '主机id',
  `belong_user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '主机属于用户的ID',
  `host_ip` varchar(32) NOT NULL DEFAULT '' COMMENT '主机IP',
  `port` varchar(10) NOT NULL DEFAULT '' COMMENT '主机端口号',
  `host_user` varchar(64) NOT NULL DEFAULT '' COMMENT '主机端用户',
  `passwd` varchar(64) NOT NULL DEFAULT '' COMMENT '主机密码',
  `remark` varchar(32) NOT NULL DEFAULT '' COMMENT '备注',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未启用 1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（申请时间）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志: 0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='远程主机';

-- ----------------------------
-- Table structure for t_remote_host_operate
-- ----------------------------
DROP TABLE IF EXISTS `t_remote_host_operate`;
CREATE TABLE `t_remote_host_operate` (
  `id` varchar(32) NOT NULL COMMENT '操作id',
  `belong_remote_id` varchar(20) NOT NULL DEFAULT '' COMMENT '当前操作记录属于远程主机的ID',
  `operate_user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '当前操作人ID',
  `operate_cmd` varchar(128) NOT NULL DEFAULT '' COMMENT '操作cmd',
  `operate_response` tinytext NOT NULL COMMENT '操作的返回值',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（申请时间）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志: 0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='远程操作cmd返回值记录';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL COMMENT '用户登录ID',
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(32) DEFAULT '' COMMENT '盐',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `email` varchar(32) DEFAULT '' COMMENT '邮箱',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未启用 1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（申请时间）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志: 0未删除，1已删除',
  `approve_status` tinyint(4) DEFAULT '0' COMMENT '0: 待审批 1：通过 ：2 拒绝',
  `white_list_user` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_ ame` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('6824f88900ca44878e07859f5ed03d52', 'whc', '123456', '01020BFD5F3DEB9594B5DC461ECD31DF', NULL, NULL, 0, '2019-06-08 15:15:01', '2019-06-08 15:15:00', 0, NULL, NULL);
INSERT INTO `t_user` VALUES ('cf50a5a91a20409cb4580ab96a402f75', 'whc', '1111', 'E5E2E739811BA9D7B97868306518C207', NULL, NULL, 0, '2019-06-08 15:45:23', '2019-06-08 15:45:22', 0, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
