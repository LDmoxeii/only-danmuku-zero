
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `__achrived_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__achrived_event` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `event_uuid` varchar(64) NOT NULL DEFAULT '' COMMENT '事件uuid',
  `svc_name` varchar(255) NOT NULL DEFAULT '' COMMENT '服务',
  `event_type` varchar(255) NOT NULL DEFAULT '' COMMENT '事件类型',
  `data` text COMMENT '事件数据',
  `data_type` varchar(255) NOT NULL DEFAULT '' COMMENT '事件数据类型',
  `exception` text COMMENT '事件发送异常',
  `expire_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `event_state` int NOT NULL DEFAULT '0' COMMENT '分发状态@E=0:INIT:init|-1:DELIVERING:delivering|-2:CANCEL:cancel|-3:EXPIRED:expired|-4:EXHAUSTED:exhausted|-9:EXCEPTION:exception|1:DELIVERED:delivered;',
  `last_try_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次尝试时间',
  `next_try_time` datetime NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '下次尝试时间',
  `tried_times` int NOT NULL DEFAULT '0' COMMENT '已尝试次数',
  `try_times` int NOT NULL DEFAULT '0' COMMENT '尝试次数',
  `version` int NOT NULL DEFAULT '0',
  `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`),
  KEY `idx_event_uuid` (`event_uuid`),
  KEY `idx_event_type` (`event_type`,`svc_name`),
  KEY `idx_create_at` (`create_at`),
  KEY `idx_expire_at` (`expire_at`),
  KEY `idx_next_try_time` (`next_try_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件发件箱存档 support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `__archived_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__archived_request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `request_uuid` varchar(64) NOT NULL DEFAULT '' COMMENT 'REQUEST uuid',
  `svc_name` varchar(255) NOT NULL DEFAULT '' COMMENT '服务',
  `request_type` varchar(255) NOT NULL DEFAULT '' COMMENT 'REQUEST类型',
  `param` text COMMENT '参数',
  `param_type` varchar(255) NOT NULL DEFAULT '' COMMENT '参数类型',
  `result` text COMMENT '结果',
  `result_type` varchar(255) NOT NULL DEFAULT '' COMMENT '结果类型',
  `exception` text COMMENT '执行异常',
  `expire_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `request_state` int NOT NULL DEFAULT '0' COMMENT '执行状态@E=0:INIT:init|-1:EXECUTING:executing|-2:CANCEL:cancel|-3:EXPIRED:expired|-4:EXHAUSTED:exhausted|-9:EXCEPTION:exception|1:EXECUTED:executed;@T=RequestState;',
  `last_try_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次尝试时间',
  `next_try_time` datetime NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '下次尝试时间',
  `tried_times` int NOT NULL DEFAULT '0' COMMENT '已尝试次数',
  `try_times` int NOT NULL DEFAULT '0' COMMENT '尝试次数',
  `version` int NOT NULL DEFAULT '0',
  `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`),
  KEY `idx_request_uuid` (`request_uuid`),
  KEY `idx_request_type` (`request_type`,`svc_name`),
  KEY `idx_create_at` (`create_at`),
  KEY `idx_expire_at` (`expire_at`),
  KEY `idx_next_try_time` (`next_try_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='请求(存档) support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `__archived_saga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__archived_saga` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `saga_uuid` varchar(64) NOT NULL DEFAULT '' COMMENT 'SAGA uuid',
  `svc_name` varchar(255) NOT NULL DEFAULT '' COMMENT '服务',
  `saga_type` varchar(255) NOT NULL DEFAULT '' COMMENT 'SAGA类型',
  `param` text COMMENT '参数',
  `param_type` varchar(255) NOT NULL DEFAULT '' COMMENT '参数类型',
  `result` text COMMENT '结果',
  `result_type` varchar(255) NOT NULL DEFAULT '' COMMENT '结果类型',
  `exception` text COMMENT '执行异常',
  `expire_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `saga_state` int NOT NULL DEFAULT '0' COMMENT '执行状态@E=0:INIT:init|-1:EXECUTING:executing|-2:CANCEL:cancel|-3:EXPIRED:expired|-4:EXHAUSTED:exhausted|-9:EXCEPTION:exception|1:EXECUTED:executed;@T=SagaState;',
  `last_try_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次尝试时间',
  `next_try_time` datetime NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '下次尝试时间',
  `tried_times` int NOT NULL DEFAULT '0' COMMENT '已尝试次数',
  `try_times` int NOT NULL DEFAULT '0' COMMENT '尝试次数',
  `version` int NOT NULL DEFAULT '0',
  `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`),
  KEY `idx_saga_uuid` (`saga_uuid`),
  KEY `idx_saga_type` (`saga_type`,`svc_name`),
  KEY `idx_create_at` (`create_at`),
  KEY `idx_expire_at` (`expire_at`),
  KEY `idx_next_try_time` (`next_try_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='SAGA事务(存档) support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `__archived_saga_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__archived_saga_process` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `saga_id` bigint NOT NULL DEFAULT '0',
  `process_code` varchar(255) NOT NULL DEFAULT '' COMMENT 'SAGA处理环节代码',
  `param` text COMMENT '参数',
  `param_type` varchar(255) NOT NULL DEFAULT '' COMMENT '参数类型',
  `result` text COMMENT '结果',
  `result_type` varchar(255) NOT NULL DEFAULT '' COMMENT '结果类型',
  `exception` text COMMENT '执行异常',
  `process_state` int NOT NULL DEFAULT '0' COMMENT '执行状态@E=0:INIT:init|-1:EXECUTING:executing|-9:EXCEPTION:exception|1:EXECUTED:executed;@T=SagaProcessState;',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_try_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次尝试时间',
  `tried_times` int NOT NULL DEFAULT '0' COMMENT '尝试次数',
  `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`),
  KEY `idx_saga_id` (`saga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='SAGA事务-子环节(存档) support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `__event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__event` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `event_uuid` varchar(64) NOT NULL DEFAULT '' COMMENT '事件uuid',
  `svc_name` varchar(255) NOT NULL DEFAULT '' COMMENT '服务',
  `event_type` varchar(255) NOT NULL DEFAULT '' COMMENT '事件类型',
  `data` text COMMENT '事件数据',
  `data_type` varchar(255) NOT NULL DEFAULT '' COMMENT '事件数据类型',
  `exception` text COMMENT '事件发送异常',
  `expire_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `event_state` int NOT NULL DEFAULT '0' COMMENT '分发状态@E=0:INIT:init|-1:DELIVERING:delivering|-2:CANCEL:cancel|-3:EXPIRED:expired|-4:EXHAUSTED:exhausted|-9:EXCEPTION:exception|1:DELIVERED:delivered;',
  `last_try_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次尝试时间',
  `next_try_time` datetime NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '下次尝试时间',
  `tried_times` int NOT NULL DEFAULT '0' COMMENT '已尝试次数',
  `try_times` int NOT NULL DEFAULT '0' COMMENT '尝试次数',
  `version` int NOT NULL DEFAULT '0',
  `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`),
  KEY `idx_event_uuid` (`event_uuid`),
  KEY `idx_event_type` (`event_type`,`svc_name`),
  KEY `idx_create_at` (`create_at`),
  KEY `idx_expire_at` (`expire_at`),
  KEY `idx_next_try_time` (`next_try_time`)
) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件发件箱 support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `__event_http_subscriber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__event_http_subscriber` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `event` varchar(255) NOT NULL DEFAULT '' COMMENT '事件',
  `subscriber` varchar(255) NOT NULL DEFAULT '' COMMENT '订阅者',
  `callback_url` varchar(1024) NOT NULL DEFAULT '' COMMENT '事件回调地址',
  `version` int NOT NULL DEFAULT '0',
  `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `db_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`),
  KEY `idx_event` (`event`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件HTTP订阅 support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `__locker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__locker` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '锁名称',
  `pwd` varchar(100) NOT NULL DEFAULT '' COMMENT '锁密码',
  `lock_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '锁获取时间',
  `unlock_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '锁释放时间',
  `version` bigint unsigned NOT NULL DEFAULT '0',
  `db_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_name` (`name`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='锁 support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `__request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `request_uuid` varchar(64) NOT NULL DEFAULT '' COMMENT 'REQUEST uuid',
  `svc_name` varchar(255) NOT NULL DEFAULT '' COMMENT '服务',
  `request_type` varchar(255) NOT NULL DEFAULT '' COMMENT 'REQUEST类型',
  `param` text COMMENT '参数',
  `param_type` varchar(255) NOT NULL DEFAULT '' COMMENT '参数类型',
  `result` text COMMENT '结果',
  `result_type` varchar(255) NOT NULL DEFAULT '' COMMENT '结果类型',
  `exception` text COMMENT '执行异常',
  `expire_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `request_state` int NOT NULL DEFAULT '0' COMMENT '执行状态@E=0:INIT:init|-1:EXECUTING:executing|-2:CANCEL:cancel|-3:EXPIRED:expired|-4:EXHAUSTED:exhausted|-9:EXCEPTION:exception|1:EXECUTED:executed;@T=RequestState;',
  `last_try_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次尝试时间',
  `next_try_time` datetime NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '下次尝试时间',
  `tried_times` int NOT NULL DEFAULT '0' COMMENT '已尝试次数',
  `try_times` int NOT NULL DEFAULT '0' COMMENT '尝试次数',
  `version` int NOT NULL DEFAULT '0',
  `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`),
  KEY `idx_request_uuid` (`request_uuid`),
  KEY `idx_request_type` (`request_type`,`svc_name`),
  KEY `idx_create_at` (`create_at`),
  KEY `idx_expire_at` (`expire_at`),
  KEY `idx_next_try_time` (`next_try_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='请求 support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `__saga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__saga` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `saga_uuid` varchar(64) NOT NULL DEFAULT '' COMMENT 'SAGA uuid',
  `svc_name` varchar(255) NOT NULL DEFAULT '' COMMENT '服务',
  `saga_type` varchar(255) NOT NULL DEFAULT '' COMMENT 'SAGA类型',
  `param` text COMMENT '参数',
  `param_type` varchar(255) NOT NULL DEFAULT '' COMMENT '参数类型',
  `result` text COMMENT '结果',
  `result_type` varchar(255) NOT NULL DEFAULT '' COMMENT '结果类型',
  `exception` text COMMENT '执行异常',
  `expire_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `saga_state` int NOT NULL DEFAULT '0' COMMENT '执行状态@E=0:INIT:init|-1:EXECUTING:executing|-2:CANCEL:cancel|-3:EXPIRED:expired|-4:EXHAUSTED:exhausted|-9:EXCEPTION:exception|1:EXECUTED:executed;@T=SagaState;',
  `last_try_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次尝试时间',
  `next_try_time` datetime NOT NULL DEFAULT '0001-01-01 00:00:00' COMMENT '下次尝试时间',
  `tried_times` int NOT NULL DEFAULT '0' COMMENT '已尝试次数',
  `try_times` int NOT NULL DEFAULT '0' COMMENT '尝试次数',
  `version` int NOT NULL DEFAULT '0',
  `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`),
  KEY `idx_saga_uuid` (`saga_uuid`),
  KEY `idx_saga_type` (`saga_type`,`svc_name`),
  KEY `idx_create_at` (`create_at`),
  KEY `idx_expire_at` (`expire_at`),
  KEY `idx_next_try_time` (`next_try_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='SAGA事务 support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `__saga_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__saga_process` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `saga_id` bigint NOT NULL DEFAULT '0',
  `process_code` varchar(255) NOT NULL DEFAULT '' COMMENT 'SAGA处理环节代码',
  `param` text COMMENT '参数',
  `param_type` varchar(255) NOT NULL DEFAULT '' COMMENT '参数类型',
  `result` text COMMENT '结果',
  `result_type` varchar(255) NOT NULL DEFAULT '' COMMENT '结果类型',
  `exception` text COMMENT '执行异常',
  `process_state` int NOT NULL DEFAULT '0' COMMENT '执行状态@E=0:INIT:init|-1:EXECUTING:executing|-9:EXCEPTION:exception|1:EXECUTED:executed;@T=SagaProcessState;',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_try_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次尝试时间',
  `tried_times` int NOT NULL DEFAULT '0' COMMENT '尝试次数',
  `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`),
  KEY `idx_saga_id` (`saga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='SAGA事务-子环节 support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `__worker_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `__worker_id` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `datacenter_id` int unsigned NOT NULL DEFAULT '0' COMMENT '数据分区',
  `worker_id` int unsigned NOT NULL DEFAULT '0' COMMENT '机器分区',
  `dispatch_to` varchar(100) NOT NULL DEFAULT '' COMMENT '分配对象名称',
  `dispatch_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '分配时间',
  `expire_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '过期时间',
  `version` bigint unsigned NOT NULL DEFAULT '0',
  `db_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `db_updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_db_created_at` (`db_created_at`),
  KEY `idx_db_updated_at` (`db_updated_at`)
) ENGINE=InnoDB AUTO_INCREMENT=1025 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='雪花机器码 support by cap4j\n@I;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL COMMENT 'ID',
  `parent_id` bigint NOT NULL COMMENT '父级ID',
  `node_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路径',
  `sort` tinyint NOT NULL COMMENT '排序号',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编码',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标',
  `background` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '背景图',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_v_code` (`code`,`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='分类信息;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `customer_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_action` (
  `id` bigint NOT NULL COMMENT 'ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `video_id` bigint NOT NULL COMMENT '视频ID',
  `video_owner_id` bigint NOT NULL COMMENT '视频用户ID',
  `comment_id` bigint DEFAULT NULL COMMENT '评论ID',
  `action_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '行为类型 @E=0:UNKNOW:未知行为|1:LIKE_COMMENT:评论喜欢点赞|2:HATE_COMMENT:讨厌评论|3:LIKE_VIDEO:视频点赞|4:FAVORITE_VIDEO:视频收藏|5:COIN_VIDEO:视频投币;@T=ActionType',
  `action_count` int NOT NULL COMMENT '数量',
  `action_time` bigint NOT NULL COMMENT '操作时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_v_type` (`video_id`,`comment_id`,`action_type`,`customer_id`,`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户行为 点赞、评论;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `customer_focus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_focus` (
  `id` bigint NOT NULL COMMENT 'ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `focus_customer_id` bigint NOT NULL COMMENT '用户ID',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户关注;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `customer_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_message` (
  `id` bigint NOT NULL COMMENT 'ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `video_id` bigint DEFAULT NULL COMMENT '主体ID',
  `message_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息类型 @E=0:UNKNOW:未知消息|1:SYSTEM_MESSAGE:系统消息|2:LIKE_MESSAGE:收到的赞|3:COLLECTION_MESSAGE:收到收藏|4:COMMENT_MENTION:评论和@|5:PRIVATE_MESSAGE:私信消息;@T=MessageType',
  `send_subject_id` bigint DEFAULT NULL COMMENT '发送主体ID',
  `read_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '读取状态 @E=0:UNKNOW:未知状态|1:UNREAD:未读|2:READ:已读;@T=ReadType',
  `extend_json` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '扩展信息@T=UserMessageExtend?',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户消息表;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `customer_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_profile` (
  `id` bigint NOT NULL COMMENT 'ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `email` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别 @E=0:UNKNOWN:未知|1:FEMALE:女|2:MALE:男|;@T=SexType',
  `birthday` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '出生日期',
  `school` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学校',
  `person_introduction` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个人简介',
  `notice_info` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '空间公告',
  `total_coin_count` int NOT NULL COMMENT '硬币总数量',
  `current_coin_count` int NOT NULL COMMENT '当前硬币数',
  `theme` tinyint(1) NOT NULL DEFAULT '0' COMMENT '主题 @E=0:UNKNOW:未知主题|1:LIGHT:浅色主题|2:DARK:深色主题|3:SYSTEM:跟随系统;@T=ThemeType',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_v_email` (`email`,`deleted`),
  UNIQUE KEY `uk_v_nick_name` (`nick_name`,`deleted`),
  UNIQUE KEY `uk_v_phone` (`phone`,`deleted`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户信息;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `customer_video_series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_video_series` (
  `id` bigint NOT NULL COMMENT 'ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `series_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '列表名称',
  `series_description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `sort` tinyint NOT NULL COMMENT '排序',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户视频序列归档;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `customer_video_series_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_video_series_video` (
  `id` bigint NOT NULL COMMENT 'ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `series_id` bigint NOT NULL COMMENT '列表ID @Ref=customer_video_series',
  `video_id` bigint NOT NULL COMMENT '视频ID',
  `sort` tinyint NOT NULL COMMENT '排序',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户视频序列视频关联;@P=customer_video_series;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statistics` (
  `id` bigint NOT NULL COMMENT 'ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `data_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据统计类型 @E=0:UNKNOW:未知类型|1:PLAY:播放量|2:FANS:粉丝|3:LIKE:点赞|4:COLLECTION:收藏|5:COIN:投币|6:COMMENT:评论|7:DANMUKU:弹幕;@T=StatisticsDataType',
  `statistics_count` int DEFAULT NULL COMMENT '统计数量',
  `statistics_date` bigint NOT NULL COMMENT '统计日期（秒级时间戳）',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间（秒级时间戳）',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间（秒级时间戳）',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='统计信息;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT 'ID',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '帐号类型 @E=0:UNKNOW:未知类型|1:SYS_USER:系统管理员;@T=UserType',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `email` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `join_time` bigint NOT NULL COMMENT '加入时间',
  `last_login_time` bigint DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后登录IP',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0:禁用 1:正常',
  `related_id` bigint DEFAULT NULL COMMENT '关联ID; 用户、管理员 = 0',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_v_email` (`email`,`deleted`),
  UNIQUE KEY `uk_v_phone` (`phone`,`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='帐号;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `user_abnormal_operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_abnormal_operation_log` (
  `id` bigint NOT NULL COMMENT 'ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `user_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户类型 @E=0:UNKNOW:未知类型|1:SYS_USER:系统管理员;@T=UserType',
  `op_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '异常类型 @E=0:UNKNOW:未知异常|1:PASSWORD_FAIL_TOO_MANY_TIMES:密码失败次数过多;@T=AbnormalOpType',
  `ip` varchar(45) NOT NULL COMMENT '触发IP',
  `occur_time` bigint NOT NULL COMMENT '触发时间',
  `description` varchar(200) DEFAULT NULL COMMENT '异常描述',
  `extra` text COMMENT '扩展JSON，如关联登录日志ID列表等',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_abnormal_op_user_type` (`user_id`,`op_type`) USING BTREE,
  KEY `idx_user_abnormal_op_time` (`occur_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户异常操作日志;@Spe;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `user_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_login_log` (
  `id` bigint NOT NULL COMMENT 'ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID，可为空（未知用户）',
  `user_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户类型 @E=0:UNKNOW:未知类型|1:SYS_USER:系统管理员;@T=UserType',
  `login_name` varchar(150) NOT NULL COMMENT '登录名（邮箱或手机号）',
  `login_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '登录类型 @E=0:UNKNOW:未知登录类型|1:PASSWORD:密码登录|2:SMS_CODE:短信验证码登录|3:LOGOUT:退出登录;@T=LoginType',
  `result` tinyint(1) NOT NULL DEFAULT '0' COMMENT '登录结果 @E=0:UNKNOW:未知结果|1:SUCCESS:成功|2:FAILURE:失败;@T=LoginResult',
  `ip` varchar(45) NOT NULL COMMENT '登录IP',
  `user_agent` varchar(255) DEFAULT NULL COMMENT 'User-Agent',
  `reason` varchar(200) DEFAULT NULL COMMENT '失败原因描述',
  `occur_time` bigint NOT NULL COMMENT '发生时间（秒级或毫秒级时间戳）',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_login_log_user_id_time` (`user_id`,`occur_time`) USING BTREE,
  KEY `idx_user_login_log_login_name_time` (`login_name`,`occur_time`) USING BTREE,
  KEY `idx_user_login_log_result_time` (`result`,`occur_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户登录日志;@Spe;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `id` bigint NOT NULL COMMENT 'ID',
  `video_post_id` bigint NOT NULL COMMENT '视频草稿ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `video_cover` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频封面',
  `video_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频名称',
  `p_category_id` bigint NOT NULL COMMENT '父级分类ID',
  `category_id` bigint DEFAULT NULL COMMENT '分类ID',
  `post_type` tinyint NOT NULL DEFAULT '0' COMMENT '投稿类型 @E=0:UNKNOW:未知类型|1:ORIGINAL:自制作|2:REPOST:转载;@T=PostType',
  `origin_info` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '原资源说明',
  `tags` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
  `introduction` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简介',
  `interaction` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '互动设置',
  `duration` int NOT NULL DEFAULT '0' COMMENT '持续时间（秒）',
  `play_count` int NOT NULL DEFAULT '0' COMMENT '播放数量',
  `like_count` int NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `danmuku_count` int NOT NULL DEFAULT '0' COMMENT '弹幕数量',
  `comment_count` int NOT NULL DEFAULT '0' COMMENT '评论数量',
  `coin_count` int NOT NULL DEFAULT '0' COMMENT '投币数量',
  `collect_count` int NOT NULL DEFAULT '0' COMMENT '收藏数量',
  `recommend_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '推荐状态 @E=0:UNKNOW:未知状态|1:NOT_RECOMMEND:未推荐|2:RECOMMEND:已推荐;@T=RecommendType',
  `last_play_time` bigint DEFAULT NULL COMMENT '最后播放时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频信息;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_audit_trace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_audit_trace` (
  `id` bigint NOT NULL COMMENT 'ID',
  `video_post_id` bigint NOT NULL COMMENT '视频稿件ID',
  `audit_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审核状态 @E=0:UNKNOW:未知|1:PASSED:审核通过|2:FAILED:审核不通过;@T=AuditStatus',
  `reviewer_id` bigint DEFAULT NULL COMMENT '审核人ID，可为空',
  `reviewer_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审核人类型 @E=0:UNKNOW:未知类型|1:SYS_USER:系统管理员;@T=UserType',
  `reason` varchar(255) DEFAULT NULL COMMENT '审核备注/失败原因',
  `occur_time` bigint NOT NULL COMMENT '审核发生时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_video_audit_trace_video_time` (`video_post_id`,`occur_time`) USING BTREE,
  KEY `idx_video_audit_trace_status_time` (`audit_status`,`occur_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频审核追溯记录;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_comment` (
  `id` bigint NOT NULL COMMENT '评论ID',
  `parent_id` bigint NOT NULL COMMENT '父级评论ID',
  `video_id` bigint NOT NULL COMMENT '视频ID',
  `video_owner_id` bigint NOT NULL COMMENT '视频用户ID',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '回复内容',
  `img_path` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `reply_customer_id` bigint DEFAULT NULL COMMENT '回复人ID',
  `top_type` tinyint DEFAULT '0' COMMENT '0:未置顶  1:置顶',
  `post_time` bigint NOT NULL COMMENT '发布时间',
  `like_count` int DEFAULT '0' COMMENT '喜欢数量',
  `hate_count` int DEFAULT '0' COMMENT '讨厌数量',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='评论;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_danmuku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_danmuku` (
  `id` bigint NOT NULL COMMENT 'ID',
  `video_id` bigint NOT NULL COMMENT '视频ID',
  `file_id` bigint NOT NULL COMMENT '唯一ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `post_time` bigint DEFAULT NULL COMMENT '发布时间',
  `text` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '内容',
  `mode` tinyint(1) DEFAULT NULL COMMENT '展示位置',
  `color` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '颜色',
  `time` int DEFAULT NULL COMMENT '展示时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频弹幕;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_file` (
  `id` bigint NOT NULL COMMENT 'ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `video_id` bigint NOT NULL COMMENT '视频ID',
  `video_file_post_id` bigint NOT NULL COMMENT '视频文件草稿ID',
  `file_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件名',
  `file_index` int NOT NULL COMMENT '文件索引',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  `file_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件路径',
  `duration` int DEFAULT NULL COMMENT '持续时间（秒）',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频文件信息;@P=video;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_file_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_file_post` (
  `id` bigint NOT NULL COMMENT 'ID',
  `video_post_id` bigint NOT NULL COMMENT '视频稿件ID',
  `upload_id` bigint NOT NULL COMMENT '上传ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `file_index` int NOT NULL COMMENT '文件索引',
  `file_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件名',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  `transcode_output_prefix` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '转码输出对象前缀',
  `encrypt_output_prefix` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '加密输出对象前缀',
  `transfer_result` tinyint NOT NULL DEFAULT '0' COMMENT '转码结果 @E=0:UNKNOW:未知结果|1:TRANSCODING:转码中|2:SUCCESS:转码成功|3:FAILED:转码失败;@T=TransferResult',
  `encrypt_status` int NOT NULL DEFAULT '1' COMMENT '加密状态@E=0:UNKNOW:未知|1:UNENCRYPTED:未加密|2:ENCRYPTING:加密中|3:ENCRYPTED:已加密|4:FAILED:失败;@T=EncryptStatus',
  `encrypt_method` int NOT NULL DEFAULT '1' COMMENT '加密方式@E=0:UNKNOW:未知|1:HLS_AES_128:AES-128|2:SAMPLE_AES:SAMPLE-AES|3:DRM:DRM占位;@T=EncryptMethod',
  `encrypt_key_version` int DEFAULT NULL COMMENT '加密密钥版本',
  `duration` int DEFAULT NULL COMMENT '持续时间（秒）',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_v_upload_id` (`upload_id`,`customer_id`,`deleted`) USING BTREE,
  UNIQUE KEY `uk_i` (`video_post_id`,`file_index`,`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频文件信息;@P=video_post;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_file_post_variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_file_post_variant` (
  `id` bigint NOT NULL COMMENT 'ID',
  `file_post_id` bigint NOT NULL COMMENT '稿件文件ID@Ref=video_file_post',
  `quality` varchar(32) NOT NULL COMMENT '清晰度档位，如 1080p/720p',
  `width` int NOT NULL COMMENT '输出宽度(px)',
  `height` int NOT NULL COMMENT '输出高度(px)',
  `video_bitrate_kbps` int NOT NULL COMMENT '视频码率(kbps)',
  `audio_bitrate_kbps` int NOT NULL COMMENT '音频码率(kbps)',
  `bandwidth_bps` int NOT NULL COMMENT 'Master 中的 BANDWIDTH（bps）',
  `playlist_path` varchar(512) NOT NULL COMMENT '子清晰度 m3u8 路径，如 720p/index.m3u8',
  `segment_prefix` varchar(512) DEFAULT NULL COMMENT '切片目录前缀，如 720p/',
  `segment_duration` int DEFAULT NULL COMMENT '切片目标时长（秒）',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_i` (`file_post_id`,`quality`,`deleted`),
  KEY `idx_vfpv_height` (`height`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频稿件文件分辨率档位;@P=video_file_post;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_file_upload_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_file_upload_session` (
  `id` bigint NOT NULL COMMENT 'ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `file_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件名',
  `chunks` int NOT NULL COMMENT '分片总数',
  `chunk_index` int NOT NULL DEFAULT '0' COMMENT '当前已上传的最大分片索引',
  `file_size` bigint DEFAULT '0' COMMENT '累计已上传大小（字节）',
  `temp_dir` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '临时目录（绝对或相对路径）',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 @E=0:UNKNOW:未知类型|1:CREATED:已创建|2:UPLOADING:上传中|3:DONE:完成|4:ABORTED:已放弃|5:EXPIRED:已过期;@T=UploadStatus',
  `duration` int DEFAULT NULL COMMENT '视频时长（秒，可选）',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `expires_at` bigint DEFAULT NULL COMMENT '过期时间（秒时间戳）',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_status` (`customer_id`,`status`) USING BTREE,
  KEY `idx_expires_at` (`expires_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频分片上传会话; 用于跟踪预上传与分片进度';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_file_variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_file_variant` (
  `id` bigint NOT NULL COMMENT 'ID',
  `file_id` bigint NOT NULL COMMENT '视频文件ID@Ref=video_file',
  `quality` varchar(32) NOT NULL COMMENT '清晰度档位，如 1080p/720p',
  `width` int NOT NULL COMMENT '输出宽度(px)',
  `height` int NOT NULL COMMENT '输出高度(px)',
  `video_bitrate_kbps` int NOT NULL COMMENT '视频码率(kbps)',
  `audio_bitrate_kbps` int NOT NULL COMMENT '音频码率(kbps)',
  `bandwidth_bps` int NOT NULL COMMENT 'Master 中的 BANDWIDTH（bps）',
  `playlist_path` varchar(512) NOT NULL COMMENT '子清晰度 m3u8 路径，如 720p/index.m3u8',
  `segment_prefix` varchar(512) DEFAULT NULL COMMENT '切片目录前缀，如 720p/',
  `segment_duration` int DEFAULT NULL COMMENT '切片目标时长（秒）',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_i` (`file_id`,`quality`,`deleted`) USING BTREE,
  KEY `idx_vfv_height` (`height`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频文件分辨率档位;@P=video_file;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_hls_encrypt_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_hls_encrypt_key` (
  `id` bigint NOT NULL COMMENT 'ID',
  `video_post_id` bigint NOT NULL COMMENT '视频稿件ID',
  `video_id` bigint DEFAULT NULL COMMENT '视频ID',
  `file_index` int NOT NULL COMMENT '文件索引',
  `quality` varchar(32) NOT NULL COMMENT '绑定清晰度',
  `key_id` varchar(64) NOT NULL COMMENT 'Key ID（m3u8 暴露）',
  `key_ciphertext` varchar(512) NOT NULL COMMENT '密钥密文（KMS 加密后 Base64）',
  `iv_hex` varchar(64) DEFAULT NULL COMMENT 'IV hex（16字节，可空）',
  `key_version` int NOT NULL DEFAULT '1' COMMENT '密钥版本号（轮换递增）',
  `method` int NOT NULL DEFAULT '1' COMMENT '加密方式@E=0:UNKNOW:未知|1:HLS_AES_128:AES-128|2:SAMPLE_AES:SAMPLE-AES|3:DRM:DRM占位;@T=EncryptMethod',
  `key_uri_template` varchar(512) NOT NULL COMMENT 'm3u8 中的 URI 模板，含 token 占位',
  `expire_time` bigint DEFAULT NULL COMMENT '过期时间（ms）',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态@E=0:UNKNOW:未知|1:ACTIVE:可用|2:REVOKED:吊销|3:EXPIRED:过期;@T=EncryptKeyStatus',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注/轮换原因',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_i` (`video_post_id`,`file_index`,`key_id`,`key_version`,`quality`,`deleted`),
  KEY `idx_video_hls_encrypt_key_expire` (`expire_time`) USING BTREE,
  KEY `idx_video_hls_encrypt_key_post` (`video_post_id`,`file_index`,`status`),
  KEY `idx_video_hls_encrypt_key_video` (`video_id`,`file_index`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频 HLS 加密密钥';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_hls_key_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_hls_key_token` (
  `id` bigint NOT NULL COMMENT 'ID',
  `video_post_id` bigint NOT NULL COMMENT '视频稿件ID',
  `video_id` bigint DEFAULT NULL COMMENT '视频ID',
  `file_index` int NOT NULL COMMENT '文件索引',
  `key_version` int NOT NULL DEFAULT '1' COMMENT '密钥版本',
  `allowed_qualities` varchar(512) DEFAULT NULL COMMENT '授权清晰度列表 JSON，空表示全量',
  `token_hash` varchar(128) NOT NULL COMMENT 'token 哈希（sha256）',
  `audience` varchar(128) DEFAULT NULL COMMENT '受众标识（用户/终端）',
  `expire_time` bigint NOT NULL COMMENT '过期时间（ms）',
  `max_use` int NOT NULL DEFAULT '5' COMMENT '最大可用次数',
  `used_count` int NOT NULL DEFAULT '0' COMMENT '已使用次数',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态@E=0:UNKNOW:未知|1:VALID:有效|2:EXHAUSTED:已用尽|3:EXPIRED:过期|4:REVOKED:吊销;@T=EncryptTokenStatus',
  `issue_ip` varchar(64) DEFAULT NULL COMMENT '签发 IP',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_i` (`token_hash`,`deleted`) USING BTREE,
  KEY `idx_video_hls_key_token_expire` (`expire_time`) USING BTREE,
  KEY `idx_video_hls_key_token_post` (`video_post_id`,`file_index`,`status`),
  KEY `idx_video_hls_key_token_video` (`video_id`,`file_index`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='HLS 加密播放 token';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_play_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_play_history` (
  `id` bigint NOT NULL COMMENT 'ID',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `video_id` bigint NOT NULL COMMENT '视频ID',
  `file_index` int NOT NULL COMMENT '文件索引',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频播放历史;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_post` (
  `id` bigint NOT NULL COMMENT 'ID',
  `video_cover` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频封面',
  `video_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频名称',
  `customer_id` bigint NOT NULL COMMENT '用户ID',
  `p_category_id` bigint NOT NULL COMMENT '父级分类ID',
  `category_id` bigint DEFAULT NULL COMMENT '分类ID',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '视频状态 @E=0:UNKNOW:未知状态|1:TRANSCODING:转码中|2:TRANSCODE_FAILED:转码失败|3:PENDING_REVIEW:待审核|4:REVIEW_PASSED:审核成功|5:REVIEW_FAILED:审核失败;@T=VideoStatus',
  `post_type` tinyint NOT NULL DEFAULT '0' COMMENT '投稿类型 @E=0:UNKNOW:未知类型|1:ORIGINAL:自制作|2:REPOST:转载;@T=PostType',
  `origin_info` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '原资源说明',
  `tags` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
  `introduction` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简介',
  `interaction` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '互动设置',
  `duration` int NOT NULL DEFAULT '0' COMMENT '持续时间（秒）',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='视频信息;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_post_processing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_post_processing` (
  `id` bigint NOT NULL COMMENT 'ID',
  `video_post_id` bigint NOT NULL COMMENT '视频稿件ID',
  `total_files` int NOT NULL COMMENT '分P总数',
  `transcode_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '转码状态 @E=0:UNKNOW:未知|1:PENDING:待处理|2:PROCESSING:处理中|3:SUCCESS:成功|4:FAILED:失败|5:SKIPPED:跳过;@T=ProcessStatus',
  `encrypt_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '加密状态 @E=0:UNKNOW:未知|1:PENDING:待处理|2:PROCESSING:处理中|3:SUCCESS:成功|4:FAILED:失败|5:SKIPPED:跳过;@T=ProcessStatus',
  `transcode_done_count` int NOT NULL DEFAULT '0' COMMENT '转码完成数',
  `encrypt_done_count` int NOT NULL DEFAULT '0' COMMENT '加密完成数（含 SKIPPED）',
  `failed_count` int NOT NULL DEFAULT '0' COMMENT '失败文件数',
  `last_fail_reason` varchar(512) DEFAULT NULL COMMENT '最近失败原因',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_i` (`video_post_id`,`deleted`),
  KEY `idx_vpp_status` (`transcode_status`,`encrypt_status`,`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频稿件处理聚合;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_post_processing_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_post_processing_file` (
  `id` bigint NOT NULL COMMENT 'ID',
  `parent_id` bigint NOT NULL COMMENT '视频稿件处理ID@Ref=video_post_processing',
  `file_index` int NOT NULL COMMENT '文件索引',
  `upload_id` bigint NOT NULL COMMENT '上传会话ID',
  `transcode_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '转码状态 @E=0:UNKNOW:未知|1:PENDING:待处理|2:PROCESSING:处理中|3:SUCCESS:成功|4:FAILED:失败|5:SKIPPED:跳过;@T=ProcessStatus',
  `encrypt_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '加密状态 @E=0:UNKNOW:未知|1:PENDING:待处理|2:PROCESSING:处理中|3:SUCCESS:成功|4:FAILED:失败|5:SKIPPED:跳过;@T=ProcessStatus',
  `encrypt_method` int NOT NULL DEFAULT '1' COMMENT '加密方式@E=0:UNKNOW:未知|1:HLS_AES_128:AES-128|2:SAMPLE_AES:SAMPLE-AES|3:DRM:DRM占位;@T=EncryptMethod',
  `encrypt_key_version` int DEFAULT NULL COMMENT '加密密钥版本',
  `transcode_output_prefix` varchar(512) DEFAULT NULL COMMENT '转码输出对象前缀',
  `transcode_output_path` varchar(512) DEFAULT NULL COMMENT '转码输出本地路径',
  `transcode_variants_json` text COMMENT '转码清晰度结果 JSON',
  `encrypt_output_dir` varchar(512) DEFAULT NULL COMMENT '加密输出本地目录',
  `encrypt_output_prefix` varchar(512) DEFAULT NULL COMMENT '加密输出对象前缀',
  `duration` int DEFAULT NULL COMMENT '时长（秒）',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  `fail_reason` varchar(512) DEFAULT NULL COMMENT '失败原因',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_i` (`parent_id`,`file_index`,`deleted`),
  KEY `idx_vppf_post` (`parent_id`) USING BTREE,
  KEY `idx_vppf_transcode` (`transcode_status`,`update_time`) USING BTREE,
  KEY `idx_vppf_encrypt` (`encrypt_status`,`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频稿件处理文件状态;@P=video_post_processing;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_post_processing_variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_post_processing_variant` (
  `id` bigint NOT NULL COMMENT 'ID',
  `parent_id` bigint NOT NULL COMMENT '视频稿件处理文件ID@Ref=video_post_processing_file',
  `quality` varchar(32) NOT NULL COMMENT '清晰度档位，如 1080p/720p',
  `width` int NOT NULL COMMENT '输出宽度(px)',
  `height` int NOT NULL COMMENT '输出高度(px)',
  `video_bitrate_kbps` int NOT NULL COMMENT '视频码率(kbps)',
  `audio_bitrate_kbps` int NOT NULL COMMENT '音频码率(kbps)',
  `bandwidth_bps` int NOT NULL COMMENT 'Master 中的 BANDWIDTH（bps）',
  `playlist_path` varchar(512) NOT NULL COMMENT '子清晰度 m3u8 路径，如 720p/index.m3u8',
  `segment_prefix` varchar(512) DEFAULT NULL COMMENT '切片目录前缀，如 720p/',
  `segment_duration` int DEFAULT NULL COMMENT '切片目标时长（秒）',
  `transcode_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '转码状态 @E=0:UNKNOW:未知|1:PENDING:待处理|2:PROCESSING:处理中|3:SUCCESS:成功|4:FAILED:失败|5:SKIPPED:跳过;@T=ProcessStatus',
  `encrypt_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '加密状态 @E=0:UNKNOW:未知|1:PENDING:待处理|2:PROCESSING:处理中|3:SUCCESS:成功|4:FAILED:失败|5:SKIPPED:跳过;@T=ProcessStatus',
  `encrypt_fail_reason` varchar(512) DEFAULT NULL COMMENT '加密失败原因',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_i` (`parent_id`,`quality`,`deleted`),
  KEY `idx_vpp_av_height` (`height`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频稿件处理分辨率档位;@P=video_post_processing_file;';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `video_quality_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_quality_policy` (
  `id` bigint NOT NULL COMMENT 'ID',
  `video_id` bigint NOT NULL COMMENT '视频ID',
  `file_index` int NOT NULL COMMENT '文件索引',
  `quality` varchar(32) NOT NULL COMMENT '清晰度档位，如 1080p',
  `auth_policy` int NOT NULL DEFAULT '1' COMMENT '授权策略@E=0:UNKNOW:未知|1:PUBLIC:公开|2:LOGIN:登录|3:PAID:付费|4:CUSTOM:自定义;@T=QualityAuthPolicy',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注/策略说明',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人名称',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人名称',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  `deleted` bigint NOT NULL DEFAULT '0' COMMENT '删除标识 0：未删除 id：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_i` (`video_id`,`file_index`,`quality`,`deleted`),
  KEY `idx_video_quality_policy` (`auth_policy`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频清晰度策略;@Spe;@Fac;';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

