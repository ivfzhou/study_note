# Docker-Compose 安装

```yml
services:
  rabbitmq:
    image: rabbitmq:4.0.5-management
    container_name: rabbitmq
    privileged: true
    hostname: ivfzhou-docker-rabbitmq
    networks:
      network:
        ipv4_address: 172.16.3.128
    volumes:
      - /home/ivfzhou/volumes/rabbitmq/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf:rw
      - /home/ivfzhou/volumes/rabbitmq/enabled_plugins:/etc/rabbitmq/enabled_plugins:rw
      - /home/ivfzhou/volumes/rabbitmq/.erlang.cookie:/var/lib/rabbitmq/.erlang.cookie:rw
      - /home/ivfzhou/volumes/rabbitmq/log:/var/log/rabbitmq:rw
      - /home/ivfzhou/volumes/rabbitmq/mnesia:/var/lib/rabbitmq/mnesia:rw
    ports:
      - "15672:15672"
      - "5672:5672"
    environment:
      - RABBITMQ_DEFAULT_USER: guest
      - RABBITMQ_DEFAULT_PASS: guest
      - RABBITMQ_DEFAULT_VHOST: /
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

1. mkdir -p volumes/rabbitmq
1. mv [rabbitmq.conf](./rabbitmq.conf) volumes/rabbitmq/
1. mv [enabled_plugins](./enabled_plugins) volumes/rabbitmq/
1. mv [.erlang.cookie](./.erlang.cookie) volumes/rabbitmq/
1. sudo chown -R 999:999 volumes/rabbitmq
1. sudo chmod 400 volumes/rabbitmq/.erlang.cookie
1. docker-compose -f docker-compose.yml up -d rabbitmq

## Docker 安装

1. docker pull rabbitmq:4.0.5-management ## https://hub.docker.com/_/rabbitmq/
1. docker network create --subnet 172.16.3.0/24 ivfzhou-docker-network
1. mkdir -p volumes/rabbitmq
1. mv [rabbitmq.conf](./rabbitmq.conf) volumes/rabbitmq/
1. mv [enabled_plugins](./enabled_plugins) volumes/rabbitmq/
1. mv [.erlang.cookie](./.erlang.cookie) volumes/rabbitmq/
1. sudo chown -R 999:999 volumes/rabbitmq
1. sudo chmod 400 volumes/rabbitmq/.erlang.cookie
1. docker run --name rabbitmq --hostname ivfzhou-docker-rabbitmq --network ivfzhou-docker-network --ip 172.16.3.129 -p 15672:15672 -p 5672:5672 -v /home/ivfzhou/volumes/rabbitmq/rabbitmq.conf:/opt/rabbitmq/etc/rabbitmq/rabbitmq.conf -v /home/ivfzhou/volumes/rabbitmq/enabled_plugins:/opt/rabbitmq/etc/rabbitmq/enabled_plugins -v /home/ivfzhou/volumes/rabbitmq/.erlang.cookie:/var/lib/rabbitmq/.erlang.cookie -v /home/ivfzhou/volumes/rabbitmq/log:/var/log/rabbitmq -v /home/ivfzhou/volumes/rabbitmq/mnesia:/var/lib/rabbitmq/mnesia -d rabbitmq:4.0.5-management

# Debian12 apt 安装

```shell
sudo apt-get install curl gnupg apt-transport-https -y
curl -1sLf "https://keys.openpgp.org/vks/v1/by-fingerprint/0A9AF2115F4687BD29803A206B73A36E6026DFCA" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/com.rabbitmq.team.gpg > /dev/null
curl -1sLf https://github.com/rabbitmq/signing-keys/releases/download/3.0/cloudsmith.rabbitmq-erlang.E495BB49CC4BBE5B.key | sudo gpg --dearmor | sudo tee /usr/share/keyrings/rabbitmq.E495BB49CC4BBE5B.gpg > /dev/null
curl -1sLf https://github.com/rabbitmq/signing-keys/releases/download/3.0/cloudsmith.rabbitmq-server.9F4587F226208342.key | sudo gpg --dearmor | sudo tee /usr/share/keyrings/rabbitmq.9F4587F226208342.gpg > /dev/null
sudo tee /etc/apt/sources.list.d/rabbitmq.list <<EOF
## Provides modern Erlang/OTP releases
##
deb [arch=amd64 signed-by=/usr/share/keyrings/rabbitmq.E495BB49CC4BBE5B.gpg] https://ppa1.rabbitmq.com/rabbitmq/rabbitmq-erlang/deb/debian bookworm main
deb-src [signed-by=/usr/share/keyrings/rabbitmq.E495BB49CC4BBE5B.gpg] https://ppa1.rabbitmq.com/rabbitmq/rabbitmq-erlang/deb/debian bookworm main

