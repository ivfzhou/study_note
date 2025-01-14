# C++ 笔记

1. 参考网站：cppreference.com。
1. 逗号运算符表达式取右值。
1. `switch case` 值只能作用于整形常量。
1. 函数返回值不能是数组。
1. `const` 值不能赋值给非 `const` 指针，非 `const` 指针不能赋值给 `const` 指针。
    ```c++
    const int var = 1; int *ptr = &var;
    const int **pptr; int *ptr; pptr = &ptr;
    ```
1. 不允许 `main` 函数递归。
1. `auto` 类型自动推断不能用于初始化列表。
1. 内联函数不能递归。
1. 引用变量需要在声明时初始化。`const` 指针也是如此。
1. 外部变量、静态变量有默认值，为零值。
1. 函数参数有默认值的必须都写在最右边。
1. 类中成员变量不能在声明时初始化。
1. 作用域：程序级、文件级、函数级、块级。
1. `int` 长度不小于 `short`，`short` 至少 16 位，`long` 至少 32 位，且不小于 `int`，`long long` 至少 64 位，且不小于 `long`。`float` 至少 32 位，`double` 至少 48 位，且不少于 `float`，`long double` 至少和 `double` 一样多。
1. 最派生类调用虚基类构造函数，其它派生类不再调用了。
1. 成员初始化列表中，虚基类优先执行。
1. 基类构造函数的执行顺序取决于继承定义顺序。
1. 先执行派生类析构再执行基类的。
1. 使用 `new[]` 创建的对象数组，类中必须要定义默认构造函数。
1. 构造函数和析构函数中的虚函数使用静态联编。
1. 引用形参为 const 时，允许实参为临时变量。不是左值，和需要类型转换的会生成临时变量。
1. 函数选择中，非const指针引用和非模板的优先。
1. 初始化列表的执行顺序取决于项目在类中的声明顺序，如果类中有成员作为另一个成员的初始化部分，这个执行顺序就很重要。
1. `const_cast<>` 去除变量的 const，但不能改变真 const 变量的值。
1. lambda 表达式，匿名函数：`[](){}`。`[]`：捕获列表
    - `[]`：不捕获任何变量。
    - `[=]`：以值方式捕获所有外部变量（即复制所有外部变量）。
    - `[&]`：以引用方式捕获所有外部变量（即创建所有外部变量的引用）。
    - `[var]`：以值方式捕获外部变量 var。
    - `[&var]`：以引用方式捕获外部变量 var。
    - `[this]`：捕获当前对象（在类成员函数中）。
    - `[=, &var]`或`[&, var]`：混合捕获。前者以值方式捕获所有变量，但是以引用方式捕获 var；后者以引用方式捕获所有变量，但是以值方式捕获 var。

# C/C++ 标准

- C 语言标准
    - K&R
    - ANSI、C89、C90：ISO/IEC 9899:1990
    - C99：ISO/IEC 9899:1999
    - C11：ISO/IEC 9899:2011
    - C18：ISO/IEC 9899:2018
- C++ 语言标准
    - C++98：ISO/IEC 14882:1998
    - C++03：ISO/IEC 14882:2003
    - C++11：ISO/IEC 14882:2011
    - C++14：ISO/IEC 14882:2014
    - C++17：ISO/IEC 14882:2017
    - C++20：ISO/IEC 14882:2020

# C++ 运算符

