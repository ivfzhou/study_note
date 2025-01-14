# 札记

- ${nexus.home}/sonatype-work/nexus3/admin.password第一次启动后初始密码所在文件。http://0.0.0.0:8081/
- 必须要1.8jdk，且必须在${nexus}/etc/nexus文件里指定jdk路径。
- [nexus_volumes.zip](nexus_volumes.zip)

# 命令

- Linux
- start 启动。
- stop 停止。
- run 启动。
- run-redirect 目标启动。
- status 状态。
- restart 重启。
- force-reload 强制重启。
- windows 符号/可以替换成--
- /help 帮助。
- /install 安装服务。
- /uninstall 卸载服务。
- /stop 停止。
- /start 启动。
- /status 状态。
- /run 启动。
- /run-redirect 目标启动。

# docker 运行

- chown -R 200 /home/ivfzhou/volumes/nexus/
- docker run --name nexus --ip 172.16.2.2 --hostname ivfzhou-docker-nexus -p 8081:8081 -v /home/ivfzhou/volumes/nexus/nexus-data:/nexus-data:rw --network ivfzhou-docker-network -td sonatype/nexus3:3.60.0

# 配置https

- /nacos-data/etc/nexus.properties里面修改配置，放开ssl端口，添加jetty-https.xml。
- jetty-https.xml中修改证书信息。
- 生成根证书私钥openssl genrsa -out ivfzhou.pri 4096。
- 生成根证书公钥openssl req -x509 -new -nodes -key ivfzhou.pri -days 365 -out ivfzhou.pub。
- 生成nexus用的证书私钥openssl genrsa -out nexus.pri 4096。
- 生成nexus的csr openssl req -new -key nexus.pri -out nexus.csr -config <(printf "[SAN]\nsubjectAltName=DNS:ivfzhou-docker-nexus,IP:172.16.2.2")>。
- 根证书签名openssl x509 -req -in nexus.csr -CA ivfzhou.pub -CAkey ivfzhou.pri -CAcreateserial -days 365 -ext “SAN=IP:172.16.2.2” -extensions SAN -out nexus.pub -extfile <(printf "-[SAN]\nsubjectAltName=DNS:ivfzhou-docker-nexus,IP:172.16.2.2")>
- 打成pkcs12 openssl pkcs12 -export -in nexus.pub -inkey nexus.pri -out nexus.pfx。
- 转成jks keytool -importkeystore -srckeystore nexus.pfx -srcstoretype pkcs12 -destkeystore nexus.jks -deststoretype jks -deststorepass 654321 -destkeypass 123456。
- 将nexus.jks复制到/opt/nexus/etc/ssl/下。

# Docker-Compose 配置

```yaml
version: "3.9"
services:
  nexus:
    image: sonatype/nexus3:3.40.1
    container_name: nexus
    hostname: nexus
    networks:
      network:
        ipv4_address: 220.254.1.2
    privileged: true
    ports:
      - "8081:8081"
    volumes:
      - /home/ivfzhou/volumes/nexus/data:/nexus-data:rw
networks:
  network:
    driver: bridge
    attachable: true
    ipam:
      config:
        - subnet: 220.254.1.0/24
          gateway: 220.254.1.1
```
