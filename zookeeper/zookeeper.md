# 笔记

- 配置 [zoo_sample.cfg](./zoo_sample.cfg)。
- 2181 端口对客户端提供服务。2888 端口集群通讯。3888 端口选举主节点端口。
- 节点类型：持久节点、临时节点、持久有序节点、临时有序节点。
- 时序锁：创建临时节点，获取节点最小值，监听最小值，没有比自己小的获取锁。

# 命令

- zkServer.sh *参数* *命令*：服务端命令。
  - 参数
    - --config *路径*：zoo.cfg 文件的所在文件夹路径（路径最后位不能带/），指定配置文件。
  - 命令
    - version：打印版本信息。
    - start：后台启动。
    - start-foreground：前台启动。
    - stop：停止。
    - restart：重启。
    - status：打印状态信息。
- zkCli.sh -server *ip*：连接到服务器。
    - ls *节点名*：查看节点的子节点。
    - get *节点名*：查看当前节点数据。
    - create *参数* *节点名* *可选数据*：创建节点。
      - -s：有序节点。
      - -e：临时节点。
    - set *节点名* *新数据*：修改节点数据。
    - delete *节点名*：删除空节点。
    - deleteall *节点名*：删除节点及子节点。
    - stat *节点名*：显示状态信息。
    - connect *IP:端口*：连接指定地址服务。
    - quit：退出连接。

# Docker-Compose 配置

```yaml
services:
  zookeeper:
    image: zookeeper:3.9.0
    container_name: zookeeper
    hostname: ivfzhou-docker-zookeeper
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.5
    ports:
      - "2181:2181"
    environment:
      ZOO_MY_ID: 1
      ZOO_SERVERS: server.1=ivfzhou-docker-zookeeper:2888:3888;2181
    volumes:
      - /home/ivfzhou/volumes/zookeeper/data:/data:rw
      - /home/ivfzhou/volumes/zookeeper/datalog:/datalog:rw
      - /home/ivfzhou/volumes/zookeeper/logs:/logs:rw
      - /home/ivfzhou/volumes/zookeeper/conf:/conf:rw
  zookeeper_0:
    image: zookeeper:3.9.0
    container_name: zookeeper_0
    hostname: ivfzhou-docker-zookeeper_0
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.6
    ports:
      - "2182:2181"
    environment:
      ZOO_MY_ID: 1
      ZOO_SERVERS: server.1=zookeeper_0:2888:3888;2181 server.2=zookeeper_1:2888:3888;2181 server.3=zookeeper_2:2888:3888;2181
    volumes:
      - /home/ivfzhou/volumes/zookeeper/0/data:/data:rw
      - /home/ivfzhou/volumes/zookeeper/0/datalog:/datalog:rw
      - /home/ivfzhou/volumes/zookeeper/0/logs:/logs:rw
      - /home/ivfzhou/volumes/zookeeper/0/conf:/conf:rw
  zookeeper_1:
    image: zookeeper:3.9.0
    container_name: zookeeper_1
    hostname: ivfzhou-docker-zookeeper_1
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.7
    ports:
      - "2183:2181"
    environment:
      ZOO_MY_ID: 2
      ZOO_SERVERS: server.1=zookeeper_0:2888:3888;2181 server.2=zookeeper_1:2888:3888;2181 server.3=zookeeper_2:2888:3888;2181
    volumes:
      - /home/ivfzhou/volumes/zookeeper/1/data:/data:rw
      - /home/ivfzhou/volumes/zookeeper/1/datalog:/datalog:rw
      - /home/ivfzhou/volumes/zookeeper/1/logs:/logs:rw
      - /home/ivfzhou/volumes/zookeeper/1/conf:/conf:rw
  zookeeper_2:
    image: zookeeper:3.9.0
    container_name: zookeeper_2
    hostname: ivfzhou-docker-zookeeper_2
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.8
    ports:
      - "2184:2181"
    environment:
      ZOO_MY_ID: 3
      ZOO_SERVERS: server.1=zookeeper_0:2888:3888;2181 server.2=zookeeper_1:2888:3888;2181 server.3=zookeeper_2:2888:3888;2181
    volumes:
      - /home/ivfzhou/volumes/zookeeper/2/data:/data:rw
      - /home/ivfzhou/volumes/zookeeper/2/datalog:/datalog:rw
      - /home/ivfzhou/volumes/zookeeper/2/logs:/logs:rw
      - /home/ivfzhou/volumes/zookeeper/2/conf:/conf:rw
networks:
  network:
    driver: bridge
    attachable: true
    ipam:
      driver: default
      config:
        - subnet: 172.16.3.0/24
          gateway: 172.16.3.1
```
1. mkdir -p ~/volumes/zookeeper/data。
1. mkdir -p ~/volumes/zookeeper/datalog。
1. mkdir -p ~/volumes/zookeeper/logs。
1. mkdir -p ~/volumes/zookeeper/conf。
1. sudo chown -R 1000:1000 ~/volumes/zookeeper。
1. docker-compose -f ~/docker-compose.yml up -d zookeeper。
___
1. mkdir -p ~/volumes/zookeeper/0/data ~/volumes/zookeeper/0/datalog ~/volumes/zookeeper/0/logs ~/volumes/zookeeper/0/conf。
1. mkdir -p ~/volumes/zookeeper/1/data ~/volumes/zookeeper/1/datalog ~/volumes/zookeeper/1/logs ~/volumes/zookeeper/1/conf。
1. mkdir -p ~/volumes/zookeeper/2/data ~/volumes/zookeeper/2/datalog ~/volumes/zookeeper/2/logs ~/volumes/zookeeper/2/conf。
1. sudo chown -R 1000:1000 ~/volumes/zookeeper。
1. docker-compose -f ~/docker-compose.yml up -d zookeeper_0 zookeeper_1 zookeeper_2。
