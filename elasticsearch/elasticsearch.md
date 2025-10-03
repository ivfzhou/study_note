# 一、虚拟机内存大小

修改文件位置 /etc/sysctl.conf，添加 vm.max_map_count=262144，执行 sudo sysctl -p。

# 二、分词器

WhitespaceAnalyzer（去除空格）、SimpleAnalyzer（字母小写）、StopAnalyzer（去除停顿词）、StandardAnalyzer（中文单字切割）、CJKAnalyzer、SmartChineseAnalyzer、IKAnalyzer。

# 三、ES 特点

处理多租户不需要特殊配置、实时索引建立、自动分配分片副本、自动管理请求负载、自动迁移扩展分片、也就是保证了高可用、事务日志记录会复制到多个节点上、RestfulAPI 风格操作。

# 四、每个节点的分片数量

低于每 GB 堆内存 20～25 个分片。

# 五、用户资源控制

1. vim /etc/security/limits.conf
    - soft nofile 65536
    - hard nofile 131072
    - soft nproc 4096
    - hard nproc 4096

# 六、数据类型

1. text, keyword
1. long(-2^63~2^63-1), integer(-2^31-2^31), short(-32768-32,767), byte(-128~127), double, float, half_float, scaled_float
1. date, date_nanos
1. boolean
1. binary
1. integer_range, float_range, long_range, double_range, date_range, ip_range
1. object, nested
1. geo_point, geo_shape, ip, completion, token_count, murmur3, annotated-text, shape, histogram

# 七、命令

1. elasticsearch
    - -E *键值对*：配置设置。
    - -V、--version：显示版本。
    - -d、--daemonize：后台启动。
    - -h、--help：显示帮助信息。
    - -p、--pidfile *路径*：生成含 pid 的文件 path。
    - -q、--quiet：关闭标准输出流和错误输出流。
    - -s、--silent：较少的信息输出。
    - -v、--verbose：较多信息输出。
1. elasticsearch-setup-passwords
    - interactive：初始化密码。elastic,apm_system,kibana,logstash_system,beats_system,remote_monitoring_user。
1. elasticsearch-keystore
    - create：生成 keystore 文件。
    - list：列出条目。
    - passwd：修改 keystore 文件密码。
1. curl -H "Content-Type:application/json" -X POST -u elastic -d '{"password":"123456"}' 'http://127.0.0.1:9200/xpack/security/user/elastic/password'：修改密码。
1. elasticsearch-users
    - useradd  -p *密码* -r superuser *用户名*：创建用户。
1. curl -H 'Content-Type:application/json' -u *超级用户* -d '{"password":"123456"}' -X PUT 'http://127.0.0.1:9200/xpack/security/user/elastic/_password'：通过超级用户修改 elastic 密码。
1. elasticsearch-certutil
    - -E *键值对*：配置设置。
    - -h、--help：帮助。
    - -s、--silent ：息少输出。
    - -v、--verbose：信息多输出。
    - csr：生成签名请求的证书。
    - cert：生成 X.509 证书和 key。
        - --pem 生成 instance 和 ca。
    - ca：生成本地证书授权。
    - http：生成 http 接口的证书。
1. kibana
    - -e、--elasticsearch *url1*,*url2*...：设置 EL 的 URL。
    - -c、--config *路径*：设置配置文件。
    - -p、--port *端口号*：设置端口号。
    - -q、--quiet：只打印 error 级别日至信息。
    - -Q、--silent：不打印信息。
    - --verbose：打印很多信息。
    - -H、--host *主机名*：绑定指定 host。
    - -l、--log-file *路径*：指定日志输出文件。
    - --plugins、--plugin-dir *路径*：设置要扫描插件路径。
    - --plugin-path *路径*：添加插件。
    - --optimize：运行插件优化器，然后停止服务。
    - -h、--help：显示帮助信息。

# 八、Docker-Compose 安装

