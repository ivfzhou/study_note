# 笔记

- python -h 显示帮助信息。
- python {Enter} 进入交互式编程模式。
- pyhton 环境变量，PYTHONPATH 表示 import 的模块的搜索路径，PYTHONSTARTUP 表示启功python 时先运行的代码。PYTHONCASEOK 表示 import 模块名不区分大小写，PYTHONHOME 模块搜索路径。
- 使用换行和分号作为代码结束符，使用缩进区分代码块。
- pip 路径：./Scripts。
- 导入模块：import *module*，导入函数：from *module* import *func*，导入多个函数：from *module* import *func*,*func0*，全部导入：from *module* import *。
- 标准数据类型：int、float、bool、complex、str、list、tuple、set、dict。
- 单行注释 #，多行注释 """ 或者 '''。
- 可以同行多变量赋值。
- 变量弱类型，可以赋值不同类型的值。
- 运算符 / 返回浮点数，// 返回整数。
- \__name__ 等于 \__main__ 表示不是被导入的运行。
- 文件夹下包含文件名为 \__init__.py 才会被认为一个包。
- 类中成员名以 __ 开头表示私有成员。

# pip 下载代理源配置

`pip config set global.index-url https://pypi.tuna.tsinghua.edu.cn/simple`  
文件路径 [pip.ini](./pip.ini)：C:\Users\you\AppData\Remoting\pip\pip.ini  
清华：https://pypi.tuna.tsinghua.edu.cn/simple  
阿里云：http://mirrors.aliyun.com/pypi/simple  
中国科技大学 https://pypi.mirrors.ustc.edu.cn/simple  
华中理工大学：http://pypi.hustunique.com/  
山东理工大学：http://pypi.sdutlinux.org  
豆瓣：http://pypi.douban.com/simple  

- 卸载 pip：`python.exe -m pip uninstall pip`
- 安装 pip：`python.exe -m ensurepip --default-pip`
- 下载依赖：`pip install pywinauto`

# 运算符

- 算数运算符 `+ - * / // ** %`
- 比较运算符 `== != > < >= <= and or not`
- 位运算符 `& | ^ ~按位取反 << >>`
- 赋值运算符 `= += -= /= *= //= %= **=`
- 成员运算符 `in not in`
- 身份运算符 `is is not `
- 优先级
    - `(expressions...), [expressions...], {key: value...}, {expressions...}` 圆括号的表达式
    - `x[index], x[index:index], x(arguments...), x.attribute` 读取，切片，调用，属性引用
    - `await x`	await 表达式
    - `**` 乘方(指数)
    - `+x, -x, ~x` 正，负，按位非 NOT
    - `*, @, /, //, %` 乘，矩阵乘，除，整除，取余
    - `+, -` 加和减
    - `<<, >>` 移位
    - `&` 按位与 AND
    - `^` 按位异或 XOR
    - `|` 按位或 OR
    - `in,not in, is,is not, <, <=, >, >=, !=, ==` 比较运算，包括成员检测和标识号检测
    - `not x` 逻辑非 NOT
    - `and` 逻辑与 AND
    - `or` 逻辑或 OR
    - `if -- else` 条件表达式
    - `lambda` lambda 表达式
    - `:=` 赋值表达式
