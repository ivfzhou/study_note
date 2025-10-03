# 一、命令

1. <list | l> *行号*：显示源代码，默认显示十行代码。
1. <break | b> *行号*：设置断点。
1. <delete | d> *序号*：删除断点。
1. info breakpoints：列出设置的断点信息。
1. <backtrace | bt>：打印代码执行过程。
1. info locals：显示当前执行中程序的变量值。
1. info goroutines：显示当前执行的协程列表。
1. <print | p> *变量名*...：打印变量信息，可以使用 $len()、$cap()。
1. <next | n>：单步调试，下一步。
1. <continue | c> *次数*：跳到下一个指定次数断点。
1. set *变量名*=*值*：程序执行中改变值。
1. step：进入该行代码执行。
1. quit：退出。
1. run：执行程序。

# 二、使用

1. gdb ./main

1. break main：在 main 函数处设置断点。

1. break *filename.c*:*line_number*：设置断点。

1. run：开始运行。

1. run *arg1* *arg2*：给 main 添加启动参数。

1. next：下一行代码。

1. continue：到下一个断点。

1. step：进入函数。

1. print *variable*：打印变量。

1. backtrace：打印堆栈。

1. list：显示源码。

1. quit：退出。

   
