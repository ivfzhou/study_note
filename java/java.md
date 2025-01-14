# 笔记

1. 将源码转成 HTML 格式文档：
   ```shell
   cd <openjdk22>
   export MODULEPATH=$JAVA_HOME/jmods
   export JAVA_TOOL_OPTIONS='-Duser.language=en -Duser.country=US'
   javadoc --module-source-path ./src/ -d ./docs/ -charset utf8 -docencoding utf8 -locale en_US --module java.base,java.compiler,java.datatransfer,java.desktop,java.instrument,java.logging,java.management,java.management.rmi,java.naming,java.net.http,java.prefs,java.rmi,java.scripting,java.se,java.security.jgss,java.security.sasl,java.smartcardio,java.sql,java.sql.rowset,java.transaction.xa,java.xml,java.xml.crypto,jdk.accessibility,jdk.attach,jdk.charsets,jdk.compiler,jdk.crypto.cryptoki,jdk.crypto.ec,jdk.crypto.mscapi,jdk.dynalink,jdk.editpad,jdk.graal.compiler,jdk.graal.compiler.management,jdk.hotspot.agent,jdk.httpserver,jdk.incubator.vector,jdk.internal.ed,jdk.internal.jvmstat,jdk.internal.le,jdk.internal.opt,jdk.internal.vm.ci,jdk.jartool,jdk.javadoc,jdk.jcmd,jdk.jconsole,jdk.jdeps,jdk.jdi,jdk.jdwp.agent,jdk.jfr,jdk.jlink,jdk.jpackage,jdk.jshell,jdk.jsobject,jdk.jstatd,jdk.localedata,jdk.management,jdk.management.agent,jdk.management.jfr,jdk.naming.dns,jdk.naming.rmi,jdk.net,jdk.nio.mapmode,jdk.random,jdk.sctp,jdk.security.auth,jdk.security.jgss,jdk.unsupported,jdk.unsupported.desktop,jdk.xml.dom,jdk.zipfs
   ```
1. switch 作用对象为 byte short int char enum String。
1. 方法重载：同一个类中，方法名字相同，参数列表不同。参数个数不同，参数数据类型不同，参数顺序不同。
1. 方法重写：方法名、参数列表、返回值类型（兼容）和父类相同。
1. this() super() 只能在构造方法中第一行语句。
1. 面向对象编程的七个原则：
    - 开闭原则：软件实体应当对扩展开放，对修改关闭。
    - 单一职责：一个类应该有且仅有一个引起它变化的原因，否则类应该被拆分。
    - 最少知道：如果两个软件实体无须直接通信，那么就不应当发生直接的相互调用，可以通过第三方转发该调用。
    - 里氏替换：继承必须确保超类所拥有的性质在子类中仍然成立。
    - 接口隔离：客户端不应该被迫依赖于它不使用的方法，一个类对另一个类的依赖应该建立在最小的接口上。
    - 依赖倒置：高层模块不应该依赖低层模块，两者都应该依赖其抽象；抽象不应该依赖细节，细节应该依赖抽象。
    - 合成复用：尽量先使用组合或者聚合等关联关系来实现，其次才考虑使用继承关系来实现。
1. 类之间关系：依赖、关联、聚合、组合、泛化、实现。
1. 添加信任证书 keytool -importcert -trustcacerts -alias ivfzhou -file ~/ivfzhou.pub -keystore lib/security/cacerts -cacerts
1. synchronied 锁升级
    - 偏向锁 -> 自旋锁 -> 重量级锁。
    - 如果异常，synchronied 会自动释放锁。
1. volatile 保证变量在线程间的可见性。
1. SpringFrame IoC/DI、AOP：解耦。
1. CGLib（ASM）Enhancer 无法代理 final 类，因为无法被继承。

# 注解

// 单行注解。  
/**/ 多行注解。  
/** @author @version @since @see @param @return
\*/ 文档注释。

# 关键字

abstract boolean break byte case catch char class const continue default do double else extends final finally float for goto if implements import instanceof int interface long native new package private protected public return short static strictfp super switch synchronized this throw throws transient try viod volatile while assert enum

# 数据类型

1. byte：1 个字节，范围-128 到 127。
1. short：2 个字节，范围-32768 到 32767。
1. int：4 个字节。-2147483648 到 2147483647。
1. long：8 个字节。-9223373036854775808 到 9223373036854775807。
1. float：4 个字节，-3.40E38 到+3.40E38，有效位数 7-8 位。
1. double：8 个字节，-1.79E308 到+1.79E308，有效位数 15-16 位。
1. char：2 个字节，0-65535，65536 个字符。
1. boolean：不确定。

# 引用类型

类、接口、数组、枚举、注解、String。

# 转义字符

1. n 换行。
1. r 回车。
1. t 水平制表。
1. ' 单引号。
1. " 双引号。
1. \ 斜杠。

#  运算符

