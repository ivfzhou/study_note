# 笔记
docker run --name nacos -e MODE=standalone -p 8848:8848 --hostname nacos -v /home/ivfzhou/volumes/nacos/config:/home/nacos/init.d/ -d nacos/nacos-server:2.0.2

# Docker-Compose 配置

```yaml
version: "3.9"
services:
  nacos_0:
    image: nacos/nacos-server:2.0.2
    container_name: nacos_0
    privileged: true
    hostname: nacos_0
    networks:
      network:
        ipv4_address: 220.254.1.3
    environment:
      - NACOS_SERVERS=220.254.1.3:8848 220.254.1.4:8848 220.254.1.5:8848
      - MODE=cluster
      - NACOS_SERVER_IP=220.254.1.3
      - PREFER_HOST_MODE=ip
      - NACOS_SERVER_PORT=8848
      - JVM_XMS=256m
      - JVM_XMX=512m
      - JVM_XMN=256m
      - JVM_MS=128m
      - JVM_MMS=256m
      - MYSQL_SERVICE_HOST=220.254.1.6
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
    hostname: nacos_1
    privileged: true
    networks:
      network:
        ipv4_address: 220.254.1.4
    environment:
      - NACOS_SERVERS=220.254.1.3:8848 220.254.1.4:8848 220.254.1.5:8848
      - MODE=cluster
      - NACOS_SERVER_IP=220.254.1.4
      - PREFER_HOST_MODE=ip
      - NACOS_SERVER_PORT=8848
      - JVM_XMS=256m
      - JVM_XMX=512m
      - JVM_XMN=256m
      - JVM_MS=128m
      - JVM_MMS=256m
      - MYSQL_SERVICE_HOST=220.254.1.6
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
    hostname: nacos_2
    privileged: true
    networks:
      network:
        ipv4_address: 220.254.1.5
    environment:
      - NACOS_SERVERS=220.254.1.3:8848 220.254.1.4:8848 220.254.1.5:8848
      - MODE=cluster
      - NACOS_SERVER_IP=220.254.1.5
      - PREFER_HOST_MODE=ip
      - NACOS_SERVER_PORT=8848
      - JVM_XMS=256m
      - JVM_XMX=512m
      - JVM_XMN=256m
      - JVM_MS=128m
      - JVM_MMS=256m
      - MYSQL_SERVICE_HOST=220.254.1.6
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
    hostname: nacos_mysql
    privileged: true
    networks:
      network:
        ipv4_address: 220.254.1.6
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
    hostname: prometheus
    privileged: true
    networks:
      network:
        ipv4_address: 220.254.1.7
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
    hostname: grafana
    networks:
      network:
        ipv4_address: 220.254.1.8
    privileged: true
    ports:
      - "3000:3000"
    restart: on-failure
networks:
  network:
    driver: bridge
    attachable: true
    ipam:
      config:
        - subnet: 220.254.1.0/24
          gateway: 220.254.1.1
```
