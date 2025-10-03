# 一、Docker 安装

1. docker pull jaspeen/oracle-11g。
2. 将程序包解压到 /home/ivfzhou/volumes/oracle/。
3. docker run --privileged --name oracle11g -p 1521:1521 -v /home/ivfzhou/volumes/oracle/database:/install/database --hostname oracle11g jaspeen/oracle-11g。等待控制台打印100% complete表示安装完毕。
4. vim /etc/profile 添加 export ORACLE_HOME=/opt/oracle/app/product/11.2.0/dbhome_1。
   - export ORACLE_SID=orcl。
   - export PATH=$PATH:$ORACLE_HOME/bin。

5. dokcer exec -it oracle11g bash。
  - su oracle
  - sqlplus / as sysdba 进入控制台。
  - alter user scott account unlock; 解锁 scott 帐号。
  - commit;
  - conn scott/tiger 修改密码。  
  - sqlplus /nolog 无用户名登录。
  - sqlplus scott/94683364 登录 scott 用户。
  - conn /as sysdba 管理员数据库。
  - alter user system identified by 94683364; 修改密码。
  - grant create view on table to scott; 授权。
  - sqlplus system/94683364@127.0.0.1:1521/orcl 连接数据库。

# 二、三大范式

1. 数据库表中的所有字段值都是不可分解的原子值。
2. 数据库表中的每一列都和主键相关，而不能只与主键的某一部分相关。
3. 确保数据表中的每一列数据都和主键直接相关，而不能间接相关。

# 三、数据类型

1. CHAR(长度)
2. VARCHAR(长度)
3. VARCHAR2(长度)
4. NUMBER(长度)
5. DATE
6. TIMESTAMP
7. CLOB
8. BLOB

# 四、表

1. WITH *临时表名* AS (*查询语句*)：定义临时表。
2. CREATE TABLE *表名* (*字段名* *数据类型* DEFAULT *默认值*, ...);：创建表。
3. CREATE TABLE *表名* AS *查询语句*;：创建表。
4. RENAME *旧表名* TO *新表名*;：修改表名称。
5. DROP TABLE *表名*;：删除表。
6. DELETE FROM *表名*;：删除表数据。
7. FLASHBACK TABLE *表名* TO BEFORE DROP;：恢复 recyclebin 中删除的表。
8. ALTER TABLE *表名* ADD (*字段名* *数据类型* DEFAULT *默认值*, ...);：添加表字段。
9. ALTER TABLE *表名* RENAME TO COLUMN *旧字段名* TO *新字段名*;：修改表字段名。
10. ALTER TABLE *表名* MODIFY (*字段名* *数据类型* DEFAULT *默认值*);：修改表字段属性。
11. LATER TABEL *表名* DROP COLUMN *字段名*;：删除表字段。
12. UPDATE *表名* SET *字段名*=*值*,... WHERE *条件*;：更新字段数据。
13. SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME='*表名*';：查看表的字段信息。
14. SELECT *字段*||*字段* * FROM *表名*;：拼接显示。
15. BETWEEN AND ORDER BY UNION UNION ALL HAVING GROUP BY DISTINCT 
16. (+)放在等号右边表示左链接
17. (+)放在等号左边表示右链接
18. where (job, mgr) = (select job, mgr ...)
19. where (job, mgr) in (select job, mgr ...)
20. COMMENT ON COLUMN *表名*.*字段* IS '*注释*';：给字段加注释。
21. COMMENT ON TABLE *表名* IS '*注释*';：给表加注释。

# 五、序列

1. CREATE SEQUENCE *序列名* INCREMENT BY *步长*，默认 1 START WITH *起始值*，默认 0 MAXVALUE *最大值*，默认27个9 MINVALUE *最小值* CYCLE或者NOCYCLE(默认不循环) CACHE *大小*，默认缓存20或者NOCACHE;：创建序列。
2. SELECT *序列名*.nextval FROM dual;：序列自增一次。
3. SELECT *序列名*.currval FROM dual;：查看序列当前值。
4. DROP SEQUENCE *序列名*;：删除序列。
5. 伪列
6. ROWID
7. ROWNUM 
8. sysdate
9. select \* from (select a.\*, rownum rn from *表名* a where rownum<=courrentPage\*pageSize) tmp where tmp.rn>(courrentPage-1)\*pageSize;：分页查询。
10. SELECT * FROM user_sequences;：查询序列。

# 六、事务

1. SET AUTOCOMMI OFF：取消自动提交事务，用户需要手动提交事务。
2. SET AUTOCOMMI ON：打开自动提交事务，事务自动提交。
3. COMMIT：提交事务。
4. ROLLBACK：回滚事务。
5. ROLLBACK TO *回滚点*：回滚到某个事务保存点。
6. SAVEPOINT *事务保存点名称*：设置事务保存点。

# 七、索引

1. CREATE INDEX *索引名* ON *表名*(*字段*);：创建索引。
2. SELECT * FROM user_indexes;：查询索引。
3. DROP INDEX *索引名*;：删除索引。

# 八、用户

1. GRANT *权限* TO *用户名*;：授权。
2. ALTER USER *用户名* IDENTIFIED BY *密码*;：修改用户密码。
3. SELECT * FROM system_privilege_map;：查询系统权限。
4. SELECT * FROM table_privilege_map;：查询对象权限。
5. SELECT * FROM dba_users;：查询所有用户。
6. ALTER USER *用户名* ACCOUNT UNLOCK或者LOCK;：上解锁用户。

