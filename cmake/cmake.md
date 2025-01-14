# 命令

1. **cmake_minimum_required**(VERSION *version*)：用于指定项目所需的最低 CMake 版本。
1. **project**(*project_name* [ VERSION *version* ] [ LANGUAGES <*lang* ...> ])：定义项目的名称和版本，它会自动创建一些与项目相关的变量，如 `PROJECT_NAME`、`PROJECT_VERSION` 等。
1. **target_compile_features**(*target* PRIVATE cxx_std_11)：为特定的目标设置语言标准版本。
1. **execute_process**(COMMAND *command* [ *args* ... ]  
    [ WORKING_DIRECTORY *dir* ]  
    [ RESULT_VARIABLE *var* ]  
    [ OUTPUT_VARIABLE *var* ]  
    [ ERROR_VARIABLE *var* ]  
    [ OUTPUT_STRIP_TRAILING_WHITESPACE ]  
    [ ERROR_STRIP_TRAILING_WHITESPACE ]  
    [ OUTPUT_QUIET ]  
    [ ERROR_QUIET ])：用于在构建过程中执行外部进程。
    - RESULT_VARIABLE：用于存储命令的返回值。
    - OUTPUT_VARIABLE：用于存储命令的标准输出。
    - ERROR_VARIABLE：用于存储命令的标准错误输出。
    - OUTPUT_QUIET、ERROR_QUIET：用于禁止将命令的标准输出或错误输出打印到 CMake 的输出中。
    - OUTPUT_STRIP_TRAILING_WHITESPACE、ERROR_STRIP_TRAILING_WHITESPACE：去除标准输出或错误输出的末尾空白字符。
1. **message**([ *mode* ] <"*msg*" ...>)：用于在 CMake 构建过程中输出消息或变量的值。
    - mode:
        - STATUS：输出一条状态消息，通常用于显示构建过程中的进度信息。
        - WARNING：输出一条警告消息，用于指示潜在的问题或不推荐的用法。
        - AUTHOR_WARNING：输出一条作者警告消息，用于指示重要的问题或不兼容的更改。
        - SEND_ERROR：输出一条错误消息，并停止 CMake 构建过程。
        - FATAL_ERROR：输出一条致命错误消息，并终止 CMake 构建过程。
1. **set**(*variable* <*value* ...> [ CACHE *type* "desc" [ FORCE ] ])：用于设置变量的值。
    - *variable*：是要设置的变量名。
    - *value*：是要为变量设置的值。可以将字符串、数字、布尔值等作为变量的值。
    - CACHE：参数是可选的，用于将变量设置为缓存变量。缓存变量的值可以在 CMake 构建过程中通过命令行或图形界面进行配置，并且会被保存在 CMake 缓存中，以便下次构建时使用。
    - *type*：参数用于指定缓存变量的类型，常用的类型有 STRING、BOOL、PATH 等。
    - desc：参数是可选的，用于提供关于变量的描述文本，通常用于生成文档或显示帮助信息。
    - FORCE：参数是可选的，用于强制设置变量的值，即使变量已经被设置过。
    - `set(CMAKE_CXX_STANDARD 20)`
    - `set(CMAKE_C_STANDARD 18)`
    - `set(BUILD_SHARED_LIBS ON)`
    - `set(THREADS_PREFER_PTHREAD_FLAG TRUE)`：在链接线程库时优先使用 -pthread 标志。
    - `set(CMAKE_THREAD_PREFER_PTHREAD TRUE)`：寻找线程库时优先选择 Pthreads（POSIX threads）。
    - `set(CMAKE_CXX_STANDARD_REQUIRED ON)`：编译器必须支持该语言标准，否则不构建。
    - `set(CMAKE_CXX_EXTENSIONS OFF)`：表示禁用编译器特定的扩展，这可以确保代码具有更好的可移植性。
1. **include**(*filename*)：用于包含其他 CMake 脚本的命令，将其中的命令和定义应用到当前的 CMake 上下文中。
    - `include(ExternalProject)`：使用 CMake 的 ExternalProject 模块。
1. **add_library**(*library_name* [ SHARED | STATIC | MODULE ] *source1* [ *source2* ... ])：用于添加一个库到项目中。
    - SHARED、STATIC、MODULE是库的类型，可以是SHARED（共享库），STATIC（静态库）或MODULE（模块库）。这个参数是可选的，如果不提供，CMake 会根据 BUILD_SHARED_LIBS 变量的值来决定库的类型。
1. **string**(*command* *output_variable* [ *input_string* ... ])：用于处理字符串的命令，它提供了多种字符串操作和转换的功能。
    - command：是要执行的字符串操作命令。
        - LENGTH：获取字符串的长度。
        - SUBSTRING：提取字符串的子串。
        - FIND：在字符串中查找子串。
        - REPLACE：替换字符串中的子串。
        - TOUPPER：将字符串转换为大写。
        - TOLOWER：将字符串转换为小写。
        - COMPARE：比较两个字符串。
    - output_variable：是用于存储结果的变量名。
    - input_string：是要操作的输入字符串。
    - `string(TIMESTAMP BUILT_TIME "%Y-%m-%d %H:%M:%S")`
    - `string(REPLACE "/MD" "/MT" ${CompilerFlag} "${${CompilerFlag}}")`
