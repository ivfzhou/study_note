# 笔记

1. 在 redis 中，key 在添加设置时，如不存在将自动创建。key 的值为空时将自动删除。
1. 配置文件：[redis.conf.example](./redis.conf.example)、[sentinel.conf](./sentinel.conf)、[redis_cluster_volume.zip](./redis_cluster_volume.zip)。

# 安装

## Docker-Compose 安装

```yaml
services:
  redis:
    image: redis:8.0.2
    container_name: redis
    privileged: true
    hostname: ivfzhoudockerredis
    networks:
      network:
        ipv4_address: 172.16.3.129
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    working_dir: /data
    ports:
      - "6379:6379"
    volumes:
      - /home/ivfzhou/volumes/redis/config:/config:rw
      - /home/ivfzhou/volumes/redis/data:/data:rw
    command:
      - redis-server
      - /config/redis.conf
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
1. mkdir -p volumes/redis/config

1. mkdir -p volumes/redis/data

1. cp src/note/redis/redis.conf volumes/redis/config/redis.conf

1. sudo chown -R 999:999 volumes/redis

1. sudo tee -a /etc/hosts <<EOF
172.16.3.129 ivfzhoudockerredis
   EOF

1. docker-compose -f src/note/docker/docker-compose.yml up -d redis

## Docker 安装

docker run -v volumes/redis/config:/etc/redis -v volumes/redis/data:/data --name redis --hostname ivfzhoudockerredis -p 6379:6379 redis:8.0.2 redis-server /etc/redis/redis.conf

## Debian12 二进制包安装

1. cd /home/ivfzhou/programs/redis 目录下，执行 make。
1. 安装 tcl：
    ```shell
    wget http://downloads.sourceforge.net/tcl/tcl8.6.1-src.tar.gz
    tar -xzvf tcl8.6.1-src.tar.gz  -C /home/ivfzhou/programs/tcl/
    cd  /home/ivfzhou/programs/tcl/unix/
    sudo ./configure
    sudo make
    sudo make install
    ```
1. cd /home/ivfzhou/programs/redis，执行 make test。
1. 修改 /etc/sysctl.conf，添加 vm.overcommit_memory=1。
1. 修改 /etc/rc.local 文件添加 echo never > /sys/kernel/mm/transparent_hugepage/enabled。

## Centos 二进制包安装

1. cd /home/ivfzhou/programs/redis && make MALLOC=libc
1. make distclean
1. make
1. make install
1. 修改 /etc/sysctl.conf，添加 vm.overcommit_memory=1。
1. 修改 /etc/rc.local 文件添加 echo never > /sys/kernel/mm/transparent_hugepage/enabled。
1. sudo -i
1. echo 511 > /proc/sys/net/core/somaxconn
1. 修改 redis.conf，maxclient 500。

## Debian12 apt 安装

```shell
sudo apt install lsb-release curl gpg
curl -fsSL https://packages.redis.io/gpg | sudo gpg --dearmor -o /usr/share/keyrings/redis-archive-keyring.gpg
echo "deb [signed-by=/usr/share/keyrings/redis-archive-keyring.gpg] https://packages.redis.io/deb $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/redis.list
sudo apt update
sudo apt install redis
sudo systemctl disable redis-server
sudo vim /etc/redis/redis.conf
```

## Docker 安装 sentinel

docker run -v volumes/sentinel/config:/etc/redis -v volumes/sentinel/data:/data --hostname ivfzhoudockerredissentinel --name redis-sentinel redis:8.0.2 redis-sentinel /etc/redis/sentinel.conf

# Cluster 集群连接建立

1. redis-cli --askpass --cluster create 172.16.3.130:6379 172.16.3.131:6379 172.16.3.132:6379 172.16.3.133:6379 172.16.3.134:6379 172.16.3.135:6379 --cluster-replicas 1
1. 关闭集群主节点间的总线端口防火墙 +1000，16379。
1. 从节点注意配置主节点账号密码。
1. redis-cli -c -h 172.16.3.130

# Docker-compose 集群配置

```yaml
services:
  redis_0:
    image: redis:8.0.2
    container_name: redis_0
    privileged: true
    hostname: ivfzhoudockerredis0
    networks:
      network:
        ipv4_address: 172.16.3.130
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    working_dir: /data
    ports:
      - "6380:6380"
      - "16380:16380"
    volumes:
      - /home/ivfzhou/volumes/redis/cluster/config:/config:rw
      - /home/ivfzhou/volumes/redis/cluster/data:/data:rw
    command:
      - redis-server
      - /config/redis_6380.conf
  redis_1:
    image: redis:8.0.2
    working_dir: /data
    container_name: redis_1
    hostname: ivfzhoudockerredis1
    networks:
      network:
        ipv4_address: 172.16.3.131
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    privileged: true
    ports:
      - "6381:6381"
      - "16381:16381"
    volumes:
      - /home/ivfzhou/volumes/redis/cluster/config:/config
      - /home/ivfzhou/volumes/redis/cluster/data:/data
    command:
      - redis-server
      - /config/redis_6381.conf
  redis_2:
    image: redis:8.0.2
    container_name: redis_2
    working_dir: /data
    privileged: true
    hostname: ivfzhoudockerredis2
    networks:
      network:
        ipv4_address: 172.16.3.132
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    ports:
      - "6382:6382"
      - "16382:16382"
    stdin_open: true
    volumes:
      - /home/ivfzhou/volumes/redis/cluster/config:/config
      - /home/ivfzhou/volumes/redis/cluster/data:/data
    command:
      - redis-server
      - /config/redis_6382.conf
  redis_3:
    image: redis:8.0.2
    container_name: redis_3
    working_dir: /data
    hostname: ivfzhoudockerredis3
    networks:
      network:
        ipv4_address: 172.16.3.133
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    privileged: true
    ports:
      - "6383:6383"
      - "16383:16383"
    volumes:
      - /home/ivfzhou/volumes/redis/cluster/config:/config
      - /home/ivfzhou/volumes/redis/cluster/data:/data
    command:
      - redis-server
      - /config/redis_6383.conf
  redis_4:
    image: redis:7.2.1
    working_dir: /data
    container_name: redis_4
    hostname: ivfzhoudockerredis4
    networks:
      network:
        ipv4_address: 172.16.3.134
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    privileged: true
    ports:
      - "6384:6384"
      - "16384:16384"
    volumes:
      - /home/ivfzhou/volumes/redis/cluster/config:/config
      - /home/ivfzhou/volumes/redis/cluster/data:/data
    command:
      - redis-server
      - /config/redis_6384.conf
  redis_5:
    image: redis:8.0.2
    container_name: redis_5
    working_dir: /data
    privileged: true
    hostname: ivfzhoudockerredis5
    volumes:
      - /home/ivfzhou/volumes/redis/cluster/config:/config
      - /home/ivfzhou/volumes/redis/cluster/data:/data
    ports:
      - "6385:6385"
      - "16385:16385"
    networks:
      network:
        ipv4_address: 172.16.3.135
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    command:
      - redis-server
      - /config/redis_6385.conf
  redis_sentinel_0:
    image: redis:8.0.2
    container_name: redis_sentinel_0
    working_dir: /data
    hostname: ivfzhoudockerredissentinel0
    networks:
      network:
        ipv4_address: 172.16.3.136
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    privileged: true
    ports:
      - "6386:6386"
    volumes:
      - /home/ivfzhou/volumes/redis/cluster/config:/config
      - /home/ivfzhou/volumes/redis/cluster/data:/data
    command:
      - redis-sentinel
      - /config/sentinel_6386.conf
    depends_on:
      - redis_0
      - redis_1
      - redis_2
      - redis_3
      - redis_4
      - redis_5
  redis_sentinel_1:
    image: redis:7.2.1
    container_name: redis_sentinel_1
    working_dir: /data
    hostname: ivfzhoudockerredissentinel1
    networks:
      network:
        ipv4_address: 172.16.3.137
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    privileged: true
    ports:
      - "6387:6387"
    volumes:
      - /home/ivfzhou/volumes/redis/cluster/config:/config
      - /home/ivfzhou/volumes/redis/cluster/data:/data
    command:
      - redis-sentinel
      - /config/sentinel_6387.conf
    depends_on:
      - redis_0
      - redis_1
      - redis_2
      - redis_3
      - redis_4
      - redis_5
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
1. mkdir -p volumes/redis/cluster
1. cp [redis_cluster_volume](./redis_cluster_volume)/* volumes/redis/cluster/
1. sudo chown -R 999:999 volumes/redis/cluster
1. docker-compose -f docker-compose.yml up -d redis_0 redis_1 redis_2 redis_3 redis_4 redis_5 redis_sentinel_0 redis_sentinel_1

# 命令

## 系统命令

1. redis-server /opt/redis/redis.conf --loadmodule /opt/redis/redisbloom.so
2. redis-cli -h 172.18.0.1 -p 6379：连接服务进入命令窗口。
3. redis-cli -h 172.18.0.1 --user ivfzhou --askpass
4. EXIST：退出命令行。
5. SAVE：将数据储存到根目录的 dump.rdb。
6. BGSAVE：后台进行数据存储。
7. BGREWRITEAOF：后台写日志。
8. PING：测试链接。
9. SELECT *index*：选择数据库 0-15。
10. CONFIG SET REQUIREPASS *password*：设置密码。
11. CONFIG GET *：获得所有参数。
12. CONFIG SET *param* *value*：设置参数。

## 操作命令

1. DEL *key*：删除 key，无论数据类型。
2. EXISTS *key*：判断 key 是否存在，存在返回 1，不存在返回 0。
3. EXPIRE *key* *second*：设置 key 过期时间，单位秒。
4. PEXPIRE *key* *millisecond*：设置 key 的过期时间，单位毫秒。
5. TTL *key*：查询 key 的剩余过期时间，单位秒。-1 表示长期，-2 表示 key 不存在。key 重新设置后，重置为 -1。
6. PTTL *key*：查询 key 的剩余过期时间，单位毫秒。-1 表示长期，-2 表示 key 不存在。
7. PERSIST *key*：移除 key 的过期时间。
8. MULTI：开启事务。
9. EXEC：提交事务。
10. DISCARD：丢弃事务。
11. WATCH *key*：监听某个 key 的值是否发生变化,如果发生变化，watch 之后的操作会失败。
12. SUBSCRIBE *channel*：订阅通道。
13. PUBLISH *channel* *msg*：发布 msg 到 channel。
14. FLUSHDB：删除该 db 数据。
15. FLUSHALL：删除所有 db 数据。
16. KEYS *：查看当前 redis 库中所有的 key。正则匹配。
17. TYPE *key*：返回 key 对应的数据类型。
18. EVAL *script* *numkeys* *key*... *args*...：执行 lua 脚本，KEYS 和 ARGV。
     - redis.call('set', 'name', 'ivfzhou')：lua 脚本函数。
     - for i,len,step do *执行体* end：lua 中循环。
     - local *var*：lua 中定义变量。
     - tonumber(*str* or 0)：返回数字。
     - if *条件* then *语句* elseif *条件* then *语句* elseif *条件* then *代码块* end：判断语句。
19. SCRIPT LOAD *script*：生成 lua 脚本散列值。
20. EVALSHA *sha* *numkeys* *keys*... *args*...：执行 lua脚本。
21. INFO REPLLICATION：查看集群信息。
22. INFO：打印数据库信息。
23. MOVE *key* *dbindex*：将当前数据中的 key 转移到其他数据库。
24. RANDOMKEY：返回一个随机 key。
25. DBSIZE：查看当前数据库 key 数量。
26. CLUSTER INFO：集群信息。
27. CLUSTER MODES：集群节点信息。

## string/integer

1. SET *key* *value* EX *second* PX *minute*：设置 key value 并指定过期时间。
2. GET *key*：获取 key value。
3. INCR *key*：给 key vlaue 加一，若没有该 key 自动创建。
4. INCRBY *key* *number*：给 key value 加 number。
5. DECR *key*：给 key value 减一，若没有该 key 则自动创建。
6. DECRBY *key* *number*：给 key value 减 number。
7. SETNX *key* *value*：只有 key 不存在时设置值。
8. SETEX *key* *second* *value*：设置时效。
9. SETRANGE *key* *offset* *value*：在指定位置替换值。
10. MSET *key* *value*...：设置多个键值。
11. APPEND *key* *value*：向值追加值。
12. GETSET *key* *value*：设置新值返回久值。
13. MGET *key*...：批量读取。
14. STRLEN *key*：返回长度。

## list

1. LPUSH *key* *value*...：向 list 左边添加 value，返回 list 长度。
2. RPUSH *key* *value*...：向 list 右边添加 value，返回 list 长度。
3. LPOP *key*：返回 list 左边的 value，并从 list 中删除该 value。
4. RPOP *key*：返回 list 右边的 value，并从 list 中删除该 value。
5. LLEN *key*：返回 list 的长度。
6. LRANGE *key* *start* *end*：返回 list 中指定返回的所有 value，第一个 value 下标为 0，下标含头含尾。end 为 -1 时表示取到最后一位，-2 时表示取到倒数第二位，以此类推。
7. LTRIM *key* *start* *end*：保留指定范围的值，其它删除。
8. LINDEX *key* *index*：返回名称为 key 的 list 中 index 位置的元素。
9. BLPOP *key*：移出并获取列表的第一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
10. BRPOP *key*：移出并获取列表的最后一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
11. LINSERT *key* [ BEFORE | AFTER ] *index* *value*：在指定位置插入值 value。
12. LSET *key* *index* *value*：将 key 集合中 index 下标的元素替换掉。
13. LREM *key* *count* *value*：删除 key 中 count 个为 value 的元素，并返回删除的个数。count><0 从左右开始删，count=0 全删。
14. RPOPLPUSH *key1* *key2*：删除 key1 的最后元素，并添加到 key2 中左边，没有 key2 则创建，并返回这个元素。

## set

1. SADD *key* *value*...：向 set 添加 value，返回成功数量。
2. SREM *key* *value*：删除 set 集合的元素。成功返回 1，没有 value 可删除返回 0。
3. SISMEMBER *key* *value*：判断 value 是否在 key 中，存在返回 1，不存在返回 0。
4. SUNION *key1* *key2*：合并多个 set 返回所有 value。
5. SMEMBERS *key*：返回 set 所有 value。
6. SPOP *key* *many*：取出 set 中 many 个 value 返回，并从 set 中删除。
7. SRANDMEMBER *key* *many*：取出 set 中 many 个 value 返回。
8. SCARD *key*：获取集合长度。
9. SDIFF *key* *key1*...：返回在第一个 set 中存在，其它 set 中不存在的内容。
10. SDIFFSTORE *key* *key1*...：将其它 set 中不同与 key 中的元素保存到 key 中。
11. SINTER *key1* *key2*...：取交集，返回集合重复的数据。
12. SINTERSTORE *key* *key1*...：其它 key 取交集后保存到 key 中。
13. SUNIONSTORE *key* *key1*...：取并集后添加到 key。
14. SMOVE *key* *key1* *value*：将 value 从 key 移到 key1。

## sorted set

1. ZADD *key* *score* *value*：向 sorted-set 添加 value。
2. ZRANGE *key* *start* *end*：可选 WITHSCORE 返回 sorted-set 指定范围内的 value。withscore 表示显示 score。
3. ZSCORE *key* *value*：获取指定 key 中指定内容的分数。
4. ZREM *key* *value*...：删除 key 中的 value 元素。
5. ZREVRANGE *key* *start* *end*：可选 WITHSCORE，获取范围元素，从大到小排序，withscore 表示显示 score。
6. ZRANGEBYSCORE *key* *min* *max*：可选 WITHSCORE，可选 LIMIT *offset* *size*，列出成员。
7. ZREVRANGEBYSCORE *key* *max* *min*：列出成员。
8. ZCARD *key*：返回集合里所有元素的个数。
9. ZCOUNT *key* *min* *max*：返回集合中 score 在给定区间中的数量。
10. ZINCRBY *key* *num* *value*：给指定成员 score 加值。
11. ZREMRANGEBYRANK *key* *start* *end*：移除有序集合中给定的排名区间的所有成员。
12. ZREMRANGEBYSCORE *key* *min* *max*：移除有序集合中给定的分数区间的所有成员。

## hash

1. HSET | HMSET *key* *field1* *value1* *field2* *value2*...：设置 hash value。
2. HGETALL *key*：返回 hash value。
3. HGET | HMGET *key* *field*...：返回 hash key 中一个 field 的 value。
4. HDEL *key* *field*：删除 hash key 中一个 field 的 value。
5. HINCRBY *key* *field* *number*：将 hash key 中的 field value 加一。
6. HSETNX *key* *field* *value*：若果不存在设置成功。
7. HEXISTS *key* *field*：指定字段存在返回 1，否则 0。
8. HLEN *key*：返回指定键的长度。
9. HKEYS *key*：返回指定 key 的所有字段。
10. HVALS *key*：返回指定 key 的所有值。

## boolm

1. bf.reserva *key* *容错率* *大小*：初始化一个布隆过滤器。
2. bf.add *key* *value*：向布隆过滤器添加元素。
3. bf.exist *key* *value*：检查布隆过滤器中是否有该元素。

## hyperloglog

统计一个集合中不重复元素的数量。

1. PFADD *key* *value*...：添加基数型 key。
2. PFCOUNT *key*：计算该 key 基数。
3. PFMERGE *key* *key1*...：将 key1 的基数加如到 key 中。