# another mirror for redundancy
deb [arch=amd64 signed-by=/usr/share/keyrings/rabbitmq.E495BB49CC4BBE5B.gpg] https://ppa2.rabbitmq.com/rabbitmq/rabbitmq-erlang/deb/debian bookworm main
deb-src [signed-by=/usr/share/keyrings/rabbitmq.E495BB49CC4BBE5B.gpg] https://ppa2.rabbitmq.com/rabbitmq/rabbitmq-erlang/deb/debian bookworm main

## Provides RabbitMQ
##
deb [arch=amd64 signed-by=/usr/share/keyrings/rabbitmq.9F4587F226208342.gpg] https://ppa1.rabbitmq.com/rabbitmq/rabbitmq-server/deb/debian bookworm main
deb-src [signed-by=/usr/share/keyrings/rabbitmq.9F4587F226208342.gpg] https://ppa1.rabbitmq.com/rabbitmq/rabbitmq-server/deb/debian bookworm main

# another mirror for redundancy
deb [arch=amd64 signed-by=/usr/share/keyrings/rabbitmq.9F4587F226208342.gpg] https://ppa2.rabbitmq.com/rabbitmq/rabbitmq-server/deb/debian bookworm main
deb-src [signed-by=/usr/share/keyrings/rabbitmq.9F4587F226208342.gpg] https://ppa2.rabbitmq.com/rabbitmq/rabbitmq-server/deb/debian bookworm main
EOF
sudo apt-get update -y
sudo apt-get install -y erlang-base \
                        erlang-asn1 erlang-crypto erlang-eldap erlang-ftp erlang-inets \
                        erlang-mnesia erlang-os-mon erlang-parsetools erlang-public-key \
                        erlang-runtime-tools erlang-snmp erlang-ssl \
                        erlang-syntax-tools erlang-tftp erlang-tools erlang-xmerl
