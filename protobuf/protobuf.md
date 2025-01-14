# 笔记

- 数据类型：double、float、int32/64（编码负数效率低）、uint32/64、sint32/64（编码负数效率高）、fixed32/64（总是编码成4/8字节长度，编码大于2^28/2^56数字效率高）、sfixed32/64、bool、string（必须 utf8 编码）、bytes。
- 字段序号范围[1, 2^29-1(536870911)]，[19000, 19999]为字段描述占用。
- 语言数据类型是 32 位，但电报数据是 64 位，则会把多余位舍弃。
- sint32 最大值 2^30 10737418243(11)，最小值 2^-30 - 1 -1073741824。sint64 最大值 2^62-1 4611686018427387903(19)，最小值 2^-62 -4611686018427387904(19)。
- enum 使用 varint32 位编码。enum 第一个值必须是 0。
- oneof、map 字段不能 repeated。map key 只能是整数或字符串型。oneof 结构反序列化字节序列时存在多个字段，只使用字段序号最大的。
- map 中的键只能是整数类型或者字符串，不能是 bytes 和 enum 和浮点数。值不能是 map 类型。
- 数据反序列化时，不能识别的字段会放入 unknown_field。
- 在 proto3 中数字类型的 repeated 默认 packed。没有 packed 时，字节序列将可能插入其它字段零散出现，但总的顺序不会保持不变。只有数字类型可以 packed。
- 转 JSON 时，map 的 key 转成字符串型，bytes 使用 base64 编码的字符串。
- 环境变量 GOLANG_PROTOBUF_REGISTRATION_CONFLICT=warn 控制 pb 命名冲突。

# 命令

- protoc *参数* *proto文件*...：根据给定的参数解析 .proto 文件，生成对应语言文件。
    - -I *路径* --proto_path=*路径*：定义 import 路径。可以多次使用，将按顺序搜索。默认为当前路径。若未定义将参考 --descriptor_set_in 引用的 proto。
    - --version：打印版本信息然后退出。
    - -h --help：打印帮助信息。
    - --encode=msg_type：从标准输入读取格式化文本，编码后从标准输出输出。
    - --deterministic_output：当使用 --encode 时。
    - --decode=msg_type：与 --encode 作用相反。
    - --decode_raw：解析任意类型的 pb，然后在标准输出 kv 形式值。
    - --descpritor_set_in=*files*：加载 descriptor.proto，多次出现只使用前者。
    - -o *FILE* --descpritor_set_out=*files*：输出文件。
    - --include_imports：当使用 --descpritor_set_out 时。
    - --include_source_info：当使用 --descpritor_set_out 时，包含源码信息。
    - --dependency_out=file：写出依赖信息。
    - --error_format=format：错误信息打印格式化。
    - --fatal_warnings：有 warn 时，程序返回非零值。
    - --print_free_field_numbers：打印消息的空闲字段数字。
    - --plugin=executable或者name=path：指定要使用的插件。
    - @*filename*：参数从文件中读取。
    - --cpp_out=*path*
    - --java_out=*path*
    - --python_out=*path*
    - --go_out=*path*
    - --js_out=*path*
    - --kotlin_out=*path*
    - --ruby_out=*path*
    - --objc_out=*path*
    - --csharp_out=*path*
    - --php_out=*path*
    - --ruby_out=*path*
    - --go_opt=*opt*：指定 protoc-gen-go 命令参数。
    - paths=import(默认)|*source_relative*：默认以包路径为生成路径。修改生成文件路径。
    - module=*prefix*：生成文件路径去掉 prefix 部分。
    - Mproto_file=*go_import_path*：指定文件包全称。
    - --go-grpc_out=*path*
    - --go-grpc_opt=*opts*

# 编解码规则

- 字段格式为 TAG-VALUE，TAG 最多 32 位，后三位为 wire type，剩下 29 位为字段序号。序号[1, 15] 编码后的 TAG 占 1 字节，[16, 2047] 占 2 字节。
    - wire 类型：
    - 0 为 Varint：int32、int64、uint32、uint64、sint32、sint64、bool、enum。
    - 1 为 64 位：fixed64、sfixed64、double。
    - 2 为 Length-delimited：string、bytes、embedded messages、packed repeated fields。
    - 5 为 32 位：fixed32、sfixed32、float。
- 每个字段的 wire_format 分可以三部分：tag、length、value。length 只有当 wire_type=2 时使用。
- tag 和 length 部分使用一个或多个字节编码，字节最前一位为 1 表示还有下一字节。取每字节后 7 位倒序拼接而成，tag 的拼接后再取后三位为 wire-type，剩下的为序号。
- tag 部分包含字段序号和 wire_type 数值，其为字段序号数字补码值左移三位后拼上占三位的  wire_type 值。
- length 部分表示 value 字节的长度，其二进制值就是长度值。
- value 部分代表字段值，
    - wire_type 为 0 时 value 使用 varint、zigzag 编码，值为每个字节后 7 位倒序拼接。
    - wire_type 为 1 时 value 使用 little-endian 编码，后面总是八个字节，值为每个字节倒序拼接。
    - wire_type 为 5 时 value 使用 little-endian 编码，后面总是四个字节，值为每个字节倒序拼接。
    - wire_type 为 2 时 value 使用 length-delimited 编码，tag 后部分 length 表示 value 使用的字节个数，字段类型为 string 和 bytes 时 value 就是值，为 nested_msg 和 repeated、map 时为 protobuf 编码的值，repeated、map 编码成一个元素一个tag-length-value。tag-type/length/other-tag-values。numeric repeated value 编码默认使用 packed，packed 后的编码为一个 tag-length-value。非 numeric repeated value 编码不能 packed，且不一定连续出现。map 编码等价于 repeated message（map 比msg 多个 1:1 的 kv）。
- 相同数据编码不保证每次字节输出顺序完全一致，取决于实现。 
- sint 编码：32 n<<1 ^ n>>31、64 n<<1 ^ n>>63，解码：if x % 2 == 0; (x ^ 0) >> 1 else (x ^ -1) >> 1。
