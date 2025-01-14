# 命令

1. list, l 行号 显示源代码，默认显示十行代码。
1. break, b 行号 设置断点。
1. delete, d 序号 删除断点。
1. info breakpoints 列出设置的断点信息。
1. backtrace, bt 打印代码执行过程。
1. info locals 显示当前执行中程序的变量值。
1. info goroutines 显示当前执行的协程列表。
1. print, p 变量名... 打印变量信息，可以使用$len()、$cap()。
1. next, n 单步调试，下一步。
1. continue, c 次数 跳到下一个指定次数断点。
1. set 变量名=值 程序执行中改变值。
1. stepi 进入该行代码执行。
1. quit 退出。
1. run 执行程序。
