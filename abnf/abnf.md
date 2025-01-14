# 规则

- **规则名**(Rule-Name)：以字母开头，由字母、数字、连字符 `-` 组成。大小写不敏感，可有一对尖括号包围。
- **规则体**(Rule-Form)：由一个或多个规则、字面量或终值操作构成，以回车换行 `\r\n` 结尾。个体间有空白字符相隔。多行内容同第一行左对齐。
- **终值**(Terminal-Value)：由百分比加指示符号加非负整数构成。指示符有 `b`、`d`、`x`。多个终值可以点号相连紧凑表示。
- **字面值**(Value-Definition)：由双引号包围的字符构成。在前面加 `%i` 表示大小写匹配不敏感，默认行为，加 `%s` 表示大小写匹配敏感。

# 操作(Operator)

- **相连**(Concatenation)：表示须连续匹配规则，多个规则有空白字符隔开。
- **或者**(Alternative)：表示须匹配其中单个规则，使用前下划线 `/` 分割。
- **增量或者**(Incremental-Alternative)：表示须匹配其中单个规则。向规则继续添加个体。在等号后加前下划线 `=/` 表示。
- **范围**(Range)：表示须匹配连续性规则中的一个。使用连字符 `-` 表示。
- **括号**(Grouping)：表示这段规则匹配优先级高。小括号`()`包围表示。
- **重复**(Repetition)：表示匹配指定次数规则。形式为在规则前加 `*`。`*` 两边可有数字，表示次数范围，默认为零和无穷。若两者相同，可缩写为一个数字。
- **可选**(Optional)：表示规则可匹配也可不匹配。一对中括号 `[]` 包围表示。
- **注释**(Comment)：表示注释文字。分号 `;` 后书写注释内容，作用到行尾。

# 优先级

1. **范围** Range
1. **重复** Repetition
1. **括号** **可选** Grouping, Optional
1. **相连** Concatenation
1. **或者** Alternative

# 样例

1. `foo = %d102 %b110111 %x6f`
1. `bar = %d98.97.114`
1. `for = %i"for"`
1. `ber = %s"ber"`
1. `FooAndBar = foo bar`
1. `AlternativeRule = foo / bar / ber`
1. `IncrementalAlternativeRule =/ ber`
1. `RangeAlternativeRule = %x30-39`
1. `SequenceGroupRule = for (foo / bar) ber`
1. `RepetitionRule = 1*2foo`
1. `RepetitionRule = 2foo`
1. `OptionalSequenceRule = [foo] bar`

# 预定义规则

```abnf
ALPHA = %x41-5A / %x61-7A
BIT  = "0" / "1"
CHAR = %x01-7F
CR = %x0D
CRLF = CR LF
CTL = %x00-1F / %x7F
DIGIT = %x30-39
DQUOTE = %x22
HEXDIG = DIGIT / "A" / "B" / "C" / "D" / "E" / "F"
HTAB = %x09
LF = %x0A
LWSP = *(WSP / CRLF WSP)
OCTET = %x00-FF
SP = %x20
VCHAR = %x21-7E
WSP = SP / HTAB
```

# ABNF 描述 ABNF

```abnf
rulelist = 1*( rule / (*c-wsp c-nl) )
rule = rulename defined-as elements c-nl
rulename = ALPHA *(ALPHA / DIGIT / "-")
defined-as = *c-wsp ("=" / "=/") *c-wsp
elements = alternation *c-wsp
c-wsp = WSP / (c-nl WSP)
c-nl = comment / CRLF
comment = ";" *(WSP / VCHAR) CRLF
alternation = concatenation *(*c-wsp "/" *c-wsp concatenation)
concatenation = repetition *(1*c-wsp repetition)
repetition = [repeat] element
repeat = 1*DIGIT / (*DIGIT "*" *DIGIT)
element = rulename / group / option / char-val / num-val / prose-val
group = "(" *c-wsp alternation *c-wsp ")"
option = "[" *c-wsp alternation *c-wsp "]"
char-val = case-insensitive-string / case-sensitive-string
case-insensitive-string = [ "%i" ] quoted-string
case-sensitive-string = "%s" quoted-string
quoted-string =  DQUOTE *(%x20-21 / %x23-7E) DQUOTE
num-val = "%" (bin-val / dec-val / hex-val)
bin-val = "b" 1*BIT [ 1*("." 1*BIT) / ("-" 1*BIT) ]
dec-val = "d" 1*DIGIT [ 1*("." 1*DIGIT) / ("-" 1*DIGIT) ]
hex-val = "x" 1*HEXDIG [ 1*("." 1*HEXDIG) / ("-" 1*HEXDIG) ]
prose-val = "<" *(%x20-3D / %x3F-7E) ">"
```