| 运算符 | 结合性 | 含义 |
|:-:|:-:|:-|
| 优先级 1 组 |
| `::` | | 作用域解析运算符 |
| 优先级 2 组 |
| `(表达式)` | | 分组 |
| `()` | L-R | 函数调用 |
| `()` | | 值构造，即 type(expr) |
| `[]` | | 数组下标 |
| `->` | | 间接成员运算符 |
| `.` | | 直接成员运算符 |
| `const_cast` | | 专用的类型转换 |
| `dynamic_cast` | | 专用的类型转换 |
| `reinterpret_cast` | | 专用的类型转换 |
| `static_cast` | | 专用的类型转换 |
| `typeid` | | 类型标识 |
| `++` | | 加一运算符，后缀 |
| `--` | | 减一运算符，后缀 |
| 优先级 3 组，全是一元运算符 |
| `!` | R-L | 逻辑非 |
| `~` | | 位非 |
| `+` | | 一元加号，正号 |
| `-` | | 一元减号，负号 |
| `++` | | 加一运算符，前缀 |
| `--` | | 减一运算符，前缀 |
| `&` | | 地址 |
| `*` | | 解除引用，间接值 |
| `()` | | 类型转换，即 type(expr) |
| `sizeof` | | 长度，以字节为单位 |
| `new` | | 动态分配内存 |
| `new[]` | | 动态分配数组 |
| `delete` | | 动态释放内存 |
| `delete[]` | | 动态释放数组 |
| 优先级 4 组 |
| `.*` | L-R | 成员解除引用 |
| `->*` | | 间接成员解除引用 |
| 优先级 5 组，全是二元运算符 |
| `*` | L-R | 乘 |
| `/` | | 除 |
| `^` | | 模，余数 |
| 优先级 6 组，全是二元运算符 |
| `+` | L-R | 加 |
| `-` | | 减 |
| 优先级 7 组 |
| `<<` | L-R | 左移 |
| `>>` | | 右移 |
| 优先级 8 组 |
| `<` | L-R | 小于 |
| `<=` | | 小于或等于 |
| `>=` | | 大于或等于 |
| `>` | | 大于 |
| 优先级 9 组 |
| `==` | L-R | 等于 |
| `!=` | | 不等于 |
| 优先级 10 组，一元运算符 |
| `&` | L-R | 按位 AND |
| 优先级 11 组 |
| `^` | L-R | 按位 XOF，异或 |
| 优先级 12 组 |
| `\|` | L-R | 按位 OR |
| 优先级 13 组 |
| `&&` | L-R | 逻辑 AND |
| 优先级 14 组 |
| `\|\|` | L-R | 逻辑 OR |
| 优先级 15 组 |
| `:?` | R-L | 条件 |
| 优先级 16 组 |
| `=` | R-L | 简单赋值 |
| `*=` | | 乘并赋值 |
| `/=` | | 乘并赋值 |
| `%=` | | 乘并赋值 |
| `+=` | | 乘并赋值 |
| `&=` | | 按位 AND 并赋值 |
| `^=` | | 按位 XOR 并赋值 |
| `\|=` | | 按位 OR 并赋值 |
| `<<=` | | 左移并赋值 |
| `>>=` | | 右移并赋值 |
| 优先级 17 组 |
| `throw` | L-R | 引发异常 |
| 优先级 18 组 |
| `,` | L-R | 将两个表达式合并成一个 |

# C++ 成员函数属性

| 函数 | 能够继承 | 成员还是友元 | 默认能否生成 | 能否为虚函数 | 是否可以有返回值类型 |
|:-:|:-:|:-:|:-:|:-:|:-:|
| 构造函数 | 否 | 成员 | 能 | 否 | 否 |
| 析构函数 | 否 | 成员 | 能 | 能 | 否 |
| = | 否 | 成员 | 能 | 能 | 能 |
| & | 能 | 任意 | 能 | 能 | 能 |
| 转换函数 | 能  | 成员 | 否 | 能 | 否 |
| () | 能 | 成员 | 否 | 能 | 能 |
| [] | 能 | 成员 | 否 | 能 | 能 |
| -> | 能 | 成员 | 否 | 能 | 能 |
| op= | 能 | 任意 | 否 | 能 | 能 |
| new | 能 | 静态成员 | 否 | 否 | void* |
| delete | 能 | 静态成员 | 否 | 否 | void |
| 其他运算符 | 能 | 任意 | 否 | 能 | 能 |
| 其他成员 | 能 | 成员 | 否 | 能 | 能 |
| 友元 | 否 | 友元 | 否 | 否 | 能 |

# C++ 继承方式

