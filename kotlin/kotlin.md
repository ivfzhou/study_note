# 笔记

1. https://kotlinlang.org/docs/basic-types.html
1. 不支持八进制字面量。
1. 以下包默认导入：
    ```
    kotlin.*
    kotlin.annotation.*
    kotlin.collections.*
    kotlin.comparisons.*
    kotlin.io.*
    kotlin.ranges.*
    kotlin.sequences.*
    kotlin.text.*

    JVM:
    java.lang.*
    kotlin.jvm.*

    JS:
    kotlin.js.*
    ```
1. 类 val 字段可以被子类重写为 var 字段。
1. 访问修饰符默认为 public，internal 表明只能在相同模块内访问，private 表明只能在声明的文件内访问，protected 不能用于顶层声明。重写的成员默认为父成员访问修饰符。类字段 set 可设置不同的访问修饰符。密封类访问修饰符默认为 protected。
1. 类方法优先于扩展函数，如果两者同名。
1. 密封类和接口的直接子类只能在与密封类相同的模块和包下。

# 语法

1. 变量。Variables
    ```kotlin
    // 可变变量
    var x：Int = 0

    // 只读变量
    val y: Int = 0

    // 省略类型
    val z = 0

    // 使用前初始化
    val m: Int
    m = 0

    // 使用时初始化值。Lazy property
    val n: String by lazy { "" + "" }
    ```
1. 字符串模板。String templates
    ```kotlin
    var x = 1
    println("$x")
    println("${x + 1}")
    println("${x + "1"}")
    ```
1. 基础数据类型。Basic types
    ```kotlin
    Byte, Short, Int, Long
    UByte, UShort, UInt, ULong
    Float, Double
    Boolean
    Char
    String

    val x = 1 // Int
    val x = 1.0 // Double
    val x = 1f // Float
    val x = 1u // UInt
    val x = 1ul // ULong
    val x = 0xff // Int
    val x = 1e1 // Double
    val x = 0b1 // Int

    // 多行字符串
    val str = """
              Hello
              World
              """

    // 键值对数组
    val arr = arrayOf("a" to 59, "b" to 60)
    arr.toMap() // 转成映射
    ```
1. 集合类型。Collections
    ```kotlin
    List, MutableList
    Set, MutableSet
    Map, MutableMap

    // 有序集合
    listOf("a", "b", "c")

    // 去重集合
    setOf("a", "b")

    // 映射
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    println(map["key"])
    for ((k, v) in map) {}
    ```
1. 流程控制。Control flow
    - 条件表达式。Conditional expressions
        ```kotlin
        if (bool) {} else if {} else {}
        if (x !in list) {}

        val z = if (bool) x else y

        when(x) {
            1 -> println(1)
            else -> println()
        }

        when {
            bool -> println()
            else -> println()
        }

        val y = when(x) {
            1 -> 1
            else -> 0
        }
        ```
    - 范围。Ranges
        ```kotlin
        1..5 // 包含 1 和 5
        1..x
        1..x+1
        1..<5 // 不包含 5
        'z' downTo 's'
        1..5 step 2
        (1..10).forEach {}
        ```
    - 循环。Loops
        ```kotlin
        for (v in 1..5) {}

        for (v in string) {}

        for (v in collections) {}

        while(bool) {}

        do{} while(bool)

        label@for() { break@label }
        ```
    - 异常
        ```kotlin
        val result = try {
            1
        } catch (e: ArithmeticException) {
            throw IllegalStateException(e)
        } finally {

        }
        ```
1. 函数。Functions
    ```kotlin
    // 函数
    fun sum(x: Int, y: Int): Int {}

    // 命名参数。Named arguments
    sum(y = 1, x = 3)

    // 默认参数。Default parameter values
    fun sum(x: Int, y: Int = 0, z: Int = 0): Int {}
    sum(1, 1)

    // 单表达式函数。Single-expression functions
    fun sum(x: Int, y: Int) = x + y

    // 扩展类方法。Extension functions
    fun String.spaceToCamelCase() {}
    "".spaceToCamelCase()

    // 结束拉姆达函数，否则返回外面的函数
    listOf(1, 2).forEach lit@{
        return@lit
    }
    listOf(1, 2).forEach {
        return@forEach
    }
    ```
    - 函数返回值默认值类型为 Unit
    - 拉姆达表达式。Lambda expressions
       ```kotlin
       val f = { param: Int -> println(param) }

       // 返回值是函数类型。Function types
       fun sum(): () -> Int {}

       // 尾部拉姆达。Trailing lambdas
       listOf(1, 2, 3).fold(0, { x, item -> x + item })
       listOf(1, 2, 3).fold(0) { x, item -> x + item }
       listOf(1, 2, 3).fold1 { x, item -> x + item }
       listOf(1, 2, 3).fold2 { it }
       ```
    - 变参函数。
        ```kotlin
        fun printAllStrings(vararg strings: String) {}
        val lettersArray = arrayOf("c", "d")
        printAllStrings("a", "b", *lettersArray) // abcd
        ```
