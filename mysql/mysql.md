# 笔记

1. MySQL 连接报错："Public Key Retrieval is not allowed"。链接加上 `&allowPublicKeyRetrieval=true`，或者执行：`ALTER USER 'username'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';`

# Docker-Compose 配置

```yml
services:
  mysql:
    image: mysql:8.1.0
    container_name: mysql
    hostname: ivfzhou-docker-mysql
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.142
    environment:
      MYSQL_ROOT_PASSWORD: 123456
      MYSQL_DATABASE: db_xxx # 启动创建数据库
      MYSQL_USER: user # 创建新用户，拥有数据库 db_xxx 所有权限。不要设置成 root
      MYSQL_PASSWORD: password # 新用户密码
      MYSQL_ONETIME_PASSWORD: yes # root 用户登录后就要修改密码
      MYSQL_INITDB_SKIP_TZINFO: yes # 跳过时区解析
      MYSQL_ROOT_PASSWORD_FILE: /path/to/file # root 用户密码在容器指定文件中获取。以上环境变量也可以追加 _FILE。
      MYSQL_ALLOW_EMPTY_PASSWORD: yes # root 用户密码为空
      MYSQL_RANDOM_ROOT_PASSWORD: yes # root 用户密码随机，在控制台打印密码
    ports:
      - "3306:3306"
    volumes:
      - /home/ivfzhou/volumes/mysql/data/:/var/lib/mysql/:rw
      - /home/ivfzhou/volumes/mysql/config/:/etc/mysql/conf.d/:rw
      - /home/ivfzhou/volumes/mysql/log/:/logs/:rw
      - xxx:/docker-entrypoint-initdb.d # 这个目录下的 SQL 文件将被导入到数据库中，默认数据库是变量 MYSQL_DATABASE 的值。
networks:
  network:
    name: ivfzhou-docker-network
    driver: bridge
    attachable: true
    ipam:
      driver: default
      config:
        - subnet: 172.16.3.0/24
          gateway: 172.16.3.1
```
1. mkdir -p /home/ivfzhou/volumes/mysql
1. sudo chown -R 999:999 /home/ivfzhou/volumes/mysql
1. docker-compose -f docker-compose.yml up -d mysql

# 配置文件

[my.cnf](./my.cnf)

# 注释

\# 单行注释  
-- 单行注释  
/**/ 多行注释

# 数据类型

1. TINYINT(l)：1 字节。范围：有符号 [-128, 127]，无符号 [0, 255]。后加 unsigned 表示为无符号范围。后加 zerofill 表示数值不足指定的位数时以 0 来填充
1. SMALLINT(l)：2 字节。范围：有符号 [-32768, 32767]，无符号 [0, 65535]
1. MEDIUMINT(l)：3 字节。范围：有符号 [-8388608, 8388607]，无符号 [0, 16777215]
1. INT|INTERGE(l)：4 个字节。范围：有符号 [-2147483648, 2147483647]，无符号 [0, 4294967295]
1. BIGINT(l)：8 字节。范围：有符号 [-9233372036854775808, 9223372036854775807]，无符号 [0, 18446744073709551615]
1. FLOAT(总位数 m, 小数位数 d)：4 字节。浮点数类型
1. DOUBLE(m, d)：8 个字节。浮点数类型
1. DECIMAL(m, d)：大小：如果 M>D,为 M+2 否则为 D+2。(精度 1-255 默认 10，标度 1-30 默认 0)。定点数类型。例如 decimal(5,2) 能够存储具有总共五位数有两位小数的任何值
1. CHAR(n)：大小：字符数大小，范围 [0, 255] 字符。固定内存空间大小存值
1. VARCHAR(n)：大小：范围 [0, 65535] 字节。如字符编码为 UTF-8，则大小范围 [0, 16383] 字符。GBK 为 [0, 32767] 字符。会额外用一两个字节储存数据长度
1. TINYTEXT：大小：[0, 255] 字节。只对 max_sort_length 个字节排序
1. TEXT：大小：[0, 65535] 字节
1. MEDIUMTEXT：大小：[0, 16777215] 字节（16MB）
1. LONGTEXT：大小：[0, 4294967295] 字节（4GB）
1. ENUM('elem1', 'elem2', ...)：枚举类型。插入数据时可以选下标也可指定值。最多 65535 个成员。成员尾部空格自动删除。默认值为 NULL。如果字段为 NOT NULL 则默认值为枚举第 1 个成员
1. SET('elem1', 'elem2', ...)：去重集合。插入数据时可以选下标也可指定值，插入多个值时，值之间用逗号分割，可以插入多个值。值尾部空格自动删除。默认值为 NULL
1. BIT(x)：位数据类型。最大 64 位，默认 1 位。值不足位数前面补 0
1. BINARY(字节)
1. VARBINATY(字节)：最大数为 65535
1. TINYBLOB：大小：[0, 255] 字节。只对 max_sort_length 个字节排序
1. BLOB：大小：[0, 65535] 字节
1. MEDIUMBLOB：大小：[0, 16777215] 字节
1. LONGBLOB：大小：[0, 4294967295] 字节
1. YEAR：1字节。格式：YYYY，范围 [1901, 2155]。插入字符型或者整型两位数：1-69 表示 2001-2069，70-99 表示 1970-1999。字符 0 表示 2000，而整型 0 表示 0000
1. DATE：3字节。格式：YYYY-MM-dd，范围 [0000-01-01,9999-12-31]。插入字符型或者整型两位数：1-69 表示 2001-2069，70-99 表示 1970-1999  
   字符型格式：'YYYY-MM-dd'、'YY-MM-dd'、'YYYYMMdd'、'YYMMdd'。中间分割符-可以替换成 ./@+*  
   整形格式：YYYYMMdd、YYMMdd、(00)MMdd
1. TIME(i)：3 字节。格式：HHH:mm:ss，范围 [-838:59:59, 838:59:59]
   字符型格式：'D HH:mm:ss'、'HHH:mm:ss'、'HH:mm'、'D HH'、'HHHmmss'、'mmss'、'ss'  
   整型格式：HHHmmss、mmss、ss
