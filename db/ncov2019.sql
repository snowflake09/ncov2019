-- MySQL dump 10.13  Distrib 8.0.17, for Linux (x86_64)
--
-- Host: 111.205.32.60    Database: ncov2019
-- ------------------------------------------------------
-- Server version	5.7.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ncov_region`
--

DROP TABLE IF EXISTS `ncov_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ncov_region` (
  `ID` bigint(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL COMMENT '小区名称',
  `ADDRESS` varchar(150) DEFAULT NULL COMMENT '小区地址',
  `MANAGE_ID` bigint(10) NOT NULL COMMENT '管理员id',
  `MANAGE_NAME` varchar(45) DEFAULT NULL COMMENT '管理员名词',
  `Region_Qr_Path` varchar(200) DEFAULT NULL,
  `CODE` varchar(128) DEFAULT NULL,
  `STATUS` int(1) NOT NULL COMMENT '状态1停用，0启用',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(20) DEFAULT NULL COMMENT '创建人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ncov_region_buildings`
--

DROP TABLE IF EXISTS `ncov_region_buildings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ncov_region_buildings` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUILDING_NAME` varchar(30) DEFAULT NULL COMMENT '楼号',
  `MANAGER_NAME` varchar(30) DEFAULT NULL COMMENT '管理员姓名',
  `MANAGER_PHONE` varchar(11) DEFAULT NULL COMMENT '管理员电话',
  `REGION_ID` int(10) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL COMMENT '状态',
  `CREATE_USER` varchar(30) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(30) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ncov_user_record`
--

DROP TABLE IF EXISTS `ncov_user_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ncov_user_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_NAME` varchar(40) DEFAULT NULL COMMENT '用户名',
  `AGE` int(3) DEFAULT NULL COMMENT '年龄',
  `SEX` int(1) DEFAULT NULL COMMENT '性别0女，1男',
  `TEMPERATURE` varchar(10) DEFAULT NULL COMMENT '体温',
  `HEALTHY` int(1) DEFAULT NULL COMMENT '是否健康',
  `HEALTH_NOTE` varchar(100) DEFAULT NULL COMMENT '健康说明',
  `REPORT_USER_ID` bigint(10) DEFAULT NULL COMMENT '上报用户ID',
  `CREATE_USER` varchar(20) DEFAULT NULL COMMENT '上报用户名',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '上报时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ncov_users`
--

DROP TABLE IF EXISTS `ncov_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ncov_users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `WECHAT_ID` varchar(40) NOT NULL COMMENT '微信号',
  `NAME` varchar(20) NOT NULL COMMENT '姓名',
  `PHONE` varchar(11) NOT NULL COMMENT '联系电话',
  `CARD_NO` varchar(18) NOT NULL COMMENT '身份证号码',
  `AGE` int(3) DEFAULT NULL,
  `SEX` int(1) DEFAULT NULL COMMENT '出行方式0自驾，1火车，2飞机，3其他',
  `PROVINCE_NAME` varchar(40) DEFAULT NULL COMMENT '户口所在地',
  `CITY_NAME` varchar(40) DEFAULT NULL COMMENT '返京时间',
  `AREA_NAME` varchar(40) DEFAULT NULL COMMENT '出发地',
  `USER_TYPE` int(1) DEFAULT NULL COMMENT '用户类型0返京，1日常，2访客',
  `REGION_ID` bigint(10) DEFAULT NULL COMMENT '小区ID',
  `REGION_ADDRESS` varchar(100) DEFAULT NULL COMMENT '小区住址',
  `TEMPERATURE` varchar(10) DEFAULT NULL COMMENT '体温',
  `HEALTHY` int(1) DEFAULT NULL COMMENT '是否有湖北人接触',
  `HEALTH_NOTE` varchar(45) DEFAULT NULL COMMENT '健康说明',
  `CAR_NO` varchar(15) DEFAULT NULL COMMENT '车牌号',
  `TRIP_NO` varchar(10) DEFAULT NULL COMMENT '车次',
  `DOG` varchar(10) DEFAULT NULL COMMENT '犬种',
  `REASON` varchar(15) DEFAULT NULL COMMENT '事由',
  `USER_STATUS` int(1) DEFAULT NULL COMMENT '状态0有效，1删除',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(40) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_dictionary`
--

DROP TABLE IF EXISTS `sys_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dictionary` (
  `ID` bigint(10) NOT NULL AUTO_INCREMENT,
  `VALUE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '值',
  `LABEL` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标签',
  `TYPE` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型',
  `DESCRIPTION` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `SEQ` int(3) DEFAULT NULL COMMENT '排序',
  `STATUS` int(1) NOT NULL COMMENT '状态',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='数据字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_manage_user`
--

DROP TABLE IF EXISTS `sys_manage_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_manage_user` (
  `ID` bigint(10) NOT NULL AUTO_INCREMENT,
  `LOGIN_NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登陆用户名',
  `USERNAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `PASSWORD` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `PHONE` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机',
  `ROLE_ID` bigint(10) DEFAULT NULL COMMENT '角色ID',
  `STATUS` int(1) DEFAULT NULL COMMENT '状态',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='管理用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_resource` (
  `ID` bigint(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `URL` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '链接',
  `RESOURCE_TYPE` int(1) DEFAULT NULL COMMENT '操作类型',
  `DESCRIPTION` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `ICONCLS` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图标',
  `PID` bigint(10) DEFAULT NULL COMMENT '父节点ID',
  `TREE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '树关系',
  `SEQ` tinyint(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `STATUS` int(1) NOT NULL COMMENT '状态',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `ID` bigint(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `DISCRIPTION` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `SEQ` int(5) DEFAULT NULL COMMENT '排序',
  `STATUS` int(1) DEFAULT NULL COMMENT '状态',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_role_resource`
--

DROP TABLE IF EXISTS `sys_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_resource` (
  `ID` bigint(10) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(10) DEFAULT NULL,
  `RESOURCE_ID` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4862 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色资源表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-17  9:30:51
