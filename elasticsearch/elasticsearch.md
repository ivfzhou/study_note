# 虚拟机内存大小

修改文件位置/etc/sysctl.conf，添加vm.max_map_count=262144，执行sudo sysctl -p。

# 分词器

WhitespaceAnalyzer(去除空格)、SimpleAnalyzer(字母小写)、StopAnalyzer(去除停顿词)、StandardAnalyzer(中文单字切割)、CJKAnalyzer、SmartChineseAnalyzer、IKAnalyzer。

# ES特点

处理多租户不需要特殊配置、实时索引建立、自动分配分片副本、自动管理请求负载、自动迁移扩展分片、也就是保证了高可用、事务日志记录会复制到多个节点上、RestfulAPI风格操作。

# 每个节点的分片数量

低于每GB堆内存20～25个分片。

# 用户资源控制

- vim /etc/security/limits.conf
    - soft nofile 65536
    - hard nofile 131072
    - soft nproc 4096
    - hard nproc 4096

# 数据类型

1. text,keyword,
1. long(-2^63~2^63-1),integer(-2^31-2^31),short(-32768-32,767),byte(-128~127),double,float,half_float,scaled_float,
1. date,date_nanos,
1. boolean,
1. binary,
1. integer_range,float_range,long_range,double_range,date_range,ip_range,
1. object,nested,
1. geo_point,geo_shape,ip,completion,token_count,murmur3,annotated-text,shape,histogram

# 命令

1. elasticsearch
    - -E 键值对配置设置。
    - -V或者--version 显示版本。
    - -d或者--daemonize 后台启动。
    - -h或者--help 显示帮助信息。
    - -p或者--pidfile 路径生成含pid的文件path。
    - -q或者--quiet 关闭标准输出流和错误输出流。
    - -s或者--silent 较少的信息输出。
    - -v或者--verbose 较多信息输出。
1. elasticsearch-setup-passwords
    - interactive 初始化密码。elastic,apm_system,kibana,logstash_system,beats_system,remote_monitoring_user。
1. elasticsearch-keystore
    - create 生成keystore文件。
    - list 列出条目。
    - passwd 修改keystore文件密码。
1. curl -H "Content-Type:application/json" -X POST -u elastic -d '{"password":"94683364"}' 'http://127.0.0.1:9200/_xpack/security/user/elastic/password' 修改密码。
1. elasticsearch-users
    - useradd  -p 密码 -r superuser 用户名创建用户。
1. curl -H 'Content-Type:application/json' -u 超级用户 -d '{"password":"密码"}' -X PUT 'http://172.18.0.1:9200/_xpack/security/user/elastic/_password' 通过超级用户修改elastic密码。
1. elasticsearch-certutil
    - -E 键值对配置设置。
    - -h或者--help 帮助。
    - -s或者--silent 信息少输出。
    - -v或者--verbose 信息多输出。
    - csr 生成签名请求的证书。
    - cert 生成X.509证书和key。
        - --pem 生成instance和ca。
    - ca 生成本地证书授权。
    - http 生成http接口的证书。
1. kibana
    - -e或者--elasticsearch url1,url2... 设置el的url。
    - -c或者--config 路径 设置配置文件。
    - -p或者--port 端口号 设置端口号。
    - -q或者--quiet 只打印error级别日至信息。
    - -Q或者--silent 不打印信息。
    - --verbose 打印很多信息。
    - -H或者--host 主机名 绑定指定host。
    - -l或者--log-file 路径指定日志输出文件。
    - --plugins或者--plugin-dir 路径 设置要扫描插件路径。
    - --plugin-path 路径添加插件。
    - --optimize 运行插件优化器，然后停止服务。
    - -h或者--help 显示帮助信息。

# kibana 运行

