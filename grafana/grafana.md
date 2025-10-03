# 一、笔记

1. [grafana.ini](./grafana.ini)

# 二、Docker-Compose

```yml
services:
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
```
