# ==================表和测试数据 ========================
CREATE DATABASE IF NOT EXISTS train;
use train;

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

--
-- Table structure for table `clazz`
--

DROP TABLE IF EXISTS `clazz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clazz` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,主键',
  `name` varchar(30) NOT NULL COMMENT '班级名称',
  `room` varchar(20) DEFAULT NULL COMMENT '班级教室',
  `begin_date` date NOT NULL COMMENT '开课时间',
  `end_date` date NOT NULL COMMENT '结课时间',
  `master_id` int unsigned DEFAULT NULL COMMENT '班主任ID, 关联员工表ID',
  `subject` tinyint unsigned NOT NULL COMMENT '学科, 1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6: 嵌入式',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clazz`
--

LOCK TABLES `clazz` WRITE;
/*!40000 ALTER TABLE `clazz` DISABLE KEYS */;
INSERT INTO `clazz` VALUES (1,'JavaEE就业163期','212','2024-04-30','2024-06-29',10,1,'2024-06-01 17:08:23','2024-06-01 17:39:58'),(2,'前端就业90期','210','2024-07-10','2024-01-20',3,2,'2024-06-01 17:45:12','2024-06-01 17:45:12'),(3,'JavaEE就业165期','108','2024-06-15','2024-12-25',6,1,'2024-06-01 17:45:40','2024-06-01 17:45:40'),(4,'JavaEE就业166期','105','2024-07-20','2024-02-20',20,1,'2024-06-01 17:46:10','2024-06-01 17:46:10'),(5,'大数据就业58期','209','2024-08-01','2024-02-15',7,3,'2024-06-01 17:51:21','2024-06-01 17:51:21'),(6,'JavaEE就业167期','325','2024-11-20','2024-05-10',36,1,'2024-11-15 11:35:46','2024-12-13 14:31:24'),(7,'JavaEE170期','504','2025-12-12','2026-12-30',40,1,'2025-12-26 22:12:27','2026-02-15 19:44:33'),(8,'Java+AI就业2期','506','2025-12-11','2026-12-08',38,1,'2025-12-26 22:15:08','2025-12-27 10:00:03'),(9,'嵌入式18期','201','2026-05-01','2027-05-04',39,6,'2025-12-27 10:02:19','2025-12-27 10:03:04'),(12,'Go精通1班','510','2026-02-02','2027-02-10',2,5,'2026-02-15 16:15:46','2026-02-15 19:45:09');
/*!40000 ALTER TABLE `clazz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,主键',
  `name` varchar(10) NOT NULL COMMENT '部门名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (2,'教研部','2024-09-25 09:47:40','2024-09-25 09:47:40'),(3,'咨询部','2024-09-25 09:47:40','2024-09-30 21:26:24'),(4,'就业部','2024-09-25 09:47:40','2025-12-03 15:40:00'),(5,'人事部','2024-09-25 09:47:40','2024-09-25 09:47:40'),(6,'行政部👍','2024-11-30 20:56:37','2026-02-17 17:55:53'),(7,'策划部','2025-12-02 20:50:00','2025-12-03 15:39:45'),(8,'财务部😀','2025-12-02 20:54:32','2025-12-03 21:16:40'),(11,'研发部','2025-12-02 21:03:24','2025-12-02 21:03:24'),(12,'开发部','2025-12-02 21:09:34','2025-12-02 21:09:34'),(13,'后勤部','2025-12-03 15:50:25','2025-12-03 15:50:25');
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,主键',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT '123456' COMMENT '密码',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `gender` tinyint unsigned NOT NULL COMMENT '性别, 1:男, 2:女',
  `phone` char(11) NOT NULL COMMENT '手机号',
  `job` tinyint unsigned DEFAULT NULL COMMENT '职位, 1 班主任, 2 讲师 , 3 学工主管, 4 教研主管, 5 咨询师',
  `salary` int unsigned DEFAULT NULL COMMENT '薪资',
  `image` varchar(255) DEFAULT NULL COMMENT '头像',
  `entry_date` date DEFAULT NULL COMMENT '入职日期',
  `dept_id` int unsigned DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT INTO `emp` VALUES (1,'shinaian','123456','施耐庵',1,'13309090001',4,15000,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2000-01-01',2,'2023-10-20 16:35:33','2023-11-16 16:11:26'),(2,'songjiang','123456','宋江',1,'13309090002',2,8600,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2015-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:37'),(3,'lujunyi','123456','卢俊义',1,'13309090003',2,8900,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2008-05-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:39'),(4,'wuyong','123456','吴用',1,'13309090004',2,9200,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2007-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:41'),(5,'gongsunsheng','123456','公孙胜',1,'13309090005',2,9500,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2012-12-05',2,'2023-10-20 16:35:33','2023-10-20 16:35:43'),(6,'huosanniang','123456','扈三娘',2,'13309090006',3,6500,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2013-09-05',1,'2023-10-20 16:35:33','2023-10-20 16:35:45'),(7,'chaijin','123456','柴进',1,'13309090007',1,4700,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2005-08-01',1,'2023-10-20 16:35:33','2023-10-20 16:35:47'),(8,'likui','123456','李逵',1,'13309090008',1,4800,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2014-11-09',1,'2023-10-20 16:35:33','2023-10-20 16:35:49'),(9,'wusong','123456','武松',1,'13309090009',1,4900,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2011-03-11',1,'2023-10-20 16:35:33','2023-10-20 16:35:51'),(10,'linchong','123456','林冲',1,'13309090010',1,5000,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2013-09-05',1,'2023-10-20 16:35:33','2023-10-20 16:35:53'),(11,'huyanzhuo','123456','呼延灼',1,'13309090011',2,9700,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2007-02-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:55'),(12,'xiaoliguang','123456','小李广',1,'13309090012',2,10000,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2008-08-18',2,'2023-10-20 16:35:33','2023-10-20 16:35:57'),(13,'yangzhi','123456','杨志',1,'13309090013',1,5300,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2012-11-01',1,'2023-10-20 16:35:33','2023-10-20 16:35:59'),(14,'shijin','123456','史进',1,'13309090014',2,10600,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2002-08-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:01'),(15,'sunerniang','123456','孙二娘',2,'13309090015',2,10900,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2011-05-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:03'),(16,'luzhishen','123456','鲁智深',1,'13309090016',2,9600,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2010-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:05'),(17,'liying','12345678','李应',1,'13309090017',1,5800,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2015-03-21',1,'2023-10-20 16:35:33','2023-10-20 16:36:07'),(18,'shiqian','123456','时迁',1,'13309090018',2,10200,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2015-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:09'),(19,'gudasao','123456','顾大嫂',2,'13309090019',2,10500,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2008-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:11'),(20,'ruanxiaoer','123456','阮小二',1,'13309090020',2,10800,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2018-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:13'),(21,'ruanxiaowu','123456','阮小五',1,'13309090021',5,5200,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2015-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:15'),(22,'ruanxiaoqi','123456','阮小七',1,'13309090022',5,5500,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2016-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:17'),(23,'ruanji','123456','阮籍',1,'13309090023',5,5800,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2012-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:19'),(24,'tongwei','123456','童威',1,'13309090024',5,5000,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2006-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:21'),(25,'tongmeng','123456','童猛',1,'13309090025',5,4800,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2002-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:23'),(26,'yanshun','123456','燕顺',1,'13309090026',5,5400,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2011-01-01',3,'2023-10-20 16:35:33','2023-11-08 22:12:46'),(30,'liyun','14e1b600b1fd579f47433b88e8d85291','李云',1,'13309090030',3,7000,'https://batrits-java-ai.oss-cn-beijing.aliyuncs.com/2026/05/e3425b06-93e2-4555-aa24-fcf834deaa61.png','2020-03-01',12,'2023-10-20 16:35:33','2026-05-21 15:00:49'),(36,'linghuchong','123456','令狐冲',1,'18809091212',2,6800,'https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg','2023-10-19',2,'2023-10-20 20:44:54','2023-11-09 09:41:04'),(38,'hanli_bat',NULL,'韩立',1,'12345678988',2,10000,'','2025-12-13',12,'2025-12-13 14:29:59','2025-12-22 16:10:47'),(39,'ziling',NULL,'紫灵',2,'13140000520',5,9000,'https://batrits-java-ai.oss-cn-beijing.aliyuncs.com/2026/02/cdcb0216-7da7-4916-b06f-f00fde974c49.png','2025-12-13',3,'2025-12-13 15:55:02','2026-02-14 15:26:19'),(40,'meining',NULL,'梅凝',2,'10258888520',3,10000,'https://batrits-java-ai.oss-cn-beijing.aliyuncs.com/2025/12/65b44cf2-2e6d-4fb5-8bad-ec3ed51ffce2.png','2025-12-13',5,'2025-12-13 17:23:52','2025-12-21 21:15:57'),(41,'qiankong',NULL,'千空',1,'13665664661',2,10000,'','2025-12-13',2,'2025-12-13 17:26:44','2025-12-13 17:26:44'),(50,'ssss',NULL,'莎莎',1,'13000123451',2,12222,'https://batrits-java-ai.oss-cn-beijing.aliyuncs.com/2025/12/8e5b2347-925d-407b-a84c-50f1d938b5f3.jpg','2025-12-17',4,'2025-12-17 22:05:53','2026-02-12 22:47:17'),(51,'qinmu',NULL,'秦牧',1,'13552302239',1,12000,'https://batrits-java-ai.oss-cn-beijing.aliyuncs.com/2026/02/dc460e36-df56-4a66-8534-99226873572b.png','2026-02-11',11,'2026-02-12 15:40:10','2026-02-12 15:40:10');
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_expr`
--

DROP TABLE IF EXISTS `emp_expr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_expr` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID, 主键',
  `emp_id` int unsigned DEFAULT NULL COMMENT '员工ID',
  `begin` date DEFAULT NULL COMMENT '开始时间',
  `end` date DEFAULT NULL COMMENT '结束时间',
  `company` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `job` varchar(50) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工作经历';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_expr`
