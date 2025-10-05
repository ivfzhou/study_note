一、笔记

1. 控制台主页地址：http://ivfzhoudockernacos:8080/index.html

# 二、运行

## 1. Docker

```shell
docker run --name nacos -e MODE=standalone -p 8848:8848 --hostname ivfzhoudockernacos -d nacos/nacos-server:2.0.2
```

## 2. Docker-Compose

```yaml
version: "3.9"
services:
  nacos_0:
    image: nacos/nacos-server:v3.1.0
    container_name: nacos_0
    privileged: true
    hostname: ivfzhoudockernacos0
    networks:
      network:
        ipv4_address: 172.16.3.150
    environment:
      - NACOS_SERVERS=172.16.3.150:8848 172.16.3.151:8848 172.16.3.152:8848
      - MODE=cluster
      - NACOS_SERVER_IP=172.16.3.150
      - PREFER_HOST_MODE=ip
      - NACOS_SERVER_PORT=8848
      - JVM_XMS=256m # JVM 初始堆大小。
      - JVM_XMX=512m # JVM 最大堆大小。
      - JVM_XMN=256m # 新生代（Young Generation）大小。
      - JVM_MS=128m # 设置 Metaspace 的初始大小。
      - JVM_MMS=512m # 设置 Metaspace 最大大小。
      - MYSQL_SERVICE_HOST=172.16.3.153
      - MYSQL_SERVICE_PORT=3306
      - MYSQL_SERVICE_DB_NAME=db_nacos
      - MYSQL_SERVICE_USER=nacos
      - MYSQL_SERVICE_PASSWORD=nacos
      - SPRING_DATASOURCE_PLATFORM=mysql
      - NACOS_AUTH_ENABLE=true
      - NACOS_AUTH_IDENTITY_KEY=key
      - NACOS_AUTH_IDENTITY_VALUE=value
      - NACOS_AUTH_TOKEN=MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI=
      - MYSQL_SERVICE_DB_PARAM=characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true
    volumes:
      - /home/ivfzhou/volumes/nacos/0/logs:/home/nacos/logs:rw
      - /home/ivfzhou/volumes/nacos/0/data:/home/nacos/data:rw
    ports:
      - "8848:8848"
      - "9848:9848"
      - "9555:9555"
    depends_on:
      - nacos_mysql
  nacos_1:
    image: nacos/nacos-server:v3.1.0
    container_name: nacos_1
    hostname: ivfzhoudockernacos1
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.151
    environment:
      - NACOS_SERVERS=172.16.3.150:8848 172.16.3.151:8848 172.16.3.152:8848
      - MODE=cluster
      - NACOS_SERVER_IP=172.16.3.151
      - PREFER_HOST_MODE=ip
      - NACOS_SERVER_PORT=8848
      - JVM_XMS=256m
      - JVM_XMX=512m
      - JVM_XMN=256m
      - JVM_MS=128m
      - JVM_MMS=512m
      - MYSQL_SERVICE_HOST=172.16.3.153
      - MYSQL_SERVICE_PORT=3306
      - MYSQL_SERVICE_DB_NAME=db_nacos
      - MYSQL_SERVICE_USER=nacos
      - MYSQL_SERVICE_PASSWORD=nacos
      - SPRING_DATASOURCE_PLATFORM=mysql
      - NACOS_AUTH_ENABLE=true
      - NACOS_AUTH_IDENTITY_KEY=key
      - NACOS_AUTH_IDENTITY_VALUE=value
      - NACOS_AUTH_TOKEN=MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI=
      - MYSQL_SERVICE_DB_PARAM=characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true
    volumes:
      - /home/ivfzhou/volumes/nacos/1/logs:/home/nacos/logs:rw
      - /home/ivfzhou/volumes/nacos/1/data:/home/nacos/data:rw
    ports:
      - "28848:8848"
      - "29848:9848"
      - "29555:29555"
    depends_on:
      - nacos_mysql
  nacos_2:
    image: nacos/nacos-server:v3.1.0
    container_name: nacos_2
    hostname: ivfzhoudockernacos2
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.152
    environment:
      - NACOS_SERVERS=172.16.3.150:8848 172.16.3.151:8848 172.16.3.152:8848
      - MODE=cluster
      - NACOS_SERVER_IP=172.16.3.152
      - PREFER_HOST_MODE=ip
      - NACOS_SERVER_PORT=8848
      - JVM_XMS=256m
      - JVM_XMX=512m
      - JVM_XMN=256m
      - JVM_MS=128m
      - JVM_MMS=512m
      - MYSQL_SERVICE_HOST=172.16.3.153
      - MYSQL_SERVICE_PORT=3306
      - MYSQL_SERVICE_DB_NAME=db_nacos
      - SPRING_DATASOURCE_PLATFORM=mysql
      - MYSQL_SERVICE_USER=nacos
      - MYSQL_SERVICE_PASSWORD=nacos
      - NACOS_AUTH_ENABLE=true
      - NACOS_AUTH_IDENTITY_KEY=key
      - NACOS_AUTH_IDENTITY_VALUE=value
      - NACOS_AUTH_TOKEN=MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI=
      - MYSQL_SERVICE_DB_PARAM=characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true
    volumes:
      - /home/ivfzhou/volumes/nacos/2/logs:/home/nacos/logs:rw
      - /home/ivfzhou/volumes/nacos/2/data:/home/nacos/data:rw
    ports:
      - "38848:8848"
      - "39848:9848"
      - "39555:39555"
    depends_on:
      - nacos_mysql
  nacos_mysql:
    container_name: nacos_mysql
    image: mysql:9.4.0
    hostname: ivfzhoudockernacosmysql
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.153
    environment:
      - MYSQL_ROOT_PASSWORD=123456
      - MYSQL_DATABASE=db_nacos
      - MYSQL_USER=nacos
      - MYSQL_PASSWORD=nacos
    volumes:
      - /home/ivfzhou/volumes/nacos/mysql/data:/var/lib/mysql:rw
      - /home/ivfzhou/src/note/nacos/mysql-schema-v3.1.0.sql:/docker-entrypoint-initdb.d/init.sql:ro
  prometheus:
    container_name: prometheus
    image: prom/prometheus:v2.37.1
    hostname: ivfzhoudockerprometheus
    privileged: true
    networks:
      network:
        ipv4_address: 172.16.3.154
    volumes:
      - /home/ivfzhou/volumes/prometheus/prometheus-standalone.yml:/etc/prometheus/prometheus.yml:rw
    ports:
      - "9090:9090"
    depends_on:
      - nacos_0
      - nacos_1
      - nacos_2
    restart: on-failure
  grafana:
    container_name: grafana
    image: grafana/grafana:9.0.9
    hostname: ivfzhoudockergrafana
    networks:
      network:
        ipv4_address: 172.16.3.155
    privileged: true
    ports:
      - "3000:3000"
    restart: on-failure
```

- mkdir -p ~/volumes/nacos/0/logs ~/volumes/nacos/1/logs ~/volumes/nacos/2/logs ~/volumes/nacos/mysql/data ~/volumes/prometheus ~/volumes/nacos/0/data ~/volumes/nacos/1/data ~/volumes/nacos/2/data
- cp ~/src/note/prometheus/prometheus.yml ~/volumes/prometheus/prometheus-standalone.yml
- sudo chown -R 65534:65534 ~/volumes/prometheus
- docker-compose -f ~/src/note/docker/docker-compose.yml up -d nacos_mysql
- sleep 10s
- docker-compose -f ~/src/note/docker/docker-compose.yml up -d nacos_0 nacos_1 nacos_2 nacos_mysql prometheus grafana