1. 类。Classes
    ```kotlin
    // 类，默认 final
    final class Shape

    // 默认类构造
    class Shape public @Inject constructor(name: String)
    class Shape(val x: Int, var y: Int) {}
    val x = Shape(2, 3)
 
    class Shape(override val x: Int = 0) {}
    class Shape {
        val x: Int = 0
        // 默认 final
        final fun f() {
            super<SuperClass>.f()
        }
 
        // 构造函数
        constructor() : this() {}
 
        val x: Int = 0
            get() =
            set(x) {
                field = x
            }
            private set
            @Inject set
 
        // 初始化语句
        init {
 
        }
 
        inner class c {
            func m() {
                super@Shape.f()
            }
        }
 
        const val x = ""
        lateinit var x: Int
    }
    interface MyInterface {}
    class Child : MyInterface {}
 
    // 数据类。Data classes
    data class User(val name: String, val id: Int)
    val u = User("zs", 1)
    u.toString()
    u.equals(u)
    u.copy()
    u.copy(id = 2)
 
    // 单例。singleton
    object Resource {}
 
    // 抽象类
    abstract class C {
        abstract fun m()
    }
 
    // 类继承
    open class Shape
    class Rectangle: Shape()
 
    // 密封类接口
    sealed interface I
    sealed class C {
        class C0: C()
    }
    ```
1. 空类型安全。Null safety
    ```kotlin
    var s: String? = null

    // safe call
    println(s?.length)

    // Elvis operator
    println(s?.length ?: 0) // print 0
    val email = values["email"] ?: throw IllegalStateException("Email is missing!")

    // 智能类型识别
    if (x != null) {
        println(x * x)
    }

    value?.let { println("if not null execute") }
    value?.let { println("is null ${it}") }
    ```
1. 注释。Comments
    ```kotlin
    // 单行注释

    /* 多行注释 */

    /* /* 内嵌注释 */ */
    ```
1. 类型检测和类型自动转换。
    ```kotlin
    if (obj is String) {
        obj.length
    }

    if (obj is String && obj.length > 0) {}

    if (signalStatus is Postponed || signalStatus is Declined) {
        signalStatus.signal()
    }

    // 类型强转
    val x = y as String?

    // 安全强转，失败返回 null
    val x = y as? String
    ```
1. with
    ```kotlin
    class Turtle {
        fun penDown()
        fun penUp()
        fun turn(degrees: Double)
        fun forward(pixels: Double)
    }

    val myTurtle = Turtle()
    with(myTurtle) {
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
    ```
1. use
    ```kotlin
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use { 
        reader -> println(reader.readText())
    }
    ```
1. 异常
    ```kotlin
    // 抛出异常
    throw Exception()
    ```
1. 导入。
    ```kotlin
    import org.example.Message
    import org.example.*
    import org.example.Message
    import org.test.Message as TestMessage
    ```
1. 函数接口。SAM
   ```kotlin
   fun interface IntPredicate {
       fun accept(i: Int): Boolean
   }
   val isEven = object : IntPredicate {
      override fun accept(i: Int): Boolean {
          return i % 2 == 0
      }
   }
   val isEven = IntPredicate { it % 2 == 0 }
   ```
1. 类型别名。
    ```kotlin
    typealias IntPredicate = (i: Int) -> Boolean
    val isEven: IntPredicate = { it % 2 == 0 }
    ```
1. 扩展。
    ```kotlin
    fun <T> AClass<T>.swap(index1: Int, index2: Int) {}
    val <T> List<T>.lastIndex: Int
        get() = size - 1

    class AClass {
        companion object {}
    }
    fun MyClass.Companion.m() {}
    ```
1. 解构。
    ```kotlin
    val jane = User("Jane", 35)
    val (name, age) = jane
    ```