1. TIMESTAMP(i)：4 字节。格式：YYYY-MM-dd hh:mm:ss UTC，范围 (1970-01-01 08:00:00, 2038-1-19 11:14:07]。插入字符型或者整型两位数：1-69 表示 2001-2069，70-99 表示 1970-1999
   字符型格式：'YYYY-MM-dd HH:mm:ss'、'YYYY-MM-dd'、'YY-MM-dd'、'YYMMddHHmmss'、'YYMMddHHmm'、'YYYYMMdd'、'YYMMdd'  
   整形格式：YYYYMMddHHmmss、YYMMddHHmmss、YYYYMMdd、YYMMdd、00MMdd
1. DATETIME(i)：8 字节。格式：YYYY-MM-dd HH:mm:ss，范围 [0000-01-01 00:00:00, 9999-12-31 23:59:59]。插入字符型或者整型两位数：1-69 表示 2001-2069，70-99 表示 1970-1999
   字符型格式：'YYYY-MM-dd HH:mm:ss'、'YYYY-MM-dd'、'YY-MM-dd'、'YYMMddHHmmss'、'YYMMddHHmm'、'YYYYMMdd'、'YYMMdd'  
   整形格式：YYYYMMddHHmmss、YYMMddHHmmss、YYYYMMdd、YYMMdd、00MMdd

# 约束

- PRIMARY KEY 主键约束，不能重复，不能为null
- UNIQUE KEY 唯一约束，不能重复，可以为null
- NOT NULL 非空约束
- DEFAULT 默认约束
- FOREGIN KEY 外键约束
- AUTO_INCREMENT 自动增长
- CHECK 选择约束。check(sex='男' or sex='女')、check(sex in ('男', '女'))
- ON DELETE|UPDATE RESTRICT 不允许父表删除更新有后代的元组
- ON DELETE|UPDATE CASCADE 同步删除和更新
- ON DELETE|UPDATE SET NULL 对应元组属性设置为空值
- ON DELETE|UPDATE SET DEFAULT 对应元组属性置位默认值
- CREATE ASSERTION 名 CHECK (条件) 定义断言

# 库

- CREATE DATABASE [IF NOT EXISTS] `数据库名` [DEFAULT CHARACTER SET `字符名`] [DEFAULT COLLATE utf8mb4_unicode_ci]; 新建数据库
- ALTER DATABASE|SCHEMA `数据库名`（省略表示选择当前的数据库）[DEFAULT CHARACTER SET `字符名`] [DEFAULT COLLATE utf8mb4_unicode_ci]; 修改数据库字符编码
- DROP DATABASE [IF EXISTS] `数据库名`; 删除数据库
- USE `数据库名`; 进入数据库
- SHOW DATABASES [LIKE '数据库名']; 显示数据库
- SHOW CREATE DATABASE `数据库名`; 查看创建数据库的语句
- SELECT DATABASE(); 查看当前数据库

# 表

- CREATE TABLE [IF NOT EXISTS] `表名` (  
  `字段名` 数据类型(大小) 约束 COMMENT '注释内容' COLLATE utf8mb4_unicode_ci DEFAULT 函数或者值 ON UPDATE 函数或者值,  
  [UNIQUE INDEX `索引名`(`字段名`(长度),...)],  
  [KEY `索引名`(`字段名`,...)],  
  [CONSTRAINT `约束名` FOREIGN KEY(`字段名`,...) REFERENCES `目标表名`(`目标字段名`,...)],  
  [PRIMARY KEY(`字段`,...)],  
  [UNIQUE KEY(`字段`,...)],  
  [CONTRAINT CHECK(表达式)],  
) [CHARSET=字符名] COMMENT '注释内容' ENGINE 储存引擎 AUTO_INCREMENT=数字; 新建表，两表之间外键列的数据类型一定要与关联的主键的类型一致
- ALTER TABLE xxx CONVERT TO CHARACTER SET xxx COLLATE utf8mb4_general_ci;
- ALTER TABLE xxx DEFAULT CHARACTER SET xxx COLLATE xxx;
- CREATE TABLE `表名` [AS] SELECT 查询语句;
- CREATE TABLE `表名` LIKE `其它表名`; 创建表，结构和参考表一样。
- ALTER TABLE `表名`
  - ADD `字段名` 数据类型(大小) 约束 COMMENT '注释内容' [FIRST|AFTER `参考字段`]; 添加表字段
  - MODIFY `字段名` 数据类型(大小) 约束 COMMENT '注释内容'; 修改表字段
  - DROP `字段名`; 删除表字段
  - CHANGE `旧字段名` `新字段名` 数据类型(大小) 约束 COMMENT '注释内容'; 更改表字段名
  - COMMENT '注释内容'; 修改表注释
  - ADD 约束语句; 添加约束，外、主键、唯一、检查约束
  - ADD PRIMARY KEY(字段,...); 添加主键
  - DROP FOREIGN KEY `外键名`; 删除主键约束
  - DROP INDEX `唯一约束名`; 删除字段的唯一约束
  - DROP CONSTRAINT `检查约束名`; 删除检测约束
  - DROP PRIMARY KEY; 删除主键约束
  - CHARACTER SET 字符名; 更改表字符编码
  - RENAME [TO] `新表名`;
  - ALTER COLUMN 字段 SET DEFAULT 默认值; 设置字段默认值，这是修改默认值文件，并不会锁表重建表
- DROP TABLE [IF EXISTS] `表名`,...; 删除表
- RENAME TABLE `旧表名` TO `新表名`; 更改表名
- INSERT INTO `表名`(字段...) SELECT 查询语句;
- INSERT INTO `表名` SET `字段`=值,...; 插入一行数据
- INSERT INTO `表名` (`字段名`,...) VALUES (字段值,...),...  
  [ON DUPLICATE KEY UPDATE `字段名`=字段值|VALUES(`字段名`)],... 如果存在唯一、主键冲突则更新该行指定的字段; 如果插入空值，请使用null。插入的日期和字符一样，都使用单引号括起来
- UPDATE `表名` SET `字段名`=字段值,... WHERE 条件 ORDER BY `字段名`|表达式或者第几列,... ASC|DESC LIMIT 起始行,条数; 更改数据  
  SET `字段名` = CASE `字段名` WHEN 字段值 THEN 字段值,...; case-when-then 更新字段值
