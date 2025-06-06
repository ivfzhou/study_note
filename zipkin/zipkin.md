# Docker-Compose 配置

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