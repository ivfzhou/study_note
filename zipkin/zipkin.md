# 一、Docker-Compose 安装

```yml
services:
  zipkin:
    image: openzipkin/zipkin:3.5
    container_name: zipkin
    hostname: ivfzhoudockerzipkin
    privileged: true
    ports:
      - "9411:9411"
    networks:
      network:
        ipv4_address: 172.16.3.143
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
```
1. sudo tee -a /etc/hosts <<EOF
   172.16.3.143 ivfzhoudockerzipkin
   EOF

# 二、笔记

1. 控制台地址 http://ivfzhoudockerzipkin:9411/zipkin/。