1. 算术运算符：+ 、 - 、 * 、 / 、 % 、 ++ 、 --
1. 赋值运算符：= 、 += 、 -= 、 *= 、 /= 、 %=
1. 关系运算符：> 、 < 、 >= 、 <= 、 == 、 !=
1. 逻辑运算符：! 、 & 、 | 、 ^ 、 && 、 ||
1. 位运算符：& 、 | 、 ^ 、 ~ 、 >> 、 << 、 >>>
1. 字符串连接运算符：+
1. 三目运算符：?:

# 访问修饰符

1. public：本类、同包类、不同包子类、不同包类。
1. protected：本类、同包类、不同包子类。
1. default：本类、同包类。
1. private：本类。
1. 类：public 可不写。
1. 类成员：都有。
1. 局部变量：都没有。

# 状态修饰符

1. final：类、类成员、局部变量。
1. static：类成员、局部变量。
1. abstract：类、类方法。

# 正则

1. a：字符 a。
1. [abc]：匹配 a 或 b 或 c。
1. [^abc]：任何字符，除了 a、b 或 c（否定）。
1. [a-zA-Z]：a 到 z 或 A 到 Z，两头的字母包括在内（范围）。
1. [a-d[m-p]]：a 到 d 或 m 到 p，[a-dm-p]（并集）。
1. [a-z&&[def]]：d、e 或 f（交集）。
1. [a-z&&[^bc]]：a 到 z，除了 b 和 c，[ad-z]（减去）。
1. [a-z&&[^m-p]]：a 到 z，且非 m 到 p，[a-lq-z]（减去）。
1. (red|blue|green)：查找任何指定的选项。
1. .：任意字符（与行结束符可能匹配也可能不匹配）。
1. \\d：数字 [0-9]。
1. \\D：查找非数字字符。
1. \\w：单词字符 [a-zA-Z_0-9]。
1. \\W：查找非单词字符。
1. \\s：查找空白字符。
1. \\S：查找非空白字符。
1. \\b：匹配单词边界。
1. \\B：匹配非单词边界。
1. \\0：查找 NUL 字符。
1. \\n：查找换行符。
1. \\f：查找换页符。
1. \\r：查找回车符。
1. \\t：查找制表符。
1. \\v：查找垂直制表符。
1. \\*xxx*：查找以八进制数 *xxx* 规定的字符。
1. \\x*dd*：查找以十六进制数 *dd* 规定的字符。
1. \\u*xxxx*：查找以十六进制数 *xxxx* 规定的 Unicode 字符。
1. ^：行开头。
1. $：行结尾。
1. *X*?：一次或 0 次。
1. *X*\*：0 次或多次（包括 1 次）。
1. *X*+：一次或多次。
1. *X*{*n*}：恰好 *n* 次。
1. *X*{*n*,}：至少 *n* 次。
1. *X*{*n*, *m*}：至少 *n* 次,不超过 m 次。
1. ^*n*：匹配任何开头为 *n* 的字符串。
1. *n*$：匹配任何结尾为 *n* 的字符串。
1. ?=*n*：匹配任何其后紧接指定字符串 *n* 的字符串。
1. ?!*n*：匹配任何其后没有紧接指定字符串 *n* 的字符串。
1. i：执行对大小写不敏感的匹配。
1. g：执行全局匹配（查找所有匹配而非在找到第一个匹配后停止）。
1. m：执行多行匹配。

# java 命令参数

1. --version 查看版本。
1. -jar *包路径*... arg1 arg2 arg3 运行 jar 包。
1. -javaagent
1. -Dfile.encoding=UTF-8 指定字符编码。
1. -classpath *包路径*;*包路径*...
1. -Xms500m 堆初始内存。
1. -Xmx3000m 堆最大内存。
1. -Xss2m 栈空间。

# 成员变量和局部变量的区别

1. 定义的位置不同
    - 成员变量：定义于类中，作用于整个类。
    - 局部变量：定义于方法或者语句中，作用于该方法或者该语句。
1. 内存中出现的时间和位置不同
    - 成员变量：当对象被创建时，出现在堆内存当中。
    - 局部变量：所属的区间被运算时，出现在栈内存当中。
1. 生命周期不同
    - 成员变量：随着对象的出现而出现，随着对象的消失而消失。
    - 局部变量：随着所属区间运算结束，它就被释放。
1. 初始化值不同
    - 成员变量：成员变量因为在堆内存当中，所以他有默认的初始值
    - 局部变量：没有默认的初始值
1. 成员变量和局部变量名字相同，局部变量优先级高。就近原则

# 内部类

1. 成员内部类
    - 成员不能是 static。
    - 可访问外部类成员，Outer.this/super
1. 静态成员内部类：相当于外部类。
1. 局部内部类：可访问外部类，可访问 final 局部变量。
1. 匿名内部类：在 new 时定义类成员。new Interface(){ implements }。

# 设计模式：

1. 创建型模式
工厂方法，抽象工厂，建造器，原型，单例
1. 结构型模式
适配器，桥接，组合，装饰器，外观，代理，享元
1. 行为模式
责任链，命令，迭代器，中介者，备忘录，观察者，状态，策略，模板方法，访问者
