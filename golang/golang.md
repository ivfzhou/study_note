# 一、笔记

1. `go install golang.org/x/tools/cmd/goimports`

## 1. 模块

1. `// indirect` 出现情形：项目的间接依赖版本号升级后。在 go1.17 所有间接依赖在独立块中列出。
1. exclude 和 replace 命令只在主项目中生效。被 exclude 的依赖取它高一级的版本代替，去除伪版本。replace 依赖为系统路径时须以 ./ 或 ../ 开头，替换依赖的 module_path 须匹配。
1. `+incompatible` 出现情形：依赖没有 go.mod，module_path 路径部分匹配相对的仓库根路径。这类依赖升级不考虑语义化版本限制。可能出现在伪版本后。
1. module_path 字符限制：只能是 ASCII 字母数字和 - _ . ~ 组成。至少有一个 /，/ 不能在开头末尾。. 不能是路径部分元素的开头或末尾。第一个 . 前面不能是以 ~ 数字结尾。
1. 项目能被引用的要求：第一个 / 前部分作为域名，须为小写字母数字 . - 组成，- 不能在开头，必须包含一个 .。最后一部分 /vN，如果 N 为数字，则不能以 0 开头，也不能是 1，也不能包含 .。路径部分须匹配 go.mod 文件相对与仓库根路径。
1. go.mod 文件只有单行注释。
1. 依赖多个不同版本号的相同依赖，取最高版本使用。
1. file path package 校验规则是由 golang.org/x/mod/module 定义。

## 2. 包

1. 一个文件夹下只可以一个包名，测试文件包名可以加后缀 _test。
1. 文件夹名为 internal 下的包只能被同 internal 父文件夹下的包引用。
1. 文件头注释并空一行，// +build linux darwin windows ignore !windows。
1. 生成的可执行文件，文件名是其父目录名，或者第一个 .go 文件名。
1. 标识符可由任意字符组成，但不能数字开头。只有大写字母是可导出。中日韩字符开头的是不导出。
1. 程序启动过程：main 函数文件中 imports => const => var => init() => main()
1. 同一项目的 var 和 init() 只会运行一次。
1. init 函数不可被调用，可多次定义。且包名不能是 init。

## 3. 结构体

1. 结构体中匿名成员实际类型不能是指针类型，非接口类型可以加取址符 * 一次。
1. 可直接访问匿名成员中属性或方法，若多个匿名成员在同层级有相同的字段或函数，则不可以直接引用。选择匿名类型字段时会选择距离路径最短的。
1. 如果结构体 S 包含匿名字段类型 T，则 S 拥有 T 的方法，*S 拥有 *T 和 T 的方法。如果结构体 S 包含匿名字段 *T，则 S 和 *S 都拥有 T 和 *T 的方法。
1. 接收器类型不能是接口、指针，且其类型定义须在本包中。
1. 不允许超过一个 * 指针类型作为方法接受器。
1. 指针值可以调用值方法和指针方法，但值不能直接调指针方法。

## 4. 通道

1. select 中的 case 会伪随机选择。
1. select 开始计算通道接收和发送表达式后，改变通道值不会再改变 select 选择。左边赋值语句不会立马计算。
1. 向 nil 通道发送或接受会永久阻塞。只读通道不可关闭，一般由发送者关闭通道。多次 close 会触发恐慌。关闭 nil 通道会 panic。
1. 通道操作符 <- 以最左边作用。chan (<-chan int) 需要打括号，不然是 chan<- chan int。
1. 竞态解决方法：1. 限制竟态变量在一个 goroutine 2. goroutine 串行绑定变量。

## 5. 内置函数

