# Batrits Train — 技能培训管理系统（后端）

基于 Spring Boot 3 的技能培训机构教务管理系统后端，覆盖员工、部门、班级、学员的完整 CRUD 与数据报表，集成阿里云 OSS 文件上传与 AOP 操作审计。

> 前端仓库：[batrits-train-web](https://github.com/LinShinan/batrits-train-web)
>
> 后端仓库：[batrits-train](https://github.com/LinShinan/batrits-train)

## 技术栈

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.8-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-3.0.5-1E1E1E?style=flat-square&logo=mybatis&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat-square&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-JJWT_0.9.1-000000?style=flat-square&logo=jsonwebtokens&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-多模块-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-supported-DA291C?style=flat-square&logo=lombok&logoColor=white)
![Aliyun OSS](https://img.shields.io/badge/Aliyun_OSS-3.17.4-FF6A00?style=flat-square&logo=alibabacloud&logoColor=white)
![PageHelper](https://img.shields.io/badge/PageHelper-1.4.7-409EFF?style=flat-square)
![AOP](https://img.shields.io/badge/Spring_AOP-操作日志-6DB33F?style=flat-square&logo=spring&logoColor=white)
![RESTful API](https://img.shields.io/badge/API-RESTful-009688?style=flat-square)

## 项目结构

```
batrits-train
├── batrits-pojo        —— 公共实体 & DTO 模块
├── batrits-utils       —— 工具 & 第三方集成模块
└── batrits-management  —— 主业务模块（控制器 / 服务 / 数据访问）
```

| 模块 | 职责 |
|------|------|
| **batrits-pojo** | 实体类、查询参数 DTO、统一响应体 `Result<T>`、分页封装 `PageResult<T>` |
| **batrits-utils** | JWT 生成与解析、阿里云 OSS 上传组件、ThreadLocal 用户上下文 |
| **batrits-management** | REST 控制器、Service 层、MyBatis Mapper（注解 + XML）、过滤器 / 拦截器、AOP 切面、全局异常处理 |

## 分层架构

```
  Controller  ──  接收请求、参数绑定、调用 Service、返回 Result
      │
  Service     ──  业务逻辑编排、事务管理、数据校验
      │
  Mapper      ──  MyBatis 注解 + XML 混合：简单 SQL 用注解，动态 SQL 用 XML
      │
  Database    ──  MySQL 8.0，7 张表
```

- 横切关注点：AOP 操作日志、Filter 身份认证、`@RestControllerAdvice` 全局异常处理
- 分页策略：员工 / 学员使用 PageHelper 插件；班级使用手动 LIMIT/OFFSET + 单独 COUNT 查询
- 数据校验：Controller 层参数校验、Service 层业务规则校验（如删除前的依赖检查）

## 业务实现细节

### 认证与鉴权

- **登录**：`POST /login` 接收 JSON `{username, password}`，查 `emp` 表校验，通过后生成 JWT（HS256，12h 有效期），返回 `LoginInfo`（含 token）
- **请求拦截**：`TokenFilter`（`@WebFilter(urlPatterns="/*")`）拦截所有请求，`/login` 路径直接放行，其余从 `Token` 请求头提取 JWT 并校验
- **用户上下文**：校验通过后，从 JWT Claims 中提取员工 ID，存入 `CurrentHolder`（`ThreadLocal<Integer>`），请求结束在 `finally` 中 `remove()` 防止内存泄漏
- **401 响应**：Token 缺失或解析失败直接返回 `SC_UNAUTHORIZED`，不进入后续链路
- 采用 Servlet Filter 方案而非 Spring Interceptor，配合 `@ServletComponentScan` 扫描注册

### 操作日志（AOP）

- 自定义 `@OperateLog` 注解标记需要审计的方法（如部门 / 班级的增删改）
- `OperateLogAspect` 使用 `@Around` 环绕通知，记录：操作人 ID（从 `CurrentHolder` 取）、操作时间、类名、方法名、方法参数、返回值、执行耗时
- 日志写入 `operate_log` 表，**与业务事务独立**，即使业务回滚日志也不丢失

### 事务控制

- Service 层 `@Transactional` 管理事务边界
- `EmpServiceImpl.save()` 在 `try` 中做业务插入、`finally` 中调用 `EmpLogService.insertLog()` 写操作记录
- `EmpLogService` 使用 `Propagation.REQUIRES_NEW` 传播级别——开启新事务，**不参与外层事务**，保证日志在业务异常回滚时依然持久化

### 员工管理

- **分页查询**：PageHelper 自动拦截，`EmpMapper.list()` 使用 MyBatis `<where>` + `<if>` 动态拼接姓名模糊搜索、性别筛选、入职日期区间筛选，LEFT JOIN `dept` 获取部门名称
- **新增**：`@Transactional` 包裹，先插入 `emp` 获取自增 ID，再将 ID 回填给 `emp_expr` 列表，**批量插入**（`INSERT INTO ... VALUES (...), (...), (...)` 一次 SQL）替代逐条插入
- **删除**：批量删除员工及关联工作经历，两条 SQL 在同一事务中执行
- **编辑**：MyBatis `<set>` 动态 SQL 按需更新字段（密码 `md5()` 加密），工作经历采用 **先删后增** 策略避免并发冲突
- **详情查询**：`resultMap` + `<collection>` 实现 `emp` 与 `emp_expr` 的一对多映射，**一条 LEFT JOIN SQL** 查出全部数据，MyBatis 自动组装嵌套对象

### 部门管理

- **删除校验**：删除前查询该部门下员工数，`count > 0` 时抛出 `DataDependencyException("该部门下有员工，不能删除")`，由全局异常处理器捕获返回友好提示
- 增删改方法均标记 `@OperateLog`，自动审计

### 班级管理

- **状态动态计算**：不在数据库存储状态字段，查询时将 `beginDate` / `endDate` 与 `LocalDate.now()` 比较，得出"未开班 / 在读中 / 已结束"三种状态——保证数据实时准确，无冗余
- **手动分页**：区别于 PageHelper，在 XML 中手写 `LIMIT #{start}, #{pageSize}`，配合独立 `getTotal()` 查询，适用于参数复杂的场景
- **删除校验**：与部门同理，检查班级下是否存在学员

### 学员管理

- **多条件分页**：PageHelper + 动态 SQL，LEFT JOIN `clazz` 获取班级名称，支持姓名 / 学历 / 班级三维筛选
- **违纪扣分**：`updateViolation()` 执行单条 SQL——`violation_count = violation_count + 1, violation_score = violation_score + #{score}`，原子累加，无并发问题
- **批量删除**：MyBatis `<foreach>` 拼接 `DELETE FROM student WHERE id IN (...)`，一次 SQL 完成

### 数据报表

- `ReportServiceImpl` 调用 Mapper 返回 `List<Map<String, Object>>`，通过 Stream API 将原始结果转换为前端图表所需结构
- SQL 层使用 `CASE WHEN` + `GROUP BY` 完成聚合，减少应用层计算开销
- 四项统计：员工职位分布、员工性别分布、各班学员人数、学员学历分布

### 文件上传

- 接入阿里云 OSS，AccessKey 通过环境变量 `OSS_ACCESS_KEY_ID` / `OSS_ACCESS_KEY_SECRET` 注入，签名版本 V4
- 上传路径规则：`yyyy/MM/{uuid}.{原扩展名}`，按月份自动分区
- OSS 配置（endpoint / bucketName / region）通过 `@ConfigurationProperties(prefix="aliyun.oss")` 绑定，存放于 `application-secret.yaml`

### 全局异常处理

- `@RestControllerAdvice` 统一拦截异常，返回结构化 `Result.error()`
- `DuplicateKeyException` 解析 MySQL 报错信息，提取重复字段名返回 `"xxx已存在"`
- `DataDependencyException` 透传业务校验失败信息
- 兜底 `Exception` 返回通用提示，日志记录完整堆栈

## 数据库设计

| 表 | 字段要点 |
|------|------|
| `emp` | id, username, password, name, gender, phone, job(岗位编码), salary, image, entry_date, dept_id(FK), create_time, update_time |
| `emp_expr` | id, emp_id(FK), begin, end, company, job（员工工作经历，一对多） |
| `emp_log` | id, operate_time, info（员工操作记录，独立事务） |
| `dept` | id, name, create_time, update_time |
| `clazz` | id, name, room, begin_date, end_date, master_id(FK→emp), subject(学科编码), create_time, update_time |
| `student` | id, name, no(学号), gender, phone, id_card, is_college, address, degree(学历编码), graduation_date, clazz_id(FK), violation_count, violation_score, create_time, update_time |
| `operate_log` | id, operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time |

## 安全设计

- JWT 签名密钥与 OSS 配置存放于 `application-secret.yaml`，通过 `spring.config.import` 导入，已加入 `.gitignore`，提供 `.example.yaml` 模板
- OSS AccessKey 不落盘，仅通过环境变量读取
- 全局异常处理器返回统一 `Result` 结构，不暴露异常堆栈细节

## 快速启动

```bash
# 1. 克隆
git clone https://github.com/LinShinan/batrits-train.git

# 2. 配置密钥
cp batrits-management/src/main/resources/application-secret.example.yaml \
   batrits-management/src/main/resources/application-secret.yaml
# 按实际环境编辑 application-secret.yaml

# 3. 设置 OSS 环境变量
export OSS_ACCESS_KEY_ID=<your-key>
export OSS_ACCESS_KEY_SECRET=<your-secret>

# 4. 建库
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS train DEFAULT CHARSET utf8mb4;"

# 5. 编译运行
mvn clean package -DskipTests
java -jar batrits-management/target/batrits-management-1.0-SNAPSHOT.jar
```

应用默认运行在 `http://localhost:8080`。