- UPDATE `表名` JOIN `表名` ON 连接条件 SET `字段名`=字段值,... WHERE 条件; 连表更改数据
- DELETE FROM `表名` WHERE 条件 ORDER BY `字段名`|表达式或者第几列,... ASC|DESC LIMIT 起始行,条数; 删除数据，自增种子还在自增
- DETELE 别名1,别名2 FROM `表名` AS `别名1` JOIN `表名` AS `别名2` ON 连接条件 WHERE 条件; 连表删除字段
- TRUNCATE TABLE `表名`; 删除表然后又创建一个一模一样的新表
- SELECT `字段名`,... FROM `数据库`.`表名`  
  WHERE 条件  
  GROUP BY `字段`,...（和聚合函数同时出现的列名要写在group by后）  
  HAVING 条件 (分组后筛选)  
  ORDER BY `字段名`|表达式或者第几列,... ASC|DESC（所有null值分为一组）  
  LIMIT 起始条数,显示条数  
  FOR UPDATE（获取排他锁）  
  LOCK IN SHARE MODE（共享锁）; 查询语句。两事务获取共享锁。其中一个等待获取排他锁，另一个也去获取排他锁时会报deadlock  
  查询过程 FROM -> ON -> JOIN -> WHERE -> GROUP BY -> HAVING -> SELECT -> DISTINCT -> ORDER BY -> LIMIT
- SHOW TABLES; 显示所有表
- DESCRIBE|DESC `表名`; 查看表详情
- SHOW TABLE STATUS LIKE '表名'; 查看表的储存引擎
- SHOW FULL COLUMNS FROM '表名'; 查询所有字段信息
- SHOW CREATE TABLE '表名'\G 查看创建表的语句
- CHECK TABLE '表名'; 检查表、视图，并返回结果
- 查询语句 INTO OUTFILE '输出路径' FIELDS TERMINATED BY '字段间间隔符' LINES TERMINATED BY '行结束符'; 将查出的信息文件导出
- LOAD DATA INFILE '文件路径' INTO TABLE `表名` FIELDS TERMINATED BY '字段间间隔符' LINES TERMINATED BY '行结束符' IGNORE 跳过行数; 从文件中恢复数据到表中
- REPLACE INTO `表名` (`字段名`,...) VALUES (字段值,...); 向表中添加数据，如何存在唯一、主键字段冲突则删除该行再插入
- INTERSCT 交集  EXCEPT 差集 UNION 并集
- INNER JOIN 内连接 NATURAL JOIN 自然连接 CROSS JOIN 交叉连接 LEFT OUTER JOIN 左外连接 RIGHT OUTER JOIN 右外连接 full OUTER JOIN 全连接 SELF JOIN 自连接

# 视图

- CREATE VIEW `视图名` (`字段名`,...) AS 查询语句 [UNION 查询语句],... WITH CHECK OPTION（检查插入数据是否符合WHERE设置的条件）; 创建视图。合并查询列数要相同。查询语句不能引用系统或用户变量
- ALTER VIEW `视图名` AS 查询语句 [UNION 查询语句],...; 修改视图
- SHOW CREATE VIEW `视图名`; 查看视图创建情况
- DESCRIBE `视图名`; 查看视图;
- INSERT INTO `视图名` VALUES (字段值,...); 向视图中插入值
- DROP VIEW [IF EXISTS] `视图名`,...; 删除视图名
- UPDATE `视图名` SET `字段`=值,... WHERE 条件; 更新视图字段信息
- SELECT `字段`,... FROM `视图` ORDER BY `字段`,...; 排序会覆盖视图中定义的排序规则

# 触发器

- CREATE TRIGGER `触发器名` [AFTER|BEFORE] [INSERT|UPDATA|DELETE] ON `表名` FOR EACH ROW BEGIN SQL语句(NEW新增行对象，OLD删除行对象) END$$ 创建触发器  
  before前可以设置new对象的值。对于给自增值的列，当前值是零。如果有多个同类型的触发器则会依次触发，创建最晚的最后触发
- DROP TRIGGER IF EXISTS `数据库`.`触发器名`; 删除触发器。表被删除了，对应触发器自动删除
- SHOW TRIGGERS; 显示数据库的触发器
- DELIMITER \$\$ 临时定义语句结束符

# 索引

- CREATE [FULLTEXT|UNIQUE] INDEX `索引名` ON `表名` (`字段名`(长度) DESC|ASC,...); 创建索引  
  BLOB和TEXT类型，必须指定长度。组合索引，最左前缀原则，查询条件左边连续一列或几列命中索引。InnoDB数据表不支持全文索引。MySQL把同一个数据表里的索引总数限制为16个
- ALTER TABLE `表名` ADD INDEX [UNIQUE|FULLTEXT（全文索引）|PRIMARY KEY（聚簇索引）] `索引名` (`字段名`,...ASC|DESC) USING BTREE|HASH|FULLTEXT|RTREE; 添加索引
- DROP INDEX `索引名` ON `表名`; 删除索引
- ALTER TABLE `表名` DROP INDEX `索引名`; 删除索引
- SHOW INDEX FROM `数据库`.`表名`; 查看索引
- EXPLAIN 查询语句; 执行计划  
  id：执行顺序，从上往下
  select_type查询类型：simple单表查询。primary包含了子查询的外层查询。derived FROM后面的子查询。subquery SELECT和WHERE后面的子查询。dependent subquery依赖主查询的子查询。uncachable subquery使用了@@全局变量的子查询，不用缓存。union UNION后的查询。union result从UNION表获取结果的查询  
  table：该查询来自哪个表  
  type查询的访问类型：system(表只有一行记录，系统表)>const(索引一次找到)>eq_ref(唯一索引)>ref(非唯一索引)>fulltext>ref_or_null>index_merge(多个索引组合使用)>unique_subquery(关联子查询的唯一索引)>index_subquery(利用索引来关联子查询)>range(检索给定范围)>index(使用了索引但没用来过滤)>ALL  
  possible_keys：可能使用的索引  
  key：实际使用的索引  
  key_len：索引中使用的字节数  
    索引上字段的类型、长度。比如int=4、varchar(20)=20、char(20)=20  
    varchar或者char这种字符串字段，视字符集要乘不同的值，比如utf-8要乘3，GBK要乘2  
    varchar这种动态字符串要加2个字节  
    允许为空的字段要加1个字节  
  ref：哪些列或常量被用于查找索引列上的值  
  rows：检查的行数  
  extra：using filesort：文件排序。查询条件和排序条件的索引不一致。using temporary：使用了临时表保存中间结果。using index：使用了索引，没有用表的数据行。using where：使用where过滤。using join buffer：连接查询条件没有使用索引。impossible where：where条件恒false。select tables optimized away：聚合字段使用索引

