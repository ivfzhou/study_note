# 笔记

1. ${nexus.home}/sonatype-work/nexus3/admin.password 第一次启动后初始密码所在文件。
2. 必须要 jdk8，且必须在 ${nexus}/etc/nexus 文件里指定 jdk 路径。

# 命令

1. Linux：
    1. start：启动。
    2. stop：停止。
    3. run：启动。
    4. run-redirect：目标启动。
    5. status：状态。
    6. restart：重启。
    7. force-reload：强制重启。

2. windows：符号 / 可以替换成 --。
    1. /help：帮助。
    2. /install：安装服务。
    3. /uninstall：卸载服务。
    4. /stop：停止。
    5. /start：启动。
    6. /status：状态。
    7. /run：启动。
    8. /run-redirect：目标启动。


# Docker 运行

1. chown -R 200 /home/ivfzhou/volumes/nexus/
2. docker run --name nexus --ip 172.16.3.132 --hostname ivfzhou_docker_nexus -p 8081:8081 -v /home/ivfzhou/volumes/nexus/nexus-data:/nexus-data:rw --network ivfzhou_docker_network -td sonatype/nexus3:3.60.0

# 配置 https

1. /nacos-data/etc/nexus.properties 里面修改配置，放开 ssl 端口，添加 jetty-https.xml。
2. jetty-https.xml 中修改证书信息。
3. 生成根证书私钥 openssl genrsa -out ivfzhou.pri 4096。
4. 生成根证书公钥 openssl req -x509 -new -nodes -key ivfzhou.pri -days 365 -out ivfzhou.pub。
5. 生成 nexus 用的证书私钥 openssl genrsa -out nexus.pri 4096。
6. 生成 nexus 的 csr openssl req -new -key nexus.pri -out nexus.csr -config <(printf "[SAN]\nsubjectAltName=DNS:ivfzhou_docker_nexus,IP:172.16.2.2")>。
7. 根证书签名 openssl x509 -req -in nexus.csr -CA ivfzhou.pub -CAkey ivfzhou.pri -CAcreateserial -days 365 -ext “SAN=IP:172.16.3.132” -extensions SAN -out nexus.pub -extfile <(printf "-[SAN]\nsubjectAltName=DNS:ivfzhou_docker_nexus,IP:172.16.3.132")>
8. 打成 pkcs12 openssl pkcs12 -export -in nexus.pub -inkey nexus.pri -out nexus.pfx。
9. 转成 jks keytool -importkeystore -srckeystore nexus.pfx -srcstoretype pkcs12 -destkeystore nexus.jks -deststoretype jks -deststorepass 654321 -destkeypass 123456。
10. 将 nexus.jks 复制到 /opt/nexus/etc/ssl/ 下。
11. [http.zip](./https.zip)

# Docker-Compose 配置

```yaml
version: "3.9"
services:
  nexus:
    image: sonatype/nexus3:3.60.0
    container_name: nexus
    hostname: ivfzhou_docker_nexus
    extra_hosts:
      - "ivfzhou_debian:172.16.3.1"
    networks:
      network:
        ipv4_address: 172.16.3.132
    privileged: true
    ports:
      - "8081:8081"
    #volumes:
    #  - /home/ivfzhou/volumes/nexus/data:/nexus-data:rw
networks:
  network:
    driver: bridge
    attachable: true
    ipam:
      config:
        - subnet: 172.16.3.0/24
          gateway: 172.16.3.1
```
