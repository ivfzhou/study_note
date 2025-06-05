# 笔记

1. 删除数据卷文件可能导致 run 失败，运行 systemctl restart docker 修复。

1. 指定的文件数据卷，目录下生成随机文件夹名。

1. 数据卷默认路径：/var/lib/docker/volumes。

1. 配置文件路径：/etc/docker/daemon.json。

1. 数据卷有文件就覆盖容器的，没有就复制容器的。

1. 配置文件增加字段 "insecure-registries":[ *host* ]，表示 push 时不使用 ssl。

1. Dockerfile 例子：

    ```dockerfile
    FROM golang:1.23.2
    FROM ubuntu:20.04
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

8. 下载 Docker-Compose 程序：
    1. curl -SL https://github.com/docker/compose/releases/download/v2.35.0/docker-compose-linux-x86_64 -o bin/docker-compose
    2. chmod +x bin/docker-compose

# 命令

1. docker -v：打印版本。
1. docker run *参数* *容器名*：运行容器。
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
1. docker search *imageName*：搜索镜像文件。
    - -f、--filter：根据提供的条件过滤器输出。
    - --format：用 Go 模板打印出漂亮的搜索结果。
    - --limit *n*：搜索结果的最大数量（默认值为 25）。
    - --no-trunc：不要截断输。
1. docker pull *image*:*version*：下载镜像。
1. docker images：显示已安装的镜像。
    - -q：只显示 Id。
1. docker rmi <*imagename*:*tag | imageId*>：删除镜像。
    - -f：强制删除。
1. docker rm *containerName*：删除容器。
    - -f：强制删除。
1. docker rm -f ${docker ps -qa}：删除所有容器。
1. docker ps：查看运行的容器，默认显示运行的容器。
    - -a：查看所有容器。
    - -q：只显示 Id。
1. docker <start | stop | restart> *imamgName*：开启关闭重启容器。
    - -i：实时交互，控制台打印信息。
1. docker inspect *imageName*：检查镜像元数据。
1. docker inspect -f '{{.NetworkSettings.IPAddress}}' *id*：查看容器 IP。
1. docker exec *参数* *containerName* bash：进去运行中的容器。args：表示进入容器后执行的命令，如果需要和容器进行交互式访问，需要配置参数为 -it，并且命令设置为 bash（表示通过命令行的方式交互访问容器）。
    - -i：保持和 docker 容器内的交互。
    - -t：为容器的标准输入，虚拟一个 tty。
    - -w：指定进入容器后的工作路径。
1. docker logs *containerName*：查看日志。
    - --tail *n*：选项可以指定查看后 n 条日志。
    - -t：选项则可以对日志条目附加时间戳。
    - --until：显示在某个时间戳（例如：2018-05-25T 13:23:37）之前的日志，还可以相对时间（例如：42m 42minutes）。
    - -f：实时打印日志内容。
1. docker cp *cintainerName*:*containerPath* *masterPath*：从容器复制文件到宿主机。
1. docker cp *masterPath* *cintainerName*:*containerPath*：从宿主机复制文件到容器。
1. docker volume create *volume*：创建数据卷。
1. docker volumn ls：查看所有卷。
1. docker tag *imageId* *newName*:*version*：创建一个跟 imageId 一样的镜像。
1. docker push *urlPathName*:*version*：将镜像推送至云。
1. docker build -f ./Dockerfile -t *image:version* ./：构造镜像。
1. docker login --username=*name* *url*：登录云仓库。
1. docker save -o *file* *image*：将镜像复制到本地文件。
1. docker load -i *file*：将本地文件复制到 docker 镜像。
1. docker network *参数*。
    - connect：将容器连接至网络。
    - disconnect：断开容器网络连接。
    - inspect：显示一个或多个网络详细信息。
    - ls：列出所有网络。
    - rm：删除一个或多个网络。
    - prune：删除没有使用的网络。
    - create *参数* *名称*：创建一个网络。
        - --subnet 172.18.1.0/24。
1. docker images prune -f：删除虚镜像。
1. docker-compose -v：打印版本信息。
1. docker-compose version：打印版本信息。
1. docker-compose up -d：启动镜像容器。
     - --build：重新生成镜像启动。
1. docker-compose start：启动容器。
1. docker-compose stop：停止容器。
1. docker-compose down：停止容器并删除。

# Debian12 安装

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

# 镜像代理服务器

1. https://mirror.ccs.tencentyun.com
1. https://docker.mirrors.ustc.edu.cn
1. http://hub-mirror.c.163.com
1. https://registry.docker-cn.com
1. https://xxxxxxxx.mirror.aliyuncs.com
1. https://mirror.baidubce.com

# 镜像版本

1. mysql:8.4.5
1. grafana/grafana:10.1.2
1. elasticsearch:8.10.2
1. kibana:8.10.2
1. zookeeper:3.9.0
1. nginx:1.25.2
1. nacos/nacos-server:v2.2.3
1. apache/rocketmq:5.1.3
1. openzipkin/zipkin:2.24.3
1. sonatype/nexus3:3.80.0
1. redis:8.0.2
1. rabbitmq:4.1.1-management
1. tusproject/tusd:v2.8
1. jaspeen/oracle-11g:latest

# 网络代理配置

在 /etc/docker/daemon.json 里添加：
```json
{
	"proxies": {
		"http-proxy": "http://127.0.0.1:7897",
		"https-proxy": "http://127.0.0.1:7897"
	}
}
```
然后运行：
sudo systemctl daemon-reload
sudo systemctl restart docker

