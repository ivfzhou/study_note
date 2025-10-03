# 一、笔记

1. 删除数据卷文件可能导致 run 失败，运行 systemctl restart docker 修复。

1. 指定的文件数据卷，目录下生成随机文件夹名。

3. 数据卷默认路径：/var/lib/docker/volumes。

4. 配置文件路径：/etc/docker/daemon.json。

5. 数据卷有文件就覆盖容器的，没有就复制容器的。

6. 配置文件增加字段 "insecure-registries":[ *host* ]，表示 push 时不使用 SSL。

7. [docker-compose.yml](./docker-compose.yml)

8. Dockerfile 例子：

    ```dockerfile
    FROM golang:1.23.2
    FROM debian:12.11
    COPY --from=0 /usr/local/go/lib/time/zoneinfo.zip /opt/zoneinfo.zip
    ENV ZONEINFO /opt/zoneinfo.zip
    ENV TZ Asia/Shanghai
    RUN ln -fs /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
    RUN echo 'Asia/Shanghai' >/etc/timezone
    RUN apt-get update && apt-get install -y tzdata
    RUN dpkg-reconfigure -f noninteractive tzdata
    COPY --from=0 /etc/ssl/certs/ca-certificates.crt /etc/ssl/certs/
    ENV WORKDIR /home/user/app
    CMD ./bin
    ENTRYPOINT java -Xms256m -Xmx512m -Xss2m -jar -Dserver.port=${PORT} ${MODULE}-0.0.1-SNAPSHOT.jar
    ARG module # docker build --build-arg=module=xxx
    ```

# 二、命令

1. docker -v：打印版本。
2. docker run *参数* *容器名*：运行容器。
    - -i：保持和 docker 容器内的交互。
    - -t：为容器的标准输入，虚拟一个 tty。
    - -d：后台运行容器。
    - --hostname *主机名*：给容器设置主机名。
    - --add-host *名字*:*IP*：给容器添加 host。
    - --rm：容器在启动后，执行完成命令或程序后就销毁（不可于 -d 一起使用）。
    - --name *name*：给容器起一个自定义名称。
    - --restart=*select*：docker1.12 新增加的参数，用来指定容器的重启策略。当前提供的策略包括：no 默认值，如果容器挂掉不自动重启。on-failure 若容器的退出状态非 0，则 docker 自动重启容器，还可以指定重启次数。always 只要容器退出，则 docker 将自动重启容器。
    - -p *主机 port*:*容器 port*：将宿主机中的某个端口映射到容器中的某个端口上。
    - -v *masterPath*:*containerPath*:*ro*：在创建容器时指定数据卷，默认 rw。
    - --mount source=*masterPath*,target=*containerPath*,type=*volume*：在创建容器时指定数据卷，type 指定为数据卷的方式挂载，还可以选择 bind、tmpfs，默认为 volume。
    - --user:*uid*:*gid*：指定运行容器主程序的用户和组。避免程序访问挂载文件无权限问题。
3. docker search *imageName*：搜索镜像文件。
    - -f、--filter：根据提供的条件过滤器输出。
    - --format：用 Go 模板打印出漂亮的搜索结果。
    - --limit *n*：搜索结果的最大数量（默认值为 25）。
    - --no-trunc：不要截断输。
4. docker pull *image*:*version*：下载镜像。
5. docker images：显示已安装的镜像。
    - -q：只显示 ID。
6. docker rmi <*imagename*:*tag | imageId*>：删除镜像。
    - -f：强制删除。
7. docker rm *containerName*：删除容器。
    - -f：强制删除。
8. docker rm -f ${docker ps -qa}：删除所有容器。
9. docker ps：查看运行的容器，默认显示运行的容器。
    - -a：查看所有容器。
    - -q：只显示 ID。
10. docker <start | stop | restart> *imamgName*：开启关闭重启容器。
    - -i：实时交互，控制台打印信息。
11. docker inspect *imageName*：检查镜像元数据。
12. docker inspect -f '{{.NetworkSettings.IPAddress}}' *ID*：查看容器 IP。
13. docker exec *参数* *containerName* *args*：进去运行中的容器。args 表示进入容器后执行的命令，如果需要和容器进行交互式访问，需要配置参数为 -it，并且命令设置为 bash（表示通过命令行的方式交互访问容器）。
    - -i：保持和 docker 容器内的交互。
    - -t：为容器的标准输入，虚拟一个 tty。
    - -w：指定进入容器后的工作路径。
14. docker logs *containerName*：查看日志。
    - --tail *n*：选项可以指定查看后 n 条日志。
    - -t：选项则可以对日志条目附加时间戳。
    - --until：显示在某个时间戳（例如：2018-05-25T 13:23:37）之前的日志，还可以相对时间（例如：42m 42minutes）。
    - -f：实时打印日志内容。