# 索引数据结构

二叉树  
平衡二叉树：通过左右旋保持两边高度差不大于一  
多叉查找树：每个节点会有多个关键值，节点拥有的子节点不超过树高度  
多路搜索树：所有数据放在了叶子节点下

# 分区

- CREATE TABLE 表名 (字段定义) PARTITION BY  
  - RANGE(`整形字段`|表达式)(  
    PARTITION 分区名 VALUES LESS THAN(),  
    PARTITION 分区名 VALUES LESS THAN MAXVALUE,  
    ...  
    ); 范围分区，改字段值超过分区区间时插入数据将失败
  - LIST(`整形字段`|表达式)(
    PARTTION 分区名 VALUES IN (数字,...)  
    ); 列表分区
  - HASH(`整形字段`|表达式) PARTITIONS 整数; 对字段取模分区
  - KEY(`字段`,...) PARTITIONS 整数; 键值分区
- SELECT * FROM TABLE PARTITION(分区名); 使用分区查询

# 参数

- SET @@参数=值; 设置全局参数值
- SET GLOBAL 参数=值; 设置全局参数值
- SET|SELECT 参数=值; 设置会话参数值
- SELECT @参数=字段 FROM TABLE;
- SHOW STATUS LIKE 参数; 查看参数
- SHOW VARIABLES LIKE 参数; 查看参数
  - innodb_locks_unsafe_for_binlog：innodb储存引擎是否使用共享锁insert table ... select操作。默认off表示加共享锁。这样可以确保binlog数据恢复复制正确
  - innodb_table_locks：默认on。innodb可以预防与server层的表锁带来的死锁
  - autocommit：默认on。自动提交
  - innodb_lock_wait_timeout：innodb储存引擎等待获得锁的延时。默认50s。超时事务回滚
  - low_priority_updates：更新请求获取锁的优先级
  - concurrent_insert：myisam表并发尾插行为，默认1，表示表没有间隙可以尾插。0不能尾插，2总是可以尾插
  - max_write_lock_count：最大写锁数，超过后myisam写锁更易获得
  - slow_query_log：全局，开关慢查询功能
  - slow_query_log_file：慢查询日志文件路径。默认是主机名-slow.log
  - long_query_time：慢查询日志时间阈值
  - unique_checks：开关唯一校验
  - max_connections：全局，最大连接数，最大为16384，设置成85%比较好。MySQL会为每个连接创建缓冲区,所以不能盲目调大最大连接数
  - back_log：全局，当连接数不够时，请求暂放堆中，堆的大小，默认80，设置成最大连接数的1/3比较好
  - innodb_thread_concurrency：全局，innodb并发线程数，默认0，表示无上线，设置成cpu核心数的两倍比较好
  - wait_timeout：全局，连接超时，默认8小时
  - innodb_buffer_pool_size：全局，InnoDB的缓存容量，默认128m
  - thread_cache_size：连接线程缓冲数量
  - character_set_client：字符编码
  - character_set_results：字符编码
  - max_sort_length：bolb、text类型字段排序使用前多少字节数
  - max_heap_table_size：内存表大小最大数
  - tmp_table_size：内存临时表的大小
  - read_buffer_size：缓存扫描大小
  - sort_buffer_size：排序缓冲区
  - join_buffer_size：连表查询缓冲大小
  - read_rnd_buffer_size
  - log_queries_not_using_indexes：开关查询索引未使用收集
  - query_cache_min_res_unit：查询缓存块以多少大小进行
  - query_cache_size：查询缓存总大小
  - query_cache_limit：使用缓存的最大值
  - log_bin_trust_function_creators：创建函数时使用参数
  - secure_file_priv：表数据信息输出文件路径
  - innodb_file_per_table：innodb储存引擎使用.ibd独享文件，还是.ibdate共享文件
  - max_length_for_sort_data：与排序算法一次扫描和两次扫描有关
  - optimizer_search_depth：关联表查询
  - log_bin：开关二进制日志文件，设置文件名称
  - binlog_do_db：进行复制的数据库名称
  - relay_log：开启中继日志，设置日志名称
  - profiling：开启后，可以通过show profiles查看上条sql执行时间
  - foreign_key_checks：外键检测
  - innodb_row_lock_current_waits：当前正在等待锁定的数量
  - innodb_row_lock_time：从系统启动到现在锁定的总时间长度，单位ms
  - innodb_row_lock_time_avg：从系统启动到现在每次等待锁所花平均时间
  - innodb_row_lock_time_max：从系统启动到现在等待锁最长的一次所花的时间
  - innodb_row_lock_waits：从系统启动到现在总共等待的次数
  - table_locks_immediate：可以立即获取锁的查询次数
  - table_locks_waited：不能立即获取锁的次数
  - max_used_connections：当前的连接数

# 储存过程

- CREATE DEFINER=用户名@主机名 PROCEDURE 储存过程名(IN|OUT|INOUT（后两者只能传变量。列名优先） 参数名 参数数据类型,...)  
  COMMENT 'string' | LANGUAGE SQL | [NOT] DETERMINISTIC | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA } | SQL SECURITY { DEFINER | INVOKER }  
  标志名:BEGIN 过程体（可嵌套begin end） END 标志名$$ 创建储存过程
- 过程体中控制语句
  - DECLATE 变量名,... 数据类型 DEFAULT 默认值; 变量声明
  - SET 变量名=值|变量名|表达式,...; 变量赋值
  - IF 条件 THEN 语句 ESLE 语句 END IF;
  - CASE 变量 WHEN 值 THEN 语句,... ELSE 语句 END CASE;
  - 标志名:WHILE 条件 DO 语句 LEAVE|ITERATE 标志名; END WHILE;
  - 标志名:REPEAT 语句 LEAVE|ITERATE 标志名; UNTIL 条件 END REPEAT;
  - 标志名:LOOP 语句 LEAVE|ITERATE 标志名; END LOOP;
  - DECLARE 游标名 CURSOR FOR SELECT 语句; 声明游标
  - OPEN 游标名; 打开游标
  - FETCH 游标名 INTO 变量名,...; 保存指针数据到变量中
  - DECLARE 处理种类(EXIT|CONTINUE) HANDLER FOR 例外的种类(NOT FOUND) 例外发生时的处理; 例外处理
  - CLOSE 游标名; 关闭游标
  - PREPARE 名 FROM @变量;
  - EXECUTE 名;
  - DEALLOCATE PREPARE 名; 执行SQL语句