```yml
services:
  elasticsearch:
    image: elasticsearch:9.0.2
    container_name: elasticsearch
    hostname: ivfzhoudockerelasticsearch
    privileged: true
    ports:
      - "9200:9200"
      - "9300:9300"
    networks:
      network:
        ipv4_address: 172.16.3.144
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=true
      - ES_JAVA_OPTS=-Xms1g -Xmx1g
      - ELASTIC_PASSWORD=123456 # 设置 elastic 用户的密码。
      - xpack.security.authc.api_key.enabled=true
    volumes:
      - "/home/ivfzhou/volumes/elasticsearch/data:/usr/share/elasticsearch/data:rw"
      - "/home/ivfzhou/volumes/elasticsearch/config:/usr/share/elasticsearch/config:rw"
      - "/home/ivfzhou/volumes/elasticsearch/log:/user/share/elasticsearch/logs:rw"
      - "/home/ivfzhou/volumes/elasticsearch/plugin:/usr/share/elasticsearch/plugins:rw"
   kibana:
    image: kibana:9.0.2
    container_name: kibana
    hostname: ivfzhoudockerkibana
    privileged: true
    ports:
      - "5601:5601"
    networks:
      network:
        ipv4_address: 172.16.3.145
    extra_hosts:
      - "ivfzhoudebian:172.16.3.1"
      - "ivfzhoudockerelasticsearch:172.16.3.144"
    depends_on:
      - elasticsearch
    environment:
      - ELASTICSEARCH_HOSTS=http://ivfzhoudockerelasticsearch:9200
      - ELASTICSEARCH_USERNAME=kibana_system
      - ELASTICSEARCH_PASSWORD=123456
    volumes:
      - "/home/ivfzhou/volumes/kibana/data:/usr/share/kibana/data:rw"
      - "/home/ivfzhou/volumes/kibana/config:/usr/share/kibana/config:rw"
      - "/home/ivfzhou/volumes/kibana/log:/user/share/kibana/logs:rw"
      - "/home/ivfzhou/volumes/kibana/plugin:/usr/share/kibana/plugins:rw"
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

1. sudo tee -a /etc/hosts <<EOF
   172.16.3.144 ivfzhoudockerelasticsearch
   172.16.3.145 ivfzhoudockerkibana
   EOF
1. docker-compose -f src/note/docker/docker-compose.yml up -d elasticsearch kibana
1. mkdir -p volumes/elasticsearch
1. mkdir -p volumes/kibana
1. docker cp elasticsearch:/usr/share/elasticsearch/data volumes/elasticsearch/
1. docker cp elasticsearch:/usr/share/elasticsearch/config volumes/elasticsearch/
1. mkdir -p volumes/elasticsearch/logs
1. mkdir -p volumes/elasticsearch/plugins
1. sudo chown 1000:0 -R volumes/elasticsearch
1. docker cp kibana:/usr/share/kibana/data volumes/kibana/
1. docker cp kibana:/usr/share/kibana/config volumes/kibana/
1. mkdir -p volumes/kibana/plugins
1. mkdir -p volumes/kibana/logs
1. sudo chown 1000:0 -R volumes/kibana
1. docker-compose -f src/note/docker/docker-compose.yml down elasticsearch kibana
1. docker-compose -f src/note/docker/docker-compose.yml up -d elasticsearch kibana
1. docker exec -it elasticsearch /usr/share/elasticsearch/bin/elasticsearch-reset-password -u kibana_system -i

# 九、Docker kibana 安装

1. docker run -v volumes/kibana/config:/usr/share/kibana/config --hostname ivfzhoudockerkibana -v 5601:5601 --name kibana kibana:9.0.2
1. 复制配置文件到数据卷。
1. kibana 控制台报错需添加配置
    - xpack.encryptedSavedObjects.encryptionKey: "woyaocouqi32weizifu+++++++++++++"
    - xpack.security.encryptionKey: "woyaocouqi32weizifu+++++++++++++"
    - xpack.reporting.encryptionKey: "woyaocouqi32weizifu+++++++++++++"

# 十、集群搭建

1. 使用解压缩版本，本地位置 /opt/elasticsearch/ 下。
1. 修改本地 /etc/hosts 文件，添加键值对：172.16.3.1 ivfzhoudebian、172.16.3.146 ivfzhoudockerelasticsearch。
1. 修改本地防火墙以防止容器访问本地被拒接，firewall-cmd --add-port=9200/tcp --add-port=9300/tcp、ufw allow from 172.16.3.0/24。
1. 修改本地配置文件：cluster.name: elasticsearch、node.name: ivfzhoudebian、node.master: true、node.data: true、path.data: /opt/elasticsearch/data、path.logs: /opt/elasticsearch/logs、network.host: 172.16.3.1、discovery.seed_hosts: ["172.16.3.146"]、cluster.initial_master_nodes: ["http://172.16.3.1:9200"]、xpack.security.enabled: true、xpack.security.transport.ssl.enabled: true、xpack.security.transport.ssl.verification_mode: certificate、xpack.security.transport.ssl.key: /opt/elasticsearch/config/instance/instance.key、xpack.security.transport.ssl.certificate: /opt/elasticsearch/config/instance/instance.crt、xpack.security.transport.ssl.certificate_authorities: ["/opt/elasticsearch/config/ca/ca.crt"]。
1. 生成 certificate 证书，运行 /opt/elasticsearch/bin/elasticsearch-certutil cert --pem 输入文件名，解压文件，将 instance 和 ca 两文件夹放到程序的 config 目录下。
1. 创建 docker network create --subnet 172.16.3.0/24 ivfzhou_docker_network 网段。
1. 执行命令：docker run -v ~/volumes/elasticsearch/data:/usr/share/elasticsearch/data:rw -v  ~/volumes/elasticsearch/config:/usr/share/elasticsearch/config:rw -v  ~/volumes/elasticsearch/log:/user/share/elasticsearch/logs:rw -v  ~/volumes/elasticsearch/plugin:/usr/share/elasticsearch/plugins:rw --hostname ivfzhoudockerelasticsearch --add-host ivfzhoudebian:172.16.3.1 --network ivfzhou_docker_network --ip 172.16.3.146 --name elasticsearch elasticsearch:9.0.2
1. 容器运行报错停止，修改容器数据卷 /var/lib/docker/volumes/elasticsearch_config/_data/elasticsearch.yml：cluster.name: elasticsearch、node.name: ivfzhou-docker-elasticsearch、node.master: false、node.data: true、path.logs: /usr/share/elasticsearch/logs、path.data: /usr/share/elasticsearch/data、network.host: 172.16.3.146、discovery.seed_hosts: ["172.16.3.1"]、cluster.initial_master_nodes: ["http://172.16.3.1:9200"]、xpack.security.transport.ssl.enabled: true、xpack.security.transport.ssl.verification_mode: certificate、xpack.security.transport.ssl.key:/usr/share/elasticsearch/config/instance/instance.key、xpack.security.transport.ssl.certificate: /usr/share/elasticsearch/config/instance/instance.crt、xpack.security.transport.ssl.certificate_authorities: ["/usr/share/elasticsearch/config/ca/ca.crt"]、xpack.security.enabled: true。
1. 复制刚才的 Instance 和 ca 两文件夹到数据卷 /var/lib/docker/volumes/elasticsearch_config/_data/ 下。
1. 复制 ik-analyze 文件夹到 /var/lib/docker/volumes/elasticsearch_plugin/_data/ 下。
1. 运行本地 ES，重启运行容器 ES。
1. [elk_volume.zip](./elk_volume.zip)
1. [docker-compose.yml](./elasticsearch-docker-compose.yml)