15. docker cp *cintainerName*:*containerPath* *masterPath*：从容器复制文件到宿主机。
16. docker cp *masterPath* *cintainerName*:*containerPath*：从宿主机复制文件到容器。
17. docker volume create *volume*：创建数据卷。
18. docker volumn ls：查看所有卷。
19. docker tag *imageId* *newName*:*version*：创建一个跟 imageId 一样的镜像。
20. docker push *urlPathName*:*version*：将镜像推送至云。
21. docker build -f ./Dockerfile -t *image:version* ./：构造镜像。
22. docker login --username=*name* *url*：登录云仓库。
23. docker save -o *file* *image*：将镜像复制到本地文件。
24. docker load -i *file*：将本地文件复制到 docker 镜像。
25. docker network *参数*。
    - connect：将容器连接至网络。
    - disconnect：断开容器网络连接。
    - inspect：显示一个或多个网络详细信息。
    - ls：列出所有网络。
    - rm：删除一个或多个网络。
    - prune：删除没有使用的网络。
    - create *参数* *名称*：创建一个网络。
        - --subnet 172.18.1.0/24。
26. docker images prune -f：删除虚镜像。
27. docker-compose -v：打印版本信息。
28. docker-compose version：打印版本信息。
29. docker-compose up -d：启动镜像容器。
     - --build：重新生成镜像启动。
30. docker-compose start：启动容器。
31. docker-compose stop：停止容器。
32. docker-compose down：停止容器并删除。

# 三、Debian apt 安装运行

```shell
for pkg in docker.io docker-doc docker-compose podman-docker containerd runc; do sudo apt-get remove $pkg; done
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/debian/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc
echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/debian \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
    "registry-mirrors": ["https://registry.docker-cn.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

# 四、Debian 源码编译

1. 运行 git clone https://github.com/moby/moby.git -o github docker。

2. 运行 cd docker。

3. 从 https://download.docker.com/linux/static/stable/ 下载编译好的 docker 程序包。

4. 解压 docker 程序包，将文件放到 docker_boot/ 下。

5. 从 https://github.com/docker/buildx/releases 下载 docker-buildx。

6. 将 docker-buildx 可执行程序放到 ~/.config/cli-plugins/ 下，将权限设置为可执行。

7. 将 docker_boot 文件夹设置到环境变量 PATH 中。

8. 配置 docker 网络代理，生成文件 config.json。

9. root 用户运行 dockerd --userland-proxy=false --config-file=config.json --bip=172.17.0.1/16。

10. 配置 Docker 容器内的网络代码，在 Dockerfile 中，分别在运行 apt 和 go 命令处添加：

    ```dockerfile
    ENV GOPROXY "goproxy.cn,direct"
    ENV http_proxy "http://172.17.0.1:7897"
    ENV https_proxy "http://172.17.0.1:7897"
    ```

11. 运行 make。

12. 生成可执行文件在 bundle 文件夹下。

13. 运行 sudo ip link delete docker0 删除网络接口。

# 五、Debian 二进制包运行

1. 下载二进制压缩包，https://download.docker.com/linux/static/stable/。

2. 解压，设置环境变量。

3. 运行 sudo env PATH=$PATH:/sbin dockerd。

# 六、Debian 二进制包安装 docker-compose

1. 运行 mkdir bin。

2. 运行 curl -SL https://github.com/docker/compose/releases/download/v2.37.2/docker-compose-linux-x86_64 -o ~/bin/docker-compose。

3. 运行 chmod +x ~/bin/docker-compose。

# 七、镜像版本

1. mysql:8.4.5
2. grafana/grafana:10.1.2
3. elasticsearch:9.0.2
4. kibana:9.0.2
5. zookeeper:3.9.0
6. nginx:1.25.2
7. nacos/nacos-server:v2.2.3
8. apache/rocketmq:5.1.3
9. openzipkin/zipkin:2.24.3
10. sonatype/nexus3:3.80.0
11. redis:8.0.2
12. rabbitmq:4.1.1-management
13. tusproject/tusd:v2.8
14. jaspeen/oracle-11g:latest
15. openzipkin/zipkin:3.5

# 八、网络代理配置

1. 在 /etc/docker/daemon.json 里添加：

    ```json
    {
      "proxies": {
        "http-proxy": "http://127.0.0.1:7897",
        "https-proxy": "http://127.0.0.1:7897"
     }
    }
    ```
    
2. 运行 sudo systemctl daemon-reload。

3. 运行 sudo systemctl restart docker。

     
