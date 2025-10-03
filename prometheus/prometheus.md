# 一、笔记

1. [prometheus.yml](./prometheus.yml)

# 二、Docker-Compose

```yml
services:
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
```
