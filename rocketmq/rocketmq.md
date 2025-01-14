# Docker-Compose 配置

```yaml
version: "3.9"
services:
  rocketmq_name_server_0:
    image: apache/rocketmq:4.9.4
    container_name: rocketmq_name_server_0
    hostname: rocketmq_name_server_0
    privileged: true
    networks:
      network:
        ipv4_address: 220.254.1.18
    volumes:
      - /home/ivfzhou/volumes/rocketmq/logs_name_0:/home/rocketmq/logs/rocketmqlogs:rw
      - /home/ivfzhou/volumes/rocketmq/runserver.sh:/home/rocketmq/bin/runserver.sh:rw
    ports:
      - "19876:9876"
    entrypoint: "./mqnamesrv"
  rocketmq_name_server_1:
    image: apache/rocketmq:4.9.4
    container_name: rocketmq_name_server_1
    hostname: rocketmq_name_server_1
    privileged: true
    networks:
      network:
        ipv4_address: 220.254.1.19
    volumes:
      - /home/ivfzhou/volumes/rocketmq/logs_name_1:/home/rocketmq/logs/rocketmqlogs:rw
      - /home/ivfzhou/volumes/rocketmq/runserver.sh:/home/rocketmq/bin/runserver.sh:rw
    ports:
      - "29876:9876"
    entrypoint: "./mqnamesrv"
  rocketmq_broker_0:
    image: apache/rocketmq:4.9.4
    container_name: rocketmq_broker_0
    hostname: rocketmq_broker_0
    privileged: true
    networks:
      network:
        ipv4_address: 220.254.1.20
    volumes:
      - /home/ivfzhou/volumes/rocketmq/config/broker-a.properties:/home/rocketmq/rocketmq-4.9.4/conf/2m-2s-async/broker-a.properties
      - /home/ivfzhou/volumes/rocketmq/logs_broker_0:/home/rocketmq/logs/rocketmqlogs:rw
      - /home/ivfzhou/volumes/rocketmq/store_broker_0:/home/rocketmq/store:rw
      - /home/ivfzhou/volumes/rocketmq/runbroker.sh:/home/rocketmq/bin/runbroker.sh:rw
    ports:
      - "10909:10909"
      - "10911:10911"
      - "10912:10912"
    entrypoint: "./mqbroker -n 220.254.1.18:9876;220.254.1.19:9876 -c ../conf/2m-2s-async/broker-a.properties"
    depends_on:
      - rocketmq_name_server_0
      - rocketmq_name_server_1
  rocketmq_broker_1:
    image: apache/rocketmq:4.9.4
    container_name: rocketmq_broker_1
    hostname: rocketmq_broker_1
    privileged: true
    networks:
      network:
        ipv4_address: 220.254.1.21
    environment:
      - JVM_XMX=512m
    ports:
      - "10913:10913"
      - "10914:10914"
      - "10915:10915"
    volumes:
      - /home/ivfzhou/volumes/rocketmq/config/broker-b.properties:/home/rocketmq/rocketmq-4.9.4/conf/2m-2s-async/broker-b.properties
      - /home/ivfzhou/volumes/rocketmq/logs_broker_1:/home/rocketmq/logs/rocketmqlogs:rw
      - /home/ivfzhou/volumes/rocketmq/store_broker_1:/home/rocketmq/store:rw
      - /home/ivfzhou/volumes/rocketmq/runbroker.sh:/home/rocketmq/bin/runbroker.sh:rw
    entrypoint: "./mqbroker -n 220.254.1.18:9876;220.254.1.19:9876 -c ../conf/2m-2s-async/broker-b.properties"
    depends_on:
      - rocketmq_name_server_0
      - rocketmq_name_server_1
  rocketmq_broker_2:
    image: apache/rocketmq:4.9.4
    container_name: rocketmq_broker_2
    hostname: rocketmq_broker_2
    privileged: true
    networks:
      network:
        ipv4_address: 220.254.1.22
    ports:
      - "10916:10916"
      - "10917:10917"
      - "10918:10918"
    volumes:
      - /home/ivfzhou/volumes/rocketmq/config/broker-a-s.properties:/home/rocketmq/rocketmq-4.9.4/conf/2m-2s-async/broker-a-s.properties
      - /home/ivfzhou/volumes/rocketmq/logs_broker_2:/home/rocketmq/logs/rocketmqlogs:rw
      - /home/ivfzhou/volumes/rocketmq/store_broker_2:/home/rocketmq/store:rw
      - /home/ivfzhou/volumes/rocketmq/runbroker.sh:/home/rocketmq/bin/runbroker.sh:rw
    entrypoint: "./mqbroker -n 220.254.1.18:9876;220.254.1.19:9876 -c ../conf/2m-2s-async/broker-a-s.properties"
    depends_on:
      - rocketmq_name_server_0
      - rocketmq_name_server_1
  rocketmq_broker_3:
    image: apache/rocketmq:4.9.4
    container_name: rocketmq_broker_3
    hostname: rocketmq_broker_3
    privileged: true
    networks:
      network:
        ipv4_address: 220.254.1.23
    ports:
      - "10919:10919"
      - "10920:10920"
      - "10921:10921"
    volumes:
      - /home/ivfzhou/volumes/rocketmq/config/broker-b-s.properties:/home/rocketmq/rocketmq-4.9.4/conf/2m-2s-async/broker-b-s.properties
      - /home/ivfzhou/volumes/rocketmq/logs_broker_3:/home/rocketmq/logs/rocketmqlogs:rw
      - /home/ivfzhou/volumes/rocketmq/store_broker_3:/home/rocketmq/store:rw
      - /home/ivfzhou/volumes/rocketmq/runbroker.sh:/home/rocketmq/bin/runbroker.sh:rw
    entrypoint: "./mqbroker -n 220.254.1.18:9876;220.254.1.19:9876 -c ../conf/2m-2s-async/broker-b-s.properties"
    depends_on:
      - rocketmq_name_server_0
      - rocketmq_name_server_1
  rocketmq_dashboard:
    image: apacherocketmq/rocketmq-dashboard:1.0.0
    container_name: rocketmq_dashboard
    networks:
      network:
        ipv4_address: 220.254.1.24
    environment:
      JAVA_OPTS: "-Drocketmq.namesrv.addr=220.254.1.18:9876;220.254.1.19:9876"
    ports:
      - "8080:8080"
    depends_on:
      - rocketmq_name_server_0
      - rocketmq_name_server_1
networks:
  network:
    driver: bridge
    attachable: true
    ipam:
      config:
        - subnet: 220.254.1.0/24
          gateway: 220.254.1.1
```
