# 笔记

1. `make -f makefile -C dir target`
1. 依赖文件的修改时间大于目标文件的修改时间，或者目标项文件不存在，就执行该目标。依赖可以是其他目标。
1. `#` 号开头为注释。
1. `.ONESHELL:`，目标变成在一个 shell 环境下运行，使用换行符 `\` 或者分号 `;` 分割命令能达到同样的效果。
1. `.RECIPEPEFIX = >`，命令开头的 tab 换成 `>`。
1. 命令和注释前加上 `@` 可以关闭目标在执行时打印执行的命令。
1. 命令中 `*` 号通配符匹配所有。
1. 目标和依赖中的 `%` 号表示模式匹配。
1. 设置变量值：`var=val`。命令中使用 `$(var)` 获取 val。shell 变量需要两个 `$$` 获取。
1. `var=val` 在执行时扩展，动态扩展，允许递归扩展。`var:=val` 在定义时扩展，静态扩展。`var?=val` 只有在该变量为空时才设置值。`var+=val` 将值追加到变量的尾端。
1.	内置变量 `$(CC)`、`$(MAKE)`。
1.	自动变量：`$@` 指目标，`$<` 指第一个依赖，`$?` 指比目标更新的依赖，`$^` 指所有依赖，`$*` 指模式匹配 `%` 部分，`$(@D)` 和 `$(@F)` 指目标的文件夹和文件名，`$(<D)` 和 `$(<F)` 指第一个依赖的文件夹和文件名。
1.	分支语句：`ifeq ($(CC), cc) ... else ... endif`。
1.	`var=v1 v2 v3`，数组变量。
1.	循环语句：`for i in var; do ...; done`。`for i in v1 v2 v3; do ...; done`。
1.	函数调用，`$(func args)`，`${func, args}`。
1.	常用函数，`shell` 执行命令，`wildcard` 替换通配符，`subst from,to,text` 替换文本，`patsubst pattern,replace,text` 模式匹配替换，替换后缀名：`$(VAR:.js=.ts)`。

# 语法
    ```Makefile
    目标: 依赖
        命令

    # 默认目标。
    all: 

    # clean 变成伪目标，伪目标不会当作成文件。
    .PHONY: clean all install
    clean: 
        rm -f *.o

    # 所有命令在一个环境下运行。
    .ONESHELL:
    your_target:

    # 命令开始不用 tab 而用 >。
    .RECIPEPEFIX = >
    your_target:

    # 编译所有 .c 文件，生成 .o 文件。
    %.o: %.c
        $(CC) $(CFLAGS) -c $< -o $@

    # 判断语句
    ifeq ($(DEBUG), 1)
        CFLAGS += -g
    else ifeq ()
        ...
    else
        ...
    endif

    # 循环语句。
    for i in var; do \
        ...; \
    done
    for i in v1 v2 v3; do \
        ...; \
    done

    # 函数调用。
    srcfiles := $(shell echo src/{00..99}.txt)
    srcfiles := $(wildcard src/*.txt)
    $(subst ee,EE,feet on the street)
    $(patsubst %.c,%.o,x.c.c bar.c)
    $(error Unsupported platform: $(PLATFORM))
    ```