1. docker run -v /home/ivfzhou/volumes/kibana/config:/usr/share/kibana/config --hostname kibana -v 5601:5601 --name kibana kibana:7.17.4
1. 复制配置文件到数据卷。
1. kibana控制台报错需添加配置
    - xpack.encryptedSavedObjects.encryptionKey: "woyaocouqi32weizifu+++++++++++++"
    - xpack.security.encryptionKey: "woyaocouqi32weizifu+++++++++++++"
    - xpack.reporting.encryptionKey: "woyaocouqi32weizifu+++++++++++++"

# 集群搭建

1. 使用解压缩版本，本地位置/opt/elasticsearch/下。
1. 修改本地/etc/hosts文件，添加键值对：172.18.2.1 ivfzhou-ubuntu、172.18.2.2 ivfzhou-docker-elasticsearch。
1. 修改本地防火墙以防止容器访问本地被拒接，firewall-cmd --add-port=9200/tcp --add-port=9300/tcp、ufw allow from 172.18.2.0/24。
1. 修改本地配置文件：cluster.name: elasticsearch、node.name: ivfzhou-ubuntu、node.master: true、node.data: true、path.data: /opt/elasticsearch/data、path.logs: /opt/elasticsearch/logs、network.host: 172.18.2.1、discovery.seed_hosts: ["172.18.2.2"]、cluster.initial_master_nodes: ["http://172.18.2.1:9200"]、xpack.security.enabled: true、xpack.security.transport.ssl.enabled: true、xpack.security.transport.ssl.verification_mode: certificate、xpack.security.transport.ssl.key: /opt/elasticsearch/config/instance/instance.key、xpack.security.transport.ssl.certificate: /opt/elasticsearch/config/instance/instance.crt、xpack.security.transport.ssl.certificate_authorities: ["/opt/elasticsearch/config/ca/ca.crt"]。
1. 生成certificate证书，运行/opt/elasticsearch/bin/elasticsearch-certutil cert --pem输入文件名，解压文件，将instance和ca两文件夹放到程序的config目录下。
1. 创建docker network create --subnet 172.18.2.0/24 elk-network网段。
1. 执行命令：docker run -v ~/volumes/elasticsearch/data:/usr/share/elasticsearch/data:rw -v  ~/volumes/elasticsearch/config:/usr/share/elasticsearch/config:rw -v  ~/volumes/elasticsearch/log:/user/share/elasticsearch/logs:rw -v  ~/volumes/elasticsearch/plugin:/usr/share/elasticsearch/plugins:rw --hostname ivfzhou-docker-elasticsearch --add-host ivfzhou-ubuntu:172.18.2.1 --network elk-network --ip 172.18.2.2 --name elasticsearch elasticsearch:7.17.4
1. 容器运行报错停止，修改容器数据卷/var/lib/docker/volumes/elasticsearch_config/_data/elasticsearch.yml：cluster.name: elasticsearch、node.name: zhoufeng-docker-elasticsearch、node.master: false、node.data: true、path.logs: /usr/share/elasticsearch/logs、path.data: /usr/share/elasticsearch/data、network.host: 172.18.0.2、discovery.seed_hosts: ["172.18.2.1"]、cluster.initial_master_nodes: ["http://172.18.2.1:9200"]、xpack.security.transport.ssl.enabled: true、xpack.security.transport.ssl.verification_mode: certificate、xpack.security.transport.ssl.key:/usr/share/elasticsearch/config/instance/instance.key、xpack.security.transport.ssl.certificate: /usr/share/elasticsearch/config/instance/instance.crt、xpack.security.transport.ssl.certificate_authorities: ["/usr/share/elasticsearch/config/ca/ca.crt"]、xpack.security.enabled: true。
1. 复制刚才的Instance和ca两文件夹到数据卷/var/lib/docker/volumes/elasticsearch_config/_data/下。
1. 复制ik-analyze文件夹到/var/lib/docker/volumes/elasticsearch_plugin/_data/下。
1. 运行本地es，重启运行容器es。
1. [elk_volume.zip](./elk_volume.zip)