- DROP PROCEDURE 储存过程名; 删除储存过程
- CALL 储存过程名(参数值)$$ 调用储存过程
- DELIMITER $$ 临时定义语句结束符
- SHOW PROCEDURE STATUS WHERE DB=数据名; 查看数据库的储存过程
- SHOW CREATE PROCEDURE 数据库名.储存过程名; 查看数据库的储存过程
- SELECT `routine_name` FROM `information_schema`.`routines` WHERE `routine_schema`=数据库名; 查询储存过程

# 函数

- CREATE FUNCTION 方法名(形参 类型,...) RETURNS 返回数据类型 BEGIN RETURN (返回值查询语句（返回结果集必须是一列一行）); END$$ 创建函数
- DROP FUNCTION IF EXISTS 方法名; 删除函数
- SHOW CREATE FUNCTION `函数名`; 查看函数详情
- SHOW FUNCTION STATUS; 打印一堆

# 用户

- mysql -u *用户名* -p *密码* -h *主机*名 -A（表示选择数据库时关闭预读表和字段信息） -P *端口号*：登陆 mysql
- mysql -u *用户名* -p *密码* -h *主机名* -A -P *端口号* *数据库名* < *备份文件*：恢复备份
- CREATE USER `用户名`@`主机名`|`%`（表示所有） IDENTIFIED WITH mysql_native_password BY '*密码*';：创建用户
- GRANT *权限类型* [ (`字段`), ... ] ON `数据库名`.`表名`（\*号代表所有） TO `用户名`@*主机名*;：给用户授权。  
权限类型：SELECT、INSERT、DELETE、UPDATE、REFERENCES(外键权限)、CREATE、ALTER、INDEX、DROP、SHOW VIEW、CREATE ROUTINE、ALTER ROUTINE、CREATE TEMPORARY TABLES CREATE VIEW、LOCK TABLES、ALL(ALL PRIVILEGES)、CREATE USER、SHOW DATABASES、REPLICATION SLAVE、REPLICATION CLIENT。
- SHOW GRANTS FOR *用户名*@*主机名*; 查看用户权限
- REVOKE *权限类型* [ (`字段`), ... ] ON `数据库名`.`表名` FROM `用户名`@*主机名*;：删除用户权限
- DROP USER *用户名*@*主机名*, ...;：删除用户
- SET PASSWORD FOR [ *用户名*@*主机名* = ]密码;：修改用户密码
- ALTER USER *用户名*@*主机名* IDENTIFIED BY 密码;：修改用户密码
- mysqladmin -u *root* *password* *xxx*：修改密码
- mysqldump -u *用户名* -h *主机名* -p [ *密码* ] *数据库名* *表名*, ... > *输出路径*：备份数据库到指定路径
- mysqladmin -u *root* -p shutdown：停止服务
- SOURCE *数据库路径*; 从指定路径导入数据到当前数据库
- RENAME USER *旧帐号*@*主机名* TO *新帐号*@*主机名*;：修改用户名
- flush privileges;：刷新权限

# 事务

- START TRANSACTION; 开启事务，手动提交
- ROLLBACK TO 保存点名; 回滚
- COMMIT; 提交
- SAVEPOINT 保存点名; 设置保存点
- SET GLOBAL TRANSACTION ISOLATION LEVEL SERIALIZABLE|REPEATABLE READ|READ COMMITTED|READ UNCOMMITED; 设置事务隔离级别
- FLUSH PRIVILEGES; 刷新权限
- SELECT @@tx_isolation; 查看全局参数@@事务隔离级别的值

# 其它

- SELECT 值 INTO 用户变量; 初始化或更改变量值
- QUIT; 退出
- EXIT; 退出
- SHOW WARNINGS; 显示警告信息
- SET GLOBAL TIME_ZONE='+8:00'; 设置全局时区
- SHOW VARIABLES LIKE '%CHARACTER%'; 查看字符编码设置
- SELECT LAST_INSERT_ID(); 查询最后一次生成的主键ID
- SHOW GLOBAL VARIABLES; 显示参数设置详情
- SHOW ENGINES; 显示储存引擎信息
- SHOW MATER STATUS; 测试主库
- SHOW SLAVE STATUS; 测试从库
- CHANGE MASTER TO  
  MASTER_HOST='172.18.0.1',  
  MASTER_PORT=3306,  
  MASTER_USER='mycat',  
  MASTER_PASSWORD='123',  
  MASTER_LOG_FILE='master-data-logs.000009',  
  MASTER_LOG_POS=155; 配置从库连接主库
- START SLAVE; 从库开始复制
- STOP SLAVE; 从库结束复制
- FLUSH LOGS; 重置主库复制日志
- LOCK TBALES 表名 AS 别名 READ|WRITE LOCAL（myisam允许尾插）,... 给表上锁
- UNLOCK TABLES; 解锁表
- OPTIMIZE TABLE 表名; 回收空洞
- SHOW ENGINE INNODB STATUS; 显示innodb监视器
- SHOW PROCESSLIST; 显示正在查询的SQL
- SHOW PROFILES; 查看每条sql执行所花时间
- TEE 文件路径 将输出信息保存到指定的文件中
- NOTEE 结束信息输出到文件

# 运算符

- = 等于。相等返回1，否则返回0
- <=> 安全等于。null<=>null返回1
- !=|<> 不等于
- < 小于
- <= 小于等于
- > 大于
- >= 大于等于
- + 加运算
- - 减运算
- * 积运算
- / 除运算
- % 取模
- _ 通配一个字符
- % 通配多个字符
- >> 右位移
- << 左位移
- | 与位运算
- & 且位元算
- ^ 抑或位运算
- ~ 按位取反
- DIV 除法返回结果的整数部分
- BETWEEN AND 在之间
- IS NULL 是空判断
- IS NOT NULL 非空判断
- AND|&& 并且
- OR||| 或者
- XOR 抑或
- NOT|! 不是
- [NOT] LIKE
- REGEXP '正则表达式'
- RLIKE '正则表达式'
- 子查询多行单列运算符
  - ANY或者SOME 例如SELECT * FROM emp WHERE sal > ALL (SELECT sal FROM emp WHERE deptno=30)
  - ALL