1. **add_definitions**(-D*macro1*=*var* -D*macro2*=*var* ...)：用于向编译器添加预定义的宏定义。指定的宏定义传递给编译器，以便在编译过程中进行预处理。
1. **add_executable**(*target* [ WIN32 ] [ MACOSX_BUNDLE ] [ EXCLUDE_FROM_ALL ] *source1* [ *source2* ... ])：用于创建一个可执行文件目标。
1. **target_link_libraries**(*target* *library1* [ *library2* ... ])：用于将目标与一个或多个库进行链接。library 可以是库的目标名称、库文件的绝对路径或库的别名。
1. **find_package**()：用于加载和使用外部项目（通常是库）的命令。
1. **include_directories**([ AFTER | BEFORE ] [ SYSTEM ] *dir1* [ *dir2* ... ])：向编译器添加包含目录。AFTER 表示将目录添加到已有的包含目录之后，BEFORE 表示将目录添加到已有的包含目录之前。默认情况下，目录会被添加到已有的包含目录之后。SYSTEM 是可选的参数，用于指定添加的目录是系统级别的目录。
1. **add_subdirectory**(*path*)：指明包含子目录，子目录的 CMkaeLists.txt 也会被处理。
1. **list**(*command* <*args* ...>)：用于对列表进行操作和管理
    - command：
        - LENGTH：获取列表的长度。
        - GET：获取列表中指定索引位置的元素。
        - SET：设置列表中指定索引位置的元素的值。
        - APPEND：将元素添加到列表的末尾。
        - INSERT：在列表的指定索引位置插入元素。
        - REMOVE_AT：从列表中删除指定索引位置的元素。
        - FIND：在列表中查找指定值的索引位置。
        - SORT：对列表进行排序。
        - REVERSE：反转列表的顺序。
    - `list(APPEND LIB_LIST wsock32 ws2_32)`
1. **file**(*command* <*args* ...>)：用于对文件和目录进行操作和管理。
    - command：可以是以下一些常用的文件操作命令。
        - GLOB：获取匹配指定模式的文件列表。
        - GLOB_RECURSE：递归获取匹配指定模式的文件列表。
        - READ：读取文件内容到变量。
        - WRITE：将变量内容写入文件。
        - APPEND：将变量内容追加到文件末尾。
        - MAKE_DIRECTORY：创建目录。
        - REMOVE：删除文件或目录。
        - RENAME：重命名文件或目录。
        - COPY：复制文件或目录。
        - TO_CMAKE_PATH：将路径转换为 CMake 格式。
        - TO_NATIVE_PATH：将路径转换为本地格式。
    - `file(GLOB SRC common/*.cpp ./*.cpp common/msvc-posix-compat/src/getopt.c)`
1. **aux_source_directory**(*path* *var*)：将文件夹下文件名保存在变量中。
1. **find_path**(*variable*  
    NAMES *head.h*  
    PATHS *path*  
    NO_DEFAULT_PATH)：用于查找指定文件或目录的路径，路径储存到变量中 *variable*。NO_DEFAULT_PATH 确保只在 PATHS 下搜索。
1. **ExternalProject_Add**(*name*  
    PREFIX *value*  
    GIT_REPOSITORY *value*  
    GIT_TAG *value*  
    CONFIGURE_COMMAND *value*  
    BUILD_COMMAND *value*  
    CMAKE_ARGS *value*  
    INSTALL_COMMAND *value*)：用于在构建过程中添加外部项目作为依赖。
1. **FetchContent_Declare**(*name* GIT_REPOSITORY *value*)
1. **FetchContent_MakeAvailable**(*name*)：用于下载和构建在 CMake 项目中声明的内容。
1. **find_library**(*var*  
    NAMES *file*  
    PATHS *path*  
    NO_DEFAULT_PATH)：用于查找指定库文件的路径，路径储存到变量中 *variable*。
1. 分支语句：
    ```cmake
    if( ... )
    ...
    elseif ( ... )
    ...
    else()
    ...
    endif()
    ```
1. 判断表达式：
    ```cmake
    EQUAL
    STREQUAL
    NOT DEFINED
    AND
    MATCHES
    VERSION_LESS
    ```
1. 循环语句：
    ```cmake
    foreach(v list_var)
    ...
    endforeach()
    ```

# 变量

1. CMAKE_C_COMPILER
1. CMAKE_CXX_COMPILER
1. CMAKE_CXX_COMPILER_VERSION
1. CMAKE_SYSTEM_NAME
1. CMAKE_BINARY_DIR
1. CMAKE_SOURCE_DIR
1. CMAKE_SIZEOF_VOID_P：表示指针类型（void*）的大小，以字节为单位。

# 笔记

1. 构建过程
    ```shell
    mkdir build
    cd build
    cmake -DCMAKE_INSTALL_PREFIX=xxx -DCMAKE_BUILD_TYPE=Release  ..
    cmake -S /src/path -B /generate/file/path -G 'Unix Makefiles'
    cmake --build . --target install --config Release
    make -j
    make install
    ```