|特征|公有继承|保护继承|私有继承|
|:-:|:-:|:-:|:-:|
|公有成员变成|派生类的公有成员|派生类的保护成员|派生类的私有成员|
|保护成员变成|派生类的保护成员|派生类的保护成员|派生类的私有成员|
|私有成员变成|只能通过基类接口访问|只能通过基类接口访问 | 只能通过基类接口访问 |
|能否隐式向上转换|是|是，只能在派生类中  | 否 |

# C 笔记

1. 语句：标号语句、复合语句、表达式语句、选择语句、迭代语句、跳转语句。
1. 储存类型说明符：_Thread_local、auto、extern、static、register。
1. 类型限定符：const、volatile（代理（而不是变量所在的程序）可以改变该变量的值）、restrict（用于指针，表明该指针是访问数据对象的唯一且初始的方式）、_Atomic。
1. 转义字符：
    - `\a` 警报
    - `\b` 退格
    - `\f` 换页
    - `\n` 换行
    - `\r` 回车
    - `\t` 水平制表符
    - `\v` 垂直制表符
    - `\\` 反斜杠
    - `\'` 单引号
    - `\"` 双引号
    - `\?` 问号
    - `\[0]77` 八进制值
    - `\xff` 十六进制值
1. scanf 输入格式中的空白表示跳过空白寻找下一个字符，%c 除外，%c 前面的空白表示跳过空白，前面的非空白会匹配。
1. const 表示修饰的变量不能通过这个变量修改值，但可以通过别的变量修改。
1. 不允许数组作为一个单元赋值给另一个数组，除初始化以外不允许使用大括号列表形式赋值。变长数组不能初始化
1. short 占用的存储空间不能多于 int，long 占用的存储空间不能少于 int。
1. 使用 `\` 和多对双引号表示字符串断行。
1. 扩展字符：
    - ??= #
    - ??( [
    - ??/ \\
    - ??) ]
    - ??' ^
    - ??< {
    - ??! |
    - ??> }
    - ??- ~
    - <: [
    - :> ]
    - <% {
    - %> }
    - %: #
    - %:%: ##
1. 预定义宏：
    - \_\_DATE__：预处理的日期（"Mmm dd yyyy" 形式的字符串字面量，如 Nov 23 2013）。
    - \_\_FILE__：表示当前源代码文件名的字符串字面量。
    - \_\_LINE__：表示当前源代文件码中行号的整形常量。
    - \_\_STDC__：设置为 1 时，表明实现遵循 C 标准。
    - \_\_STDC_HOSTED__：本机环境设置为 1，否则设置为 0。
    - \_\_STDC_VERSION__：支持 C99 标准设置为 199901L；支持 C11 标准，设置为 201112L。
    - \_\_TIME__：编译代码的时间，格式为 "hh:mm:ss"。
1. fopen 字符串模式：
    - r：以只读模式打开文件。
    - w：以写模式打开文件，把现有文件的长度截断为 0，如果文件不存在，则创建一个新文件。
    - a：以写模式打开文件，在现有文件的末尾添加内容，如果文件不存在，则创建一个新文件。
    - r+：以更新模式打开文件，即可以读写文件。
    - w+：以更新模式打开文件，即读写，如果文件存在，则将文件长度截断为 0；如果不存在，则创建一个新文件。
    - a+：以更新模式打开文件，即读写，在现有文件的末尾添加内容，如果文件不存在，则创建一个新文件；可以读整个文件，但只能在末尾添加内容。
    - rb、wb、ab、ab+、a+b、wb+、w+b：与上面模式类似，但是以二进制模式而不是文本模式打开文件。
    - wx、wbx、w+x、wb+x、w+bx：（C11）类似非 x 模式，但是如果文件已存在或以独占模式打开文件，则打开文件失败。
1. 输出占位符格式：%[flags][width][.precision][length]specifier。
1. 输入占位符格式：%[*][width][length]specifier。
1. `include <string.h>`，尖括号表示存放标准头文件的位置去查找文件。双引号表示在工作目录下查找文件。
1. 变量和标记的名称使用了不同的共享名称空间。
    ```c
    struct rect { double x; double y; };
    int rect; // 在C中不会产生冲突。
    ```
1. 复杂的声明：
    ```c
    int *arr[10] // 数组，每个元素为 int 指针，[] 的优先级比 * 高。
    int (*arr)[10] // 指针，指向数组，数组元素为 int。
    int *arr[3][4] // 数组，每个元素为数组，该数组的元素又是 int 指针。
    int (*arr)[3][4] // 指针，指向数组。
    int (*arr[3])[4] // 数组，元素为数组，该数组元素为 int 指针。
    int ((*arr)[3])[4] // 数组，元素为指针，该指针指向数组。
    char *func(int) // 函数，返回 char 指针的函数。
    char (*func)(int) // 指针，指向函数，该函数的返回类型为 char。
    char (*func[3])(int) // 数组，内含 3 个指针，每个指针都指向返回类型为 char 的函数。
    int arr[static const restrict 20]
    void (*func)(char *); // func 是一个指向函数的指针。
    ```
1. 数字字面量：
    ```c
    int binary = 0B111; // 0b111
    int octal = 0777;
    int hexadecimal = 0Xf; // 0xf
    double exponent = 1e2; // 1E2
    double exponent1 = 0x1p1; // 0x1P1
    ```
1. 字符字面量：
    ```
    \a \b \f \n \r \t \v \' \" \\
    \0-\177 \x00-\x7f
    ```

# C 关键字

auto、extern、short、while、break、float、case、for、sizeof、char、goto、static、if、struct、continue、inline、switch、default、int、typedef、do、long、union、double、register、unsigned、else、restrict、void、return  
C90 新增：signed、const、enum、volatile  
C11 新增：_Alignas、_Alignof、_Atomic、_Bool、_Complex、_Generic、_Imaginary、_Noreturn、_Static_assert、_Thread_local

# C 整数浮点数尾部表示

- unsigned int 无符号整数 U
- unsigned long 无符号长整数 UL
- unsigned long long 无符号长长整数 ULL
- long 长整数或者长双精度浮点数 L
- long long 长长整数 LL
- float 单浮点数 F

# C 运算符

| 运算符，优先级从高到低 | 结合律 |
|:-|:-:|
| `++后缀`、`--后缀`、`()函数调用`、`[]`、`{}复合字面量`、`.`、`->` | L-R |
| `++前缀`、`--前缀`、`-`、`+`、`~`、`!`、`*解引用`、`&取址`、`sizeof`、`_Alignof(类型名)` 都是一元运算符 | R-L |
| `(类型名)` | R-L |
| `*`、`/`、`%` | L-R |
| `+`、`-` 都是二元运算符 | L-R |
| `<<`、`>>` | L-R |
| `<`、`>`、`>=`、`<=` | L-R |
| `==`、`!=` | L-R |
| `&` | L-R |
| `^` | L-R |
| `|` | L-R |
| `&&` | L-R |
| `||` | L-R |
| `?:` | R-L |
| `=`、`*=`、`/=`、`+=`、`-=`、`<<=`、`>>=`、`&=`、`|=`、`^=` | R-L |
| `,` | L-R |

# C 存储类别

| 存储类别 | 存储器 | 作用域 | 链接 | 如何声明 |
|:-|:-|:-|:-|:-|
| 自动 |自动 | 块 | 无 | 在块中 |
| 寄存器 | 自动 | 块 | 无 | 在块中，使用 register |
| 静态、外部链接 | 静态 | 文件 | 外部 | 在所有函数外部 |
| 静态，内部链接 | 静态 | 文件 | 内部 | 在所有函数外部，使用关键字 static |
| 静态，无链接 | 静态 | 块 | 无 | 在块中，使用关键字 static |
| 线程，外部链接 | 线程 | 文件 | 外部 | 在所有块外部，使用关键字 _Thread_local |
| 线程，内部链接 | 线程 | 文件 | 内部 | 在所有块外部，使用关键字 _Thread_local 和 static |
| 线程，无链接 | 线程 | 块 | 无 | 在块中，使用关键字 static 和 _Thread_local |

# C 标准头文件

1. C89/C90 标准头文件
    - <assert.h> - 断言宏
    - <ctype.h> - 字符类型函数
    - <errno.h> - 错误码
    - <float.h> - 浮点数特性
    - <limits.h> - 整数特性
    - <locale.h> - 本地化
    - <math.h> - 数学函数
    - <setjmp.h> - 非本地跳转
    - <signal.h> - 信号处理
    - <stdarg.h> - 可变参数列表
    - <stddef.h> - 常用类型和宏
    - <stdio.h> - 标准输入输出
    - <stdlib.h> - 通用工具函数
    - <string.h> - 字符串处理
    - <time.h> - 时间和日期函数
1. C99 标准新增头文件
    - <complex.h> - 复数运算
    - <fenv.h> - 浮点环境
    - <inttypes.h> - 格式化输入输出
    - <stdbool.h> - 布尔类型和值
    - <stdint.h> - 定宽整数类型
    - <tgmath.h> - 泛型数学函数
1. C11 标准新增头文件
    - <stdalign.h> - 对齐支持
    - <stdatomic.h> - 原子操作
    - <stdnoreturn.h> - noreturn 函数
    - <threads.h> - 线程支持
    - <uchar.h> - Unicode 字符类型

# C++ 标准头文件

1. C++98/C++03 标准头文件
    - <algorithm> - 常用算法
    - <bitset> - 位集容器
    - <complex> - 复数类
    - <deque> - 双端队列容器
    - <exception> - 异常处理类
    - <fstream> - 文件流类
    - <functional> - 函数对象和绑定器
    - <iomanip> - 输入输出操纵器
    - <ios> - 基本输入输出支持
    - <iosfwd> - 输入输出前向声明
    - <iostream> - 标准输入输出流
    - <istream> - 输入流类
    - <iterator> - 迭代器支持
    - <limits> - 数值极限
    - <list> - 双向链表容器
    - <locale> - 本地化支持
    - <map> - 映射容器
    - <memory> - 动态内存管理
    - <new> - 动态内存分配
    - <numeric> - 数值操作
    - <ostream> - 输出流类
    - <queue> - 队列容器
    - <set> - 集合容器
    - <sstream> - 字符串流类
    - <stack> - 栈容器
    - <stdexcept> - 标准异常类
    - <streambuf> - 流缓冲区
    - <string> - 字符串类
    - <typeinfo> - 运行时类型信息
    - <utility> - 通用工具类
    - <valarray> - 数组类
    - <vector> - 动态数组容器
1. C++11 标准新增头文件
    - <array> - 定长数组容器
    - <atomic> - 原子操作
    - <chrono> - 时间库
    - <codecvt> - Unicode 转换工具
    - <condition_variable> - 条件变量
    - <forward_list> - 单向链表容器
    - <future> - 异步操作支持
    - <initializer_list> - 初始化列表
    - <mutex> - 互斥锁
    - <random> - 随机数库
    - <ratio> - 编译时有理数算术
    - <regex> - 正则表达式
    - <scoped_allocator> - 作用域分配器
    - <system_error> - 系统错误支持
    - <thread> - 线程支持
    - <tuple> - 元组类
    - <typeindex> - 类型索引
    - <type_traits> - 类型特性
    - <unordered_map> - 无序映射容器
    - <unordered_set> - 无序集合容器
1. C++17 标准新增头文件
    - <any> - 类型安全的通用容器
    - <charconv> - 字符串转换
    - <filesystem> - 文件系统库
    - <memory_resource> - 多态内存资源
    - <optional> - 可选值
    - <string_view> - 字符串视图
    - <variant> - 类型安全的联合体
1. C++20 标准新增头文件
    - <barrier> - 屏障同步
    - <bit> - 位操作
    - <compare> - 三向比较
    - <concepts> - 概念
    - <coroutine> - 协程支持
    - <latch> - 闭锁同步
    - <numbers> - 数学常量
    - <ranges> - 范围库
    - <semaphore> - 信号量