sudo apt-get install rabbitmq-server -y --fix-missing
```

# 二进制文件安装

1. wget https://github.com/rabbitmq/rabbitmq-server/releases/download/v4.0.5/rabbitmq-server-generic-unix-4.0.5.tar.xz
1. tar -xJvf rabbitmq-server-generic-unix-4.0.5.tar.xz
1. mv rabbitmq_server-4.0.5 programs/
1. mv [rabbitmq.conf](./rabbitmq.conf) programs/rabbitmq_server-4.0.5/etc/rabbitmq/rabbitmq.conf
1. mv [enabled_plugins](./enabled_plugins) programs/rabbitmq_server-4.0.5/etc/rabbitmq/enabled_plugins
1. ./programs/rabbitmq_server-4.0.5/sbin/rabbitmq-server
1. ./programs/rabbitmq_server-4.0.5/sbin/rabbitmqctl shutdown

# 配置文件路径

## 本地

- /etc/rabbitmq/rabbitmq.conf 配置文件
- /etc/rabbitmq/enabled_plugins 插件配置文件
- /var/log/rabbitmq/ 日志文件
- /var/lib/rabbitmq/.erlang.cookie
- /var/lib/rabbitmq/mnesia/

## 二进制运行

- programs/rabbitmq_server-4.0.5/etc/rabbitmq/rabbitmq.conf
- programs/rabbitmq_server-4.0.5/etc/rabbitmq/enabled_plugins
- programs/rabbitmq_server-4.0.5/var/log/rabbitmq
- programs/rabbitmq_server-4.0.5/var/lib/rabbitmq/.erlang.cookie
- programs/rabbitmq_server-4.0.5/var/lib/rabbitmq/mnesia/

## Docker

- /opt/rabbitmq/etc/rabbitmq/rabbitmq.conf
- /opt/rabbitmq/etc/rabbitmq/enabled_plugins
- /var/log/rabbitmq/
- /var/lib/rabbitmq/.erlang.cookie
- /var/lib/rabbitmq/mnesia/

# 默认端口号

- 5672 ampq
- 25672 cluster
- 15672 http
- 15692 prometheus
- 5552 stream

# 命令

- rabbitmq-plugins 
  - enable rabbitmq_management：开启后台管理插件，即管理页面。先 cd 到程序目录下
  - list：查看插件
  - enable rabbitmq_stream rabbitmq_stream_management
- rabbitmq-server：运行服务
  - -detached：后台运行
- rabbitmqctl
  - shutdown：停止服务
  - add_user *用户名* *密码*：添加用户
  - set_user_tags *用户名* adminstrator：设置用户级别
  - status：查看状态
  - stop_app：停止节点
  - start_app：开启节点
  - join_cluster *参数* rabbit@ivfzhoudebian：加入集群。自身要在 stop 状态下加入
	  - --ram：自身成内存节点，加入到磁盘节点
  - forget_cluster_node：节点从集群中退出。主节点需要停止才能退出，并且自身是运行状态
  - reset：重置。要在运行后并 stop_app 之后才可以执行
  - cluster_status：查看集群状态
  - sync_queue *队列名* -p '*虚拟主机地址*'：同步主节点的数据
  - change_cluster_node_type *属性*：改变结点属性 ram 或者 disc
- rabbitmq-diagnostics status：显示诊断信息

## 版本升级后，开启所有稳定的新特性

- sudo rabbitmqctl enable_feature_flag all

# 集群搭建

1. 二进制版本与 Docker 版本组建集群
1. 修改本地 hosts 文件：172.16.3.1 ivfzhoudebian、172.16.3.130 ivfzhou-docker-rabbitmq-1、172.16.3.131 ivfzhou-docker-rabbitmq-2
1. cp [.erlang.cookie](./.erlang.cookie) programs/rabbitmq_server-4.0.5/var/lib/rabbitmq/.erlang.cookie
1. sudo chown ivfzhou:ivfzhou programs/rabbitmq_server-4.0.5/var/log/rabbitmq/.erlang.cookie
1. ./programs/rabbitmq_server-4.0.5/sbin/rabbitmq-server
1. 创建 docker network create --subnet 172.16.3.0/24 ivfzhou-docker-network 网段
1. mkdir -p volumes/rabbitmq/1
1. mkdir -p volumes/rabbitmq/2
1. cp [.erlang.cookie](./.erlang.cookie) volumes/rabbitmq/1/.erlang.cookie
1. cp [.erlang.cookie](./.erlang.cookie) volumes/rabbitmq/2/.erlang.cookie
1. sudo chown -R 999:999 volumes/rabbitmq
1. sudo chmod 400 volumes/rabbitmq/1/.erlang.cookie
1. sudo chmod 400 volumes/rabbitmq/2/.erlang.cookie
1. docker run -v /home/ivfzhou/volumes/rabbitmq/1/.erlang.cookie:/var/lib/rabbitmq/.erlang.cookie -v /home/ivfzhou/volumes/rabbitmq/1/rabbitmq.conf:/opt/rabbitmq/etc/rabbitmq/rabbitmq.conf -v /home/ivfzhou/volumes/rabbitmq/1/enabled_plugins:/opt/rabbitmq/etc/rabbitmq/enabled_plugins -v /home/ivfzhou/volumes/rabbitmq/1/log:/var/log/rabbitmq -v /home/ivfzhou/volumes/rabbitmq/1/mnesia:/var/lib/rabbitmq/mnesia --hostname ivfzhou-docker-rabbitmq-1 --add-host ivfzhoudebian:172.16.3.1 --add-host ivfzhou-docker-rabbitmq-2:172.16.3.131 --network ivfzhou-docker-network --ip 172.16.3.130 --name rabbitmq-1 --link rabbitmq-2 -p 15673:15672 -p 5673:5672 -d rabbitmq:4.0.5-management
1. docker run -v /home/ivfzhou/volumes/rabbitmq/2/.erlang.cookie:/var/lib/rabbitmq/.erlang.cookie -v /home/ivfzhou/volumes/rabbitmq/2/rabbitmq.conf:/opt/rabbitmq/etc/rabbitmq/rabbitmq.conf -v /home/ivfzhou/volumes/rabbitmq/2/enabled_plugins:/opt/rabbitmq/etc/rabbitmq/enabled_plugins -v /home/ivfzhou/volumes/rabbitmq/2/log:/var/log/rabbitmq -v /home/ivfzhou/volumes/rabbitmq/2/mnesia:/var/lib/rabbitmq/mnesia --hostname ivfzhou-docker-rabbitmq-2 --add-host ivfzhoudebian:172.16.3.1 --add-host ivfzhou-docker-rabbitmq-1:172.16.3.130 --network ivfzhou-docker-network --ip 172.16.3.131 --name rabbitmq-2 --link rabbitmq-1 -p 15674:15672 -p 5674:5672 -d rabbitmq:4.0.5-management
1. 放行本地防火墙端口：4369、25672
1. 容器中执行 /opt/rabbitmq/sbin/rabbitmqctl stop_app，接着 reset，接着 join_cluster --ram rabbit@ivfzhoudebian

# 其他

1. 服务正式环境下打开文件数可设置为 65536，测试环境可设置为 4096
1. 环境变量 RABBITMQ_LOG_BASE 可控制日志文件夹
1. apt 安装的服务可通过 sudo journalctl --system | grep rabbitmq 查看日志