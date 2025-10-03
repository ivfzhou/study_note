# 一、笔记

1. [ECMA_262_2023.pdf](./ECMA_262_2023.pdf)

1. https://developer.mozilla.org/en-US/docs/Web/JavaScript

1. 一行多变量声明：var test1 = "hi", test2 = "hello", age = 25;。

1. 原始数据类型：undefined、boolean、number、string、bigint。引用类型：object、function。

1. 未声明的变量值为 undefined，对其只能使用 typeof 运算符，使用其他运算符将报错。
    ```javascript
    // 下面的代码将引发错误
    var oTemp
    console.log(oTemp2 == undefined)
    ```
    
1. 运算结果是 NaN：
    - Infinity 乘以 0。
    - Infinity 除以 Infinity。
    - 有 Infinity 参与的取模运算。
    - Infinity 减 Infinity。
    - Infinity 加 -Infinity。
    - -Infinity 减 -Infinity。
    
1. NaN 参与比较大小永远返回 false。字符串和数字比大小，先将字符串转成数字。

1. NaN 参与的判等永远返回 false。判等中有字符串和数字时，先将另一个值转成字符串和数字。对象判等比较地址值。布尔值转成数字再判等。

1. 函数中可通过 arguments 变量获取入参。函数可以接收任意个入参，遗漏的参数值会等于 undefined。

1. function 类型：
    ```javascript
    var functionName = new function(arg1, arg2, ..., argN, funcBodyStr)
    var sayHi = new Function("Name", "Message", "alert('Hello ' + Name + Message);")
    sayHi.length
    sayHi.toString()
    ```
    
1. 创建类：
    ```javascript
    function Car(color, doors, mpg) {
      this.color = color
      this.doors = doors
      this.mpg = mpg
      this.drivers = new Array("Mike", "John")
      if (typeof Car._initialized == "undefined") {
          Car.prototype.showColor = function () {
              alert(this.color)
          };
          Car._initialized = true
      }
    }
    var car1 = new Car("red", 4, 23)
    var car2 = new Car("blue", 3, 25)
    ```
    
1. 继承：
    ```javascript
    // 对象冒充
    function ClassB(color, name) {
        this.newMethod = ClassA
        this.newMethod(color)
        delete this.newMethod
        this.name = name
        this.sayName = function () {
            alert(this.name)
        };
    }
    function ClassB(color, name) {
        ClassA.call(this, color)
        this.name = name
        this.sayName = function () {
            alert(this.name)
        };
    }
    function ClassB(color, name) {
        ClassA.apply(this, new Array(color))
        this.name = name
        this.sayName = function () {
            alert(this.name)
        }
    }
    ```
    ```javascript
    // 原型链
    function ClassB() {}
    ClassB.prototype = new ClassA()
    ClassB.prototype.name = ""
    ClassB.prototype.sayName = function () {
        alert(this.name)
    }
    ```
    ```javascript
    // 混合
    function ClassA(color) {
        this.color = color
    }
    ClassA.prototype.sayColor = function () {
        alert(this.color)
    };
    function ClassB(color, name) {
        ClassA.call(this, color)
        this.name = name
    }
    ClassB.prototype = new ClassA()
    ClassB.prototype.sayName = function () {
        alert(this.name)
    }
    ```
    
1. 标识符以数字、字母、美元符号和下划线组成。不能以数字开头。大小写敏感。

1. 重复声明变量不会丢失值。
    ```javascript
    var carName = "porsche"
    var carName
    console.log(carName)
    ```
    
1. 数字与字符串相加。
    ```javascript
    var x = "8" + 3 + 5 // 835
    var x = 3 + 5 + "8" // 88
    ```
    
1. Hoisting 应用于变量声明和函数声明。

1. var 声明的变量没有块作用域。let 声明的变量有块作用域。通过 var 关键词定义的全局变量属于 window 对象。在相同的作用域，或在相同的块中，通过 var 重新声明一个 let 变量是不允许的。const 变量必须在声明时赋值。
    ```javascript
    {
        var x = 10
    }
    // 此处可以使用 x
    ```
    ```javascript
    // 重新声明变量
    var x = 10
    // 此处 x 为 10
    {
        var x = 6
        // 此处 x 为 6
    }
    // 此处 x 为 6
    ```
    ```javascript
    var x = 10
    // 此处 x 为 10
    {
        let x = 6
        // 此处 x 为 6
    }
    // 此处 x 为 10
    ```
    ```javascript
    var carName = "porsche"
    // 此处的代码可使用 window.carName
    ```
    
1. 访问对象属性：objectName["propertyName"]、objectName[propertyName]、myNumbers[Symbol.iterator]。

1. 比较 Object 是比较地址。
    ```javascript
    var x = new String("Bill")
    var y = new String("Bill")
    // (x == y) 为 false，因为 x 和 y 是不同的对象
    ```
    
1. 字符串不可修改。数组越界访问返回 undefined。
    ```javascript
    let s = "1"
    s[1] // undefined
    s.charAt(1) // ""
    ```
    
1. 一个可多行书写的字符串的语法。
    ```javascript
    let s = `hello 
    world`
    ```
    ```javascript
    let s =
      "hello \
    world"
    ```
    
1. 一种字符串字面量写法：
    ```javascript
    let s = `abc`
    ```
    
1. 字符串插值。模板字面量。
    ```javascript
    let firstName = "Bill"
    let lastName = "Gates"
    let text = `Welcome ${firstName}, ${lastName}!`
    ```
    ```javascript
    let price = 10
    let VAT = 0.25
    let total = `Total: ${(price * (1 + VAT)).toFixed(2)}`
    ```
    
1. 数字字面量：
    ```javascript
    let x = 1e1 // 科学计数法
    x = 0xa // 十六进制
    x = 0o1 // 八进制
    x = 0b1 // 二进制
    x = 1_00_1 // 数字分隔符
    ```
    
1. 数字的字符串形式进行算术运算。
    ```javascript
    var x = "100"
    var y = "10"
    var z = x / y // z 将是 10
    ```
    
1. BigInt 数字书写。
    ```javascript
    let n = 1n
    let n = BigInt(1)
    ```
    
1. for-in 循环对顺序不确定。

1. null 属于 object 类型，NaN 属于 number 类型。

1. 数字存储为 64 位浮点数，但所有按位运算都以 32 位二进制数执行。

1. 通过在脚本或函数的开头添加 "use strict"; 来声明严格模式。
    - 不允许在不声明变量的情况下使用变量、对象。
    - 不允许删除变量（或对象）、函数。
    - 不允许重复参数名。
    - 不允许八进制数值文本。
    - 不允许转义字符。
    - 不允许写入只读属性。
    - 不允许写入只能获取的属性。
    - 不允许删除不可删除的属性。
    - 字符串 eval 不可用作变量。
    - 字符串 arguments 不可用作变量。
    - 不允许 with 语句。
    - 不允许 eval() 在其被调用的作用域中创建变量。
    - 不允许使用为未来预留的关键词。
    
1. 箭头函数。如果函数只有一个语句，并且该语句返回一个值，则可以去掉大括号和 return 关键字。如果只有一个参数，也可以略过括号。在常规函数中，关键字 this 表示调用该函数的对象。对于箭头函数，this 关键字始终表示定义箭头函数的对象。

1. 模块。script 标签中的 type="module"。模块仅适用于 HTTP(s) 协议。
    ```javascript
    export const name = "Bill"
    export const age = 19
    
    const name = "Bill"
    const age = 19
    export { name, age }
    
    import { name, age } from "./person.js"
    ```
    ```javascript
    const message = () => {
        const name = "Bill"
        const age = 19
        return name + " is " + age + "years old."
    };
    export default message
    
    import message from "./message.js"
    ```
    
1. 不要对 return 语句进行换行。因为它本身就是一条完整的语句。

1. 对象 Rest 属性。
    ```javascript
    let { y, x, ...z } = { x: 1, y: 2, a: 3, b: 4 }
    x // 1
    y // 2
    z // { a: 3, b: 4 }
    ```
    
1. 展开运算符：
    ```javascript
    const numbers = [23, 55, 21, 87, 56]
    let maxValue = Math.max(...numbers)
    ```
    
1. 解构赋值。
    ```javascript
    let s = "a b"
    let [x, y] = s.split(" ")
    ```
    
1. 可以使用比较运算符比较日期对象。
    ```javascript
    today = new Date()
    someday = new Date().setFullYear(2049, 0, 16)
    if (someday > today) {
        text = "今天在 2049 年 1 月 16 日之前"
    } else {
        text = "今天在 2049 年 1 月 16 日之后"
    }
    ```
    
1. switch 中，如果多种 case 匹配一个 case 值，则选择第一个 case。switch 使用严格比较（===）。

1. delete 删除对以前定义的对象属性或方法的引用。不能删除开发者未定义的属性和方法。
    ```javascript
    var o = new Object()
    o.name = "David"
    alert(o.name) //输出 David
    delete o.name
    alert(o.name) //输出 undefined
    ```
    
1. void 对任何值返回 undefined。
    ```html
    <a href="javascript:void(window.open('about:blank'))">Click me</a>
    ```
    
1. ~ 位运算 NOT。实质上是对数字求负，然后减 1。
    ```javascript
    var num1 = 25 //25 等于 00000000000000000000000000011001
    var num2 = ~num1 //转换为 11111111111111111111111111100110
    alert(num2) //输出 -26
    ```
    
1. 逻辑赋值运算符：
    ```javascript
    let x = 100
    x ||= 4 // 100。如果第一个值为 false，则分配第二个值。
    x &&= 5 // 5。如果第一个值为 true，则分配第二个值。
    x ??= 5 // 如果第一个值 undefined 或为 null，则分配第二个值。
    ```
    
1. 对象字段的 Getter、Setter：
    ```javascript
    let obj = {
        get x() {},
        set x(v) {}
    }
    ```
    