1. defer 语句中不能有部分内建函数或者 unsafe 包下的函数。
1. 不允许单独语句出现在 defer 中：append cap complex imag len make new real unsafe.Add unsafe.Alignof unsafe.Offsetof unsafe.Sizeof unsafe.Slice。
1. 无类型常量是高精度数值（最少 256 和 16bits）：bool、number、character、float、complex、string。
1. 常量语句只能是：类型是数字、布尔、字符串，变量是字面值，函数是内置函数。
1. 一元操作符比二元操作符优先级低。
1. 编译阶段的变量类型自动推断可使得代码重构维护变得容易。
1. struct{}{} 全局只有一份。
1. 不变量，不安全，临时量不能寻址。
1. range 表达式只会求值一次。
1. switch 中 case 表达式会依次计算值，相等后便执行 case，剩下的包括同一层的 case 表达式不再计算。
1. 短变量声明：至少有一个新变量定义（除去空标识），若有在相同作用域已声明的变量，则会被赋值。在初始化语句中（if、for、switch）和函数签名中的变量也将被赋值。
1. 在换行前的关键符号（像 int float64 等，return break continue fallthrough ++ -- ) }，以及数字字符字面量。）会被 lexer 插入分号。一对花括号一行时中间不需要分号。流程控制语句的左花括号不可折行。
1. array、slice、map 字面量可以忽略下标、元素类型名。struct 字面量可以忽略字段名但必须所有字段都按顺序列出。
1. Slice、Map、Func 不可以 == 比较。
1. 年：2006 06，月：1 01 Jan January，日：2 02 _2 __2 002 Mon Monday，时：3 03 15 pm PM，分：4 04，秒：5 05，毫秒：.0+ .9+，时区：MST -07 -0700 -070000 -07:00 -07:00:00 Z07 Z0700 Z070000 Z07:00 Z07:00:00。
1. .go 和 go.mod 文件都必须以 UTF8 字符编码。
1. 测试函数以 Test、Benchmark、Example、Fuzze 开头，后部分以大写字母开头。

# 二、GO Env

配置文件路径：

1. Windows10：`%USERPROFILE%\AppData\Roaming\go\env`
1. Debian12：`$HOME/.config/go/env`

## 1. 说明

