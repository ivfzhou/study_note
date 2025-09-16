#  笔记

1. 运行 phase 时，会把生命周期上层的 phase 依次运行一遍。
1. 非 compile 范围的依赖没有传递性。

# 配置

1. 路径：`$MAVEN_HOME/conf/settings.xml`。
1. settings.xml：
    ```xml
    <mirrors>
      <mirror>
        <id>aliyun</id>
        <name>aliyun maven repository</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
        <url>https://maven.aliyun.com/nexus/content/repositories/central</url>
        <!-- central 可以更换成 * -->
        <mirrorOf>central</mirrorOf> 
      </mirror>
    </mirrors>
    
    <servers>
      <!-- 配置从 nexus 下载验证 -->
      <server>
        <!-- 要与下面 profile 的 ID 一致 -->
        <id>nexus-download</id>
        <username>ivfzhou</username>
        <password>123456</password>
      </server>
    
      <!-- 配置上传项目到 nexus 验证 -->
      <server>
        <!-- 要与 POM 文件的 ID 一致 -->
        <id>nexus-releases-upload</id>
        <username>ivfzhou</username>
        <password>123456</password>
        <!-- 默认 ~/.ssh/id_dsa -->
        <privateKey>/path/to/identity</privateKey>
        <passphrase>123456</passphrase>
      </server>
      <server>
        <id>nexus-snapshots-upload</id>
        <username>ivfzhou</username>
        <password>123456</password>
      </server>
    </servers>
    
    <profiles>
      <profile>
        <id>nexus</id>
        <repositories>
          <repository>
            <id>nexus-download</id>
            <url>http://ivfzhoudockernexus:8081/repository/maven-public/</url>
            <releases>
              <enabled>true</enabled>
              <updatePolicy>always</updatePolicy>
            </releases>
            <snapshots>
              <enabled>true</enabled>
              <updatePolicy>always</updatePolicy>
            </snapshots>
          </repository>
        </repositories>
        <pluginRepositories>
          <!-- 插件仓库，maven 的运行依赖插件，也需要从私服下载插件 -->
          <pluginRepository>
            <!-- 插件仓库的 id 不允许重复，如果重复后边配置会覆盖前边 -->
            <id>nexus-download</id>
            <url>http://ivfzhoudockernexus:8081/repository/maven-public/</url>
            <releases>
              <enabled>true</enabled>
              <updatePolicy>always</updatePolicy>
            </releases>
            <snapshots>
              <enabled>true</enabled>
              <updatePolicy>always</updatePolicy>
            </snapshots>
          </pluginRepository>
        </pluginRepositories>
      </profile>
      <profile>
        <id>jdk21</id>
        <activation>
          <!-- 默认启用 -->
          <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
          <!-- 编译版本 -->
          <maven.compiler.release>21</maven.compiler.release>
          <!-- 或者分别写 -->
          <maven.compiler.source>21</maven.compiler.source>
          <maven.compiler.target>21</maven.compiler.target>
          <!-- 编码 -->
          <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
          <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        </properties>
      </profile>
    </profiles>
    
    <activeProfiles>
      <activeProfile>jdk21</activeProfile>
      <activeProfile>nexus</activeProfile>
    </activeProfiles>
    ```
1. pom.xml：
    ```xml
    <distributionManagement>
      <repository>
        <id>nexus-releases-upload</id>
        <url>http://ivfzhoudockernexus:8081/repository/maven-releases/</url>
      </repository>
      <snapshotRepository>
        <id>nexus-snapshots-upload</id>
        <url>http://ivfzhoudockernexus:8081/repository/maven-snapshots/</url>
      </snapshotRepository>
    </distributionManagement>
    
    <build>
      <plugins>
        <plugin>
          <groupId>org.apache.tomcat.maven</groupId>
          <artifactId>tomcat7-maven-plugin</artifactId>
          <version>2.2</version>
          <configuration>
            <port>8080</port>
            <path>/index</path>
          </configuration>
        </plugin>
      </plugins>
    </build>
    ```

# 仓库地址

1. Central=https://repo1.maven.org/maven2/
1. Aliyun=http://maven.aliyun.com/nexus/content/groups/public/  
          https://maven.aliyun.com/repository/public/