1. 在方法中，this 指的是所有者对象。单独的情况下，this 指的是全局对象。在函数中，this 指的是全局对象。严格模式下，在函数中 this 是 undefined。在事件中，this 指的是接收事件的元素。

1. 默认值参数。
    ```javascript
    function myFunction(x, y = 10) {
        return x + y
    }
    ```
    
1. 逗号运算符的表达式返回最右边的值：
    ```javascript
    let x = (1, 2) // x = 2
    ```

# 二、字符串字面量

| | |
| :-: | :-: |
| \\n | 换行 |
| \\t | 制表符 |
| \\b | 空格 |
| \\r | 回车 |
| \\f | 换页符 |
| \\v | 垂直制表符 |
| \\\ | 反斜杠 |
| \\' | 单引号 |
| \\" | 双引号 |
| \\0*nnn* | 八进制代码 nnn 表示的字符（n 是 0 到 7 中的一个八进制数字） |
| \\x*nn* | 十六进制代码 nn 表示的字符（n 是 0 到 F 中的一个十六进制数字） |
| \\u*nnnn* | 十六进制代码 nnnn 表示的 Unicode 字符（n 是 0 到 F 中的一个十六进制数字） |
| | |

# 三、运算符优先级

1. ()*括号*
1. . [] ()*函数调用* new*带参构造* ?.*可选链*
1. new*不带参构造*
1. ++ --*后缀*
1. ++ --*前缀* ! typeof delete void + -*一元* ~ delete
1. **
1. \* / %
1. \+ -*二元*
1. << >> >>>
1. < <= > >= in instanceof
1. == === != !==
1. &
1. ^
1. |
1. &&
1. || ??*空值合并*
1. ?:
1. = += -= *= /= %= <<= >>= >>>= &= ^= |= **= &&= ||= => yield ...*展开运算符* yield\**委托运算符*
1. ,

# 四、语句

1. if(*condition*) {} else if(*condition*) {} else{}
1. do{} while(*condition*);
1. while(*condition*) {}
1. for(*initialization*; *condition*; *post*){}
1. for(let *property* in *expression*){}
1. for(let [*variable*, *variable*] of *iterable*){}
1. for await (;;){}
1. *label*: {}
1. with(*val*){}
1. switch(*expression*) { case *val*: ; break; default: ; }
1. function *name*(*arg*, ...){}
1. try{} catch(*err*){} finally{}
1. try{} catch{}
1. class *ClassName* extends *ClassName* { constructor([ *field*, ... ]){}; [ static ] *method*([ *arg*, ... ]){} ... }
1. debugger
1. async function(){}
1. await *promise*
1. throw *val*

# 五、API

## 1. Boolean

- **window.Boolean(*var*)**：返回 boolean 值。
- **new Boolean(*var*)**：返回 Boolean 对象。
- ***instance*.constructor**：返回创建 Boolean 原型的函数。
- ***instance*.prototype**：允许向 Boolean 原型添加属性和方法。
- ***instance*.toString()**：返回布尔值的转换为字符串结果。
- ***instance*.valueOf()**：返回布尔值的原始值。

## 2. Number

- **window.Infinity**：表示正无穷大的数值。
- **window.NaN**：非数字（Not-a-Number）值。
- **window.parseFloat(*str*)**：解析参数并返回浮点数。*str* 中的第一个字符是否为数字。如果是，它会解析字符串直到到达数字的末尾，并将数字返回。允许前导和尾随空格。如果第一个字符不能转换为数字，返回 NaN。
- **window.parseInt(*str*, [ *radixUint* ])**：解析其参数并返回整数。允许前导和尾随空格。*radixUint* 从 2 到 36。如果第一个字符不能转换为数字，返回 NaN。
- **window.isNaN(*val*)**：确定值是否是非法数字。
- **window.isFinite(*var*)**：确定值是否是有限的合法数。如果值是 NaN 则返回 false。
- **window.Number([ *var* ])**：将参数转换为数字并返回。如果该值无法转换为合法数字，则返回 NaN。如果参数是 Date 对象，则返回自 UTC 1970 年 1 月 1 日午夜以来的毫秒数。不提供 *var* 返回 0。
- **Number.MAX_SAFE_INTEGER**：最大安全整数。
- **Number.MIN_SAFE_INTEGER**：最小安全整数。
- **Number.MAX_VALUE**：可能的最大数。大于 MAX_VALUE 的数字表示为 Infinity。
- **Number.MIN_VALUE**：可能的最小数。
- **Number.POSITIVE_INFINITY**：无穷大（溢出时返回）。
- **Number.NEGATIVE_INFINITY**：负无穷大（溢出时返回）。
- **Number.EPSILON**：1 和大于 1 的最小数之间的差。
- **Number.NaN**：非数字值。
- **Number.isInteger(*num*)**：如果参数是整数则返回 true。
- **Number.isSafeInteger(*val*)**：如果参数是安全整数，则返回 true。
- **Number.parseFloat(*str*)**
- **Number.parseInt(*str*, [ *radixUint* ])**
- **Number.isFinite(*num*)**：参数类型是 number，且值为无穷值，则返回 true。
- **Number.isNaN(*num*)**：参数类型是 number，且值为 NaN，则返回 true。
- **new Number(*var*)**：返回 number 对象。
- ***instance*.constructor**：返回对创建此对象的 number 函数的引用。
- ***instance*.prototype**：允许向对象添加属性和方法。
- ***instance*.toString([ *radixUint* ])**：将数字作为字符串返回。*radixUint* 是 2 到 36 之间的整数。
- ***instance*.toLocaleString([ *localeStr*, *optionObj* ])**：把数字转换为字符串，使用本地数字格式顺序。
    - *optionObj*：
        - currency：任何货币代码（如 EUR, USD, INR 等）。
        - currencyDisplay：合法值：symbol（默认）、code、name。
        - localeMatcher：合法值：best-fit（默认）、lookup。
        - maximumFractionDigits：从 0 到 20 的数字（默认为 3）。
        - maximumSignificantDigits：从 1 到 21 的数字（默认为 21）。
        - minimumFractionDigits：从 0 到 20 的数字（默认为 3）。
        - minimumIntegerDigits：从 1 到 21 的数字（默认为 1）。
        - minimumSignificantDigits：从 1 到 21 的数字（默认为 21）。
        - style：合法值：currency、decimal（默认）、percent。
        - useGrouping：合法值：true（默认）、false。
- ***instance*.toExponential([ *digitUint* ])**：返回以指数表示法书写的，带 *digitUint* 个小数位数的数字。*digitUint* 介于 0 和 20 之间的整数，若省略，则将其设置为表示值所需的位数。
- ***instance*.toFixed([ *digitUint* ])**：返回带 *digitUint* 个小数位数的数字。四舍五入为指定的小数位数。*digitUint* 介于 0 和 20 之间的整数。
- ***instance*.toPrecision([ *precisionUint* ])**：返回指定长度的数字。*precisionUint* 是 1 ~ 21 之间（且包括 1 和 21）的值。若省略，则返回不带任何格式的数字。
- ***instance*.valueOf()**：返回数字值。

## 3. Math

- **Math.PI**：返回 PI（约 3.14）。
- **Math.E**：返回欧拉数（约 2.718）。
- **Math.SQRT2**：返回 2 的平方根（约 1.414）。
- **Math.SQRT1_2**：返回 1/2 的平方根（约 0.707）。
- **Math.LN2**：返回 2 的自然对数（约 0.693）。
- **Math.LN10**：返回 10 的自然对数（约 2.302）。
- **Math.LOG2E**：返回 E 的以 2 为底的对数（约 1.442）。
- **Math.LOG10E**：返回 E 的以 10 为底的对数（约 0.434）。
- **Math.round(*x*)**：将 *x* 舍入为最接近的整数。-3.5 将舍入为 -3。
- **Math.fround(*x*)**：返回数的最接近的浮点数（32 位单精度）表示。
- **Math.pow(*x*, *y*)**：返回 *x* 的 *y* 次幂值。如果结果是虚数或复数，则将返回 NaN。如果由于指数过大而引起浮点溢出，则将返回 Infinity。
- **Math.sqrt(*x*)**：返回 *x* 的平方根。如果 *x* 是负数，则返回 NaN。
- **Math.cbrt(*x*)**：返回 *x* 的立方根。
- **Math.abs(*x*)**：返回 *x* 的绝对值。如果值不是数字，则返回 NaN，如果值为空则返回 0。
- **Math.ceil(*x*)**：返回 *x* 向上舍入为最接近的整数。
- **Math.floor(*x*)**：返回 *x* 向下舍入为最接近的整数。
- **Math.trunc(*x*)**：返回 *x* 的整数部分。
- **Math.min(*x*, ...)**：返回最小的值。如果没有给出参数，则返回 Infinity。如果有参数不是数字，则返回 NaN。
- **Math.max(*x*, ...)**：返回最高的值。如果没有给出参数，则返回 -Infinity。如果有参数不是数字，则返回 NaN。
- **Math.random()**：返回 0 到 1 （不包含）之间的随机数。
- **Math.sin(*x*)**：返回 *x* 的正弦值（*x* 以弧度为单位）。如果值为空，则返回 NaN。
- **Math.sinh(*x*)**：返回 *x* 的双曲正弦值。
- **Math.cos(*x*)**：返回 *x* 的余弦值（*x* 以弧度为单位）。
- **Math.cosh(*x*)**：返回 *x* 的双曲余弦值。
- **Math.tan(*x*)**：返回角度的正切值。
- **Math.tanh(*x*)**：返回数的双曲正切值。
- **Math.asin(*x*)**：返回 *x* 的反正弦值，以弧度计。值是 -PI/2 到 PI/2 之间的弧度值。如果参数 *x* 超出 -1 到 1 的范围，则将返回 NaN。
- **Math.asinh(*x*)**：返回 *x* 的双曲反正弦值。
- **Math.acos(*x*)**：返回 *x* 的反余弦值，以弧度计。值为 0 到 PI 弧度值。如果参数 *x* 超出 -1 到 1 的范围，则将返回 NaN。
- **Math.acosh(*x*)**：返回 *x* 的双曲反余弦值。如果该参数小于 1 则返回 NaN。
- **Math.atan(*x*)**：返回 *x* 作为 -PI/2 与 PI/2 弧度之间的数值的反正切值。如果值为空，则返回 NaN。
- **Math.atanh(*x*)**：返回 *x* 的双曲反正切。如果参数 *x* 大于 1 或小于 -1，则将返回 NaN。如果参数 *x* 为 1，则将返回 Infinity。如果参数 *x* 为 -1，则将返回 -Infinity。
- **Math.atan2(*y*, *x*)**：返回其参数的商的反正切。从 *x* 轴到点 (*x*, *y*) 之间的角度。如果值为空，则为 NaN。
- **Math.exp(*x*)**：返回 e 的 *x* 次方的值。
- **Math.expm1(*x*)**：返回 e 的 *x* 次方减去 1 后的值。
- **Math.log(*x*)**：返回 *x* 的自然对数。
- **Math.log10(*x*)**：返回 *x* 的以 10 为底的对数。如果参数为负数，则返回 NaN。
- **Math.log1p(*x*)**：返回 1 + *x* 的自然对数。
- **Math.log2(*x*)**：返回 *x* 的以 2 为底的对数。
- **Math.clz32(*x*)**：返回 *x* 的 32 位二进制表示中前导零的数量。
- **Math.sign(*x*)**：返回数的符号（检查它是正数、负数还是零）。如果数字为正数，则返回 1，如果数字为负数，则返回 -1，如果数字为正零，则返回 0，如果数字为负零，则返回 -0，如果不是数字，则返回 NaN。

