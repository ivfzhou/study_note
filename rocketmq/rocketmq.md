# 一、Docker-Compose 配置

```yaml
services:
  rocketmq_name_server_0:
    image: apache/rocketmq:5.1.3
    container_name: rocketmq_name_server_0
    hostname: ivfzhoudockerrocketmqnameserver0
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.158
    volumes:
      - /home/ivfzhou/volumes/rocketmq/name0/logs:/home/rocketmq/logs/rocketmqlogs:rw
    ports:
      - "19876:9876"
    entrypoint: "./mqnamesrv"
  rocketmq_name_server_1:
    image: apache/rocketmq:5.1.3
    container_name: rocketmq_name_server_1
    hostname: ivfzhoudockerrocketmqnameserver1
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.159
    volumes:
      - /home/ivfzhou/volumes/rocketmq/name1/logs:/home/rocketmq/logs/rocketmqlogs:rw
    ports:
      - "29876:9876"
    entrypoint: "./mqnamesrv"
  rocketmq_broker_0:
    image: apache/rocketmq:5.1.3
    container_name: rocketmq_broker_0
    hostname: ivfzhoudockerrocketmqbroker0
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.160
    volumes:
      - /home/ivfzhou/volumes/rocketmq/broker0/config/broker-a.properties:/home/rocketmq/rocketmq-5.1.3/conf/2m-2s-async/broker-a.properties
      - /home/ivfzhou/volumes/rocketmq/broker0/logs:/home/rocketmq/logs/rocketmqlogs:rw
      - /home/ivfzhou/volumes/rocketmq/broker0/store:/home/rocketmq/store:rw
    ports:
      - "10909:10909"
      - "10911:10911"
      - "10912:10912"
    entrypoint: "./mqbroker -n 172.16.3.158:9876;172.16.3.159:9876 -c ../conf/2m-2s-async/broker-a.properties"
    depends_on:
      - rocketmq_name_server_0
      - rocketmq_name_server_1
  rocketmq_broker_1:
    image: apache/rocketmq:5.1.3
    container_name: rocketmq_broker_1
    hostname: ivfzhoudockerrocketmqbroker1
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.161
    environment:
      - JVM_XMX=512m
    ports:
      - "10913:10913"
      - "10914:10914"
      - "10915:10915"
    volumes:
      - /home/ivfzhou/volumes/rocketmq/broker1/config/broker-b.properties:/home/rocketmq/rocketmq-5.1.3/conf/2m-2s-async/broker-b.properties
      - /home/ivfzhou/volumes/rocketmq/lbroker1/logs:/home/rocketmq/logs/rocketmqlogs:rw
      - /home/ivfzhou/volumes/rocketmq/broker1/store:/home/rocketmq/store:rw
    entrypoint: "./mqbroker -n 172.16.3.158:9876;172.16.3.159:9876 -c ../conf/2m-2s-async/broker-b.properties"
    depends_on:
      - rocketmq_name_server_0
      - rocketmq_name_server_1
  rocketmq_broker_2:
    image: apache/rocketmq:5.1.3
    container_name: rocketmq_broker_2
    hostname: ivfzhoudockerrocketmqbroker2
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.162
    ports:
      - "10916:10916"
      - "10917:10917"
      - "10918:10918"
    volumes:
      - /home/ivfzhou/volumes/rocketmq/broker2/config/broker-a-s.properties:/home/rocketmq/rocketmq-5.1.3/conf/2m-2s-async/broker-a-s.properties
      - /home/ivfzhou/volumes/rocketmq/broker2/logs:/home/rocketmq/logs/rocketmqlogs:rw
      - /home/ivfzhou/volumes/rocketmq/broker2/store:/home/rocketmq/store:rw
    entrypoint: "./mqbroker -n 172.16.3.158:9876;172.16.3.159:9876 -c ../conf/2m-2s-async/broker-a-s.properties"
    depends_on:
      - rocketmq_name_server_0
      - rocketmq_name_server_1
  rocketmq_broker_3:
    image: apache/rocketmq:5.1.3
    container_name: rocketmq_broker_3
    hostname: ivfzhoudockerrocketmqbroker3
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.163
    ports:
      - "10919:10919"
      - "10920:10920"
      - "10921:10921"
    volumes:
      - /home/ivfzhou/volumes/rocketmq/broker3/config/broker-b-s.properties:/home/rocketmq/rocketmq-5.1.3/conf/2m-2s-async/broker-b-s.properties
      - /home/ivfzhou/volumes/rocketmq/broker3/logs:/home/rocketmq/logs/rocketmqlogs:rw
      - /home/ivfzhou/volumes/rocketmq/broker3/store:/home/rocketmq/store:rw
    entrypoint: "./mqbroker -n 172.16.3.158:9876;172.16.3.159:9876 -c ../conf/2m-2s-async/broker-b-s.properties"
    depends_on:
      - rocketmq_name_server_0
      - rocketmq_name_server_1
  rocketmq_dashboard:
    image: apacherocketmq/rocketmq-dashboard:1.0.0
    container_name: rocketmq_dashboard
    hostname: ivfzhoudockerrocketmqdashboard
    networks:
      network:
        ipv4_address: 172.16.3.164
    environment:
      JAVA_OPTS: "-Drocketmq.namesrv.addr=172.16.3.158:9876;172.16.3.159:9876"
    ports:
      - "8080:8080"
    depends_on:
      - rocketmq_name_server_0
      - rocketmq_name_server_1
```

启动脚本：  

```shell
cd ~
docker-compose -f ~/src/note/docker/docker-compose.yml stop rocketmq_name_server_0 rocketmq_name_server_1 rocketmq_broker_0 rocketmq_broker_1 rocketmq_broker_2 rocketmq_broker_3 rocketmq_dashboard
docker-compose -f ~/src/note/docker/docker-compose.yml r rocketmq_name_server_0 rocketmq_name_server_1 rocketmq_broker_0 rocketmq_broker_1 rocketmq_broker_2 rocketmq_broker_3 rocketmq_dashboard
sudo rm -rf ~/volumes/rocketmq
mkdir -p ~/volumes/rocketmq/logs_name_0 ~/volumes/rocketmq/logs_name_1 ~/volumes/rocketmq/broker0/logs ~/volumes/rocketmq/broker1/logs ~/volumes/rocketmq/broker2/logs  ~/volumes/rocketmq/broker3/logs  ~/volumes/rocketmq/broker0/store ~/volumes/rocketmq/broker1/store ~/volumes/rocketmq/broker2/store ~/volumes/rocketmq/broker3/store ~/volumes/rocketmq/broker0/config ~/volumes/rocketmq/broker1/config ~/volumes/rocketmq/broker2/config ~/volumes/rocketmq/broker3/config
cp ~/src/note/rocketmq/broker-a.properties ~/volumes/rocketmq/broker0/config/broker-a.properties
cp ~/src/note/rocketmq/broker-b.properties ~/volumes/rocketmq/broker1/config/broker-b.properties
cp ~/src/note/rocketmq/broker-a-s.properties ~/volumes/rocketmq/broker2/config/broker-a-s.properties
cp ~/src/note/rocketmq/broker-b-s.properties ~/volumes/rocketmq/broker3/config/broker-b-s.properties
sudo chown -R 3000:3000 ~/volumes/rocketmq
docker-compose -f ~/src/note/docker/docker-compose.yml up -d rocketmq_name_server_0 rocketmq_name_server_1 rocketmq_broker_0 rocketmq_broker_1 rocketmq_broker_2 rocketmq_broker_3 rocketmq_dashboard
```