# 九、视图

1. CREATE FORCE表示要创建视图的表不存在也可以创建视图或者NO FORCE默认，表示要创建的视图表必须存在，否则无法创建 或者OR REPLACE如果创建的视图不存在，则创建新的视图，否则将其替换 VIEW *视图名* AS *查询语句*;：创建视图。
2. DROP VIEW *视图名*;：删除视图。

# 十、储存过程

1. CREATE OR REPLACE PROCEDURE 储存名(参数名 IN可省略，或者OUT或者IN OUT 数据类型...) IS 变量1 数据类型 := 值;... BEGIN 过程体; END; 创建储存过程。
2. 局部变量名 数据类型; 声明变量。
3. 局部变量名 := 值; 变量赋值。
4. dbms_output.put_line(内容...) 打印输出。
5. SELECT 字段... INTO 变量... 把输出值赋给变量。
6. SET SERVEROUTPUT ON; 设置在控制台输出。
7. VAR 变量名 数据类型; 声明变量。
8. CALL 储存过程名(参数,...); 执行储存过程。
9. DROP PROCEDURE 储存过程名; 删除储存。

# 十一、函数

- CREATE OR REPLACE FUNCTION 函数名(参数名 IN(可省略)或者OUT或者IN OUT 数据类型, ...) RETURN 数据类型 AS 变量1 类型;... BEGIN 函数体; END; 创建函数。

# 十二、库

1. ALTER SESSION SET CURRENT_SCHEMA=数据库; 切换数据库。

# 十三、约束

1. NOT NULL
2. UNIQUE
3. PRIMARY KEY
4. CHECK(...)
5. CONSTRAINT 表名 PRIMARY KEY (字段名)
6. CONSTRAINT 表名 FOREIGN KEY (字段名) REFERENCES 表名(字段名)

# 十四、字符串函数

1. UPPER(字符串) 字符串转大写。
2. LOWER(字符串) 字符串转小写。
3. INITCAP(字符串) 字符串首字母大写。
4. LENGTH(字符串) 返回字符串的长度。
5. SUBSTR(字符串, 下标, 长度) 截取字符串。函数是从1开始的。
6. REPLACE(字符串, 要替换的字符串, 替换成字符串) 替换字符串。
7. TRIM(字符串) 去掉字符串左右两边空格。
8. INSTR(字符串, 要查找的字符串) 查找字符串在指定字符串中的位置。

# 十五、数字函数

- ROUND(数字, 位数) 对小数进行四舍五入，可以指定保留的位数，如果不指定则表示将小数点之后的数字全部进行四舍五入。
- TRUNC(数字, 位数) 保留指定位数，如果不指定，则表示不保留小数。
- MOD(数字, 数字) 取模。

# 十六、日期函数

1. ADD_MONTHS(日期, 数字) 指定的日起加上指定的天数，返回新的日期。
2. MONTHS_BETWEEN(日期, 日期) 返回两个日期间的间隔月数。
3. NEXT_DAY(日期, 星期数) 返回下个星期的日期。
4. LAST_DAY(日期) 返回日期的最后一天。
5. SELECT SYSDATE可选(+3) FROM DUAL; 获取当前系统时间。

# 十七、转换函数

1. TO_CHAR(日期或数字, 格式) 将指定的数据按照指定格式变为字符串类型。
2. TO_DATE(字符串, 格式) 将指定的字符串按照指定格式变为日期类型。
3. YYYY 表示年
4. MM 表示月份
5. DD 表示一月里的天数
6. D 表示一周里的天数
7. DY 表示星期几
8. HH 表示12小时制
9. HH 24表示24小时制
10. MI 表示分钟
11. SS 表示秒
12. TO_NUMBER(字符串) 将指定的数据变为数字型。

# 十八、通用函数

1. NVL(列, 默认值) 如果显示数据是null，就使用默认值。
2. DECODE(列, 判断值1, 显示1, 判断值2, 显示2..., 默认值) 多值判断，如果某一列与判断值相同,则使用指定的显示结果输出，如果没有满足条件,则显示默认值。
3. CASE 列 WHEN 表达式1 THEN 显示1... ELSE 默认值 END 用于实现多条件判断，在WHEN之后编写条件，而在THEN之后编写条件满足的显示操作，如果都不满足则使用ELSE中的表达式处理。

# 十九、统计函数

1. COUNT(列) 求出全部的记录数。
2. SUM(列) 求出总和。
3. AVG(列) 平均值。
4. MAX(列) 最大值。
5. MIN(列) 最小值。
6. MEDIAN(列) 返回中间值。

# 二十、Docker-Compose 配置

```yaml
services:
  oracle11g:
    image: jaspeen/oracle-11g:latest
    container_name: oracle11g
    hostname: oracle11g
    networks:
      network:
        ipv4_address: 172.16.3.157
    privileged: true
    environment:
      - ORACLE_HOME=/opt/oracle/app/product/11.2.0/dbhome_1
      - ORACLE_SID=orcl
      - PATH=$PATH:$ORACLE_HOME/bin
    ports:
      - "1521:1521"
    volumes:
      - /home/ivfzhou/volumes/oracle/database:/install/database:rwx
```
