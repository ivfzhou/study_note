# 一、conf

1. / 通配所有。
1. ~ 正则匹配。
1. ^~ 开头正则匹配。
1. ~* 后缀正则匹配。
1. [keepalived.conf](./keepalived.conf)、[nginx_check.sh](./nginx_check.sh)。

# 二、编译安装

1. ./configure --prefix=/opt/nginx --with-http_stub_status_module --with-http_realip_module --with-http_ssl_module
1. make install

# 三、Docker-Compose 配置

```yaml
version: "3.9"
services:
  nginx:
    image: nginx:1.25.2
    container_name: nginx
    hostname: ivfzhoudcokernginx
    networks:
      network:
        ipv4_address: 172.16.3.1
    ports:
      - "80:80"
    volumes:
      - /home/ivfzhou/volumes/nginx/config/nginx.conf:/etc/nginx/nginx.conf:rw
      - /home/ivfzhou/volumes/nginx/config/conf.d:/etc/nginx/conf.d:rw
      - /home/ivfzhou/volumes/nginx/logs:/var/log/nginx:rw
```
1. mkdir -p /home/ivfzhou/volumes/nginx/config
1. mkdir -p /home/ivfzhou/volumes/nginx/logs
1. mkdir -p /home/ivfzhou/volumes/nginx/config/conf.d
1. cp [default.conf](./default.conf) /home/ivfzhou/volumes/nginx/config/conf.d/default.conf
1. cp [nginx.conf](./nginx.conf) /home/ivfzhou/volumes/nginx/config/conf.d/default.conf
1. sudo chown -R 101:101 /home/ivfzhou/volumes/nginx
