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
-- Dumping data for table `ncov_region`
--

LOCK TABLES `ncov_region` WRITE;
/*!40000 ALTER TABLE `ncov_region` DISABLE KEYS */;
INSERT INTO `ncov_region` VALUES (1,'北京金泽家园B区','北京市朝阳区东坝乡',1,'admin','/ncov2020/files/ymkj/ncovQr/202002/1410/2972a87ec41b42f4a1b9c7d8e663c299/ZZ200214102377136.jpg','ZZ200214103148470',0,'2020-02-11 10:39:04',NULL,'2020-02-13 20:11:57',NULL),(3,'北京太阳家园C区','北京市朝阳区东坝乡',3,'admin002','/ncov2020/files/ymkj/ncovQr/202002/1410/2972a87ec41b42f4a1b9c7d8e663c299/ZZ200214102377136.jpg','ZZ200214103148470',0,'2020-02-11 11:06:19',NULL,'2020-02-13 21:03:55',NULL),(4,'北京世界家园A区','北京市海淀区',5,'admin004','/ncov2020/files/ymkj/ncovQr/202002/1410/2972a87ec41b42f4a1b9c7d8e663c299/ZZ200214102377136.jpg','ZZ200214103148470',0,'2020-02-14 10:18:28',NULL,NULL,NULL),(8,'北京XX家园B区','北京市朝阳区东坝乡',5,'admin004','/ncov2020/files/ymkj/ncovQr/202002/1410/2972a87ec41b42f4a1b9c7d8e663c299/ZZ200214102377136.jpg','ZZ200214102377136',0,'2020-02-14 10:40:41',NULL,NULL,NULL),(11,'北京京旺家园','北京市朝阳区东坝乡',4,'admin003','/ncov2020/files/ymkj/ncovQr/202002/1410/801ac310c907445b9b20a55aa24f83d4/ZZ200214108122193.jpg','ZZ200214108122193',0,'2020-02-14 10:57:55',NULL,'2020-02-14 10:58:38',NULL);
/*!40000 ALTER TABLE `ncov_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ncov_region_buildings`
--

LOCK TABLES `ncov_region_buildings` WRITE;
/*!40000 ALTER TABLE `ncov_region_buildings` DISABLE KEYS */;
INSERT INTO `ncov_region_buildings` VALUES (2,'B1','杨柳','13811011011',1,0,NULL,NULL,NULL,NULL),(3,'B2','杨柳','13811011011',1,0,NULL,NULL,NULL,NULL),(4,'B3','杨柳','13811011011',1,0,NULL,NULL,NULL,NULL),(5,'B4','杜延婷','13811011011',1,0,NULL,NULL,NULL,NULL),(6,'B5','杜延婷','13811011011',1,0,NULL,NULL,NULL,NULL),(7,'B6','王磊','13811011011',1,0,NULL,NULL,NULL,NULL),(8,'B7','王磊','13811011011',1,0,NULL,NULL,NULL,NULL),(9,'C1','康兴(贾增珏)','13811011011',3,0,NULL,NULL,NULL,NULL),(10,'C2','康兴(贾增珏)','13811011011',3,0,NULL,NULL,NULL,NULL),(11,'C3','康兴(贾增珏)','13811011011',3,0,NULL,NULL,NULL,NULL),(12,'C4','马思宁','13811011011',3,0,NULL,NULL,NULL,NULL),(13,'C5','马思宁','13811011012',1,0,NULL,NULL,NULL,'2020-02-13 09:00:26'),(14,'C6','张佳然','13811011011',3,0,NULL,NULL,NULL,NULL),(15,'C7','张佳然','13811011011',3,0,NULL,NULL,NULL,NULL),(16,'C8','贾楠','13811011011',3,0,NULL,NULL,NULL,NULL),(17,'C9','贾楠','13811011011',3,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ncov_region_buildings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_dictionary`
--

LOCK TABLES `sys_dictionary` WRITE;
/*!40000 ALTER TABLE `sys_dictionary` DISABLE KEYS */;
INSERT INTO `sys_dictionary` VALUES (1,'icon-sum','系统菜单','RESOURCE_ICON','',1,1,'2016-11-12 17:35:41',NULL,NULL,NULL),(2,'http://www.iumer.cn/umer/wechat/activity?id=','活动链接','URL_TYPE','',1,1,'2016-11-12 17:51:56',NULL,'2017-03-07 13:51:54','超级管理员'),(3,'icon-add','下级菜单','RESOURCE_ICON','',2,1,'2016-11-14 12:01:30',NULL,'2016-11-14 12:01:55',NULL),(4,'完美','完美','COMMENT_LABEL','',1,1,'2017-03-20 09:50:04','超级管理员','2018-06-02 11:17:26','超级管理员'),(5,'http://www.iumer.cn/umer/wechat/zc/home?activityId=','众筹活动链接','URL_TYPE','',2,1,'2017-03-27 11:14:41','超级管理员','2017-03-27 11:14:44','超级管理员');
/*!40000 ALTER TABLE `sys_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_manage_user`
--

LOCK TABLES `sys_manage_user` WRITE;
/*!40000 ALTER TABLE `sys_manage_user` DISABLE KEYS */;
INSERT INTO `sys_manage_user` VALUES (1,'admin','超级管理员','e10adc3949ba59abbe56e057f20f883e','13661207021',1,1,NULL,NULL,'2020-02-08 20:51:50',NULL),(2,'admin001','社区管理员A','e10adc3949ba59abbe56e057f20f883e','13661207011',2,1,NULL,NULL,'2016-11-16 14:56:03',NULL),(3,'admin002','社区管理员A','e10adc3949ba59abbe56e057f20f883e','13661207011',2,1,'2017-05-08 09:57:30',NULL,'2020-02-12 20:22:35',NULL),(4,'admin003','社区管理员B','e10adc3949ba59abbe56e057f20f883e','13661207011',3,1,'2019-12-25 14:46:35',NULL,NULL,NULL),(5,'admin004','社区管理员B','e10adc3949ba59abbe56e057f20f883e','13661207011',3,1,'2019-12-26 14:40:44',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_manage_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_resource`
--

LOCK TABLES `sys_resource` WRITE;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
INSERT INTO `sys_resource` VALUES (1,'系统菜单','',0,'','icon-sum',0,'',1,1,NULL,NULL,NULL,NULL),(2,'用户管理','/sys/manageuser/listPage',0,'','',1,'',3,1,NULL,NULL,NULL,NULL),(4,'角色管理','/sys/role/listPage',0,'','',1,'',2,1,NULL,NULL,NULL,NULL),(5,'资源管理','/sys/resource/listPage',0,'',NULL,1,'',1,1,NULL,NULL,NULL,NULL),(6,'数据字典','/sys/dictionary/listPage',0,'','',1,'',4,1,NULL,NULL,NULL,NULL),(16,'新建','/sys/resource/addPage',1,NULL,'',5,NULL,2,1,NULL,NULL,NULL,NULL),(17,'编辑','/sys/resource/editPage',1,NULL,'',5,NULL,3,1,NULL,NULL,NULL,NULL),(18,'删除','/sys/resource/delete',1,NULL,'',5,NULL,4,1,NULL,NULL,NULL,NULL),(19,'上级模块选择','/sys/resource/comboTree',1,NULL,'',5,NULL,5,1,NULL,NULL,NULL,NULL),(20,'保存','/sys/resource/save',1,NULL,'',16,NULL,1,1,NULL,NULL,NULL,NULL),(23,'保存','/sys/resource/edit',1,NULL,'',17,NULL,1,1,NULL,NULL,NULL,NULL),(24,'查询','/sys/resource/treeGrid',1,NULL,'',5,NULL,1,1,NULL,NULL,NULL,NULL),(25,'查询','/sys/role/dataGrid',1,NULL,'',4,NULL,1,1,NULL,NULL,NULL,NULL),(26,'新建','/sys/role/addPage',1,NULL,'',4,NULL,2,1,NULL,NULL,NULL,NULL),(27,'编辑','/sys/role/editPage',1,NULL,'',4,NULL,3,1,NULL,NULL,NULL,NULL),(28,'授权','/sys/role/grantPage',1,NULL,'',4,NULL,4,1,NULL,NULL,NULL,NULL),(29,'保存','/sys/role/save',1,NULL,'',26,NULL,1,1,NULL,NULL,NULL,NULL),(30,'保存','/sys/role/edit',1,NULL,'',27,NULL,1,1,NULL,NULL,NULL,NULL),(31,'保存','/sys/role/grant',1,NULL,'',28,NULL,1,1,NULL,NULL,NULL,NULL),(32,'查询','/sys/role/selectPermissionList',1,NULL,'',28,NULL,2,1,NULL,NULL,NULL,NULL),(33,'删除','/sys/role/delete',1,NULL,'',4,NULL,5,1,NULL,NULL,NULL,NULL),(34,'新建','/sys/manageuser/addPage',1,NULL,'',2,NULL,2,1,NULL,NULL,NULL,NULL),(35,'查询','/sys/manageuser/dataGrid',1,NULL,'',2,NULL,1,1,NULL,NULL,NULL,NULL),(36,'编辑','/sys/manageuser/editPage',1,NULL,'',2,NULL,3,1,NULL,NULL,NULL,NULL),(37,'删除','/sys/manageuser/delete',1,NULL,'',2,NULL,4,1,NULL,NULL,NULL,NULL),(38,'保存','/sys/manageuser/save',1,NULL,'',34,NULL,1,1,NULL,NULL,NULL,NULL),(39,'保存','/sys/manageuser/edit',1,NULL,'',36,NULL,1,1,NULL,NULL,NULL,NULL),(40,'查询','/sys/dictionary/dataGrid',1,NULL,'',6,NULL,1,1,NULL,NULL,NULL,NULL),(41,'新建','/sys/dictionary/addPage',1,NULL,'',6,NULL,2,1,NULL,NULL,NULL,NULL),(42,'编辑','/sys/dictionary/editPage',1,NULL,'',6,NULL,3,1,NULL,NULL,NULL,NULL),(43,'保存','/sys/dictionary/save',1,NULL,'',41,NULL,1,1,NULL,NULL,NULL,NULL),(44,'保存','/sys/dictionary/edit',1,NULL,'',42,NULL,1,1,NULL,NULL,NULL,NULL),(172,'疫情管理','',0,NULL,'icon-sum',0,NULL,4,1,NULL,NULL,NULL,NULL),(173,'小区管理','/ncov/region/listPage',0,NULL,'',172,NULL,1,1,NULL,NULL,NULL,NULL),(174,'返京管理','/ncov/inuser/listPage',0,NULL,'',172,NULL,2,1,NULL,NULL,NULL,NULL),(175,'小区查询','/ncov/region/dataGrid',1,NULL,'',173,NULL,1,1,NULL,NULL,NULL,NULL),(176,'查询','/ncov/inuser/dataGrid',1,NULL,'',174,NULL,1,1,NULL,NULL,NULL,NULL),(177,'访客管理','/ncov/outuser/listPage',0,NULL,'',172,NULL,3,1,NULL,NULL,NULL,NULL),(178,'查询','/ncov/outuser/dataGrid',1,NULL,'',177,NULL,1,1,NULL,NULL,NULL,NULL),(179,'健康登记','/ncov/record/listPage',0,NULL,'',172,NULL,4,1,NULL,NULL,NULL,NULL),(180,'登记查询','/ncov/record/dataGrid',1,NULL,'',179,NULL,1,1,NULL,NULL,NULL,NULL),(181,'日常出入','/ncov/user/listPage',0,NULL,'',172,NULL,3,1,NULL,NULL,NULL,NULL),(182,'查询','/ncov/user/dataGrid',1,NULL,'',181,NULL,1,1,NULL,NULL,NULL,NULL),(183,'楼号管理','/ncov/building/listPage',0,NULL,'',172,NULL,1,1,NULL,NULL,NULL,NULL),(184,'查询','/ncov/building/dataGrid',1,NULL,'',183,NULL,1,1,NULL,NULL,NULL,NULL),(185,'新建','/ncov/region/addPage',1,NULL,'',173,NULL,1,1,NULL,NULL,NULL,NULL),(186,'保存','/ncov/region/save',1,NULL,'',185,NULL,1,1,NULL,NULL,NULL,NULL),(187,'编辑','/ncov/region/editPage',1,NULL,'',173,NULL,1,1,NULL,NULL,NULL,NULL),(188,'保存','/ncov/region/edit',1,NULL,'',187,NULL,1,1,NULL,NULL,NULL,NULL),(189,'删除','/ncov/region/delete',1,NULL,'',173,NULL,3,1,NULL,NULL,NULL,NULL),(190,'新建','/ncov/building/addPage',1,NULL,'',183,NULL,1,1,NULL,NULL,NULL,NULL),(191,'保存','/ncov/building/save',1,NULL,'',190,NULL,1,1,NULL,NULL,NULL,NULL),(192,'编辑','/ncov/building/editPage',1,NULL,'',183,NULL,3,1,NULL,NULL,NULL,NULL),(193,'保存','/ncov/building/edit',1,NULL,'',192,NULL,1,1,NULL,NULL,NULL,NULL),(194,'删除','/ncov/building/delete',1,NULL,'',183,NULL,5,1,NULL,NULL,NULL,NULL),(195,'健康漏报','/ncov/inuser/listNoPage',0,NULL,'',172,NULL,5,1,NULL,NULL,NULL,NULL),(196,'查询','/ncov/inuser/dataNoGrid',1,NULL,'',195,NULL,1,1,NULL,NULL,NULL,NULL),(197,'删除','/ncov/user/delete',1,NULL,'',174,NULL,2,1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','超级管理员',NULL,NULL,'2016-11-15 10:50:20',NULL,NULL,NULL),(2,'社区管理员A','社区管理员A',NULL,NULL,'2016-11-15 13:31:38',NULL,'2020-02-12 20:31:33',NULL),(3,'社区管理员B','社区管理员B',NULL,NULL,'2019-12-25 14:46:01',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_role_resource`
--

LOCK TABLES `sys_role_resource` WRITE;
/*!40000 ALTER TABLE `sys_role_resource` DISABLE KEYS */;
INSERT INTO `sys_role_resource` VALUES (4123,3,0),(4124,3,3),(4125,3,139),(4126,3,140),(4127,3,169),(4128,3,170),(4129,3,171),(4776,1,0),(4777,1,1),(4778,1,2),(4779,1,34),(4780,1,38),(4781,1,35),(4782,1,36),(4783,1,39),(4784,1,37),(4785,1,4),(4786,1,25),(4787,1,26),(4788,1,29),(4789,1,27),(4790,1,30),(4791,1,28),(4792,1,31),(4793,1,32),(4794,1,33),(4795,1,5),(4796,1,16),(4797,1,20),(4798,1,17),(4799,1,23),(4800,1,18),(4801,1,19),(4802,1,24),(4803,1,6),(4804,1,40),(4805,1,41),(4806,1,43),(4807,1,42),(4808,1,44),(4809,1,172),(4810,1,173),(4811,1,175),(4812,1,185),(4813,1,186),(4814,1,187),(4815,1,188),(4816,1,189),(4817,1,174),(4818,1,176),(4819,1,197),(4820,1,177),(4821,1,178),(4822,1,179),(4823,1,180),(4824,1,181),(4825,1,182),(4826,1,183),(4827,1,184),(4828,1,190),(4829,1,191),(4830,1,192),(4831,1,193),(4832,1,194),(4833,1,195),(4834,1,196),(4835,2,0),(4836,2,172),(4837,2,173),(4838,2,175),(4839,2,185),(4840,2,186),(4841,2,187),(4842,2,188),(4843,2,189),(4844,2,174),(4845,2,176),(4846,2,197),(4847,2,177),(4848,2,178),(4849,2,179),(4850,2,180),(4851,2,181),(4852,2,182),(4853,2,183),(4854,2,184),(4855,2,190),(4856,2,191),(4857,2,192),(4858,2,193),(4859,2,194),(4860,2,195),(4861,2,196);
/*!40000 ALTER TABLE `sys_role_resource` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-17  9:31:54
