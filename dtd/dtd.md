# 申明

\<!DOCTYPE 根元素 [元素声明]>

# 元素

- \<!ELEMENT 元素名称 EMPTY>
- \<!ELEMENT 元素名称 (#PCDATA)>
- \<!ELEMENT 元素名称 ANY>
- \<!ELEMENT 元素名称 (子元素名称1, 子元素名称2, ...)>
- \<!ELEMENT 元素名称 (子元素名称+)> 声明最少出现一次的元素
- \<!ELEMENT 元素名称 (子元素名称*)> 声明出现零次或多次的元素
- \<!ELEMENT 元素名称 (子元素名称?)> 声明出现零次或一次的元素
- \<!ELEMENT 元素名称 (to,from,header,(message|body))> 声明“非.../既...”类型的内容
- \<!ELEMENT 元素名称 (#PCDATA|to|from|header|message)*> 声明混合型的内容

# 属性

\<!ATTLIST 元素名称 属性名称 属性类型 默认值>

# 属性类型

- CDATA：值为字符数据 (character data)
- (en1|en2|...)：此值是枚举列表中的一个值
- ID：值为唯一的id
- IDREF：值为另外一个元素的id
- IDREFS：值为其他id的列表
- NMTOKEN：值为合法的XML名称
- NMTOKENS：值为合法的XML名称的列表
- ENTITY：值是一个实体
- ENTITIES：值是一个实体列表
- NOTATION：此值是符号的名称
- xml：值是一个预定义的 XML 值

# 默认值

- [值] 属性的默认值
- #REQUIRED 属性值是必需的
- #IMPLIED 属性不是必需的
- #FIXED [value] 属性值是固定的

# 实体

一个实体由三部分构成：一个和号&, 一个实体名称, 以及一个分号;  
\<!ENTITY 实体名称 "实体的值url">
