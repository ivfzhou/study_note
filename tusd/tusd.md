# Docker-Compose 安装

```yml
services:
  tusd:
    image: tusproject/tusd:v2.8
    container_name: tusd
    hostname: ivfzhoudockertusd
    privileged: true
    ports:
      - "8080:8080"
    networks:
      network:
        ipv4_address: 172.16.3.141
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    volumes:
      - /home/ivfzhou/volumes/tusd/data:/srv/srv/tusd-data/data:rw
    entrypoint: "tusd -expose-metrics=false -disable-cors -hooks-enabled-events="
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
1. mkdir -p /home/ivfzhou/volumes/tusd/data
1. sudo chown 1000:1000 -R /home/ivfzhou/volumes/tusd
1. docker-compose -f docker-compose.yml up -d tusd