## 4. String

- **window.String(*val*)**：将参数转换为字符串。
- **String.fromCharCode(*num*, ...)**：将 Unicode 值作为字符串返回。
- **new String(*var*)**：返回 String 对象。
- ***instance*.length**：返回字符串中的字符数。
- ***instance*.constructor**：返回 String 的构造函数。
- ***instance*.prototype**：允许向对象添加属性和方法。
- ***instance*.indexOf(*findStr*, [ *offsetUint* ])**：返回值在字符串中第一次出现的位置。未找到文本返回 -1。
- ***instance*.lastIndexOf(*findStr*, [ *offsetUint* ])**：返回值在字符串中最后一次出现的位置。未找到文本返回 -1。
- ***instance*.slice(*startInt*, [ *endInt* ])**：提取字符串的一部分并返回新字符串。如果某个参数为负，则从字符串的结尾开始计数。*startInt* 可为负数。
- ***instance*.substring(*startUint*, [ *endUint* ])**：从字符串中抽取子串。如果 *startUint* 大于 *endUint*，则交换参数。小于 0 则视为 0。
- ***instance*.substr(*startInt*, [ *lengthUint* ])**：从字符串中抽取子串。*startInt* 可为负数。
- ***instance*.search(*reg*)**：检索字符串中与正则表达式匹配的子串。返回第一个匹配项的索引。未找到匹配项，返回 -1。不执行全局匹配，它将忽略标志 g。
- ***instance*.replace(*oldStrOrReg*, *newOrFunc(hit, ..., index, old)str*)**：在字符串中搜索值或正则表达式，并返回替换值的字符串。
    - *new* 中的 \$ 符号含义。
        - \$1、\$2、...、\$99 与 regexp 中的第 1 到第 99 个子表达式相匹配的文本。
        - \$& 与 regexp 相匹配的子串。
        - \$` 位于匹配子串左侧的文本。
        - \$' 位于匹配子串右侧的文本。
        - \$\$ 直接量符号。
- ***instance*.replaceAll(*oldOrReg*, *newStr*)**：如果参数是正则表达式，则必须设置全局标志 g，否则会抛出 TypeError。
- ***instance*.match(*findStrReg*)**：在字符串中搜索值或正则表达式，并返回匹配项。未找到匹配项，返回 null。不带 g 的检索，返回的数组有 index 和 input 属性。
- ***instance*.matchAll(*findStrReg*)**：如果参数是正则表达式，则必须设置全局标志 g，否则会抛出 TypeError。
- ***instance*.toUpperCase()**：返回转换为大写字母的字符串。
- ***instance*.toLowerCase()**：返回转换为小写字母的字符串。
- ***instance*.toLocaleLowerCase()**：使用主机的语言环境返回转换为小写字母的字符串。
- ***instance*.toLocaleUpperCase()**：使用主机的语言环境返回转换为大写字母的字符串。
- ***instance*.concat(*str*, ...)**：返回两个或多个连接的字符串。
- ***instance*.trim()**：返回删除了两端空格的字符串。
- ***instance*.trimEnd()**：返回从末尾删除空格的字符串。
- ***instance*.trimStart()**：返回从开头删除空格的字符串。
- ***instance*.charAt(*indexUint*)**：返回指定索引处的字符。索引无效则返回空字符串。
- ***instance*.charCodeAt(*indexUint*)**：返回指定索引处字符的 Unicode。如果索引无效返回 NaN。
- ***instance*.codePointAt(*indexUint*)**：返回字符串中索引处的 Unicode 值。索引无效返回 undefined。
- ***instance*.split([ *sepStr*, *limitUint* ])**：返回将字符串拆分为子字符串数组。
- ***instance*.endsWith(*endStr*, [ *lengthUint* ])**：返回字符串是否以指定值结尾。
- ***instance*.startsWith(*startStr*, [ *startUint* ])**：检查字符串是否以指定字符开头。
- ***instance*.includes(*targetStr*, [ *startUint* ])**：返回字符串是否包含指定值。
- ***instance*.localeCompare(*str*)**：使用基于本地的顺序来比较字符串。
- ***instance*.repeat(*countUint*)**：返回拥有多个字符串副本的新字符串。
- ***instance*.toString()**：将字符串或字符串对象作为字符串返回。
- ***instance*.valueOf()**：返回字符串或字符串对象的原始值。
- ***instance*.padStart(*countUint*, *str*)**：从 *str* 取 *countUint* 个字符（不够则重复 *str*），填充到字符串首部后返回。
- ***instance*.padEnd(*countUint*, *str*)**：从 *str* 取 *countUint* 个字符（不够则重复 *str*），填充到字符串尾部后返回。

## 5. Object

- **Object.defineProperty(*obj*, *fieldStr*, *optionObj*)**：定义对象属性或更改属性的值。
    - *optionObj*
        - value：any
        - writable：bool
        - enumerable：bool
        - configurable：bool
        - get：func
        - set：func(val)
- **Object.defineProperties(*object*, *descriptors*)**：添加或更改多个对象属性。
- **Object.getOwnPropertyDescriptor(*object*, *property*)**：访问属性。
- **Object.getOwnPropertyNames(*object*)**：将所有属性作为数组返回。
- **Object.keys(*obj*)**：返回对象的键的数组。
- **Object.getPrototypeOf(*object*)**：访问原型。
- **Object.preventExtensions(*object*)**：防止向对象添加属性。
- **Object.isExtensible(*object*)**：如果可以将属性添加到对象，则返回 true。
- **Object.seal(*object*)**：防止更改对象属性（而不是值）。
- **Object.isSealed(*object*)**：如果对象被密封，则返回 true。
- **Object.freeze(*object*)**：防止对对象进行任何更改。
- **Object.isFrozen(*object*)**：如果对象被冻结，则返回 true。
- **Object.entries(*obj*)**：返回对象中键/值对的数组。
- **Object.values(*obj*)**：返回对象值的单维数组。
- **Object.fromEntries(*obj*)**
- **Object.create()**：创建对象。
- **Object.toString(*val*)**：将对象转换为字符串并返回结果。
- **new Object()**
- ***instance*.constructor**：返回创建对象原型的函数。
- ***instance*.prototype**：让您向 JavaScript 对象添加属性和方法。
- ***instance*.toString()**：将对象转换为字符串并返回结果。
- ***instance*.valueOf()**：返回对象的原始值。

## 6. RegExp

- **new RegExp(*pattern*, *attributes*)**
- ***instance*.lastIndex**：规定开始下一个匹配的索引。仅在设置了 g 修饰符时才有效。没有得到匹配，则将 lastIndex 重置为 0。
- ***instance*.constructor**：返回创建 RegExp 对象原型的函数。
- ***instance*.global**：检查是否设置了 g 修饰符。
- ***instance*.ignoreCase**：检查是否设置了 i 修饰符。
- ***instance*.multiline**：检查是否设置了 m 修饰符。
- ***instance*.source**：返回 RegExp 模式的文本。
- ***instance*.test(*txt*)**：测试字符串中的匹配项。返回 true 或 false。
- ***instance*.exec(*txt*)**：测试字符串中的匹配项。返回第一个匹配项。如果未找到匹配项返回 null。返回的值有两个属性 index、input。
- ***instance*.toString()**：返回正则表达式的字符串值。

## 7. Array

- **Array.isArray(*obj*)**：检查对象是否为数组。
- **Array.prototype**：允许您向数组添加属性和方法。
- **new Array([ *elem*, ... | *length* ])**
- ***instance*.constructor**：返回创建 Array 对象原型的函数。
- ***instance*.length**：设置或返回数组中元素的数量。
- ***instance*.sort([ *func(a, b)int* ])**：对数组的元素进行排序。默认按字母和升序将值作为字符串进行排序。返回该数组。
- ***instance*.push(*item*, ...)**：将新元素添加到数组的末尾，并返回新的长度。
- ***instance*.join([ *sep* ])**：将数组的所有元素连接成一个字符串返回。*sep* 默认是逗号。
- ***instance*.pop()**：删除数组的最后一个元素，并返回该元素。
- ***instance*.shift()**：删除数组的第一个元素，并返回该元素。
- ***instance*.unshift([ *item*, ... ])**：将新元素添加到数组的开头，并返回新的长度。
- ***instance*.splice(*index*, [ *deleteCount*, *insertItem*, ... ])**：从数组中添加/删除元素。返回被删除的项数组。
- ***instance*.concat(*arr*, ...)**：连接两个或多个数组，并返回已连接数组的副本。
- ***instance*.slice([ *start*, *end* ])**：选择数组的一部分，并返回新数组。可以使用负数（倒数）。如果不传递参数，则复制数组返回。
- ***instance*.reverse()**：反转数组中元素的顺序。返回原数组。
- ***instance*.indexOf(*item*, [ *start* ])**：在数组中搜索元素并返回其位置。它使用的全等判断。
- ***instance*.lastIndexOf(*item*, [ *start* ])**：在数组中搜索元素，从末尾开始，并返回其位置。
- ***instance*.copyWithin(*target*, [ *start*, *end* ])**：将数组中的数组元素复制到指定位置或从指定位置复制。返回原数组。
- ***instance*.entries()**：返回键/值对数组迭代对象。
- ***instance*.keys()**：返回 Array Iteration 对象，包含原始数组的键。
- ***instance*.fill(*value*, [ *start*, *end* ])**：用静态值填充数组中的元素。
- ***instance*.from(*obj*, [ *func*, *thisValue* ])**：从对象创建数组。从具有 length 属性或可迭代对象的任何对象返回 Array 对象。
- ***instance*.includes(*elem*, [ *start* ])**：检查数组是否包含指定的元素。
- ***instance*.toString()**：将数组转换为字符串，并返回结果。
- ***instance*.valueOf()**：返回数组的原始值。
- ***instance*.findIndex(*func(cur, [ index, arr ])bool*, [ *thisValue* ])**：返回数组中通过测试的第一个元素的索引。如果找到函数返回 true 值的数组元素，则 findIndex() 返回该数组元素的索引（并且不检查剩余值），否则返回 -1。
- ***instance*.find(*func(cur, [ index, arr ])bool*, [ *thisValue* ])**：返回数组中第一个通过测试的元素的值。如果找到函数返回 true 值的数组元素，则 find() 返回该数组元素的值（并且不检查剩余值），否则返回 undefined。
- ***instance*.every(*func(cur, [ index, arr ])bool*, [ *thisValue* ])**：检查数组中的每个元素是否通过测试。如果找到函数返回 false 值的数组元素，every() 返回 false（并且不检查剩余值）。
- ***instance*.some(*func(cur, [ index, arr ])bool*, [ *thisValue* ])**：检查数组中的任何元素是否通过测试。如果找到函数返回真值的数组元素，some() 返回真（并且不检查剩余值），否则返回 false。
- ***instance*.reduce(*func(total, currentValue, [ currentIndex, arr ])any*, [ *initialValue* ])**：将数组的值减为单个值（从左到右）。最终返回 *total*。
- ***instance*.reduceRight(*func(total, currentValue, [ currentIndex, arr ])any*, [ *initialValue* ])**：将数组的值减为单个值（从右到左）。
- ***instance*.map(*func(cur, [ index, arr ])any*, [ *thisValue* ])**：使用为每个数组元素调用函数的结果创建新数组。
- ***instance*.forEach(*func(cur, [ index, arr ])*, [ *thisValue* ])**：为每个数组元素调用函数。
- ***instance*.filter(*func(cur, [ index, arr ])bool*, [ *thisValue* ])**：返回数组中通过测试的每个元素创建的新数组。
- ***instance*.flat()**：展平嵌套数组来创建新数组。
- ***instance*.flatMap(*func(val)any*)**：首先映射数组的所有元素，然后通过展平数组来创建新数组。

## 8. Date

- **Date.now()**：返回自 1970 年 1 月 1 日 00:00:00 UTC 以来的毫秒数。
- **Date.parse(*dateStr*)**：返回日期字符串与 1970 年 1 月 1 日午夜之间的毫秒数。
- **new Date()**
- **new Date(*year*, *month*, [ *day*, *hours*, *minutes*, *seconds*, *milliseconds* ])**：从 0 到 11 计算月份。 一月是 0。十二月是 11。一位和两位数年份将被解释为 19xx 年。
- **new Date(*milliseconds*)**
- **new Date(*dateString*)**：运行失败返回 NaN。
- ***instance*.constructor**：返回创建 Date 对象原型的函数。
- ***instance*.prototype**：允许您向对象添加属性和方法。
- ***instance*.UTC()**：根据 UTC 时间，返回自 1970 年 1 月 1 日午夜以来的日期中的毫秒数。
- ***instance*.valueOf()**：返回 Date 对象的原始值。
- ***instance*.toString()**：将 Date 对象转换为字符串。
- ***instance*.toLocaleString([ *locales*, *options* ])**：使用区域设置约定将 Date 对象转换为字符串。
    - *locales*
          - zh-CN 中国大陆，简体字
          - zh-HK 香港，繁体字
          - zh-TW 台湾，繁体字
          - en-US 美国英语
    - *options*
      - dateStyle
          - full
          - long
          - medium
          - short
      - timeStyle
          - full
          - long
          - medium
          - short
      - localeMatcher
          - best-fit（默认）
          - lookup
      - timeZone
      - hour12
          - false
          - true
      - hourCycle
          - h11
          - h12
          - h23
          - h24
      - formatMatcher
          - basic
          - "best-fit（默认）
      - weekday
          - long
          - short
          - narrow
      - year
          - 2-digit
          - numeric
      - month
          - 2-digit
          - long
          - narrow
          - numeric
          - short
      - day
          - 2-digit
          - long
      - hour
          - 2-digit
          - long
      - minute
          - 2-digit
          - long
      - second
          - 2-digit
          - long
      - timeZoneName
          - long
          - short
- ***instance*.toUTCString()**：根据世界时，将 Date 对象转换为字符串。
- ***instance*.toDateString()**：将 Date 对象的日期部分转换为可读字符串。
- ***instance*.toLocaleDateString()**：使用区域设置约定将 Date 对象的日期部分作为字符串返回。
- ***instance*.toTimeString()**：将 Date 对象的时间部分转换为字符串。
- ***instance*.toLocaleTimeString()**：使用区域设置约定将 Date 对象的时间部分作为字符串返回。
- ***instance*.toISOString()**：使用 ISO 标准将日期作为字符串返回。
- ***instance*.toJSON()**：以字符串形式返回日期，格式为 JSON 日期。
- ***instance*.getDate()**：以数字（1-31）返回日期的日。
- ***instance*.getDay()**：以数字（0-6）返回日期的星期名。
- ***instance*.getFullYear()**：以四位数字形式返回日期年份。
- ***instance*.getHours()**：以数字（0-23）返回日期的小时数。
- ***instance*.getMilliseconds()**：以数字（0-999）返回日期的毫秒数。
- ***instance*.getMinutes()**：以数字（0-59）返回日期的分钟数。
- ***instance*.getMonth()**：以数字（0-11）返回日期的月份。
- ***instance*.getSeconds()**：以数字（0-59）返回日期的秒数。
- ***instance*.getTime()**：返回自 1970 年 1 月 1 日以来的毫秒数。
- ***instance*.getTimezoneOffset()**：返回 UTC 时间与本地时间之间的时差，以分钟为单位。
- ***instance*.getUTCDate()**：根据世界时，返回月份中的第几天（从 1 到 31）。
- ***instance*.getUTCDay()**：根据世界时，返回星期几（0-6）。
- ***instance*.getUTCFullYear()**：根据世界时，返回年份。
- ***instance*.getUTCHours()**：根据世界时，返回小时（0-23）。
- ***instance*.getUTCMilliseconds()**：根据世界时，返回毫秒数（0-999）。
- ***instance*.getUTCMinutes()**：根据世界时，返回分钟（0-59）。
- ***instance*.getUTCMonth()**：根据世界时，返回月份（0-11）。
- ***instance*.getUTCSeconds()**：根据世界时，返回秒数（0-59）。
- ***instance*.setFullYear(*year*, [ *month*, *day* ])**：设置年（可选月和日）。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setMonth(*month*, [ *day* ])**：设置月（0-11）。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setDate(*day*)**：以数值（1-31）设置日。也可用于将天数添加到日期。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setHours(*hour*, [ *min*, *sec*, *millisec* ])**：设置小时（0-23）。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setMinutes(*minute*, [ *sec*, *millisec* ])**：设置分（0-59）。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setSeconds(*second*, [ *millisec* ])**：设置秒（0-59）。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setMilliseconds(*millisec*)**：设置毫秒（0-999）。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setTime(*millisec*)**：设置时间（从 1970 年 1 月 1 日至今的毫秒数）。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setUTCFullYear(*year*, [ *month*, *day* ])**：根据世界时，设置日期对象的年份。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setUTCDate(*day*)**：根据世界时，设置 Date 对象中月份的一天。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setUTCMonth(*month*, [ *day* ])**：根据世界时，设置日期对象的月份。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setUTCMinutes(*minute*, [ *sec*, *millisec* ])**：根据世界时，设置日期对象的分钟数。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setUTCHours(*hour*, [ *min**, *sec*, *millisec* ])**：根据世界时，设置日期对象的小时。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setUTCSeconds(*second*, [ *millisec* ])**：根据世界时，设置日期对象的秒数。返回与 1970 年 1 月 1 日午夜之间的毫秒数。
- ***instance*.setUTCMilliseconds(*millisec*)**：根据世界时，设置日期对象的毫秒数。返回与 1970 年 1 月 1 日午夜之间的毫秒数。

## 9. Error

- ***instance*.code**
- ***instance*.name**：设置或返回错误名。
- ***instance*.message**：设置或返回错误消息（一条字符串）。

## 10. Map

- **new Map(*keyValueArr*)**
- ***instance*.size**：返回 Map 元素的数量。
- ***instance*.set(*key*, *value*)**：为 Map 中的键设置值。
- ***instance*.get(*key*)**：获取 Map 对象中键的值。
- ***instance*.clear()**：从 Map 中移除所有元素。
- ***instance*.delete(*key*)**：删除由某个键指定的 Map 元素。
- ***instance*.has(*key*)**：如果键存在于 Map 中，则返回 true。
- ***instance*.forEach(*func(key, value)*)**：为 Map 中的每个键/值对调用回调函数。
- ***instance*.entries()**：返回迭代器对象，其中包含 Map 中的 [ key, value ] 键值对。
- ***instance*.keys()**：返回迭代器对象，其中包含 Map 中的键。
- ***instance*.values()**：返回迭代器对象，其中包含 Map 中的值。

## 11. Set

- **new Set(*arr*)**
- ***instance*.size**：返回 Set 中元素的数量。
- ***instance*.add(*elem*)**：向 Set 中添加新元素。
- ***instance*.delete(*elem*)**：从 Set 中移除元素。
- ***instance*.has(*elem*)**：如果值存在则返回 true。
- ***instance*.clear()**：从 Set 中移除所有元素。
- ***instance*.forEach(*func(val)*)**：为每个元素调用回调函数。
- ***instance*.values()**：返回包含 Set 中所有值的迭代器。
- ***instance*.keys()**：与 values() 相同。
- ***instance*.entries()**：返回迭代器，其中包含 Set 中的 [ value,value ] 值值对。

## 12. Promise

- **Promise.resolve(*val*)**
- **new Promise(*func(resolveFunc, rejectFunc)*)**
- ***instance*.then([ *func(val)*, *func(err)* ])**
- ***instance*.catch(*func(val)*)**
- ***instance*.finally(*func()*)**

## 13. XMLHttpRequest

- **new XMLHttpRequest()**
- ***instance*.readyState**：保存 XMLHttpRequest 的状态。
    - 0：请求未初始化。
    - 1：服务器连接已建立。
    - 2：请求已收到。
    - 3：正在处理请求。
    - 4：请求已完成且响应已就绪。
- ***instance*.status**：返回请求的状态号。
- ***instance*.statusText**：返回状态文本。
- ***instance*.responseText**：以字符串形式返回响应数据。
- ***instance*.responseXML**：以 XML 数据返回响应数据。
- ***instance*.onreadystatechange**：定义当 readyState 属性发生变化时调用的函数。设置为 func()。
- ***instance*.onload**：定义接收到（加载）请求时要调用的函数。设置为 func()。
- ***instance*.open(*method*, *url*, *sync*, [ *user*, *pass* ])**：规定请求。
- ***instance*.send([ *reqBody* ])**：向服务器发送请求。
- ***instance*.abort()**：取消当前请求。
- ***instance*.getAllResponseHeaders()**：返回头部信息。
- ***instance*.getResponseHeader(*header*)**：返回特定的头部信息。
- ***instance*.setRequestHeader(*header*, *value*)**：将标签/值对添加到要发送的标头。

## 14. Worker

- **new Worker(*jsPath*)**
- ***instance*.onmessage**：设置为 func(event)。
    - event
        - data
- ***instance*.terminate()**

## 15. Window

- **name**：设置或返回窗口的名称。
- **closed**：返回窗口是否已被关闭。只读。
- **document**：返回窗口的 Document 对象。
- **screen**：返回窗口的 Screen 对象。
- **location**：返回窗口的 Location 对象。
- **history**：返回窗口的 History 对象。
- **navigator**：返回窗口的 Navigator 对象。
- **localStorage**：允许在 Web 浏览器中保存键值对。存储没有过期日期的数据。只读。
- **sessionStorage**：允许在 Web 浏览器中保存键值对。存储会话的数据。
- **frameElement**：返回运行在窗口中的框架。只读。如果窗口不在框架中运行，则返回 null。
- **console**：返回窗口的 Console 对象。
- **frames**：返回窗口中运行的所有窗口对象数组。只读。
- **length**：返回当前窗口中 iframe 元素的数量。只读。
- **innerHeight**：浏览器窗口的内高度（以像素计）。只读。
- **innerWidth**：浏览器窗口的内宽度（以像素计）。只读。
- **outerHeight**：返回浏览器窗口的高度（以像素计），包括工具栏/滚动条。只读。
- **outerWidth**：返回浏览器窗口的宽度（以像素计），包括工具栏/滚动条。只读。
- **pageXOffset**：返回当前文档从窗口左上角（水平）滚动的像素。
- **pageYOffset**：返回当前文档从窗口左上角（垂直）滚动的像素。
- **scrollX**：pageXOffset 的别名。
- **scrollY**：pageYOffset 的别名。
- **screenLeft**：返回窗口相对于屏幕的水平坐标。以像素为单位。
- **screenTop**：返回窗口相对于屏幕的垂直坐标。以像素为单位。
- **screenX**：返回窗口相对于屏幕的水平坐标。
- **screenY**：返回窗口相对于屏幕的垂直坐标。
- **opener**：返回对创建窗口的窗口的引用。
- **parent**：返回当前窗口的父窗口。只读。
- **top**：返回最顶层的浏览器窗口。只读。
- **self**：返回当前窗口。只读。
- **open(*url*, *name*, [ *features* ])**：打开新窗口，返回窗口引用对象。方法运行失败返回 null。若 *url* 为空则打开空白窗口。*features* 是以逗号分隔的键值列表。如果 *nane* 是已存在的窗口名，则返回该窗口引用。
    - *name*：
        - _blank：URL 被加载到新窗口或选项卡中。默认。
        - _parent：URL 被加载到父框架中。
        - _self：URL 替换当前页面。
        - _top：URL 替换任何可能加载的框架集。
        - *name*：窗口的名称（不指定窗口的标题）。
    - *features*：
        - height=*pixels*：窗口的高度，以像素计。最小值为 100。
        - left=*pixels*：窗口的左侧位置（X 坐标），以像素计。不允许负值。
        - menubar=yes|no|1|0：是否显示菜单栏。
        - status=yes|no|1|0：是否添加状态栏。
        - titlebar=yes|no|1|0：是否显示标题栏。除非调用的应用程序是 HTML 应用程序或受信任的对话框，否则将被忽略。
        - top=pixels：窗口的顶部位置（Y 坐标），以像素计。不允许负值。
        - width=pixels：窗口的宽度，以像素计。最小值为 100。
- **close()**：关闭当前窗口。
- **stop()**：停止加载窗口。
- **moveTo(*xInt*, *yInt*)**：将窗口移动到指定位置。
- **moveBy(*xPixelInt*, *yPixelInt*)**：相对于其当前位置移动窗口。
- **resizeTo(*widthPixelInt*, *heightPixelInt*)**：将窗口大小调整为指定的宽度和高度。
- **resizeBy(*incrWidthPixelInt*, *incrHeightPixelInt*)**：按指定像素调整窗口大小。
- **scrollBy(*xPixelInt*, *yPixelInt*)**：按指定的像素数滚动文档。
- **scrollTo(*xPixelInt*, *yPixelInt*)**：将文档滚动到指定坐标。
- **alert(*txtStr*)**：显示带有消息和确定按钮的警报框。
- **confirm(*txt*)**：显示对话框，单击确定，该框返回 true。单击取消，该框返回 false。
- **print()**：打开打印面板。
- **prompt(*msgStr*, *defaultValStr*)**：向用户用对话框请求输入一条简单的字符串。用户点击确认，则返回输入的字符串。否则返回 null。
- **setTimeout(*func*, [ *millisecond*, *param*, ... ])**：在经过指定的时间之后执行代码，返回计时器 ID。*millisecond* 默认为 0。
- **setInterval(*func*, *millisecond*, [ *param*, ... ])**：周期性执行指定的代码，返回计时器 ID。如果 *millisecond* 值小于 10，则使用 10。*param* 是传递给函数的参数。
- **clearInterval(*id*)**：清除使用 setInterval() 设置的计时器。
- **clearTimeout(*id*)**：清除使用 setTimeout() 设置的计时器。
- **atob(*str*)**：解码 base64 编码的字符串。
- **btoa(*str*)**：用 base64 编码字符串。
- **blur()**：从当前窗口移除焦点。
- **focus()**：将焦点设置到当前窗口。
- **getComputedStyle(*elemObj*, [ *pseudoSelectorStr* ])**：获取 HTML 元素的计算 CSS 属性和值，返回 CSSStyleDeclaration 对象。
- **getSelection()**：返回 Selection 对象，表示用户选择的文本范围。
- **matchMedia(*mediaQueryStr*)**：返回 MediaQueryList 对象，表示指定的 CSS 媒体查询字符串。
- **requestAnimationFrame()**：请求浏览器在下一次重绘之前调用函数来更新动画。

## 16. Document

- **all**：返回对文档中所有 HTML 元素的引用。可如下使用：all[index]、all[name]、all.tags[tagName]。
- **documentElement**：返回 html 元素对象（Element）。只读。
- **head**：返回 head 元素。
- **body**：设置或返回文档的正文（body 元素）。
- **title**：设置或返回文档标题。
- **forms**：返回所有 form 元素对象（HTMLCollection）。只读。
- **embeds**：返回所有 embed 元素对象（HTMLCollection）。只读。
- **images**：返回所有 img 元素（HTMLCollection）。只读。
- **links**：返回拥有 href 属性的所有 area 和 a 元素（HTMLCollection）。只读。
- **scripts**：返回所有 script 元素（HTMLCollection）。只读。
- **doctype**：返回与文档关联的文档类型声明对象（DocumentType）。没有则返回 null。只读。文档类型声明对象属性 name 返回名称。
- **baseURI**：返回文档的绝对基准 URI。只读。
- **cookie**：返回文档中所有 cookie 的名称值对。
- **activeElement**：返回文档中当前获得焦点的元素。只读。
- **documentURI**：设置或返回文档的位置（string）。没有则返回 null。
- **domain**：返回文档服务器的域名。没有则返回 null。
- **lastModified**：返回文档更新的日期和时间。只读。
- **readyState**：返回文档的（加载）状态（String）。
    - uninitialized：尚未开始加载。
    - loading：正在加载。
    - loaded：已加载。
    - interactive：已加载到足以与之交互。
    - complete：完全加载。
- **referrer**：返回加载当前文档的文档的 URL。
- **URL**：返回文档的完整 URL（String）。
- **characterSet**：返回文档的字符编码。
- **designMode**：控制整个文档是否应可编辑。设置为 on 或者 off（默认）。
- **defaultView**：返回与文档关联的窗口对象 window，若无可用，则返回 null。
- **implementation**：返回处理此文档的 DOMImplementation 对象（DocumentImplementation）。
- **getElementById(*idStr*)**：返回拥有指定值的 ID 属性的元素（Element）。如果未找到元素，返回 null。
- **getElementsByTagName(*tagNameStr*)**：返回包含拥有指定标签名称的所有元素（实时 HTMLCollection）。只读。*tagNameStr* 为 * 则返回所有元素。
- **getElementsByName(*nameStr*)**：返回包含拥有指定名称的所有元素（NodeList）。
- **getElementsByClassName(*classStr*)**：返回包含拥有指定类名的所有元素（实时 HTMLCollection）。只读。
- **querySelector(*cssSelectorStr*)**：返回与文档中指定的 CSS 选择器匹配的第一个元素。未找到返回 null。
- **querySelectorAll(*cssSelectorStr*)**：返回包含与文档中指定的 CSS 选择器匹配的所有元素（静态 NodeList）。
- **createElement(*tagNameStr*)**：创建元素节点。
- **createTextNode(*txtStr*)**：创建文本节点。
- **createAttribute(*nameStr*)**：创建属性节点。属性节点 value 属性设置属性值。
- **createComment([ *commentStr* ])**：创建带有指定文本的注释节点。
- **createDocumentFragment()**：创建空 DocumentFragment 节点。可避免页面多次渲染，以提高性能。
- **createEvent(*eventNameStr*)**：创建新事件。
    - *eventNameStr*
        - AnimationEvent
        - ClipboardEvent
        - DragEvent
        - FocusEvent
        - HashChangeEvent
        - InputEvent
        - KeyboardEvent
        - MouseEvent
        - PageTransitionEvent
        - PopStateEvent
        - ProgressEvent
        - StorageEvent
        - TouchEvent
        - TransitionEvent
        - UiEvent
        - WheelEvent
- **createTextNode(*txtStr*)**：创建文本节点。
- **hasFocus()**：如果文档（或文档中的任何元素）已获得焦点，则返回 true。
- **adoptNode(*nodeObj*)**：采用另一个文档中的节点。返回采用得节点。另一个文档中的节点被移除。
- **importNode(*nodeObj*, *includCchildBool*)**：从另一个文档导入节点。
- **normalize()**：删除空文本节点，并连接相邻节点。
- **open()**：打开 HTML 输出流以收集来自 document.write() 的输出。
- **write(*htmlStr*, ...)**：写入 HTML 输出流。
- **writeln(*htmlStr*, ...)**：写入 HTML 输出流。
- **close()**：关闭之前用 document.open() 打开的输出流。
- **addEventListener(*eventStr*, *func*, [ *captureBool* ])**：将事件处理程序附加到文档。
- **removeEventListener(*eventStr*, *func*, [ *captureBool* ])**：从文档中删除事件处理程序（已使用 addEventListener() 方法附加的）。

## 17. Screen

- **width**：返回以像素计的访问者屏幕宽度。只读。
- **height**：返回以像素计的访问者屏幕的高度。只读。
- **availWidth**：返回屏幕的宽度（Windows 任务栏除外）。只读。以像素为计。
- **availHeight**：返回屏幕的高度（Windows 任务栏除外）。只读。以像素为计。
- **colorDepth**：返回用于显示一种颜色的比特数。只读。
- **pixelDepth**：返回屏幕的像素深度。只读。
- **bufferDepth**：设置或返回调色板的比特深度。
- **deviceXDPI**：返回显示屏幕的每英寸水平点数。
- **deviceYDPI**：返回显示屏幕的每英寸垂直点数。
- **fontSmoothingEnabled**：返回用户是否在显示控制面板中启用了字体平滑。
- **logicalXDPI**：返回显示屏幕每英寸的水平方向的常规点数。
- **logicalYDPI**：返回显示屏幕每英寸的垂直方向的常规点数。
- **updateInterval**：设置或返回屏幕的刷新率。

## 18. Location

- **href**：设置或返回整个 URL。可用于 a 标签对象，作用于 a 标签的 heaf 属性。
- **origin**：返回 URL 的协议、主机名和端口号。只读。可用于 a 标签对象，作用于 a 标签的 heaf 属性。
- **host**：设置或返回 URL 的主机名和端口号。可用于 a 标签对象，作用于 a 标签的 heaf 属性。
- **hostname**：设置或返回 URL 的主机名。可用于 a 标签对象，作用于 a 标签的 heaf 属性。
- **hash**：设置或返回 URL 的锚部分 (#)。包含井号。可用于 a 标签对象，作用于 a 标签的 heaf 属性。
- **pathname**：设置或返回 URL 的路径名。可用于 a 标签对象，作用于 a 标签的 heaf 属性。
- **protocol**：设置或返回 URL 的协议。可用于 a 标签对象，作用于 a 标签的 heaf 属性。
- **port**：设置或返回 URL 的端口号（String）。若是协议默认端口号可能返回空串。可用于 a 标签对象，作用于 a 标签的 heaf 属性。
- **search**：设置或返回 URL 的查询字符串部分。可用于 a 标签对象，作用于 a 标签的 heaf 属性。
- **assign(*urlStr*)**：加载新文档。
- **reload()**：重新加载当前文档。刷新页面。
- **replace(*urlStr*)**：用新文档替换当前文档。

## 19. History

- **length**：返回历史列表中的 URL 数量。
- **back()**：加载历史记录列表中的上一个 URL。
- **forward()**：加载历史列表中下一个 URL。
- **go(*int*)**：从历史列表中加载一个特定的 URL。

## 20. Navigator

- **appName**：返回浏览器的名称。只读。Netscape。
- **appCodeName**：返回浏览器的代码名称。只读。Mozilla。
- **appVersion**：返回浏览器的版本信息。只读。
- **product**：返回浏览器引擎的产品名称。只读。Gecko。
- **userAgent**：返回由浏览器发送到服务器的用户代理报头（user-agent header）。只读。
- **platform**：返回浏览器平台（操作系统）。只读。
- **language**：返回浏览器语言。只读。
- **onLine**：如果浏览器在线，则返回 true。只读。
- **cookieEnabled**：如果启用了浏览器 cookie，则返回 true。
- **appMinorVersion**：返回浏览器的次级版本。
- **browserLanguage**：返回当前浏览器的语言。
- **cpuClass**：返回浏览器系统的 CPU 等级。
- **systemLanguage**：返回 OS 使用的默认语言。
- **userLanguage**：返回 OS 的自然语言设置。
- **geolocation**：返回用户位置的 Geolocation 对象。只读。
- **javaEnabled()**：如果浏览器启用了 Java，则返回 true。

## 21. LocalStorage

- **length**：返回存储在 Storage 对象中的数据项数。
- **setItem(*nameStr*, *val*)**：将数据项存储在 storage 中。
- **getItem(*nameStr*)**：从存储中检索数据项。
- **key(*indexIint*)**：返回存储中第 *indexUint* 个键的名称。
- **removeItem(*nameStr*)**：从存储中删除该键。
- **clear()**：清空所有键。

## 22. SessionStorage

- **length**：返回存储在 Storage 对象中的数据项数。
- **setItem(*nameStr*, *val*)**：将数据项存储在 storage 中。
- **getItem(*nameStr*)**：从存储中检索数据项。
- **key(*indexUint*)**：返回存储中第 *indexUint* 个键的名称。
- **removeItem(*nameStr*)**：从存储中删除该键。
- **clear()**：清空所有键。

## 23. Console

- **clear()**：清空控制台。
- **assert(*expBool*, *msgStr*)**：如果 *expBool* 为 false，则将 *msgStr* 写入控制台。
- **count([ *prefixStr* ])**：记录这个对 count() 的特定调用已被调用的次数。*prefix* 默认值为 default。
- **table(*dataObjOrArr*, [ *columnArr* ])**：将表格式的数据显示为表。
- **trace([ *labelStr* ])**：将堆栈跟踪输出到控制台。
- **group([ *labelStr* ])**：在控制台中创建新的分组。
- **groupCollapsed([ *labelStr* ])**：在控制台中创建新的行内组。但是新组是折叠创建的。用户需要使用按钮将其展开。
- **groupEnd()**：退出控制台中的当前分组。
- **time([ *labelStr* ])**：启动计时器（可跟踪操作需要多长时间）。
- **timeEnd([ *labelStr* ])**：停止以前由 console.time() 启动的计时器。
- **log(*val*)**：将消息输出到控制台。
- **info(*val*)**：将信息性消息输出到控制台。
- **warn(*val*)**：将警告消息输出到控制台。
- **error(*val*)**：将错误消息输出到控制台。

## 24. Geolocation

- **getCurrentPosition(*func(obj)*, [ *func(err)* ])**
    - *obj*.coords.latitude：以十进制数表示的纬度（始终返回）。
    - *obj*.coords.longitude：以十进制数表示的经度（始终返回）。
    - *obj*.coords.accuracy：位置精度（始终返回）。
    - *obj*.coords.altitude：平均海平面以上的高度（以米计）（如果可用则返回）。
    - *obj*.coords.altitudeAccuracy：位置的高度精度（如果可用则返回）。
    - *obj*.coords.heading：从北顺时针方向的航向（如果可用则返回）。
    - *obj*.coords.speed：以米/秒计的速度（如果可用则返回）。
    - *obj*.timestamp：响应的日期/时间（如果可用则返回）。
- **watchPosition(*func(obj)*, [ *func(err)* ])**
- **clearWatch()**：注销先前使用 Geolocation.watchPosition() 安装的位置/错误监视处理程序。

## 25. Element

- **ownerDocument**：返回元素的根元素（文档对象）。
- **accessKey**：设置或返回元素的快捷键（String）。
- **attributes**：返回元素中的属性集合（NamedNodeMap）。NamedNodeMap 有 length 属性，可使用中括号运算符，NamedNodeMap[index].value 获取属性的值。
- **childElementCount**：返回元素的子元素数。只读。
- **firstElementChild**：返回元素的第一个子元素（Node）。只读。没有则返回 null。
- **lastChild**：返回元素的最后一个子节点（Node）。只读。没有则返回 null。
- **lastElementChild**：返回元素的最后一个子元素。只读。没有则返回 null。
- **childNodes**：返回元素的子节点（包括文本和注释节点）的集合（NodeList）。只读。
- **firstChild**：返回元素的第一个子节点（Node）。没有则返回 null。
- **children**：返回元素的子元素的集合（HTMLCollection）。
- **nextSibling**：返回位于相同节点树层级的下一个节点（Node）。只读。不存在返回 null。
- **nextElementSibling**：返回位于相同节点树层级的下一个元素。只读。不存在返回 null。
- **previousSibling**：返回位于相同节点树层级的上一个节点（Node）。只读。没有则返回 null。
- **previousElementSibling**：返回位于相同节点树层级的上一个元素。只读。没有则返回 null。
- **parentNode**：返回元素的父节点（Node）。只读。没有则返回 null。
- **parentElement**：返回元素的父元素节点（Element）。只读。没有则返回 null。
- **classList**：返回元素的 CSS 类名（DOMTokenList）。
- **className**：设置或返回元素的 class 属性。
- **clientHeight**：返回元素的高度，包括内边距。以像素计，只读。
- **clientWidth**：返回元素的宽度，包括内边距。以像素计，只读。
- **clientLeft**：返回元素左边框的宽度。以像素计，只读。
- **clientTop**：返回元素左边框的宽度。以像素计，只读。
- **scrollHeight**：返回元素的整体高度，包括内边距。只读。
- **scrollWidth**：返回元素的整体宽度，包括内边距。只读。
- **scrollLeft**：设置或返回元素内容水平滚动的像素数。
- **scrollTop**：设置或返回元素内容垂直滚动的像素数。
- **offsetHeight**：返回元素的高度，包括内边距、边框和滚动条。只读。
- **offsetWidth**：返回元素的宽度，包括内边距、边框和滚动条。只读。
- **offsetLeft**：返回元素的水平偏移位置。只读。
- **offsetTop**：返回元素的垂直偏移位置。只读。
- **offsetParent**：返回元素的偏移容器（Node）。如果元素不可见（display="none"），则属性返回 null。
- **contentEditable**：设置或返回元素的内容是否可编辑。
- **isContentEditable**：如果元素的内容是可编辑的，则返回 true。只读。
- **dir**：设置或返回元素的 dir 属性的值。ltr、rtl、auto。
- **id**：设置或返回元素 id 属性的值。
- **title**：设置或返回元素的 title 属性值。
- **lang**：设置或返回元素的 lang 属性值。
- **style**：设置或返回元素 style 属性的值（CSSStyleDeclaration）。
- **tabIndex**：设置或返回元素的 tabindex 属性的值。如果该数为负，则从 Tab 键顺序中删除该元素。
- **tagName**：返回元素的标签名。只读。
- **namespaceURI**：返回元素的命名空间 URI（String）。只读。如果元素不在命名空间中，返回 null。
- **innerHTML**：设置或返回元素的内容。
- **outerHTML**：设置或返回元素的内容（包括开始标签和结束标签）。
- **innerText**：设置或返回节点及其后代的文本内容。设置属性时，所有子节点都将被删除并仅由一个新文本节点替换。
- **outerText**：设置或返回节点及其后代的外部文本内容。
- **textContent**：设置或返回节点及其后代的文本内容。
- **nodeType**：返回节点的类型（Number）。只读。返回数字。
    - 1：ELEMENT_NODE 元素。
    - 2：ATTRIBUTE_NODE 属性。
    - 3：TEXT_NODE 元素或属性中的文本内容。
    - 4：CDATA_SECTION_NODE 文档中的 CDATA 部分。
    - 5：ENTITY_REFERENCE_NODE 实体引用。
    - 6：ENTITY_NODE 实体。
    - 7：PROCESSING_INSTRUCTION_NODE 处理指令。
    - 8：COMMENT_NODE 注释。
    - 9：DOCUMENT_NODE 整个文档。
    - 10：DOCUMENT_TYPE_NODE 向为文档定义的实体提供接口。
    - 11：DOCUMENT_FRAGMENT_NODE 轻量级的 Document 对象。
    - 12：NOTATION_NODE 在 DTD 中声明的符号。
- **nodeValue**：设置或返回节点的值。元素和文档节点返回 null。文本节点返回文本内容。属性节点返回属性值。注释节点返回文本内容。
- **nodeName**：返回节点的名称。只读。元素节点是标签名（大写）。属性节点是属性名称。文本节点总是 #text。文档节点总是 #document。
- **getAttribute(*attrStr*)**：返回元素属性的值。不存在则返回 null。
- **getAttributeNode(*attrStr*)**：返回属性节点（Attribute）。不存在则返回 null。
- **hasAttribute(*nameStr*)**：如果元素拥有给定属性，则返回 true。
- **hasAttributes()**：如果元素拥有任何属性，则返回 true。如果节点不是元素节点，则返回值始终是 false。
- **setAttribute(*nameStr*, *valueStr*)**：设置或更改属性的值。
- **setAttributeNode(*attrObj*)**：设置或更改属性节点。返回被替换属性节点的 Attr 对象，没有则返回 null。
- **removeAttribute(*attrStr*)**：从元素中移除属性。
- **removeAttributeNode(*attrStr*)**：移除属性节点，并返回移除的节点（Attribute）。
- **appendChild(*node*)**：将新的子节点添加到元素中，作为最后一个子节点。返回添加的节点。
- **removeChild()**：从元素中移除子节点。返回被删除的节点（Node）。没有则返回 null。
- **replaceChild(*newNodeObj*, *oldNodeObj*)**：替换元素中的子节点。返回被替换的节点（Node）。
- **cloneNode([ *deepBool* ])**：创建节点的副本，并返回该副本（Node）。
- **isEqualNode(*nodeObj*)**：检查两个元素是否相等。
- **isSameNode(*nodeObj*)**：检查两个元素是否是同一个节点。
- **remove()**：从 DOM 中移除节点。
- **closest(*cssSelectorStr*)**：返回自身开始向父元素搜索与 CSS 选择器匹配的元素。未找到返回 null。
- **compareDocumentPosition(*nodeObj*)**：比较两个节点，并返回整数值。整数值是下列值之一或者是多个值的和。
    - 1：节点不属于同一个文档。
    - 2：第一个节点位于第二个节点之后。
    - 4：第一个节点位于第二个节点之前。
    - 8：第一个节点位于第二个节点内。
    - 16：第二个节点位于第一个节点内。
    - 32：节点是同一元素上的属性。
- **contains(*node*)**：如果节点是节点的后代，则返回 true。
- **matches(*cssSelectorStr*)**：如果元素与给定的 CSS 选择器匹配，则返回 true。
- **hasChildNodes()**：如果元素有任何子节点，则返回 true。
- **insertAdjacentElement(*positionStr*, *elemObj*)**：在相对于元素的位置插入新的 HTML 元素。
    - *positionStr*
        - afterbegin：在元素开始之后（第一个子元素）。
        - afterend：元素后。
        - beforebegin：元素前。
        - beforeend：在元素结束之前（最后一个子元素）。
- **insertAdjacentText(*positionStr*, *txtStr*)**：在相对于元素的位置插入文本。
- **insertBefore(*newnodeObj*, [ *existingnodeObj* ])**：在现有子节点之前插入新子节点。返回被插入的节点。不提供 *existingnodeObj* 则在结尾处插入节点。
- **addEventListener(*eventStr*, *funcObj*, [ *captureBool* ])**：将事件处理程序附加到元素。
- **removeEventListener(*eventStr*, *funcObj*, [ *captureBool* ])**：删除已使用 addEventListener() 方法附加的事件处理程序。
- **blur()**：元素上移除焦点。
- **focus()**：元素获得焦点。
- **click()**：模拟鼠标单击元素。
- **getBoundingClientRect()**：方法返回元素的大小及其相对于视口的位置（DOMRect）。
- **getElementsByClassName(*classesStr*)**：返回拥有给定类名的子元素的集合（NodeList）。
- **getElementsByTagName(*tagNameStr*)**：返回拥有给定标签名称的子元素的集合（NodeList）。参数值 \* 返回元素的所有子元素。
- **isDefaultNamespace(*nsStr*)**：如果给定的 namespaceURI 是默认值，则返回 true。
- **normalize()**：合并元素中相邻的文本节点，并移除空的文本节点。
- **querySelector(*cssSelectorStr*)**：返回与 CSS 选择器匹配的第一个子元素。没有则返回 null。
- **querySelectorAll(*cssSelectorStr*)**：返回与 CSS 选择器匹配的所有子元素（静态 NodeList）。
- **scrollIntoView([ *alignBool* ])**：将元素滚动到浏览器窗口的可见区域。
    - *alignBool*
        - true：元素的顶部将与可滚动祖先可见区域的顶部对齐
        - false：元素的底部将与可滚动祖先可见区域的底部对齐
        - 如果省略，它将滚动到元素的顶部。

### 25.1. Input Element

- **validity**：包含与输入元素有效性相关的布尔属性。
    - **customError**：如果设置了自定义有效性消息，则设置为 true。
    - **patternMismatch**：如果元素的值与其 pattern 属性不匹配，则设置为 true。
    - **rangeOverflow**：如果元素的值大于其 max 属性，则设置为 true。
    - **rangeUnderflow**：如果元素的值小于其 min 属性，则设置为 true。
    - **stepMismatch**：如果元素的值对其 step 属性无效，则设置为 true。
    - **tooLong**：如果元素的值超过其 maxLength 属性，则设置为 true。
    - **typeMismatch**：如果元素的值对其 type 属性无效，则设置为 true。
    - **valueMissing**：如果元素（具有 required 属性）没有值，则设置为 true。
    - **valid**：如果元素的值有效，则设置为 true。
- **validationMessage**：包含当有效性为 false 时浏览器将显示的消息。
- **willValidate**：指示是否将验证 input 元素。
- **checkValidity()**：如果 input 元素包含有效数据，则返回 true。
- **setCustomValidity(*txt*)**：设置 input 元素的 validationMessage 属性。

## 26. Event

- **altKey**：返回触发鼠标事件时是否按下了 ALT 键。
- **animationName**：返回动画的名称。只读。
- **bubbles**：返回特定事件是否为冒泡事件。
- **button**：返回触发鼠标事件时按下的鼠标按钮。0：鼠标左键，1：滚轮按钮或中间按钮（如果有），2：鼠标右键。对于左手配置的鼠标，返回值是相反的。
- **buttons**：返回触发鼠标事件时按下的鼠标按钮。如果按下了多个按钮，则将这些值组合相加起来产生一个新数字。1：鼠标左键，2：鼠标右键，4：滚轮按钮或中间按钮，8：第四个鼠标按钮（通常是“浏览器返回”按钮），16：第五个鼠标按钮（通常是“浏览器前进”按钮）。对于左手配置的鼠标，返回值是相反的。
- **cancelBubble**：设置或返回事件是否应该向上层级进行传播。
- **changeTouches**：返回在上一触摸与该触摸之间其状态已更改的所有触摸对象的列表。
- **charCode**：返回触发 onkeypress 事件的键的 Unicode 字符代码。只读。
- **clientX**：返回触发鼠标事件时，鼠标指针相对于当前窗口的水平坐标。
- **clientY**：返回触发鼠标事件时，鼠标指针相对于当前窗口的垂直坐标。

## 27. Attribute

- **name**：返回属性的名称。只读。
- **value**：设置或返回属性的值。
- **specified**：如果已指定属性，则返回 true，否则返回 false。

## 28. 全局函数

- **call(*val*, [ *arg*, ... ])**

    ```javascript
    var person1 = {
      fullName: function () {
        return this.firstName + " " + this.lastName;
      }
    }
    var person2 = {
      firstName: "Bill",
      lastName: "Gates",
    }
    person1.fullName.call(person2) // 会返回 "Bill Gates"
    ```
- **apply(*val*, *arrArg*)**
- **bind(*var*)**：绑定 this 对象。
- **JSON.parse(*str*, [ *func(key, val)any* ])**：解析 JSON 字符串并返回 JavaScript 对象。func 用于转换结果的函数。为每个项目调用该函数。任何嵌套对象都在父对象之前进行转换。如果此函数返回有效值，则将项目值替换为转换后的值。如果此函数返回 undefined，则删除该项目。
- **JSON.stringify(*val*, [ *func(key, val)any | arr*, *spaceNumOrStr* ])**：将 JavaScript 对象转换为 JSON 字符串。*func* 用于转换结果的函数或数组。如果该参数是函数，则在序列化过程中，被序列化的值的每个属性都会经过该函数的转换和处理；如果该参数是数组，则只有包含在这个数组中的属性名才会被序列化到最终的 JSON 字符串中；如果该参数为 null 或者未提供，则对象所有的属性都会被序列化。*space* 字符串或数字值。指定缩进用的空白字符串。如果参数是个数字，它代表有多少的空格；上限为 10。该值若小于 1，则意味着没有空格；如果该参数为字符串（当字符串长度超过 10 个字母，取其前 10 个字母），该字符串将被作为空格；如果该参数没有提供（或者为 null），将没有空格。
1. **eval(*codeStr*)**：评估字符串并像脚本代码一样执行它。
1. **postMessage()**：向 Worker 发送消息。
1. **fetch(*url*)**：返回 Promise 对象。结合 await 使用返回 Response。
1. **encodeURI(*str*)**：对 URI 进行编码。不对以下字符编码：, / ? : @ & = + $ #。
1. **decodeURI(*str*)**：解码 URI。不对以下字符编码：, / ? : @ & = + $ #。
1. **encodeURIComponent(*str*)**：对 URI 组件进行编码。
1. **decodeURIComponent(*str*)**：解码 URI 组件。
1. **import(*pathJs*)**：动态导入 js 文件，返回 Promise 对象。

## 29. canvas

- **canvas.getContext("2d")**
- **fillStyle**
- **strokeStyle**
- **transform(*n*, *n*, *n*, *n*, *n*, *n*)**
- **beginPath()**
- **ellipse(*x*, *y*, *n*, *n*, *n*, *n*, *n*)**
- **fill()**
- **stroke()**
- **moveTo(*n*, *n*)**
- **lineTo(*n*, *n*)**
- **getImageData()**

## 30. Plotly

- **Plotly.newPlot(*divId*, *data*, *layout*)**
    - *data*：object_array。
        - x：array。坐标点。
        - labels：array。饼图各维度名称
        - y：array。坐标点。
        - values：array。饼图各维度比重。
        - hole：number。饼图空心占比。
        - mode：string。markers 散点图、lines 折线图。
        - type：string。bar 条形图、pie 饼图。
        - orientation：string。h 水平条形。
        - marker：object。
            - color：string。条形颜色。
    - *layout*：object。
        - xaxis：object。
            - range：array。坐标轴起点和终点。
            - title：string。坐标轴描述。
        - yaxis：object。
            - range：array。坐标轴起点和终点。
            - title：string。坐标轴描述。
        - title：string。图形描述。

## 31. HTMLCollection

- **length**：返回元素数。只读。
- **item(*indexUint*)**：返回指定索引处的元素（Element）。如果索引号超出范围，则返回 null。
- **namedItem(*idOrNameStr*)**：返回有指定 ID 或名称的元素（Element）。如果元素不存在，则返回 null。

## 32. NodeList

- **length**：返回节点数。只读。
- **entries()**：从列表中返回带有键值对的迭代器。
- **forEach(*func(val, [ indexUint, arr ])*, [ *thisVal* ])**：为列表中的每个节点执行回调函数。
- **item()**：返回指定索引处的节点。如果索引超出范围，返回 null。
- **keys()**：使用列表中的键返回迭代器（Iterator）。
- **values()**：使用列表中的值返回迭代器（Iterator）。

## 33. DocumentImplementation

- **hasFeature(*str*, *str*)**：用于检查浏览器是否支持 DOM 模块。

## 34. DOMTokenList

- **length**：返回列表中的令牌数。只读。
- **value**：以字符串形式返回令牌列表。
- **add(*str*, ...)**：将一个或多个令牌添加到列表中。
- **item(*index*)**：返回指定索引处的令牌。如果索引超出范围，返回 null。
- **remove(*str*, ...)**：从列表中删除一个或多个令牌。
- **contains(*str*)**：如果列表包含类，则返回 true。
- **replace(*old*, *new*)**：替换列表中的令牌。返回 bool。
- **supports(*str*)**：如果令牌是属性支持的令牌之一，则返回 true。
- **toggle(*str*)**：在列表中的令牌之间切换。
- **forEach(*func(str, [ index, arr, thisVal ])*)**：为列表中的每个令牌执行回调函数。
- **keys()**：返回包含列表中键的迭代器。
- **values()**：返回带有列表中值的迭代器。
- **entries()**：从列表中返回带有键/值对的迭代器。

## 35. DOMRect

- **left**
- **top**
- **right**
- **bottom**
- **x**
- **y**
- **width**
- **height**

## 36. NamedNodeMap

- **length**：返回 NamedNodeMap 中的属性数。只读。
- **getNamedItem(*nameStr*)**：从 NamedNodeMap 返回属性节点（按名称）。
- **item(*indexUint*)**：从 NamedNodeMap 返回属性节点（按索引）。如果索引号超出范围，则返回 null。
- **removeNamedItem(*nameStr*)**：删除属性（节点）。返回被删除的属性节点。
- **setNamedItem(*nodeObj*)**：按名称设置属性（节点）。返回替换后的属性节点，否则返回值为 null。

## 37. 类型化数组

- Int8Array
- Uint8Array
- Uint8ClampedArray：如果将一个元素设置为 0-255 范围之外的值，它将默认为 0 或 255。
- Int16Array
- Uint16Array
- Int32Array
- Uint32Array
- Float32Array
- Float64Array
- BigInt64Array
- BigUint64Array
- **BYTES_PER_ELEMENT**：计算用于存储一个元素的字节数的属性。
- **name**：返回类型化数组的名称。
- **of(*num*, ...)**：用数组创建类型化数组。static 方法。
- **fill(*num*)**：用一个值填充所有元素。
- **find(*func(val)bool*)**：返回满足条件的第一个元素。
- **some(*func(val)bool*)**：如果一个元素满足条件，则返回 true。

# 六、Chart.js

1. https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js。

## 1. API

- **new Chart(*canvasId*, *obj*)**
    - *obj*：object。
        - type：string。scatter 散点图、line 折线图、bar 条形图、horizontalBar 水平条形图、pie 饼形图、doughnut 甜甜圈图。
        - data：object。
            - labels：array。坐标。
            - datasets：object_array。
                - fill：bool。
                - pointRadius：number。
                - pointBackgroundColor：color or array。
                - backgroundColor：color。
                - borderColor：color。
                - data：object_array or array。[{x:50, y:7}]
        - options：object。
            - title：object。
                - display：bool。
                - text：string。
                - fontSize：number。
            - legend：object。
                - display：bool。
            - scales：object。
                - xAxes：object_array。
                    - ticks：object。
                        - min：number。
                        - max：numebr。
                - yAxes：object_array。
                    - ticks：object。
                        - min：number。
                        - max：numebr。
                        - beginAtZero：bool。
