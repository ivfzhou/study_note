# docker安装

- docker pull jaspeen/oracle-11g。
- 将程序包解压到 /home/ivfzhou/volumes/oracle/。
- docker run --privileged --name oracle11g -p 1521:1521 -v /home/ivfzhou/volumes/oracle/database:/install/database --hostname oracle11g jaspeen/oracle-11g。等待控制台打印100% complete表示安装完毕。
- vim /etc/profile 添加 export ORACLE_HOME=/opt/oracle/app/product/11.2.0/dbhome_1。export ORACLE_SID=orcl。export PATH=$PATH:$ORACLE_HOME/bin。
- dokcer exec -it oracle11g bash。
  - su oracle
  - sqlplus / as sysdba 进入控制台。
  - alter user scott account unlock; 解锁 scott 帐号。
  - commit;
  - conn scott/tiger 修改密码。
  其它：  
  - sqlplus /nolog 无用户名登录。
  - sqlplus scott/94683364 登录 scott 用户。
  - conn /as sysdba 管理员数据库。
  - alter user system identified by 94683364; 修改密码。
  - grant create view on table to scott; 授权。
  - sqlplus system/94683364@127.0.0.1:1521/orcl 连接数据库。

# 三大范式

- 数据库表中的所有字段值都是不可分解的原子值。
- 数据库表中的每一列都和主键相关，而不能只与主键的某一部分相关。
- 确保数据表中的每一列数据都和主键直接相关，而不能间接相关。

# 数据类型

- CHAR(长度)
- VARCHAR(长度)
- VARCHAR2(长度)
- NUMBER(长度)
- DATE
- TIMESTAMP
- CLOB
- BLOB

# 表

- WITH *临时表名* AS (*查询语句*)：定义临时表。
- CREATE TABLE *表名* (*字段名* *数据类型* DEFAULT *默认值*, ...);：创建表。
- CREATE TABLE *表名* AS *查询语句*;：创建表。
- RENAME *旧表名* TO *新表名*;：修改表名称。
- DROP TABLE *表名*;：删除表。
- DELETE FROM *表名*;：删除表数据。
- FLASHBACK TABLE *表名* TO BEFORE DROP;：恢复 recyclebin 中删除的表。
- ALTER TABLE *表名* ADD (*字段名* *数据类型* DEFAULT *默认值*, ...);：添加表字段。
- ALTER TABLE *表名* RENAME TO COLUMN *旧字段名* TO *新字段名*;：修改表字段名。
- ALTER TABLE *表名* MODIFY (*字段名* *数据类型* DEFAULT *默认值*);：修改表字段属性。
- LATER TABEL *表名* DROP COLUMN *字段名*;：删除表字段。
- UPDATE *表名* SET *字段名*=*值*,... WHERE *条件*;：更新字段数据。
- SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME='*表名*';：查看表的字段信息。
- SELECT *字段*||*字段* * FROM *表名*;：拼接显示。
- BETWEEN AND ORDER BY UNION UNION ALL HAVING GROUP BY DISTINCT 
- (+)放在等号右边表示左链接
- (+)放在等号左边表示右链接
- where (job, mgr) = (select job, mgr ...)
- where (job, mgr) in (select job, mgr ...)
- COMMENT ON COLUMN *表名*.*字段* IS '*注释*';：给字段加注释。
- COMMENT ON TABLE *表名* IS '*注释*';：给表加注释。

# 序列

- CREATE SEQUENCE *序列名* INCREMENT BY *步长*，默认 1 START WITH *起始值*，默认 0 MAXVALUE *最大值*，默认27个9 MINVALUE *最小值* CYCLE或者NOCYCLE(默认不循环) CACHE *大小*，默认缓存20或者NOCACHE;：创建序列。
- SELECT *序列名*.nextval FROM dual;：序列自增一次。
- SELECT *序列名*.currval FROM dual;：查看序列当前值。
- DROP SEQUENCE *序列名*;：删除序列。
- 伪列
- ROWID
- ROWNUM 
- sysdate
- select \* from (select a.\*, rownum rn from *表名* a where rownum<=courrentPage\*pageSize) tmp where tmp.rn>(courrentPage-1)\*pageSize;：分页查询。
- SELECT * FROM user_sequences;：查询序列。

# 事务

- SET AUTOCOMMI OFF：取消自动提交事务，用户需要手动提交事务。
- SET AUTOCOMMI ON：打开自动提交事务，事务自动提交。
- COMMIT：提交事务。
- ROLLBACK：回滚事务。
- ROLLBACK TO *回滚点*：回滚到某个事务保存点。
- SAVEPOINT *事务保存点名称*：设置事务保存点。

# 索引

- CREATE INDEX *索引名* ON *表名*(*字段*);：创建索引。
- SELECT * FROM user_indexes;：查询索引。
- DROP INDEX *索引名*;：删除索引。

# 用户

- GRANT *权限* TO *用户名*;：授权。
- ALTER USER *用户名* IDENTIFIED BY *密码*;：修改用户密码。
- SELECT * FROM system_privilege_map;：查询系统权限。
- SELECT * FROM table_privilege_map;：查询对象权限。
- SELECT * FROM dba_users;：查询所有用户。
- ALTER USER *用户名* ACCOUNT UNLOCK或者LOCK;：上解锁用户。

