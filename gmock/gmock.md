# 命令

源码模式：使用了 -source。  
反射模式：使用 path 和 symbols。  
1. mockgen
    - -source path 指定包含被mock的接口文件。
    - -destination path mock文件输入路径，默认为标准输出。
    - -package name 指定生成的go文件的包名，默认为 mock_pkg。
    - -imports foo=bar/baz 设置要在生成文件中使用的导包，逗号分割。
    - -aux_file foo/bar/baz.go 定义一些对内嵌接口描述的文件，以逗号分割。
    - -build_flag flags 反射模式，被go build解析。
    - -mock_names Repository=MockSensorRepository,Endpoint=MockSensorEndpoint 定义生成的mock接口名。
    - -self_package package 指定文件自身包名，避免循环导入。
    - -copyright_file file 指定版权头文件。
    - -debug_parser 仅打印分析程序结果。
    - -exec_only 使用反射模式。
    - -prog_only 在反射模式下使用，输出结果到标准输出并退出。
    - -write_package_comment 默认true，写入包文档。