- [NOT] EXISTS 是否有返回数据
- [NOT] IN 在某个字段值内
- @ 设置获取用户变量set @t=100;select @t;@t:=@t+12;表示t变量等于自身加12
- @@ 设置获取系统变量
- 运算符优先级：NOT > AND > OR

# 查询函数

- DISTINCT `字段名` 该字段去重
- AS `别名` 设置别名
- UNION 合并
- UNION ALL 合并不去重
- [INNER] JOIN `表名` ON 条件 内连接查询
- CROSS JOIN 内连接查询
- LEFT [OUTER] JOIN `表名` ON 条件 左外连接
- RIGHT [OUTER] JOIN `表名` ON 条件 右外链接

# 数学函数

- BIN(数字) 返回二进制表示
- OCT(数字) 返回八进制表示
- HEX(数字) 返回十六进制表示
- CONV(数字, 原进制, 进制) 进制转换
- ABS(数字) 返回绝对值
- CEILING(数字) 返回大于数字的最小整数值
- FLOOR(数字) 返回小于数字的最大整数值
- GREATEST(数字,...) 返回集合中最大的值
- LEAST(数字,...) 返回集合中最小的值
- EXP(数字) 返回值e的数字次方
- POWER(底数, 指数) 幂
- LN(数字) 返回数字的自然对数
- LOG(真数, 底数) 返回对数
- MOD(数字1, 数字2) 返回数字1取数字2的模
- SQRT(数字)返回数字的平方根
- PI() 返回圆周率的值
- TRUNCATE(数字, 小数位数) 返回数字为指定小数位的结果
- FORMAT(数字, 小数位数) 保留小数位数
- ROUND(数字, 小数位数) 返回数字的四舍五入的有指定小数位的值。默认对小数一下四舍五入。如果位数为负数，则对整数四舍五入
- RAND(种子数) 返回0到１内的随机值，可以通过提供一个参数种子
- SIGN(数字) 返回代表数字的符号的值：1 -1 0

# 聚合函数

- AVG(`字段名`) 返回指定列的平均值。如果指定列类型不是数值类型，那么计算结果为null
- COUNT(`字段名`) 返回指定列中非NULL值的个数。COUNT(*)计算NULL值个数
- MIN(`字段名`) 返回指定列的最小值。如果指定列是字符串类型，那么使用字符串排序运算
- MAX(`字段名`) 返回指定列的最大值。如果指定列是字符串类型，那么使用字符串排序运算
- SUM(`字段名`) 返回指定列的所有值之和。如果指定列类型不是数值类型，那么计算结果为0
- GROUP_CONCAT(`字段名` [ SEPARATOR *sep* ]) 返回字段值拼接结果，中间用逗号隔开

# 字符串函数

- ASCII('字符') 返回字符的ASCII码值
- CHAR(数字,...) ASCII码转化成字符串
- BIT_LENGTH('字符串') 返回字符串的字节数
- CHARSET('字符串') 返回字串字符集
- CONCAT('字符串', '字符串',...) 连接成一个字符串
- CONCAT_WS('分割符', '字符串', '字符串',...) 连接成一个字符串，并用分割符间隔
- LCASE('字符串')|LOWER('字符串') 返回将字符串中所有字符改变为小写后的结果
- UCASE('字符串')|UPPER('字符串') 返回将字符串中所有字符转变为大写后的结果
- INSERT('字符串', 插入的开始下标, 插入长度, '待插入字符串') 字符串插入，不足替换则保留剩余未替换内容，过多替换，则原字符往后挪
- LOCATE('要搜的字符串', '被搜索字符串', 开始搜索下标) 查找字符串下标，不存在返回0
- INSTR('被搜字符串', '搜索字符串') 返回字符串首次在字符串中出现的位置，不存在返回0
- FIND_IN_SET('搜索字符串', '被搜字符串') 分析逗号分隔的列表，如果发现字符串，返回在列表中的位置
- LEFT('字符串', 字符数) 返回字符串最左边的指定个字符
- RIGHT('字符串', 字符数) 返回字符串最右边的指定个字符
- LENGTH('字符串') 返回字符串中的字节数
- CHAR_LENGTH('字符串') 返回字符串中的字符数
- BIT_LENGTH('字符串') 返回字符串中的位数
- LTRIM('字符串') 切掉字符串开头的空格
- RTRIM('字符串') 切掉字符串尾部的空格
- TRIM('字符串') 去除字符串首部和尾部的所有空格
- QUOTE('字符串') 用反斜杠转义字符串中的单引号
- REVERSE('字符串') 返回颠倒字符串的结果
- STRCMP('字符串1', '字符串2') 比较字符串，返回0表示相同，-1表示不同
- REPLACE('字符串', '搜索字符串', '代替字符串') 在字符串中用代替字符串替换搜索字符串
- RPAD('字符串', 长度, '补充字符串') 在字符串后用补充字符串补充，直到指定长度
- REPEAT(字符串, 次数) 返回重复次数字符串
- LPAD('字符串', 长度, '补充字符串') 重复用补充字符串加在字符串开头，直到字串到指定长度
- SUBSTRING('字符串', 开始下标, 长度) 从字符串的指定下标开始截取字符串
- SPACE(空格数) 生成空格
- UUID() 返回uuid字符串
- SUBSTRING_INDEX('字符串', '分隔符', count) 返回一个字符串中的子字符串

# 日期和时间函数