- **GOPRIVATE**：设置私有模块名匹配模板前缀，匹配的模块将不会走代理拉取也不会检验模块检验和，模块名匹配模板由 path.Match 制定。多个模块名匹配模板逗号分割。为 GONOSUMDB、GONOPROXY 的默认值。
- **GOPROXY**：设置拉取模块的代理服务，默认为 https 协议。file 协议设置成模块缓存目录可使用缓存文件。多个代理使用逗号或管道符分割，逗号意为前者失败为 410、404 时请求使用下个代理，管道符意为前者失败了就使用下个代理。例子：off、direct、user:passwd@proxy.golang.org、goproxy.cn、mirrors.aliyun.com/goproxy。
- **GOSUMDB**：设置校验模块检验和的服务器，默认为 https 协议。多个服务器地址空格分割。例子：off、sum.golang.org+publickey、sum.golang.org goproxy.cn/sumdb/sum.golang.org。
- **GOROOT**：SDK 目录。
- **GOPATH**：设置模块缓存或者模块代码路径，默认为家目录下 go/。可以指定多个目录，第一个目录存放 bin、pkg、src。${GOPATH//://bin:}/bin 将所有 $GOPATH 目录的 bin 文件夹设为环境变量。
- **GO111MODULE**：表明代码以何种方式运行。默认为 auto（当前目录或子目录有 go.mod 文件则使用模块化模式）。on、off。
- **GOBIN**：设置安装的程序放置目录。
- **GOVCS**：控制版本管理程序拉取模块的规则，多个规则逗号分割。hostname:git|hg,*:off,public:off,private:all。
- **GONOPROXY**：设置拉取模块不使用代理的匹配模板前缀，匹配模板由 path.Match 规定。多个匹配模板逗号分割，默认值为 GOPRIVATE值。
- **GONOSUMDB**：设置不校验模块校验和的匹配模板前缀，匹配模板由 path.Match 规定。多个匹配模板逗号分割，默认值为 GOPRIVATE 值。
- **GOMODCACHE**：设置模块拉取缓存目录，默认为 GOPATH/pkg/mod。
- **GOROOT_BOOTSTART**：编译 Golang 时，使用工具链的位置。
- **GOFALGS**：设置为 -modcacherw 表示拉取的模块缓存文件为可读写权限。
- **GODEBUG**：
    - gctrace=1：打印每次 GC 的信息。
    - inittrace=1：打印每个包初始化信息。
    - schedtrace=*X*：每 X 毫秒打印运行时信息。
    - cpu.all=off：禁用所有的可选的指令扩展集。
    - cpu.*EXT*=off：禁用 EXT（小写字母）指令扩展集。
    - gocacheverify=1：每次构建不使用缓存。
    - http2client=0：客户端不使用 http2 协议。
    - http2server=0：服务器不使用 http2 协议。
    - http2debug=1：开启 http2-debug 日志。
    - http2debug=2：更多的 http2-debug 日志。
    - netdns=go：使用 go 的 DNS 解析器。
    - netdns=cgo：使用 C 库函数 DNS 解析器。
    - netdns=1：打印日志。多个值加号分割。
- **GOINSECURE**：使用不安全的方式拉取模块。程序可以使用 http 协议拉取模块，不会检验模块校验和。
- **GOTOOLDIR**：工具程序目录。

## 2. 设置私有代码模块

```bash
go env -w GOPRIVATE=gitee.com/ivfzhou,github.com/ivfzhou
```

## 3. 设置国内模块下载代理

```bash
go env -w GOPROXY=goproxy.cn,direct
go env -w GOSUMDB="sum.golang.org https://goproxy.cn/sumdb/sum.golang.org"
```

# 三、GOPROXY protocol

1. 多个代理服务间可以逗号或者管道符分割，逗号时：当前者获取依赖响应 404 410 时才使用下个代理服务，管道符：不管什么错误都请求下一个代理。direct 表示请求版本控制系统，off 表示不进行任何通信。
1. 并行请求每个可能的模块，如果能拉到多个模块包含这个包，取模块名最长的。
1. 代理服务器响应错误时，内容应为 text/palin charset=utf8 us-acsii。
1. 请求代理服务器的 URL：
    - $proxy_url/$module_path/@v/$version_query_suffix：其中大写字母将转成感叹号加小写。
    - $proxy_url/$module_path/@v/$version_query_suffix.mod：返回模块 go.mod 文件内容。
    - $proxy_url/$module_path/@v/list：表示列出所有可用版本号。
    - $proxy_url/$module_path/@v/$version_query_suffix.info：返回 JSON 格式信息，包含时间和版本号。
1. 代理服务器会将没有 go.mod 的模块，生成的 go.mod 只带上 module_path 返回。如果是请求 VCS 则由 go command 生成该文件。
1. $proxy_url/$module_path/@v/$version_query_suffix.zip，返回打包的 zip 模块文件。该文件不包含 vendor 目录，也不包含子或父模块。如果是下载子模块，而且子模块没有 LICENSE 文件，go command 会从父模块拷贝一个过来，这样 sum 会改变。
1. $proxy_url/$module_path/@latest，返回最新版本号信息。
1. 在 GOPATH 模式下，代理不能提供模块服务。
1. 直接请求不使用代理：GET请求 [ http | git | ssh...][s]://module_path?go-get=1，返回有头部标签 meta，name 为 go-import，值为 root_path (VCS|mod) (repo_url|proxy)。如果是 mod 则转而使用代理请求模块。
1. 模块 zip 文件最大不能超过 500mib，go.mod LICENSE 不能超过16mib。
1. go command 解压 module zip 生成的文件夹中会去掉空目录。也去掉包含 go.mod 的子目录。
1. 符号链接、vendor 目录和其它非法文件，go command 在创建模块zip时会忽略掉。
1. go command 解压出来的文件名大写字母会改成感叹号加小写。

# 四、关键字

break、continue、fallthrough、return、goto  
switch、case、default、if、else、for、select  
package、import、const、var、type、func  
chan、interface、map、struct  
defer、go、range  
//、/**/、_  
...、(、)、[、]、,、.、;、:

# 五、内建

true、false、iota、nil  
int、int8、int16、int32、int64  
uint、uint8、uint16、uint32、uint64、uintptr  
float32、float64、complex128、complex64  
bool、byte、rune、string、error  
len、cap  
make、new  
panic、recover  
real、imag、complex  
copy、append、delete  
print、println

# 六、运算符

每行优先级依次递减，同行优先级相同，运行时表达式从左到右计算。  
二元

```
* / % << >> & &^（按位至零，同为0不同取前者）
+ - | ^（按位异或，同0异1）
== != < <= > >=
&&
||
```

一元

```
-
+
^（按位取反）等价于其二元的操作数全是1。
!
*
&
```

```
+=、-=、/=、*=、%=
|=、&=、^=、&^=、<<=、>>=
<-、:=
++、--
```

# 七、语句

```golang
循环
for initailization; condition; post {}
for condition {}
for {}
for i, v := range [map slice array string channel] {}

分支
if initailization; condition {}
if initailization; condition {} else if initailization; condition {} else {}
switch initailization; val { case val1: case val2: default: }
switch val.(Type) { case type1: case type2: default: }
switch {case bool1: case bool2: default: }
select { case communication1: case communication2: default: }
goto flag

变量声明
s := expression
var s type
var s = expression
var s type = expression
s, s1, s2 := expression, expression, expression
var s, s1, s2 type
var s, s1, s2 = expression, expression, expression
var (
s type
s1 type
s2 type
)
var (
s = expression
s1 = expression
s2 = expression
)
var (
s type = expression
s1 type = expression
s2 type = expression
)

常量声明
const s （type可选） = const_expression
const s, s1 （type可选） = const_expression, const_expression
const (
s （type可选） = const_expression
s1 （type可选） = const_expression
)

结构体切片数组接口映射函数通道声明
type strcut_name struct {
 field_name field_type
}
make([]type, length, capacity)
[length]type{}
[...]type{}
type interface_name interface {
method_signture
}
make(map[key_type]value_type, init_capacity)
map[key_type]value_type{}
func (paramters)results{}
make(chan type, cache_length)
type (
name type
)

导包
import "package_path"
import."packge_path"
import _ "package_path"
import (
package_paths
)
```

# 八、数据类型大小

1. bool、uintptr、map、func、chan、*T = 1 字节
1. intN、uintN、floatN、complexN = N/8 字节
1. string(data len) = 2 字节
1. []T(data len cap) = 3 字节
1. interface(type value) = 2 字节

# 九、命令

1. version_query_suffix
    - module_path@latest：获取最新版依赖。
    - module_path@upgrade：默认为该版本查询后缀。如果当前项目的依赖已是最新释放版，则不会更新。
    - module_path@patch：go1.16 添加，必须要求当前依赖版本号。
    - module_path@git_commit_id
    - module_path@git_branch_name
    - module_path@git_tag_prefix_max：比 revision banch 优先。
    - module_path@>=<git_tag_comparison：比 revision banch 优先。
    - module_path@none：移除依赖。
2. **go**
    - **bug**：弹出浏览器提交 bug 报告。
    - **get *参数* *build_flags* *packages_or_module_paths***：更新修改模块依赖，编译安装包。可用通配符：all ....。默认作用为当前运行目录。当需要改动某个依赖版本号但又与参数指定的版本号不一致时，会有报告错误提示。对于retracted、deprecated依赖会有警告提示。编译生成的程序将放置 $GOBIN 或者 $GOPATH/bin 或者 $HOME/go/bin 下。
        - -d：不构建安装，仅更新修改依赖。go1.18 默认开启。
        - -u=*patch*：获取最新版本。=path 表示获取最新的补丁版依赖。
        - -t：编译对应的测试包，同 -u 使用将更新测试包的依赖。
        - -insecure：允许使用 HTTP 等不安全的方式下载依赖源码。
    - **install *build_flags* *packages***：安装或者构建缓存包。在 $GOROOT 下的包安装到 $GOROOT/bin|$GOTOOLDIR 下。带有版本号查询后缀时将是安装项目，不带则意味着作用当前项目。版本查询后缀需要一致。<=go1.15 不支持版本查询后缀。
    - **list *参数* *list_flags* *modules***：打印包和模块信息。默认作用当前项目。默认打印 module_path version 回收过时 replacement 信息。
        - -m：使用 module 模式。
        - -u：获取可更新信息。
        - -retracted：同时列出回收的版本号。go1.16 添加。
        - -versions：列出所有版本号，但不包括回收的版本号。
        - list_flags
            - -f '{{.String}}'：默认打印行为。
            - -json：JSON 格式打印。
    - **mod**
        - download *参数* *modules*：下载模块到 modulecache。默认作用当前项目。信息及错误信息输出到标准错误。
            - -x：打印信息到标准错误。
            - -json：JSON 格式打印下载的模块信息。
        - edit *editing_flags* *go.mod*：供工具脚本使用编辑 go.mod。
            - -fmt：格式化 go.mod。
            - -print：仅打印结果。
            - -json：仅打印 JOSN 格式结果。
            - editing_flafgs
                - -module *xxx*
                - -go=*ver*
                - -retract=*xxx*
                - -require=*xxx*
                - -replace *xxx=xxx*
                - -exclude=*xxx*
                - -dropretract=*xxx*
                - -droprequire *xxx*
                - -dropreplace=*xxx*
                - -dropexclude=*xxx*
        - graph *参数*：打印依赖信息。
            - -go=*ver*：指定解析依赖的语言版本。
        - init *modulepath*：生成 go.mod 文件，module_path 默认参考导包信息、vendor目录。
        - tidy *参数*：添加缺失依赖去除未使用的依赖，更新 go.sum。
            - -v：去除的包信息输出到标准错误。
            - -e：当出错时继续处理。go1.16 添加。
            - -go=*ver*：修改 go 版本。
            - -compat=*ver*：兼容版本号。
        - vendor *参数*：生成依赖文件以及 modules.txt 到 vendor 目录下，每次运行都会重新生成覆盖，vendor 下依赖不含依赖的测试文件。
            - -e：在 go1.16 中新增，加载包时出错不中断程序。
            - -v：打印这些依赖和包到标准错误。
        - verify：校验当前项目依赖的 hase，防被修改。
        - why *参数* *packages*：打印出包的在当前项目中的依赖链。
            - -m：使用为模块的依赖链。
            - -vendor：忽略测试文件的导包。
    - **version *参数* *files***：打印版本信息。打印程序的 go 版本号。默认打印 sdk go 版本号。可以搜寻一个目录下的 go 程序一一打印编译其 go 版本号。
        - -m：同时打印 go 程序自身信息。
        - -v：打印目录下非 go 程序文件。
    - **clean *参数* *路径包***：删除对象和缓存文件。
        - -i：删除 install 生成的文件。
        - -n：打印执行命令过程，不会实际执行命令。
        - -r：递归删除依赖文件。
        - -x：打印执行命令过程。
        - -cache：删除 go build 缓存。
        - -testcache：删除测试文件缓存。
        - -modcache：删除 module_cache，位置在 $HOME/go/mod。
    - **fix**：更新包的 API。
    - **run *pkg***：编译并运行代码，使用 . 表示运行当前包。
    - **test**：运行测试。
    - **tool**：运行 go 工具。
    - **vet *pkg***：检查打印包中错误。
    - **help *命令***：打印该命令的帮助信息。
    - **build *参数* *包...***：编译包及其依赖。不会编译测试文件。编译对象可以是 .go 结尾的文件。编译生成文件默认名为第一个 go 文件名，或者是模块名，或者第一个文件夹名。
        - -o *文件*：将编译结果导出到文件，或者文件夹下。
        - -a：强制重新编译。
        - -v：打印所有编译的包。
        - -n：打印编译命令过程信息。并不会编译。
        - -x：打印编译命令过程信息。
        - -p：数量指定编译时线程数，默认是 CPU 数。
        - -race：启用数据竞争分析。
        - -msan：启用与内存清理程序的互操作。
        - -work：打印所有临时创建的文件夹，并且编译后不删除。
        - -buildmode *模式*：指定编译模式。
        - -compiler *名称*：指定编译器。
        - -asmflags pattern=*值*：设置每次调用 go tool asm 时的参数。
        - -gccgoflags pattern=*值*：设置每次调用 gccgo compiler/linker 时的参数。
        - -ldflags pattern=*值*：设置每次调用 go tool link 时的参数。
        - -gcflags pattern=*值*：设置每次编译时的参数。
        - -installsuffix *后缀*：指定安装包目录名称后缀，使用 -race 会自动加后缀，如果在指定后缀会再加上 _race 后缀，同 -msan 一样。
        - -linkshared：构建之前将链接到共享库的代码。
        - -mod=*xxx*：可选：readonly（不使用 vendor 目录，不改写 go.mod，go.mod 不合规就报错）、vendor（使用 vendor 目录，不使用网络和 modulecache）、mod。默认情况 go1.14+ 下有 vendor 目录则为 vendor，其他情况为 readonly。
        - -modcacherw：将新创建的目录保留在模块缓存中可读写，而不是只读。
        - -modfile *文件.mod*：指定使用的 go.mod 文件，同时生成的 go.sum 文件也在该文件目录下。必须 .mod 结尾。
        - -pkgdir *文件夹*：指定加载和安装的包的位置。
        - -tags *逗号分隔的标签*：指定编译时的标签。
        - -trimpath：生成的可执行文件用绝对路径。
        - -toolexec *命令及参数*：调用工具链命令。
    - **doc *参数* *目标***：打印文档注释信息。
        - -all：显示所有文档。
        - -c：区分目标大小写。
        - -cmd：打印命令帮助文档。
        - -short：一行显示。
        - -src：显示代码信息。
        - -u：显示所有信息。
    - **env *参数***：打印或设置环境设置信息。
        - -json：格式化输出。
        - -u *键...*：重置为默认值。
        - -w *键=值*：设置值。
    - **fmt *参数* *路径包***：格式化代码。
        - -n：显示会执行的命令，实际不执行。
        - -x：打印执行命令过程。
        - -mod *模式*：设置模式，readonly、vendor。
    - **generate *参数* *路径包***：生成运行命令。
        - -x：打印命令执行过程。
        - -n：打印命令执行过程。但未执行。
        - -v：打印包和文件。
3. 编译参数作用于：build、clean、get、install、list、run、test：
    - -a：不使用缓存重新编译。
    - -n：打印执行命令，不执行编译。
    - -p *数字*：编译时使用线程球。默认为 GOMAXPROCS。
    - -race：开启变量竞争检查。
    - -msan：开启内存分析。
    - -v：打印编译了的包。