# 视图

- CREATE FORCE表示要创建视图的表不存在也可以创建视图或者NO FORCE默认，表示要创建的视图表必须存在，否则无法创建 或者OR REPLACE如果创建的视图不存在，则创建新的视图，否则将其替换 VIEW *视图名* AS *查询语句*;：创建视图。
- DROP VIEW *视图名*;：删除视图。

# 储存过程

- CREATE OR REPLACE PROCEDURE 储存名(参数名 IN可省略，或者OUT或者IN OUT 数据类型...) IS 变量1 数据类型 := 值;... BEGIN 过程体; END; 创建储存过程。
- 局部变量名 数据类型; 声明变量。
- 局部变量名 := 值; 变量赋值。
- dbms_output.put_line(内容...) 打印输出。
- SELECT 字段... INTO 变量... 把输出值赋给变量。
- SET SERVEROUTPUT ON; 设置在控制台输出。
- VAR 变量名 数据类型; 声明变量。
- CALL 储存过程名(参数,...); 执行储存过程。
- DROP PROCEDURE 储存过程名; 删除储存。

# 函数

- CREATE OR REPLACE FUNCTION 函数名(参数名 IN(可省略)或者OUT或者IN OUT 数据类型, ...) RETURN 数据类型 AS 变量1 类型;... BEGIN 函数体; END; 创建函数。

# 库

- ALTER SESSION SET CURRENT_SCHEMA=数据库; 切换数据库。

# 约束

- NOT NULL
- UNIQUE
- PRIMARY KEY
- CHECK(...)
- CONSTRAINT 表名 PRIMARY KEY (字段名)
- CONSTRAINT 表名 FOREIGN KEY (字段名) REFERENCES 表名(字段名)

# 字符串函数

- UPPER(字符串) 字符串转大写。
- LOWER(字符串) 字符串转小写。
- INITCAP(字符串) 字符串首字母大写。
- LENGTH(字符串) 返回字符串的长度。
- SUBSTR(字符串, 下标, 长度) 截取字符串。函数是从1开始的。
- REPLACE(字符串, 要替换的字符串, 替换成字符串) 替换字符串。
- TRIM(字符串) 去掉字符串左右两边空格。
- INSTR(字符串, 要查找的字符串) 查找字符串在指定字符串中的位置。

# 数字函数

- ROUND(数字, 位数) 对小数进行四舍五入，可以指定保留的位数，如果不指定则表示将小数点之后的数字全部进行四舍五入。
- TRUNC(数字, 位数) 保留指定位数，如果不指定，则表示不保留小数。
- MOD(数字, 数字) 取模。

# 日期函数

- ADD_MONTHS(日期, 数字) 指定的日起加上指定的天数，返回新的日期。
- MONTHS_BETWEEN(日期, 日期) 返回两个日期间的间隔月数。
- NEXT_DAY(日期, 星期数) 返回下个星期的日期。
- LAST_DAY(日期) 返回日期的最后一天。
- SELECT SYSDATE可选(+3) FROM DUAL; 获取当前系统时间。

# 转换函数

- TO_CHAR(日期或数字, 格式) 将指定的数据按照指定格式变为字符串类型。
- TO_DATE(字符串, 格式) 将指定的字符串按照指定格式变为日期类型。
- YYYY 表示年
- MM 表示月份
- DD 表示一月里的天数
- D 表示一周里的天数
- DY 表示星期几
- HH 表示12小时制
- HH 24表示24小时制
- MI 表示分钟
- SS 表示秒
- TO_NUMBER(字符串) 将指定的数据变为数字型。

# 通用函数

- NVL(列, 默认值) 如果显示数据是null，就使用默认值。
- DECODE(列, 判断值1, 显示1, 判断值2, 显示2..., 默认值) 多值判断，如果某一列与判断值相同,则使用指定的显示结果输出，如果没有满足条件,则显示默认值。
- CASE 列 WHEN 表达式1 THEN 显示1... ELSE 默认值 END 用于实现多条件判断，在WHEN之后编写条件，而在THEN之后编写条件满足的显示操作，如果都不满足则使用ELSE中的表达式处理。

# 统计函数

- COUNT(列) 求出全部的记录数。
- SUM(列) 求出总和。
- AVG(列) 平均值。
- MAX(列) 最大值。
- MIN(列) 最小值。
- MEDIAN(列) 返回中间值。

# Docker-Compose 配置

```yaml
services:
  oracle11g:
    image: jaspeen/oracle-11g:latest
    container_name: oracle11g
    hostname: oracle11g
    networks:
      network:
        ipv4_address: 172.16.3.22
    privileged: true
    environment:
      - ORACLE_HOME=/opt/oracle/app/product/11.2.0/dbhome_1
      - ORACLE_SID=orcl
      - PATH=$PATH:$ORACLE_HOME/bin
    ports:
      - "1521:1521"
    volumes:
      - /home/ivfzhou/volumes/oracle/database:/install/database:rwx
networks:
  network:
    driver: bridge
    attachable: true
    ipam:
      config:
        - subnet: 172.16.3.0/24
          gateway: 172.16.3.1
```