- NOW() 返回当前的日期和时间
- CURDATE()|CURRENT_DATE() 返回当前的日期
- CURTIME()|CURRENT_TIME() 返回当前的时间
- CURRENT_TIMESTAMP() 返回当前时间戳
- YEAR('时间') 返回日期年份(1000~9999)
- MONTH('时间') 返回月份值(1~12)
- MONTHNAME('时间') 返回月份名
- DATE('时间') 返回时间的日期部分
- DAY('时间') 返回日期天数
- DAYOFMONTH('时间') 返回月的第几天(1~31)
- HOUR('时间') 返回小时值(0~23)
- MINUTE('时间') 返回分钟值(0~59)
- MICROSECOND('时间') 微秒
- DAYOFWEEK('时间') 返回一星期中的第几天(1~7)
- DAYNAME('时间') 返回星期名
- WEEK('时间') 返回日期一年中第几周(0~53)
- YEARWEEK('时间') 返回年数和第几周数
- QUARTER('时间') 返回季度(1~4)
- DAYOFYEAR('时间') 返回年的第几天(1~366)
- LAST_DAY('时间') 月的最后日期
- ADDTIME('时间1', '时间2') 将时间2加到时间1
- DATE_ADD('时间', INTERVAL 数 关键字) 返回日期加上间隔时间的结果，如：SELECT DATE_ADD(CURRENT_DATE，INTERVAL 6 MONTH);
  - 关键字和数的格式：
  - YEAR 年
  - MONTH 月
  - DAY 日
  - HOUR 时
  - MINUTE 分
  - SECOND 秒
  - MICROSECOND 毫秒
  - YEAR_MONTH 年-月
  - DAY_HOUR 日 时
  - DAY_MINUTE 日 时:分
  - DAY_SECOND 日 时:分:秒
  - HOUR_MINUTE 时:分
  - HOUR_SECOND 时:分:秒
  - MINUTE_SECOND 分:秒
  - SECOND_MICROSECOND 秒.毫秒
