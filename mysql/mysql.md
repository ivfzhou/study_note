# 笔记

1. MySQL 连接报错："Public Key Retrieval is not allowed"。链接加上 `&allowPublicKeyRetrieval=true`，或者执行：`ALTER USER 'username'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';`

# Docker-Compose 配置

```yml
services:
  mysql:
    image: mysql:8.1.0
    container_name: mysql
    hostname: ivfzhou_docker_mysql
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.142
    extra_hosts:
      - "ivfzhou_debian:172.16.3.1"
    environment:
      MYSQL_ROOT_PASSWORD: 123456
      MYSQL_DATABASE: db_xxx # 启动创建数据库。
      MYSQL_USER: user # 创建新用户，拥有数据库 db_xxx 所有权限。不要设置成 root。
      MYSQL_PASSWORD: password # 新用户密码。
      MYSQL_ONETIME_PASSWORD: yes # root 用户登录后就要修改密码。
      MYSQL_INITDB_SKIP_TZINFO: yes # 跳过时区解析。
      MYSQL_ROOT_PASSWORD_FILE: /path/to/file # root 用户密码在容器指定文件中获取。以上环境变量也可以追加 _FILE。
      MYSQL_ALLOW_EMPTY_PASSWORD: yes # root 用户密码为空。
      MYSQL_RANDOM_ROOT_PASSWORD: yes # root 用户密码随机，在控制台打印密码。
    ports:
      - "3306:3306"
    volumes:
      - /home/ivfzhou/volumes/mysql/data/:/var/lib/mysql/:rw
      - /home/ivfzhou/volumes/mysql/config/:/etc/mysql/conf.d/:rw
      - /home/ivfzhou/volumes/mysql/log/:/logs/:rw
      - xxx:/docker-entrypoint-initdb.d # 这个目录下的 SQL 文件将被导入到数据库中，默认数据库是变量 MYSQL_DATABASE 的值。
networks:
  network:
    name: ivfzhou_docker_network
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

\#：单行注释。  
--：单行注释。   
/**/：多行注释。

# 数据类型

1. TINYINT(*n*)：1 字节。范围：有符号 [-128, 127]，无符号 [0, 255]。后加 unsigned 表示为无符号范围。后加 zerofill 表示数值不足指定的位数时以 0 来填充。
1. SMALLINT(*n*)：2 字节。范围：有符号 [-32768, 32767]，无符号 [0, 65535]。
1. MEDIUMINT(*n*)：3 字节。范围：有符号 [-8388608, 8388607]，无符号 [0, 16777215]。
1. INT | INTERGE(*n*)：4 个字节。范围：有符号 [-2147483648, 2147483647]，无符号 [0, 4294967295]。
1. BIGINT(*n*)：8 字节。范围：有符号 [-9233372036854775808, 9223372036854775807]，无符号 [0, 18446744073709551615]
1. FLOAT(*总位数 m*, *小数位数 d*)：4 字节。浮点数类型。
1. DOUBLE(*m*, *d*)：8 个字节。浮点数类型。
1. DECIMAL(*m*, *d*)：大小：如果 M>D，为 M+2 否则为 D+2。(精度 1-255 默认 10，标度 1-30 默认 0)。定点数类型。例如 decimal(5, 2) 能够存储具有总共五位数有两位小数的任何值。
1. CHAR(*n*)：大小：字符数大小，范围 [0, 255] 字符。固定内存空间大小存值。
1. VARCHAR(n)：大小：范围 [0, 65535] 字节。如字符编码为 UTF-8，则大小范围 [0, 16383] 字符。GBK 为 [0, 32767] 字符。会额外用一两个字节储存数据长度。
1. TINYTEXT：大小为 [0, 255] 字节。只对 max_sort_length 个字节排序。
1. TEXT：大小为 [0, 65535] 字节。
1. MEDIUMTEXT：大小为 [0, 16777215] 字节（16MB）。
1. LONGTEXT：大小为 [0, 4294967295] 字节（4GB）。
1. ENUM('elem1', 'elem2', ...)：枚举类型。插入数据时可以选下标也可指定值。最多 65535 个成员。成员尾部空格自动删除。默认值为 NULL。如果字段为 NOT NULL 则默认值为枚举第 1 个成员。
1. SET('elem1', 'elem2', ...)：去重集合。插入数据时可以选下标也可指定值，插入多个值时，值之间用逗号分割，可以插入多个值。值尾部空格自动删除。默认值为 NULL。
1. BIT(*n*)：位数据类型。最大 64 位，默认 1 位。值不足位数前面补 0。
1. BINARY(*字节*)。
1. VARBINATY(*字节*)：最大数为 65535。
1. TINYBLOB：大小为 [0, 255] 字节。只对 max_sort_length 个字节排序。
1. BLOB：大小为 [0, 65535] 字节。
1. MEDIUMBLOB：大小为 [0, 16777215] 字节。
1. LONGBLOB：大小为 [0, 4294967295] 字节。
1. YEAR：1 字节。格式：YYYY，范围 [1901, 2155]。插入字符型或者整型两位数：1-69 表示 2001-2069，70-99 表示 1970-1999。字符 0 表示 2000，而整型 0 表示 0000。
1. DATE：3 字节。格式：YYYY-MM-dd，范围 [0000-01-01,9999-12-31]。插入字符型或者整型两位数：1-69 表示 2001-2069，70-99 表示 1970-1999。字符型格式：'YYYY-MM-dd'、'YY-MM-dd'、'YYYYMMdd'、'YYMMdd'。中间分割符 - 可以替换成 ./@+*。整形格式：YYYYMMdd、YYMMdd、(00)MMdd。
1. TIME(*n*)：3 字节。格式：HHH:mm:ss，范围 [-838:59:59, 838:59:59]。字符型格式：'D HH:mm:ss'、'HHH:mm:ss'、'HH:mm'、'D HH'、'HHHmmss'、'mmss'、'ss'。整型格式：HHHmmss、mmss、ss。
1. TIMESTAMP(*n*)：4 字节。格式：YYYY-MM-dd hh:mm:ss UTC，范围 (1970-01-01 08:00:00, 2038-1-19 11:14:07]。插入字符型或者整型两位数：1-69 表示 2001-2069，70-99 表示 1970-1999。字符型格式：'YYYY-MM-dd HH:mm:ss'、'YYYY-MM-dd'、'YY-MM-dd'、'YYMMddHHmmss'、'YYMMddHHmm'、'YYYYMMdd'、'YYMMdd'。整形格式：YYYYMMddHHmmss、YYMMddHHmmss、YYYYMMdd、YYMMdd、00MMdd。
1. DATETIME(*n*)：8 字节。格式：YYYY-MM-dd HH:mm:ss，范围 [0000-01-01 00:00:00, 9999-12-31 23:59:59]。插入字符型或者整型两位数：1-69 表示 2001-2069，70-99 表示 1970-1999。字符型格式：'YYYY-MM-dd HH:mm:ss'、'YYYY-MM-dd'、'YY-MM-dd'、'YYMMddHHmmss'、'YYMMddHHmm'、'YYYYMMdd'、'YYMMdd' 。整形格式：YYYYMMddHHmmss、YYMMddHHmmss、YYYYMMdd、YYMMdd、00MMdd。

# 约束

1. PRIMARY KEY：主键约束，不能重复，不能为 null。
2. UNIQUE KEY：唯一约束，不能重复，可以为 null。
3. NOT NULL：非空约束。
4. DEFAULT：默认约束。
5. FOREGIN KEY：外键约束。
6. AUTO_INCREMENT：自动增长。
7. CHECK：选择约束。check(sex='男' or sex='女')、check(sex in ('男', '女'))。
8. ON DELETE | UPDATE RESTRICT：不允许父表删除更新有后代的元组。
9. ON DELETE | UPDATE CASCADE：同步删除和更新。
10. ON DELETE | UPDATE SET NULL：对应元组属性设置为空值。
11. ON DELETE | UPDATE SET DEFAULT：对应元组属性置位默认值。
12. CREATE ASSERTION *名* CHECK (*条件*)：定义断言。

# 库

1. CREATE DATABASE [IF NOT EXISTS] *数据库名* [DEFAULT CHARACTER SET *字符名*] [DEFAULT COLLATE utf8mb4_unicode_ci]：新建数据库。
2. ALTER DATABASE|SCHEMA *数据库名* <small>省略表示选择当前的数据库</small> [DEFAULT CHARACTER SET *字符名*] [DEFAULT COLLATE utf8mb4_unicode_ci]：修改数据库字符编码。
3. DROP DATABASE [IF EXISTS] *数据库名*：删除数据库。
4. USE *数据库名*：进入数据库。
5. SHOW DATABASES [LIKE '*数据库名*']：显示数据库。
6. SHOW CREATE DATABASE *数据库名*：查看创建数据库的语句。
7. SELECT DATABASE()：查看当前数据库。

# 表

1. CREATE TABLE [IF NOT EXISTS] *表名* (
    *字段名* 数据类型(大小) 约束 COMMENT '注释内容' COLLATE utf8mb4_unicode_ci DEFAULT 函数或者值 ON UPDATE 函数或者值,  [UNIQUE INDEX *索引名*(*字段名*(*长度*),...)],
    [KEY *索引名*(*字段名*,...)],
    [CONSTRAINT *约束名* FOREIGN KEY(*字段名*,...) REFERENCES *目标表名*(*目标字段名*,...)],
    [PRIMARY KEY(*字段*,...)],
    [UNIQUE KEY(*字段*,...)],
    [CONTRAINT CHECK(*表达式*)],
    ) [CHARSET=*字符名*] COMMENT '*注释内容*' ENGINE *储存引擎* AUTO_INCREMENT=*数字*：新建表，两表之间外键列的数据类型一定要与关联的主键的类型一致。
2. ALTER TABLE *表名* CONVERT TO CHARACTER SET *utf8mb4* COLLATE utf8mb4_general_ci
3. ALTER TABLE *表名* DEFAULT CHARACTER SET *xxx* COLLATE *xxx*
4. CREATE TABLE *表名* [AS] SELECT *查询语句*
5. CREATE TABLE *表名* LIKE *其它表名*：创建表，结构和参考表一样。
6. ALTER TABLE *表名*
  - ADD *字段名* *数据类型*(*大小*) *约束* COMMENT '*注释内容*' [FIRST | AFTER *参考字段*]：添加表字段。
  - MODIFY *字段名* *数据类型*(*大小*) 约束 COMMENT '*注释内容*'：修改表字段。
  - DROP *字段名*：删除表字段。
  - CHANGE *旧字段名* *新字段名* *数据类型*(*大小*) 约束 COMMENT '*注释内容*'：更改表字段名。
  - COMMENT '*注释内容*'：修改表注释。
  - ADD *约束语句*：添加约束，外、主键、唯一、检查约束。
  - ADD PRIMARY KEY(*字段*,...)：添加主键。
  - DROP FOREIGN KEY *外键名*：删除主键约束。
  - DROP INDEX *唯一约束名*：删除字段的唯一约束。
  - DROP CONSTRAINT *检查约束名*：删除检测约束。
  - DROP PRIMARY KEY：删除主键约束。
  - CHARACTER SET *字符名*：更改表字符编码。
  - RENAME [TO] *新表名*。
  - ALTER COLUMN *字段* SET DEFAULT *默认值*：设置字段默认值，这是修改默认值文件，并不会锁表重建表。
7. DROP TABLE [IF EXISTS] *表名*,...：删除表。
8. RENAME TABLE *旧表名* TO *新表名*：更改表名。
9. INSERT INTO *表名*(*字段*,...) SELECT *查询语句*
10. INSERT INTO *表名* SET *字段*=*值*,...：插入一行数据。
11. INSERT INTO *表名* (*字段名*,...) VALUES (*字段值*,...),... [ON DUPLICATE KEY UPDATE *字段名*=*字段值* | VALUES(*字段名*)],... 如果存在唯一、主键冲突则更新该行指定的字段; 如果插入空值，请使用null。插入的日期和字符一样，都使用单引号括起来。
12. UPDATE *表名* SET *字段名*=*字段值*,... WHERE *条件* ORDER BY *字段名* | *表达式或者第几列*,... ASC | DESC LIMIT *起始行*,*条数*：更改数据。
      SET *字段名* = CASE *字段名* WHEN *字段值* THEN *字段值*,...：case-when-then 更新字段值。
13. UPDATE *表名* JOIN *表名* ON *连接条件* SET *字段名*=*字段值*,... WHERE *条件*：连表更改数据。
14. DELETE FROM *表名* WHERE 条件 ORDER BY *字段名* |*表达式或者第几列*,... ASC | DESC LIMIT *起始行*,*条数*：删除数据，自增种子还在自增。
15. DETELE *别名1*, *别名2* FROM *表名* AS *别名1* JOIN *表名* AS *别名2* ON *连接条件* WHERE *条件*：连表删除字段。
16. TRUNCATE TABLE *表名*：删除表然后又创建一个一模一样的新表。
17. SELECT *字段名*,... FROM *数据库*.*表名*
      WHERE 条件
      GROUP BY *字段*,...<small>和聚合函数同时出现的列名要写在 group by 后</small>
      HAVING *条件* <small>分组后筛选</small>
      ORDER BY *字段名* | *表达式或者第几列*,... ASC | DESC <small>所有 null 值分为一组</small>
      LIMIT *起始条数*,*显示条数*
      FOR UPDATE <small>获取排他锁</small>
      LOCK IN SHARE MODE <small>共享锁</small>：查询语句。两事务获取共享锁。其中一个等待获取排他锁，另一个也去获取排他锁时会报 deadlock。查询过程 FROM -> ON -> JOIN -> WHERE -> GROUP BY -> HAVING -> SELECT -> DISTINCT -> ORDER BY -> LIMIT
18. SHOW TABLES：显示所有表。
19. DESCRIBE | DESC *表名*：查看表详情。
20. SHOW TABLE STATUS LIKE '*表名*'：查看表的储存引擎。
21. SHOW FULL COLUMNS FROM '*表名*'：查询所有字段信息。
22. SHOW CREATE TABLE '*表名*'\G：查看创建表的语句。
23. CHECK TABLE '*表名*'：检查表、视图，并返回结果。
24. *查询语句* INTO OUTFILE '*输出路径*' FIELDS TERMINATED BY '*字段间间隔符*' LINES TERMINATED BY '*行结束符*'：将查出的信息文件导出。
25. LOAD DATA INFILE '*文件路径*' INTO TABLE *表名* FIELDS TERMINATED BY '*字段间间隔符*' LINES TERMINATED BY '*行结束符*' IGNORE *跳过行数*：从文件中恢复数据到表中。
26. REPLACE INTO *表名* (*字段名*,...) VALUES (*字段值*,...)：向表中添加数据，如何存在唯一、主键字段冲突则删除该行再插入。
27. INTERSCT 交集  EXCEPT 差集 UNION 并集
28. INNER JOIN 内连接 NATURAL JOIN 自然连接 CROSS JOIN 交叉连接 LEFT OUTER JOIN 左外连接 RIGHT OUTER JOIN 右外连接 FULL OUTER JOIN 全连接 SELF JOIN 自连接

# 视图

1. CREATE VIEW *视图名* (*字段名*,...) AS *查询语句* [UNION *查询语句*],... WITH CHECK OPTION <small>检查插入数据是否符合 WHERE 设置的条件</small>：创建视图。合并查询列数要相同。查询语句不能引用系统或用户变量。
2. ALTER VIEW *视图名* AS *查询语句* [UNION *查询语句*],...：修改视图。
3. SHOW CREATE VIEW *视图名*：查看视图创建情况。
4. DESCRIBE *视图名*：查看视图。
5. INSERT INTO *视图名* VALUES (*字段值*,...)：向视图中插入值。
6. DROP VIEW [IF EXISTS] *视图名*,...：删除视图名。
7. UPDATE *视图名* SET *字段*=*值*,... WHERE *条件*：更新视图字段信息。
8. SELECT *字段*,... FROM *视图* ORDER BY *字段*,...：排序会覆盖视图中定义的排序规则。

# 触发器

1. CREATE TRIGGER *触发器名* [AFTER | BEFORE] [INSERT | UPDATA | DELETE] ON *表名* FOR EACH ROW BEGIN *SQL语句* <small>NEW 新增行对象，OLD 删除行对象</small> END$$：创建触发器。before 前可以设置 new 对象的值。对于给自增值的列，当前值是零。如果有多个同类型的触发器则会依次触发，创建最晚的最后触发。
2. DROP TRIGGER IF EXISTS *数据库*.*触发器名*：删除触发器。表被删除了，对应触发器自动删除。
3. SHOW TRIGGERS：显示数据库的触发器。
4. DELIMITER \$\$：临时定义语句结束符。

# 索引

1. CREATE [FULLTEXT | UNIQUE] INDEX *索引名* ON *表名* (*字段名*(*长度*) DESC | ASC,...)：创建索引。BLOB 和 TEXT 类型，必须指定长度。组合索引，最左前缀原则，查询条件左边连续一列或几列命中索引。InnoDB 数据表不支持全文索引。MySQL 把同一个数据表里的索引总数限制为16个。
2. ALTER TABLE *表名* ADD INDEX [UNIQUE | FULLTEXT<small>全文索引</small> | PRIMARY KEY <small>聚簇索引</small>] *索引名* (*字段名*,... ASC | DESC) USING BTREE | HASH | FULLTEXT | RTREE：添加索引。
3. DROP INDEX *索引名* ON *表名*：删除索引。
4. ALTER TABLE *表名* DROP INDEX *索引名*：删除索引。
5. SHOW INDEX FROM *数据库*.*表名*：查看索引。
6. EXPLAIN *查询语句*：执行计划。
    id：执行顺序，从上往下。
    select_type 查询类型：simple 单表查询。primary 包含了子查询的外层查询。derived FROM 后面的子查询。subquery SELECT 和 WHERE 后面的子查询。dependent subquery 依赖主查询的子查询。uncachable subquery 使用了 @@ 全局变量的子查询，不用缓存。union UNION 后的查询。union result 从 UNION 表获取结果的查询。
    table：该查询来自哪个表。
    type 查询的访问类型：system <small>表只有一行记录，系统表</small> > const <small>索引一次找到</small> > eq_ref <small>唯一索引</small> > ref <small>非唯一索引</small> > fulltext > ref_or_null > index_merge <small>多个索引组合使用</small> > unique_subquery <small>关联子查询的唯一索引</small> > index_subquery <small>利用索引来关联子查询</small> > range <small>检索给定范围</small> > index <small>使用了索引但没用来过滤</small> > ALL。
    possible_keys：可能使用的索引。
    key：实际使用的索引。
    key_len：索引中使用的字节数。
    索引上字段的类型、长度。比如 int=4、varchar(20)=20、char(20)=20。
    varchar 或者 char 这种字符串字段，视字符集要乘不同的值，比如 utf-8 要乘 3，GBK 要乘 2。
    varchar 这种动态字符串要加 2 个字节。
    允许为空的字段要加 1 个字节。
    ref：哪些列或常量被用于查找索引列上的值。
    rows：检查的行数。
    extra：using filesort 文件排序。查询条件和排序条件的索引不一致。using temporary 使用了临时表保存中间结果。using index 使用了索引，没有用表的数据行。using where 使用 where 过滤。using join buffer 连接查询条件没有使用索引。impossible where where 条件恒 false。select tables optimized away 聚合字段使用索引。

# 索引数据结构

二叉树
平衡二叉树：通过左右旋保持两边高度差不大于一。
多叉查找树：每个节点会有多个关键值，节点拥有的子节点不超过树高度。
多路搜索树：所有数据放在了叶子节点下。

# 分区

1. CREATE TABLE *表名* (*字段定义*) PARTITION BY  
  - RANGE(*整形字段* | *表达式*)(
    PARTITION *分区名* VALUES LESS THAN(),
    PARTITION *分区名* VALUES LESS THAN MAXVALUE,
    ...
    )：范围分区，该字段值超过分区区间时插入数据将失败
  - LIST(*整形字段* |*表达式*)(
    PARTTION *分区名* VALUES IN (*数字*,...)
    )：列表分区。
  - HASH(*整形字段* |*表达式*) PARTITIONS *整数*：对字段取模分区。
  - KEY(*字段*,...) PARTITIONS *整数*：键值分区。
2. SELECT * FROM TABLE PARTITION(*分区名*)：使用分区查询。

# 参数

1. SET @@*参数*=*值*：设置全局参数值。
2. SET GLOBAL *参数*=*值*：设置全局参数值。
3. SET | SELECT *参数*=*值*：设置会话参数值。
4. SELECT @*参数*=*字段* FROM TABLE
5. SHOW STATUS LIKE *参数*：查看参数。
6. SHOW VARIABLES LIKE *参数*：查看参数。
  - innodb_locks_unsafe_for_binlog：innodb 储存引擎是否使用共享锁 insert table ... select 操作。默认 off 表示加共享锁。这样可以确保 binlog 数据恢复复制正确。
  - innodb_table_locks：默认 on。innodb 可以预防与 server 层的表锁带来的死锁。
  - autocommit：默认 on。自动提交。
  - innodb_lock_wait_timeout：innodb 储存引擎等待获得锁的延时。默认 50s。超时事务回滚。
  - low_priority_updates：更新请求获取锁的优先级。
  - concurrent_insert：myisam 表并发尾插行为，默认 1，表示表没有间隙可以尾插。0 不能尾插，2 总是可以尾插。
  - max_write_lock_count：最大写锁数，超过后 myisam 写锁更易获得。
  - slow_query_log：全局，开关慢查询功能。
  - slow_query_log_file：慢查询日志文件路径。默认是*主机名*-slow.log。
  - long_query_time：慢查询日志时间阈值。
  - unique_checks：开关唯一校验。
  - max_connections：全局，最大连接数，最大为 16384，设置成 85% 比较好。MySQL 会为每个连接创建缓冲区，所以不能盲目调大最大连接数。
  - back_log：全局，当连接数不够时，请求暂放堆中，堆的大小，默认 80，设置成最大连接数的 1/3 比较好。
  - innodb_thread_concurrency：全局，innodb 并发线程数，默认 0，表示无上线，设置成 cpu 核心数的两倍比较好。
  - wait_timeout：全局，连接超时，默认 8 小时。
  - innodb_buffer_pool_size：全局，InnoDB 的缓存容量，默认 128m。
  - thread_cache_size：连接线程缓冲数量。
  - character_set_client：字符编码。
  - character_set_results：字符编码。
  - max_sort_length：bolb、text 类型字段排序使用前多少字节数。
  - max_heap_table_size：内存表大小最大数。
  - tmp_table_size：内存临时表的大小。
  - read_buffer_size：缓存扫描大小。
  - sort_buffer_size：排序缓冲区。
  - join_buffer_size：连表查询缓冲大小。
  - read_rnd_buffer_size
  - log_queries_not_using_indexes：开关查询索引未使用收集。
  - query_cache_min_res_unit：查询缓存块以多少大小进行。
  - query_cache_size：查询缓存总大小。
  - query_cache_limit：使用缓存的最大值。
  - log_bin_trust_function_creators：创建函数时使用参数。
  - secure_file_priv：表数据信息输出文件路径。
  - innodb_file_per_table：innodb 储存引擎使用 .ibd 独享文件，还是 .ibdate 共享文件。
  - max_length_for_sort_data：与排序算法一次扫描和两次扫描有关。
  - optimizer_search_depth：关联表查询。
  - log_bin：开关二进制日志文件，设置文件名称。
  - binlog_do_db：进行复制的数据库名称。
  - relay_log：开启中继日志，设置日志名称。
  - profiling：开启后，可以通过 show profiles 查看上条 sql 执行时间。
  - foreign_key_checks：外键检测。
  - innodb_row_lock_current_waits：当前正在等待锁定的数量。
  - innodb_row_lock_time：从系统启动到现在锁定的总时间长度，单位 ms。
  - innodb_row_lock_time_avg：从系统启动到现在每次等待锁所花平均时间。
  - innodb_row_lock_time_max：从系统启动到现在等待锁最长的一次所花的时间。
  - innodb_row_lock_waits：从系统启动到现在总共等待的次数。
  - table_locks_immediate：可以立即获取锁的查询次数。
  - table_locks_waited：不能立即获取锁的次数。
  - max_used_connections：当前的连接数。

# 储存过程

1. CREATE DEFINER=*用户名*@*主机名* PROCEDURE *储存过程名*(IN | OUT | INOUT<small>后两者只能传变量。列名优先</small> *参数名* *参数数据类型*,...) COMMENT 'string' | LANGUAGE SQL | [NOT] DETERMINISTIC | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA } | SQL SECURITY { DEFINER | INVOKER } *标志名*:BEGIN *过程体* <small>可嵌套begin end</small> END *标志名*$$：创建储存过程。
2. 过程体中控制语句。
  - DECLATE *变量名*,... *数据类型* DEFAULT *默认值*：变量声明。
  - SET *变量名*=*值* | *变量名* | *表达式*,...：变量赋值。
  - IF *条件* THEN *语句* ESLE *语句* END IF;
  - CASE *变量* WHEN *值* THEN *语句*,... ELSE *语句* END CASE;
  - *标志名*:WHILE *条件* DO *语句* LEAVE | ITERATE *标志名*; END WHILE;
  - *标志名*:REPEAT *语句* LEAVE | ITERATE *标志名*; UNTIL *条件* END REPEAT;
  - *标志名*:LOOP *语句* LEAVE | ITERATE *标志名*; END LOOP;
  - DECLARE *游标名* CURSOR FOR SELECT *语句*; 声明游标。
  - OPEN *游标名*; 打开游标。
  - FETCH *游标名* INTO *变量名*,...：保存指针数据到变量中。
  - DECLARE *处理种类*(EXIT | CONTINUE) HANDLER FOR *例外的种类*(NOT FOUND) *例外发生时的处理*：例外处理
  - CLOSE *游标名*：关闭游标。
  - PREPARE *名* FROM @*变量*;
  - EXECUTE *名*;
  - DEALLOCATE PREPARE *名*：执行 SQL 语句。
3. DROP PROCEDURE *储存过程名*：删除储存过程。
4. CALL *储存过程名*(*参数值*)$$：调用储存过程。
5. DELIMITER *$$*：临时定义语句结束符。
6. SHOW PROCEDURE STATUS WHERE DB=*数据名*：查看数据库的储存过程。
7. SHOW CREATE PROCEDURE *数据库名*.*储存过程名*：查看数据库的储存过程。
8. SELECT routine_name FROM information_schema.routines WHERE routine_schema=*数据库名*：查询储存过程。

# 函数

1. CREATE FUNCTION *方法名*(*形参* *类型*,...) RETURNS *返回数据类型* BEGIN RETURN (*返回值查询语句* <small>返回结果集必须是一列一行</small>); END$$：创建函数。
2. DROP FUNCTION IF EXISTS *方法名*：删除函数。
3. SHOW CREATE FUNCTION *函数名*：查看函数详情。
4. SHOW FUNCTION STATUS：打印一堆。

# 用户

1. mysql -u *用户名* -p *密码* -h *主机*名 -A <small>表示选择数据库时关闭预读表和字段信息</small> -P *端口号*：登陆 mysql。
2. mysql -u *用户名* -p *密码* -h *主机名* -A -P *端口号* *数据库名* < *备份文件*：恢复备份。
3. CREATE USER *用户名*@*主机名* | % <small>表示所有</small> IDENTIFIED WITH mysql_native_password BY '*密码*'：创建用户。
4. GRANT *权限类型* [ (*字段*), ... ] ON *数据库名*.*表名* <small>\*号代表所有</small> TO *用户名*@*主机名*：给用户授权。
    权限类型：SELECT、INSERT、DELETE、UPDATE、REFERENCES(外键权限)、CREATE、ALTER、INDEX、DROP、SHOW VIEW、CREATE ROUTINE、ALTER ROUTINE、CREATE TEMPORARY TABLES CREATE VIEW、LOCK TABLES、ALL(ALL PRIVILEGES)、CREATE USER、SHOW DATABASES、REPLICATION SLAVE、REPLICATION CLIENT。
5. SHOW GRANTS FOR *用户名*@*主机名*：查看用户权限。
6. REVOKE *权限类型* [ (*字段*), ... ] ON *数据库名*.*表名* FROM *用户名*@*主机名*：删除用户权限。
7. DROP USER *用户名*@*主机名*, ...;：删除用户。
8. SET PASSWORD FOR [ *用户名*@*主机名* = ]*密码*：修改用户密码。
9. ALTER USER *用户名*@*主机名* IDENTIFIED BY *密码*：修改用户密码。
10. mysqladmin -u *root* password *xxx*：修改密码。
11. mysqldump -u *用户名* -h *主机名* -p [ *密码* ] *数据库名* *表名*, ... > *输出路径*：备份数据库到指定路径。
12. mysqladmin -u *root* -p shutdown：停止服务。
13. SOURCE *数据库路径*：从指定路径导入数据到当前数据库。
14. RENAME USER *旧帐号*@*主机名* TO *新帐号*@*主机名*：修改用户名。
15. flush privileges：刷新权限。

# 事务

1. START TRANSACTION：开启事务，手动提交。
2. ROLLBACK TO *保存点名*：回滚。
3. COMMIT：提交。
4. SAVEPOINT *保存点名*：设置保存点。
5. SET GLOBAL TRANSACTION ISOLATION LEVEL SERIALIZABLE | REPEATABLE READ | READ COMMITTED | READ UNCOMMITED：设置事务隔离级别。
6. FLUSH PRIVILEGES：刷新权限。
7. SELECT @@tx_isolation; 查看全局参数 @@ 事务隔离级别的值。

# 其它

1. SELECT *值* INTO *用户变量*：初始化或更改变量值。
2. QUIT：退出。
3. EXIT：退出。
4. SHOW WARNINGS：显示警告信息。
5. SET GLOBAL TIME_ZONE='+8:00'：设置全局时区。
6. SHOW VARIABLES LIKE '%CHARACTER%'：查看字符编码设置。
7. SELECT LAST_INSERT_ID()：查询最后一次生成的主键 ID。
8. SHOW GLOBAL VARIABLES：显示参数设置详情。
9. SHOW ENGINES：显示储存引擎信息。
10. SHOW MATER STATUS：测试主库。
11. SHOW SLAVE STATUS：测试从库。
12. CHANGE MASTER TO
      MASTER_HOST='172.18.0.1',
      MASTER_PORT=3306,
      MASTER_USER='mycat',
      MASTER_PASSWORD='123',
      MASTER_LOG_FILE='master-data-logs.000009',
      MASTER_LOG_POS=155：配置从库连接主库。
13. START SLAVE：从库开始复制。
14. STOP SLAVE; 从库结束复制。
15. FLUSH LOGS：重置主库复制日志。
16. LOCK TBALES *表名* AS *别名* READ | WRITE LOCAL <small>myisam允许尾插</small>,... 给表上锁。
17. UNLOCK TABLES：解锁表。
18. OPTIMIZE TABLE *表名*：回收空洞。
19. SHOW ENGINE INNODB STATUS：显示 innodb 监视器。
20. SHOW PROCESSLIST：显示正在查询的 SQL。
21. SHOW PROFILES：查看每条 sql 执行所花时间。
22. TEE *文件路径*：将输出信息保存到指定的文件中。
23. NOTEE：结束信息输出到文件。

# 运算符

1. = ：等于。相等返回 1，否则返回 0。
2. <=>：安全等于。null<=>null 返回 1。
3. != | <>：不等于。
4. <：小于。
5. <=：小于等于。
6. \>：大于。
7. \>=：大于等于。
8. \+：加运算。
9. \-：减运算。
10. \*：积运算。
11. /：除运算。
12. %：取模。
13. _：通配一个字符。
14. %：通配多个字符。
15. \>>：右位移。
16. <<：左位移。
17. |：与位运算。
18. &：且位元算。
19. ^：抑或位运算。
20. ~：按位取反。
21. DIV：除法返回结果的整数部分。
22. BETWEEN AND：在之间。
23. IS NULL：是空判断。
24. IS NOT NULL：非空判断。
25. AND | &&：并且。
26. OR | ||：或者。
27. XOR：抑或。
28. NOT | !：不是。
29. [NOT] LIKE
30. REGEXP '*正则表达式*'
31. RLIKE '*正则表达式*'
32. 子查询多行单列运算符
   - ANY 或者 SOME：例如 SELECT * FROM emp WHERE sal > ALL (SELECT sal FROM emp WHERE deptno=30)
   - ALL
33. [NOT] EXISTS：是否有返回数据。
34. [NOT] IN：在某个字段值内。
35. @：设置获取用户变量 set @t=100;select @t;@t:=@t+12; 表示 t 变量等于自身加 12。
36. @@：设置获取系统变量。
37. 运算符优先级：NOT > AND > OR。

# 查询函数

1. DISTINCT *字段名*：该字段去重。
2. AS *别名*：设置别名。
3. UNION 合并。
4. UNION ALL 合并不去重。
5. [INNER] JOIN *表名* ON *条件*：内连接查询。
6. CROSS JOIN 内连接查询。
7. LEFT [OUTER] JOIN *表名* ON *条件*：左外连接。
8. RIGHT [OUTER] JOIN *表名* ON *条件*：右外链接。

# 数学函数

1. BIN(*数字*)：返回二进制表示。
2. OCT(*数字*)：返回八进制表示。
3. HEX(*数字*)：返回十六进制表示。
4. CONV(*数字*, *原进制*, *进制*)：进制转换。
5. ABS(*数字*)：返回绝对值。
6. CEILING(*数字*)：返回大于数字的最小整数值。
7. FLOOR(*数字*)：返回小于数字的最大整数值。
8. GREATEST(*数字*,...)：返回集合中最大的值。
9. LEAST(*数字*,...)：返回集合中最小的值。
10. EXP(*数字*)：返回值 e 的数字次方。
11. POWER(*底数*, *指数*)：幂。
12. LN(*数字*)：返回数字的自然对数。
13. LOG(*真数*, *底数*)：返回对数。
14. MOD(*数字1*,* 数字2*)：返回数字 1 取数字 2 的模。
15. SQRT(*数字*)：返回数字的平方根。
16. PI()：返回圆周率的值。
17. TRUNCATE(*数字*, *小数位数*)：返回数字为指定小数位的结果。
18. FORMAT(*数字*, *小数位数*)：保留小数位数。
19. ROUND(*数字*, *小数位数*)：返回数字的四舍五入的有指定小数位的值。默认对小数一下四舍五入。如果位数为负数，则对整数四舍五入。
20. RAND(*种子数*)：返回 0 到 １ 内的随机值，可以通过提供一个参数种子。
21. SIGN(*数字*)：返回代表数字的符号的值：1 -1 0。

# 聚合函数

1. AVG(*字段名*)：返回指定列的平均值。如果指定列类型不是数值类型，那么计算结果为 null。
2. COUNT(*字段名*)：返回指定列中非 NULL 值的个数。COUNT(*) 计算 NULL 值个数。
3. MIN(*字段名*)：返回指定列的最小值。如果指定列是字符串类型，那么使用字符串排序运算。
4. MAX(*字段名*)：返回指定列的最大值。如果指定列是字符串类型，那么使用字符串排序运算。
5. SUM(*字段名*)：返回指定列的所有值之和。如果指定列类型不是数值类型，那么计算结果为 0。
6. GROUP_CONCAT(*字段名* [ SEPARATOR *sep* ])：返回字段值拼接结果，中间用逗号隔开。

# 字符串函数

1. ASCII('*字符*')：返回字符的 ASCII 码值。
2. CHAR(*数字*,...)：ASCII 码转化成字符串。
3. BIT_LENGTH('*字符串*')：返回字符串的字节数。
4. CHARSET('*字符串*')：返回字串字符集。
5. CONCAT('*字符串*', '*字符串*',...)：连接成一个字符串。
6. CONCAT_WS('*分割符*', '*字符串*', '*字符串*',...)：连接成一个字符串，并用分割符间隔。
7. LCASE('*字符串*') | LOWER('*字符串*')：返回将字符串中所有字符改变为小写后的结果。
8. UCASE('*字符串*') | UPPER('*字符串*')：返回将字符串中所有字符转变为大写后的结果。
9. INSERT('*字符串*', *插入的开始下标*, *插入长度*, '*待插入字符串*')：字符串插入，不足替换则保留剩余未替换内容，过多替换，则原字符往后挪。
10. LOCATE('*要搜的字符串*', '*被搜索字符串*', *开始搜索下标*)：查找字符串下标，不存在返回 0。
11. INSTR('*被搜字符串*', '*搜索字符串*')：返回字符串首次在字符串中出现的位置，不存在返回 0。
12. FIND_IN_SET('*搜索字符串*', '*被搜字符串*'： 分析逗号分隔的列表，如果发现字符串，返回在列表中的位置。
13. LEFT('*字符串*', *字符数*)：返回字符串最左边的指定个字符。
14. RIGHT('*字符串*', *字符数*)：返回字符串最右边的指定个字符。
15. LENGTH('*字符串*')：返回字符串中的字节数。
16. CHAR_LENGTH('*字符串*')：返回字符串中的字符数。
17. BIT_LENGTH('*字符串*')：返回字符串中的位数。
18. LTRIM('*字符串*')：切掉字符串开头的空格。
19. RTRIM('*字符串*')：切掉字符串尾部的空格。
20. TRIM('*字符串*')：去除字符串首部和尾部的所有空格。
21. QUOTE('*字符串*')：用反斜杠转义字符串中的单引号。
22. REVERSE('*字符串*')：返回颠倒字符串的结果。
23. STRCMP('*字符串1*', '*字符串2*')：比较字符串，返回 0 表示相同，-1 表示不同。
24. REPLACE('*字符串*', '*搜索字符串*', '*代替字符串*')：在字符串中用代替字符串替换搜索字符串。
25. RPAD('*字符串*', *长度*, '*补充字符串*')：在字符串后用补充字符串补充，直到指定长度。
26. REPEAT(*字符串*, *次数*)：返回重复次数字符串。
27. LPAD('*字符串*', *长度*, '*补充字符串*')：重复用补充字符串加在字符串开头，直到字串到指定长度。
28. SUBSTRING('*字符串*', *开始下标*, *长度*)：从字符串的指定下标开始截取字符串。
29. SPACE(*空格数*)：生成空格。
30. UUID()：返回 uuid 字符串。
31. SUBSTRING_INDEX('*字符串*', '*分隔符*', *count*)：返回一个字符串中的子字符串。

# 日期和时间函数

1. NOW()：返回当前的日期和时间。
2. CURDATE()|CURRENT_DATE()：返回当前的日期。
3. CURTIME()|CURRENT_TIME()：返回当前的时间。
4. CURRENT_TIMESTAMP()：返回当前时间戳。
5. YEAR('*时间*')：返回日期年份(1000~9999)。
6. MONTH('*时间*')：返回月份值 (1~12)。
7. MONTHNAME('*时间*')：返回月份名。
8. DATE('*时间*')：返回时间的日期部分。
9. DAY('*时间*')：返回日期天数。
10. DAYOFMONTH('*时间*')：返回月的第几天 (1~31)。
11. HOUR('*时间*')：返回小时值 (0~23)。
12. MINUTE('*时间*')：返回分钟值 (0~59)。
13. MICROSECOND('*时间*')：微秒。
14. DAYOFWEEK('时间')：返回一星期中的第几天 (1~7)。
15. DAYNAME('*时间*')：返回星期名。
16. WEEK('*时间*')：返回日期一年中第几周 (0~53)。
17. YEARWEEK('*时间*')：返回年数和第几周数。
18. QUARTER('*时间*')：返回季度 (1~4)。
19. DAYOFYEAR('*时间*')：返回年的第几天 (1~366)。
20. LAST_DAY('*时间*')：月的最后日期。
21. ADDTIME('*时间1*', '*时间2*')：将时间2加到时间1。
22. DATE_ADD('*时间*', INTERVAL *数* *关键字*)：返回日期加上间隔时间的结果，如：SELECT DATE_ADD(CURRENT_DATE，INTERVAL 6 MONTH);
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
23. DATE_SUB('*时间*', INTERVAL *数* *关键字*)：返回日期减上间隔时间的结果，如：SELECT DATE_SUB(CURRENT_DATE，INTERVAL 6 MONTH);
24. PERIOD_ADD(*时间*, *数字*)：增加月数到时间（以格式 YYMM 或 YYYYMM)，以格式 YYYYMM 返回值，注意参数时间不是日期值。
25. CONVERT_TZ('*时间*', *原时区*, *转成时区*)：转换时区。
26. DATE_FORMAT('*时间*', *格式*)：依照指定格式格式化日期时间值。DATE_FROMAT(NOW()，’%Y%m%d’);#20200118
27. TIME_FORMAT('*时间*', *格式*)：依照格式格式化时间值。
28. FROM_UNIXTIME(*时间戳*, *格式*)：根据指定的格式，格式化 UNIX 时间戳。
29. UNIX_TIMESTAMP()：返回 unix 时间戳，毫秒数。
30. TIMEDIFF('*时间*', '*时间*')：两个时间差。
31. DATEDIFF('*时间*', '*时间*')：两个日期差。
32. PERIOD_DIFF('*时间*', '*时间*')：返回在时期之间月数，以格式 YYMM 或 YYYYMM，注意，时期参数不是日期值。
33. TIMESTAMPDIFF(*单位*, *时间*, *时间*)。
34. EXTRACT(interval_name FROM '*时间*')：从时间中提取日期的指定部分。
35. MAKEDATE('*年*', *天数*)：给出年及年中的第几天，生成日期串。
36. MAKETIME('*小时*', '*分钟*', '*秒*')：生成时间串。
37. SEC_TO_TIME('*秒*')：秒数转成时间。
38. STR_TO_DATE('*字符串*', '*格式*')：字符串转成时间，以指定格式显示。
39. TIME_TO_SEC('*时间*')：时间转秒数。
   - 格式规则
   - %Y：年，数字，4 位。
   - %y：年，数字，2 位。
   - %M：月名字(January-December)。
   - %b：缩写的月份名字(Jan-Dec)。
   - %m：月，数字(01-12)。
   - %c：月，数字(1-12)。
   - %D：有英语前缀的月份的日期(1st，2nd，3rd，等等)。
   - %d：月份中的天数，数字(00-31)。
   - %e：月份中的天数，数字(0-31)。
   - %W：星期名字(Sunday-Saturday)。
   - %w：一个星期中的天数(0=Sunday、6=Saturday）。
   - %a：缩写的星期名字(Sun-Sat)。
   - %U：星期，这里星期天是星期的第一天。
   - %u：星期，这里星期一是星期的第一天。
   - %H：小时(00-23)。
   - %k：小时(0-23)。
   - %h：小时(01-12)。
   - %I：小时(01-12)。
   - %i：小时(1-12)。
   - %i：分钟，数字(00-59)。
   - %S：秒(00-59)。
   - %s：秒(0-59)。
   - %p：AM 或 PM。
   - %r：时间，12 小时(hh:mm:ss[A|P]M)。
   - %T：时间，24 小时(hh:mm:ss)。
   - %j：一年中的天数(001-366)。
   - %%：一个文字“%”。
   - 所有的其他字符不做解释被复制到结果中。

# 加密函数

1. AES_ENCRYPT('*字符串*', '*密钥*')：返回用密钥对字符串利用高级加密标准算法加密后的结果，调用 AES_ENCRYPT 的结果是一个二进制字符串，以 BLOB 类型存储。
2. AES_DECRYPT('*字符串*', '*密钥*')：返回用密钥对字符串利用高级加密标准算法解密后的结果。
3. DECODE('*字符串*', '*密钥*')：使用密钥解密加密字符串。
4. ENCODE('*字符串*', '*密钥*')：使用密钥加密字符串，调用 ENCODE() 的结果是一个二进制字符串，它以 BLOB 类型存储。
5. ENCRYPT('*字符串*', '*盐*')：使用 UNIXcrypt() 函数，用盐(一个可以惟一确定口令的字符串，就像钥匙一样)加密字符串。
6. MD5('*字符串*')：计算字符串的 MD5 校验和。
7. SHA('*字符串*')：计算字符串的安全散列算法(校验和)。
8. TO_BASE64(*blob 字段*)
9. FROM_BASE64(*blob 字段*)

# 控制流函数

1. CASE WHEN *条件* THEN *结果1* ELSE *结果2* END：如果条件是真，则返回结果 1，否则返回结果 2。
2. CASE *值* WHEN *等于值* THEN *结果1*,... ELSE *结果2* END
3. IF(*条件*, *结果1*, *结果2*)：如果条件是真，返回结果 1，否则返回结果 2。
4. IFNULL(*值1*, *值2*)：如果值1  不是空，返回值 1，否则返回值 2。
5. NULLIF(*值1*, *值2*)：如果两值相等返回 NULL，否则返回值 1。
6. ISNULL(*值*)：如果值是 null，返回 0。

# 格式化函数

1. INET_ATON(*IP 地址*)：返回 IP 地址的数字表示。
2. INET_NTOA(*数字*)：返回数字所代表的 IP 地址。

# 系统信息函数

1. DATABASE()：返回当前数据库名。
2. BENCHMARK(*次数*, *表达式*)：将表达式重复运行指定次数。
3. CONNECTION_ID()：返回当前客户的连接 ID。
4. FOUND_ROWS()：返回最后一个 SELECT 查询进行检索的总行数。
5. USER()|SYSTEM_USER()：返回当前登陆用户名。
6. VERSION()：返回 MySQL 服务器的版本。

# Mycat1 6.7.5

1. mycat start：运行服务。
2. mycat stop：停止服务。
3. mycat console：运行服务。

# Docker 安装

1. docker pull mysql:8.1.0
2. docker run -v volumes/mysql/data:/var/lib/mysql -v volumes/mysql/config:/etc/mysql/conf.d -v volumes/mysql/log:/logs --hostname ivfzhou-docker-mysql --name mysql --ip 172.16.3.143 --network ivfzhou-docker-network --add-host ivfzhoudebian:172.16.3.143 -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 mysql:8.1.0

# 二进制安装

1. 解压文件，在程序目录下新建 data 文件夹，里面不能有文件。
2. 编写 my.cnf 文件，复制到 /opt/mysql 下。
3. 安装依赖 sudo apt install libaio1 libtinfo5。
4. 在 bin 目录下运行 mysqld --defaults-file=/opt/mysql/my.cnf --user=ivfzhou --initialize-insecure --console：参数 insecure 表示初始化空密码。
5. 将 /support-files/mysql.server 复制到 /etc/init.d/ 下，修改该文件的 basedir 和 basedatadir。
6. 添加 mysql 环境变量，vim /etc/profile，export mysql=/opt/mysql export PATH=$PATH:$mysql/bin，source /etc/profile。
7. sudo dpkg -i libaio1_0.3.110-2_amd64.deb libc6_2.31-0ubuntu9_amd64.deb libtinfo5_6.2-0ubuntu2_amd64.deb libncurses5_6.2-0ubuntu2_amd64.deb。
8. reboot。
9. 登录 mysql 修改 root 密码：ALTER USER root@localhost IDENTIFIED BY '123456'。

# Centos 安装

1. 解压压缩包到 /home/ivfzhou/programs/mysql 下。
2. sudo rpm -i ncurses-compat-libs-6.1-7.20180224.el8.x86_64.rpm。
3. 编辑环境变量 /etc/profile 添加。
  - export mysql=/home/ivfzhou/programs/mysql
  - export PATH=$PATH:$mysql/bin
4. sudo cp my.cnf /etc/mysql/my.cnf。
5. mysqld --defaults-file=/etc/mysql/my.cnf --user=zf --initialize-insecure --console。
6. sudo cp supprot-files/mysql.server /etc/init.d/mysql.server。
7. 修改 mysql.server 里 basedir 和 datadir。
8. mysqld_safe --user=ivfzhou &。
9. reboot。
10. alter user root@localhost identified by '123456'。

# Windows 安装

1. 给添加环境变量。
2. 程序目录创建文件夹 data。
3. 程序目录创建文件 my.ini。
4. cmd 运行：
5. mysqld --initialize-insecure –console。
6. mysqld –install。
7. net start mysql。
8. 修改 root 密码，alter user root@localhost identified by '123456'。

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

1. 设置 /etc/mysql/my.cnf 添加：
  - log_bin=/ivfzhou/home/mysql/data/binlog 
  - server_id=1
2. 运行 mysql。
3. 开启主库客户端，创建用户 mycat@172.18.0.2，授权：
  - GRANT REPLICATION SLAVE,REPLICATION CLIENT ON *.* TO *mycat*@*172.18.0.2*;
  - GRANT ALL ON *mycat*.* TO *mycat*@*172.18.0.2*;
4. docker 运行 mysql 容器，数据卷 my.cnf 添加：
  - server_id=2
  - relay_log=relaylog
5. 从库开启客户端运行命令：
    CHANGE MASTER TO
    MASTER_HOST='172.18.0.1',
    MASTER_PORT=3306,
    MASTER_USER='mycat',
    MASTER_LOG_FILE='master-data-logs.000006',
    MASTER_LOG_POS=155;
6. 从库运行 START SLAVE USER=*mycat* PASSWORD='123'：开始复制。

# SQL Server 2000
1. 批处理文件以 GO 结尾标识一组语录。
2. DECLARE @*变量名* *数据类型*,...
3. SET @*变量名* = *表达式*
4. SELECT @*变量名* = *表达式*
5. SELECT @*变量名* = *字段* FROM TABLE
6. BEGINE ... END：语句块。
7. IF *条件* ... ELSE ...
8. WHILE *条件* ... [BREAK | CONTINUE]
9. RETURN *整数*：退出批处理函数存储过程触发器。
10. EXECUTE *过程函数* *参数* [OUTPUT]
11. BEGIN TRANSACTION ... [ROLLBACK TRANSACTION] [COMMIT TRANSCTION] END
12. /**/：注释。
13. --：注释。
14. DECLARE *游标* CURSOR FOR *查询语句*
15. OPEN *游标*
16. FETCH FROM *游标* INTO *变量*
17. CLOSE *游标*
18. DEALLOCATE *游标*
19. @@FETCH_STATUS：全局变量游标状态。
20. CREATE PROCEDURE *过程* *参数数据类型*,... [OUTPUT] AS ...
21. DROP PROCEDURE *过程*
22. CREATE TRIGGER *触发器* ON *表* FOR|(INSTEAD OF) UPDATE|INSERT|DELETE AS ...
23. USE *数据库*
24. EXEC sp_addumpdevice 'disk', *名*, *路径*：创建逻辑备份设备。
25. BACKUP DATEBASE *名* TO *名*：备份数据库。
26. BACKUP LOG *名* TO *名*：备份日志文件。
27. RESTORE LOG *名* FROM *名*：恢复事务日志。
28. RESTORE DATABASE *名* FROM *名* WITH NORECOVERY：恢复数据库后援副本。
29. EXEC sp_addlogin *名* *密码*：创建账户。
30. EXEC sp_grantdbaccess *账户名* *用户名*：数据库下添加一个用户。
31. GRANT SELECT, UPDATE, INSERT, UDELETE, ALL,CREATE DATEBASE, CREATE FUNCTION, CREATE PROCEDURE, CREATE TABLE, CREATE VIEW, BACKUP DATABASE, BACKUL LOG ON *库* TO *用户名* [WITH GRANT - OPTION]：授权。
32. EXEC sp_addrole *角色*：数据库下添加角色。
33. EXEC sp_addrolemember *角色* *用户*：用户赋予角色。
34. REVOKE ALL ON *数据库对象* FROM *用户* [RESTRICT <small>若存在转授也拒绝</small> | CASACDE <small>级联回收转授权限</small>]：回收权限。
35. CREATE VIEW *视图* AS *查询*：创建视图。
36. CALL *过程名* [(*参数*,...)]