--

LOCK TABLES `emp_expr` WRITE;
/*!40000 ALTER TABLE `emp_expr` DISABLE KEYS */;
INSERT INTO `emp_expr` VALUES (11,40,'2025-12-10','2025-12-10','Batrits科技有限公司','前端工程师'),(12,40,'2025-01-16','2025-12-11','Batrits体育有限公司','教练'),(17,38,'2018-12-06','2019-01-12','百度','后端工程师'),(18,38,'2025-12-01','2025-12-01','阿里','全栈工程师'),(19,51,'2021-02-02','2026-02-01','百度','客服'),(20,50,'2025-02-04','2026-02-03','字节','Java开发'),(21,39,'2024-12-06','2025-07-18','杭州咨询有限公司','咨询师');
/*!40000 ALTER TABLE `emp_expr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_log`
--

DROP TABLE IF EXISTS `emp_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID, 主键',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `info` varchar(2000) DEFAULT NULL COMMENT '日志信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_log`
--

LOCK TABLES `emp_log` WRITE;
/*!40000 ALTER TABLE `emp_log` DISABLE KEYS */;
INSERT INTO `emp_log` VALUES (1,'2025-12-14 14:58:21','新增员工Emp(id=46, username=bbb, password=null, name=bbb, gender=1, phone=11111111111, job=1, salary=10000, image=, entryDate=2025-12-11, deptId=6, createTime=2025-12-14T14:58:21.123852200, updateTime=2025-12-14T14:58:21.123852200, deptName=null, exprList=[EmpExpr(id=null, empId=null, begin=2025-12-10, end=2026-01-17, company=dddd, job=ddd)])'),(2,'2025-12-14 15:01:58','新增员工Emp(id=47, username=bbb, password=null, name=bbb, gender=1, phone=11111111111, job=1, salary=10000, image=, entryDate=2025-12-11, deptId=6, createTime=2025-12-14T15:01:57.365708300, updateTime=2025-12-14T15:01:57.365708300, deptName=null, exprList=[EmpExpr(id=null, empId=47, begin=2025-12-10, end=2026-01-17, company=dddd, job=ddd)])'),(3,'2025-12-17 21:57:08','新增员工Emp(id=48, username=cccc, password=null, name=cc, gender=1, phone=15558888666, job=4, salary=10000, image=null, entryDate=2025-12-17, deptId=2, createTime=2025-12-17T21:57:07.529235300, updateTime=2025-12-17T21:57:07.529235300, deptName=null, exprList=[EmpExpr(id=null, empId=48, begin=null, end=null, company=, job=)])'),(4,'2025-12-17 22:05:11','新增员工Emp(id=null, username=ddddd, password=null, name=dd, gender=1, phone=11111111111, job=2, salary=6111, image=https://batrits-java-ai.oss-cn-beijing.aliyuncs.com/2025/12/8e5b2347-925d-407b-a84c-50f1d938b5f3.jpg, entryDate=2025-12-17, deptId=4, createTime=2025-12-17T22:05:10.359773800, updateTime=2025-12-17T22:05:10.359773800, deptName=null, exprList=[])'),(5,'2025-12-17 22:05:53','新增员工Emp(id=50, username=ddddd, password=null, name=dd, gender=1, phone=12000123451, job=2, salary=6111, image=https://batrits-java-ai.oss-cn-beijing.aliyuncs.com/2025/12/8e5b2347-925d-407b-a84c-50f1d938b5f3.jpg, entryDate=2025-12-17, deptId=4, createTime=2025-12-17T22:05:53.050595700, updateTime=2025-12-17T22:05:53.050595700, deptName=null, exprList=[])'),(6,'2026-02-12 15:40:11','新增员工Emp(id=51, username=qinmu, password=null, name=秦牧, gender=1, phone=13552302239, job=1, salary=12000, image=https://batrits-java-ai.oss-cn-beijing.aliyuncs.com/2026/02/dc460e36-df56-4a66-8534-99226873572b.png, entryDate=2026-02-11, deptId=11, createTime=2026-02-12T15:40:10.458708700, updateTime=2026-02-12T15:40:10.458708700, deptName=null, exprList=[EmpExpr(id=null, empId=51, begin=2021-02-02, end=2026-02-01, company=百度, job=客服)])'),(7,'2026-02-13 11:35:47','新增员工Emp(id=52, username=gege, password=null, name=格格, gender=2, phone=14000000001, job=1, salary=9000, image=, entryDate=2026-02-11, deptId=8, createTime=2026-02-13T11:35:46.948617300, updateTime=2026-02-13T11:35:46.948617300, deptName=null, exprList=[])');
/*!40000 ALTER TABLE `emp_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operate_log`
--

DROP TABLE IF EXISTS `operate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operate_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `operate_emp_id` int unsigned DEFAULT NULL COMMENT '操作人ID',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `class_name` varchar(100) DEFAULT NULL COMMENT '操作的类名',
  `method_name` varchar(100) DEFAULT NULL COMMENT '操作的方法名',
  `method_params` varchar(2000) DEFAULT NULL COMMENT '方法参数',
  `return_value` varchar(2000) DEFAULT NULL COMMENT '返回值',
  `cost_time` bigint unsigned DEFAULT NULL COMMENT '方法执行耗时, 单位:ms',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operate_log`
--

LOCK TABLES `operate_log` WRITE;
/*!40000 ALTER TABLE `operate_log` DISABLE KEYS */;
INSERT INTO `operate_log` VALUES (11,10,'2026-01-29 17:21:03','com.batrits.controller.DeptController','update','[Dept(id=16, name=666222, createTime=2026-01-29T16:21:05, updateTime=2026-01-29T17:21:02.942669400)]','Result(code=1, msg=success, data=Dept(id=16, name=666222, createTime=2026-01-29T16:21:05, updateTime=2026-01-29T17:21:02.942669400))',13),(12,10,'2026-01-29 17:28:29','com.batrits.controller.DeptController','deleteById','[16]','Result(code=1, msg=success, data=null)',1701),(13,10,'2026-01-29 17:29:04','com.batrits.controller.DeptController','add','[Dept(id=null, name=mmm, createTime=2026-01-29T17:29:03.543045, updateTime=2026-01-29T17:29:03.543045)]','Result(code=1, msg=success, data=null)',6),(14,10,'2026-01-29 17:29:26','com.batrits.controller.DeptController','update','[Dept(id=17, name=mmms, createTime=2026-01-29T17:29:04, updateTime=2026-01-29T17:29:25.848664800)]','Result(code=1, msg=success, data=Dept(id=17, name=mmms, createTime=2026-01-29T17:29:04, updateTime=2026-01-29T17:29:25.848664800))',6),(15,10,'2026-01-29 17:29:36','com.batrits.controller.DeptController','deleteById','[17]','Result(code=1, msg=success, data=null)',19),(16,2,'2026-01-29 17:45:28','com.batrits.controller.ClazzController','save','[Clazz(id=11, name=5555, room=121, beginDate=2026-01-01, endDate=2026-01-29, masterId=45, subject=1, createTime=null, updateTime=null, masterName=null, status=null)]','Result(code=1, msg=success, data=null)',18),(17,2,'2026-01-29 17:45:53','com.batrits.controller.ClazzController','update','[Clazz(id=11, name=5555, room=121, beginDate=2026-01-01, endDate=2026-02-28, masterId=45, subject=1, createTime=2026-01-29T17:45:28, updateTime=2026-01-29T17:45:52.976903800, masterName=null, status=null)]','Result(code=1, msg=success, data=null)',20),(18,2,'2026-01-29 17:46:11','com.batrits.controller.ClazzController','deleteById','[11]','Result(code=1, msg=success, data=null)',36),(19,NULL,'2026-02-09 15:04:54','com.batrits.controller.DeptController','add','[Dept(id=null, name=人事二部, createTime=2026-02-09T15:04:54.223798900, updateTime=2026-02-09T15:04:54.223798900)]','Result(code=1, msg=success, data=null)',40),(20,NULL,'2026-02-09 22:38:45','com.batrits.controller.DeptController','add','[Dept(id=null, name=人事2部, createTime=2026-02-09T22:38:44.413255400, updateTime=2026-02-09T22:38:44.413255400)]','Result(code=1, msg=success, data=null)',74),(21,NULL,'2026-02-09 22:38:55','com.batrits.controller.DeptController','add','[Dept(id=null, name=人事3部, createTime=2026-02-09T22:38:55.004054100, updateTime=2026-02-09T22:38:55.004054100)]','Result(code=1, msg=success, data=null)',12),(22,NULL,'2026-02-10 10:29:17','com.batrits.controller.DeptController','update','[Dept(id=26, name=人事三部, createTime=2026-02-09T22:38:55, updateTime=2026-02-10T10:29:17.083153800)]','Result(code=1, msg=success, data=Dept(id=26, name=人事三部, createTime=2026-02-09T22:38:55, updateTime=2026-02-10T10:29:17.083153800))',33),(23,NULL,'2026-02-10 11:13:05','com.batrits.controller.DeptController','deleteById','[26]','Result(code=1, msg=success, data=null)',64),(24,NULL,'2026-02-10 11:13:19','com.batrits.controller.DeptController','deleteById','[25]','Result(code=1, msg=success, data=null)',13),(25,NULL,'2026-02-10 14:06:43','com.batrits.controller.DeptController','deleteById','[18]','Result(code=1, msg=success, data=null)',17),(26,10,'2026-02-15 16:15:47','com.batrits.controller.ClazzController','save','[Clazz(id=12, name=Go精通1班, room=504, beginDate=2026-02-02, endDate=2027-02-10, masterId=39, subject=5, createTime=null, updateTime=null, masterName=null, status=null)]','Result(code=1, msg=success, data=null)',80),(27,10,'2026-02-15 19:44:03','com.batrits.controller.ClazzController','update','[Clazz(id=12, name=Go精通1班, room=504, beginDate=2026-02-02, endDate=2027-02-10, masterId=40, subject=5, createTime=2026-02-15T16:15:46, updateTime=2026-02-15T19:44:03.372080300, masterName=null, status=null)]','Result(code=1, msg=success, data=null)',41),(28,10,'2026-02-15 19:44:33','com.batrits.controller.ClazzController','update','[Clazz(id=7, name=JavaEE170期, room=504, beginDate=2025-12-12, endDate=2026-12-30, masterId=40, subject=1, createTime=2025-12-26T22:12:27, updateTime=2026-02-15T19:44:32.696131400, masterName=null, status=null)]','Result(code=1, msg=success, data=null)',10),(29,10,'2026-02-15 19:45:09','com.batrits.controller.ClazzController','update','[Clazz(id=12, name=Go精通1班, room=510, beginDate=2026-02-02, endDate=2027-02-10, masterId=2, subject=5, createTime=2026-02-15T16:15:46, updateTime=2026-02-15T19:45:09.275283700, masterName=null, status=null)]','Result(code=1, msg=success, data=null)',5),(30,10,'2026-02-15 19:48:36','com.batrits.controller.ClazzController','save','[Clazz(id=13, name=前端哈哈哈, room=202, beginDate=2026-02-02, endDate=2028-02-10, masterId=7, subject=2, createTime=null, updateTime=null, masterName=null, status=null)]','Result(code=1, msg=success, data=null)',16),(31,10,'2026-02-15 19:49:49','com.batrits.controller.ClazzController','deleteById','[13]','Result(code=1, msg=success, data=null)',58),(32,10,'2026-02-17 17:55:53','com.batrits.controller.DeptController','update','[Dept(id=6, name=行政部👍, createTime=2024-11-30T20:56:37, updateTime=2026-02-17T17:55:53.413869800)]','Result(code=1, msg=success, data=Dept(id=6, name=行政部👍, createTime=2024-11-30T20:56:37, updateTime=2026-02-17T17:55:53.413869800))',30);
/*!40000 ALTER TABLE `operate_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,主键',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `no` char(10) NOT NULL COMMENT '学号',
  `gender` tinyint unsigned NOT NULL COMMENT '性别, 1: 男, 2: 女',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `id_card` char(18) NOT NULL COMMENT '身份证号',
  `is_college` tinyint unsigned NOT NULL COMMENT '是否来自于院校, 1:是, 0:否',
  `address` varchar(100) DEFAULT NULL COMMENT '联系地址',
  `degree` tinyint unsigned DEFAULT NULL COMMENT '最高学历, 1:初中, 2:高中, 3:大专, 4:本科, 5:硕士, 6:博士',
  `graduation_date` date DEFAULT NULL COMMENT '毕业时间',
  `clazz_id` int unsigned NOT NULL COMMENT '班级ID, 关联班级表ID',
  `violation_count` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '违纪次数',
  `violation_score` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '违纪扣分',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `no` (`no`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `id_card` (`id_card`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'段誉','2022000001',1,'18800000001','110120000300200001',1,'北京市昌平区建材城西路1号',1,'2021-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-15 16:20:59'),(2,'萧峰','2022000002',1,'18800210003','110120200301202212',1,'北京市昌平区建材城西路2号',4,'2022-07-01',12,2,3,'2024-11-14 21:22:19','2026-05-21 14:51:35'),(3,'虚竹','2022000003',1,'18800013001','110120000300200003',1,'北京市昌平区建材城西路3号',2,'2024-07-01',1,2,6,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(4,'萧远山','2022000004',1,'18800003211','110120000300200004',1,'北京市昌平区建材城西路4号',3,'2024-07-01',1,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(5,'阿朱','2022000005',2,'18800160002','110120000300200005',1,'北京市昌平区建材城西路5号',4,'2020-07-01',1,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(6,'阿紫','2022000006',2,'18800000034','110120000300200006',1,'北京市昌平区建材城西路6号',4,'2021-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(7,'游坦之','2022000007',1,'18800000067','110120000300200007',1,'北京市昌平区建材城西路7号',4,'2022-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(8,'康敏','2022000008',2,'18800000077','110120000300200008',1,'北京市昌平区建材城西路8号',5,'2024-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(9,'徐长老','2022000009',1,'18800000341','110120000300200009',1,'北京市昌平区建材城西路9号',3,'2024-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(10,'云中鹤','2022000010',1,'18800006571','110120000300200010',1,'北京市昌平区建材城西路10号',2,'2020-07-01',2,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(11,'钟万仇','2022000011',1,'18800000391','110120000300200011',1,'北京市昌平区建材城西路11号',4,'2021-07-01',1,1,1,'2024-11-14 21:22:19','2024-11-15 16:21:24'),(14,'天山童姥','2022000014',2,'18800009201','110120000300200014',1,'北京市昌平区建材城西路14号',4,'2024-07-01',1,0,0,'2024-11-14 21:22:19','2024-11-15 16:21:17'),(15,'刘竹庄','2022000015',1,'18800009401','110120000300200015',1,'北京市昌平区建材城西路15号',3,'2020-07-01',4,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(16,'李春来','2022000016',1,'18800008501','110120000300200016',1,'北京市昌平区建材城西路16号',4,'2021-07-01',4,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(17,'王语嫣','2022000017',2,'18800007601','110120000300200017',1,'北京市昌平区建材城西路17号',2,'2022-07-01',4,0,0,'2024-11-14 21:22:19','2024-11-14 21:22:19'),(18,'郑成功','2024001101',1,'13309092345','110110110110110110',0,'北京市昌平区回龙观街道88号',5,'2021-07-01',3,2,7,'2024-11-15 16:26:18','2024-11-15 16:40:10'),(19,'猪猪侠','2023082713',1,'13551010235','330322201005251237',1,'童话世界',2,'2025-12-16',2,2,3,'2025-12-29 21:47:35','2026-02-17 17:57:42'),(20,'威艳','2022082511',83,'13806685314','430100193606062580',1,'宇旁4号',4,'2025-08-30',7,2,3,'2025-12-29 21:51:53','2025-12-30 17:18:12');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-21 15:38:08