- DATE_SUB('时间', INTERVAL 数 关键字) 返回日期减上间隔时间的结果，如：SELECT DATE_SUB(CURRENT_DATE，INTERVAL 6 MONTH);
- PERIOD_ADD(时间, 数字) 增加月数到时间（以格式YYMM或YYYYMM)，以格式YYYYMM返回值，注意参数时间不是日期值
- CONVERT_TZ('时间', 原时区, 转成时区) 转换时区
- DATE_FORMAT('时间', 格式) 依照指定格式格式化日期时间值，DATA_FROMAT(NOW()，’%Y%m%d’);#20200118
- TIME_FORMAT('时间', 格式) 依照格式格式化时间值
- FROM_UNIXTIME(时间戳, 格式) 根据指定的格式，格式化UNIX时间戳
- UNIX_TIMESTAMP() 返回unix时间戳，毫秒数
- TIMEDIFF('时间', '时间') 两个时间差
- DATEDIFF('时间', '时间') 两个日期差
- PERIOD_DIFF('时间', '时间') 返回在时期之间月数，以格式YYMM或YYYYMM，注意，时期参数不是日期值
- TIMESTAMPDIFF(单位, 时间, 时间)
- EXTRACT(interval_name FROM '时间') 从时间中提取日期的指定部分
- MAKEDATE('年', 天数) 给出年及年中的第几天，生成日期串
- MAKETIME('小时', '分钟', '秒') 生成时间串
- SEC_TO_TIME('秒') 秒数转成时间
- STR_TO_DATE('字符串', '格式') 字符串转成时间，以指定格式显示
- TIME_TO_SEC('时间') 时间转秒数
  - 格式规则
  - %Y年，数字，4位
  - %y年，数字，2位
  - %M月名字(January~December)
  - %b缩写的月份名字(Jan~Dec)
  - %m月，数字(01~12)
  - %c月，数字(1~12)
  - %D有英语前缀的月份的日期(1st，2nd，3rd，等等)
  - %d月份中的天数，数字(00~31)
  - %e月份中的天数，数字(0~31)
  - %W星期名字(Sunday~Saturday)
  - %w一个星期中的天数(0=Sunday、、、6=Saturday）
  - %a缩写的星期名字(Sun~Sat)
  - %U星期，这里星期天是星期的第一天
  - %u星期，这里星期一是星期的第一天
  - %H小时(00~23)
  - %k小时(0~23)
  - %h小时(01~12)
  - %I小时(01~12)
  - %i小时(1~12)
  - %i分钟，数字(00~59)
  - %S秒(00~59)
  - %s秒(0~59)
  - %pAM或PM
  - %r时间，12小时(hh：mm：ss[A|P]M)
  - %T时间，24小时(hh：mm：ss)
  - %j一年中的天数(001~366)
  - %%一个文字“%”
  - 所有的其他字符不做解释被复制到结果中

# 加密函数

- AES_ENCRYPT('字符串', '密钥') 返回用密钥对字符串利用高级加密标准算法加密后的结果，调用AES_ENCRYPT的结果是一个二进制字符串，以BLOB类型存储
- AES_DECRYPT('字符串', '密钥') 返回用密钥对字符串利用高级加密标准算法解密后的结果
- DECODE('字符串', '密钥') 使用密钥解密加密字符串
- ENCODE('字符串', '密钥') 使用密钥加密字符串，调用ENCODE()的结果是一个二进制字符串，它以BLOB类型存储
- ENCRYPT('字符串', '盐') 使用UNIXcrypt()函数，用盐(一个可以惟一确定口令的字符串，就像钥匙一样)加密字符串
- MD5('字符串') 计算字符串的MD5校验和
- SHA('字符串') 计算字符串的安全散列算法(校验和)
- TO_BASE64(blob字段)
- FROM_BASE64(blob字段)

# 控制流函数

- CASE WHEN 条件 THEN 结果1 ELSE 结果2 END 如果条件是真，则返回结果1，否则返回结果2
- CASE 值 WHEN 等于值 THEN 结果1,... ELSE 结果2 END
- IF(条件, 结果1, 结果2) 如果条件是真，返回结果1，否则返回结果2
- IFNULL(值1, 值2) 如果值1不是空，返回值1，否则返回值2
- NULLIF(值1, 值2) 如果两值相等返回NULL，否则返回值1
- ISNULL(值) 如果值是null，返回0

# 格式化函数

- INET_ATON(IP地址) 返回IP地址的数字表示
- INET_NTOA(数字) 返回数字所代表的IP地址

# 系统信息函数

- DATABASE() 返回当前数据库名
- BENCHMARK(次数, 表达式) 将表达式重复运行指定次数
- CONNECTION_ID() 返回当前客户的连接ID
- FOUND_ROWS() 返回最后一个SELECT查询进行检索的总行数
- USER()|SYSTEM_USER() 返回当前登陆用户名
- VERSION() 返回MySQL服务器的版本

# Mycat1 6.7.5

- mycat start 运行服务
- mycat stop 停止服务
- mycat console 运行服务

# Docker 安装

- docker pull mysql:8.1.0
- docker run -v volumes/mysql/data:/var/lib/mysql -v volumes/mysql/config:/etc/mysql/conf.d -v volumes/mysql/log:/logs --hostname ivfzhou-docker-mysql --name mysql --ip 172.16.3.143 --network ivfzhou-docker-network --add-host ivfzhoudebian:172.16.3.143 -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 mysql:8.1.0

# 二进制安装

- 解压文件，在程序目录下新建data文件夹，里面不能有文件
- 编写 my.cnf 文件，复制到 /opt/mysql 下
- 安装依赖 sudo apt install libaio1 libtinfo5
- 在bin目录下运行 mysqld --defaults-file=/opt/mysql/my.cnf --user=ivfzhou --initialize-insecure --console 参数insecure表示初始化空密码
- 将 /support-files/mysql.server 复制到 /etc/init.d/ 下，修改该文件的basedir和basedatadir
- 添加 mysql 环境变量，vi /etc/profile，export mysql=/opt/mysql export PATH=$PATH:$mysql/bin，source /etc/profile
- sudo dpkg -i libaio1_0.3.110-2_amd64.deb libc6_2.31-0ubuntu9_amd64.deb libtinfo5_6.2-0ubuntu2_amd64.deb libncurses5_6.2-0ubuntu2_amd64.deb
- reboot
- 登录 mysql 修改root密码 ALTER USER root@localhost IDENTIFIED BY '123456'

# Centos 安装

- 解压压缩包到 /zf/mysql 下
- sudo rpm -i ncurses-compat-libs-6.1-7.20180224.el8.x86_64.rpm
- 编辑环境变量 /etc/profile 添加
  - export mysql=/zf/mysql
  - export PATH=$PATH:$mysql/bin
- sudo cp my.cnf /etc/mysql/my.cnf
- mysqld --defaults-file=/etc/mysql/my.cnf --user=zf --initialize-insecure --console
- sudo cp supprot-files/mysql.server /etc/init.d/mysql.server
- 修改 mysql.server 里 basedir 和 datadir
- mysqld_safe --user=zf &
- reboot.
- alter user root@localhost identified by '123456';

# Windows 安装

- 给添加环境变量
- 程序目录创建文件夹 data
- 程序目录创建文件 my.ini
- cmd 运行
- mysqld --initialize-insecure –console
- mysqld –install
- net start mysql
- 修改 root 密码，alter user root@localhost identified by '123456'

# Debian12 apt 安装

```shell
wget https://dev.mysql.com/get/mysql-apt-config_0.8.30-1_all.deb
sudo apt install lsb-release gnupg
sudo dpkg -i mysql-apt-config_0.8.30-1_all.deb
sudo apt update
sudo apt install mysql-server
sudo systemctl disable mysql
update mysql.user set host = '%' where user = 'root' and host = 'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'root'@`%`;
flush privileges;
```

# 主从搭建

- 设置 /etc/mysql/my.cnf 添加
  - log_bin=/zf/mysql/data/binlog 
  - server_id=1
- 运行 mysql
- 开启主库客户端，创建用户 mycat@172.18.0.2，授权
  - GRANT REPLICATION SLAVE,REPLICATION CLIENT ON *.* TO `mycat`@`172.18.0.2`;
  - GRANT ALL ON `mycat`.* TO `mycat`@`172.18.0.2`;
- docker 运行 mysql 容器，数据卷 mysql.cnf 添加
  - server_id=2
  - relay_log=relaylog
- 从库开启客户端运行命令  
  CHANGE MASTER TO  
  MASTER_HOST='172.18.0.1',  
  MASTER_PORT=3306,  
  MASTER_USER='mycat',  
  MASTER_LOG_FILE='master-data-logs.000006',  
  MASTER_LOG_POS=155;
- 从库运行 START SLAVE USER=`mycat` PASSWORD='123'; 开始复制

# SQL Server 2000
- 批处理文件以GO结尾标识一组语录
- DECLARE @变量名 数据类型,...
- SET @变量名 = 表达式
- SELECT @变量名 = 表达式
- SELECT @变量名 = 字段 FROM TABLE
- BEGINE ... END 语句块
- IF 条件 ... ELSE ...
- WHILE 条件 ... [BREAK|CONTINUE]
- RETURN 整数 退出批处理函数存储过程触发器
- EXECUTE 过程函数 参数 [OUTPUT]
- BEGIN TRANSACTION ... [ROLLBACK TRANSACTION] [COMMIT TRANSCTION] END
- /**/ 注释
- -- 注释
- DECLARE 游标 CURSOR FOR 查询语句
- OPEN 游标
- FETCH FROM 游标 INTO 变量
- CLOSE 游标
- DEALLOCATE 游标
- @@FETCH_STATUS 全局变量游标状态
- CREATE PROCEDURE 过程 参数数据类型,... [OUTPUT] AS ...
- DROP PROCEDURE 过程
- CREATE TRIGGER 触发器 ON 表 FOR|(INSTEAD OF) UPDATE|INSERT|DELETE AS ...
- USE 数据库
- EXEC sp_addumpdevice 'disk', 名, 路径 创建逻辑备份设备
- BACKUP DATEBASE 名 TO 名 备份数据库
- BACKUP LOG 名 TO 名 备份日志文件
- RESTORE LOG 名 FROM 名 恢复事务日志
- RESTORE DATABASE 名 FROM 名 WITH NORECOVERY 恢复数据库后援副本
- EXEC sp_addlogin 名 密码 创建账户
- EXEC sp_grantdbaccess 账户名 用户名 数据库下添加一个用户
- GRANT SELECT, UPDATE, INSERT, UDELETE, ALL,CREATE DATEBASE, CREATE FUNCTION, CREATE PROCEDURE, CREATE TABLE, CREATE VIEW, BACKUP DATABASE, BACKUL LOG ON 库 TO 用户名 [WITH GRANT - OPTION] 授权
- EXEC sp_addrole 角色 数据库下添加角色
- EXEC sp_addrolemember 角色 用户 用户赋予角色
- REVOKE ALL ON 数据库对象 FROM 用户 [RESTRICT若存在转授也拒绝|CASACDE级联回收转授权限] 回收权限
- CREATE VIEW 视图 AS 查询 创建视图
- CALL 过程名 [(参数,...)]