1. Spring Lib Release=https://repo.spring.io/libs-release/
1. Spring Plugins=https://repo.spring.io/plugins-release/
1. Spring Lib M=https://repo.spring.io/libs-milestone/
1. Cloudera=https://repository.cloudera.com/artifactory/cloudera-repos
1. Redhat=https://maven.repository.redhat.com/ga/
1. Jboos public=http://repository.jboss.org/nexus/content/groups/public
1. Pentaho=https://nexus.pentaho.org/content/repositories/omni/
1. Icm=http://maven.icm.edu.pl/artifactory/repo/
1. JBossEA=https://repository.jboss.org/nexus/content/repositories/ea/
1. JBoss Releases=https://repository.jboss.org/nexus/content/repositories/releases/

# 命令

1. **mvn** *options* *groupId:artifactId:version:goals* *phases*：goal 绑定一个 phase，当执行一个 phase 时，就将绑定的所有 goal 运行一遍。
   
    1. options：
        - --am、--also-make：同时编译依赖的项目。
        - --amd、--also-make-dependents：同时编译依赖了这个项目的项目。
        - -N、--non-recursive：不编译子项目。
        - -B、--batch-mode：批处理模式，不交互模式。
        - -b、--builder *arg*：指定编译策略 ID。
        - -C、--strict-checksums：检查校验和。
        - -c、--lax-checksums：如果检验和不匹配发出警告。
        - -pl、--projects *arg*：编译指定的项目，以逗号分隔的相对路径或 \[*groupId*\]:*artifactId*。
        - -q、--quit：较少的日志打印，仅显示错误级别。
        - -e、--errors：打印错误日志。
        - -X、--debug：较多的日志打印。
        - -l、--log-file *arg*：打印信息输出到指定文件。
        - -h、--help：打印帮助信息。
        - -v、--version：打印版本信息。
        - -V、--show-version：打印版本信息并编译。
        - -P、--activate-profiles *args*：指定环境文件，逗号分隔。
        - -D、--define *arg*：指定参数。
    1. lifecycle 的 phases，和 phase 下的默认 goals：
        - clean
            - pre-clean
            - clean：maven-clean-plugin:clean
            - post-clean
        - default
            - validate：校验项目是否正确，POM 是否完整。
            - initialize：初始化构建状态。
            - generate-sources：生成源码。
            - process-sources：处理源码，例如过滤。
            - generate-resources：生成资源文件。
            - process-resources：maven-resources-plugin:resources，复制和过滤资源到 target/classes。
            - compile：maven-compiler-plugin:compile → 编译 main 源码
            - process-classes：对编译过的字节码做处理，例如字节码增强。
            - generate-test-sources：生成测试源码
            - process-test-sources：处理测试源码。
            - generate-test-resources：生成测试资源。
            - process-test-resources：maven-resources-plugin:testResources，复制测试资源到 target/test-classes。
            - test-compile：maven-compiler-plugin:testCompile，编译测试源码。
            - process-test-classes
            - test：maven-surefire-plugin:test，运行单元测试。
            - prepare-package：在打包前做一些处理，比如 OSGi manifest 生成。
            - package：maven-jar-plugin:jar（打 jar 包，普通项目）。maven-war-plugin:war（web 项目）。
            - pre-integration-test
            - integration-test：通常绑定一些集成测试插件。
            - post-integration-test
            - verify：通常执行检查任务。
            - install：maven-install-plugin:install，把构建好的包安装到本地仓库。
            - deploy：maven-deploy-plugin:deploy，把构建好的包部署到远程仓库。
        - site
            - pre-site
            - site：maven-site-plugin:site，生成站点文档。
            - post-site
            - site-deploy：maven-site-plugin:deploy，部署站点到服务器。
    
1. **mvn clean verify**

1. **mvn test -Dtest=*classname*＃*method***

1. **mvn help:describe -Dplugin=*groupId*:*artifactId*:*version***：打印插件帮助信息。

1. **mvn dependency:resolve dependency:resolve-sources -Dclassifier=javaDoc**：下载 jar 和 source 和 doc。

1. **mvn tree**：打印依赖信息。

     
