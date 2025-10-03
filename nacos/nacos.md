# 一、笔记
docker run --name nacos -e MODE=standalone -p 8848:8848 --hostname nacos -v ~/volumes/nacos/config:/home/nacos/init.d/ -d nacos/nacos-server:2.0.2

# 二、Docker-Compose 配置

```yaml
version: "3.9"
services:
  nacos_0:
    image: nacos/nacos-server:2.0.2
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
      - JVM_XMS=256m
      - JVM_XMX=512m
      - JVM_XMN=256m
      - JVM_MS=128m
      - JVM_MMS=256m
      - MYSQL_SERVICE_HOST=172.16.3.153
      - MYSQL_SERVICE_PORT=3306
      - MYSQL_SERVICE_DB_NAME=db_nacos
      - MYSQL_SERVICE_USER=nacos
      - MYSQL_SERVICE_PASSWORD=nacos
      - MYSQL_SERVICE_DB_PARAM=characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false
    volumes:
      - /home/ivfzhou/volumes/nacos/0/logs:/home/nacos/logs:rw
    ports:
      - "8848:8848"
      - "9848:9848"
      - "9555:9555"
    depends_on:
      - nacos_mysql
  nacos_1:
    image: nacos/nacos-server:2.0.2
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
      - JVM_MMS=256m
      - MYSQL_SERVICE_HOST=172.16.3.153
      - MYSQL_SERVICE_PORT=3306
      - MYSQL_SERVICE_DB_NAME=db_nacos
      - MYSQL_SERVICE_USER=nacos
      - MYSQL_SERVICE_PASSWORD=nacos
      - MYSQL_SERVICE_DB_PARAM=characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false
    volumes:
      - /home/ivfzhou/volumes/nacos/1/logs:/home/nacos/logs:rw
    ports:
      - "28848:8848"
      - "29848:9848"
    depends_on:
      - nacos_mysql
  nacos_2:
    image: nacos/nacos-server:2.0.2
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
      - JVM_MMS=256m
      - MYSQL_SERVICE_HOST=172.16.3.153
      - MYSQL_SERVICE_PORT=3306
      - MYSQL_SERVICE_DB_NAME=db_nacos
      - MYSQL_SERVICE_USER=nacos
      - MYSQL_SERVICE_PASSWORD=nacos
      - MYSQL_SERVICE_DB_PARAM=characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false
    volumes:
      - /home/ivfzhou/volumes/nacos/2/logs:/home/nacos/logs:rw
    ports:
      - "38848:8848"
      - "39848:9848"
    depends_on:
      - nacos_mysql
  nacos_mysql:
    container_name: nacos_mysql
    image: nacos/nacos-mysql:5.7
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

1. mkdir -p ~/volumes/nacos/0/logs
2. mkdir -p ~/volumes/nacos/1/logs
3. mkdir -p ~/volumes/nacos/2/logs
4. mkdir -p ~/volumes/nacos/mysql/data
5. mkdir -p ~/volumes/prometheus
6. cp ~/src/note/prometheus/prometheus.yml ~/volumes/prometheus/prometheus-standalone.yml
7. sudo chown -R 65534:65534 ~/volumes/prometheus
8. docker-compose -f ~/src/note/docker/docker-compose.yml up -d nacos_0 nacos_1 nacos_2 nacos_mysql prometheus grafana
