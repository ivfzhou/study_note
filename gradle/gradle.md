# 命令

- gradle < -v | -version >：打印版本等信息。
- gradle --help：帮助信息。
- gradle init：初始化一个项目。
- gradle *参数* *task名*：执行这个任务。
    - -q：静默输出。
- gradle build：编译测试打包。
- gradle clean：删除 build 目录。
- gradle assemble：合成 jar 或 war 包，不会单元测试。
- gradle check：编译测试代码。
- gradle -D*JVM参数* -P*prop参数* --gradle-user-home *目录* < -I | --init-script *初始化脚本路径* > *任务名*
- gradle --status：守护程序状态。
- gradle --stop：关闭守护进程。
- gradle --daemon：启用一个守护进程。

# 配置属性、文件

1. 命令行参数形式 -D
1. 在项目根目录的 gradle.properties 中。
1. 家目录 .gradle 下的 gradle.properties 中。
1. 在 gradle 安装目录中的 gradle.properties。
1. 以上参数优先规则由高往底。

# 初始化脚本

- 从参数中解析。
- 从家目录 .gradle 下的 init.gradle 解析。
- 从家目录 .gradle/init.d 下的 .gradle 解析。
- 从程序目录下 init.d 下的 .gradle 解析。
