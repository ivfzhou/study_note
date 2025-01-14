# 注释

\<!-- --\>

# 语法

- 元素都须有关闭标签。
- 标签对大小写敏感。
- 必须正确地嵌套。
- 文档必须有根元素。
- 属性值须加单或双引号。
- 空格会被保留。
- 以 LF 存储换行。
- 名称不能以数字或者标点符号开始。
- 名称不能以字符 xml XML Xml 开始。
- 名称不能包含空格。

# 模块

- 标签
- 属性
- 实体
- PCDATA
- CDATA

# 实体

`&lt;` <  
`&gt;` >  
`&amp;` &  
`&apos;` '  
`&quot;` "  
`&ndash;` --  
`&trade;` ™  
`&nbsp;` 空格  
`&copy;` ©  
`&reg;` ®  

# DTD 引用

`<!DOCTYPE 根标签 SYSTEM或者（PUBLIC） "地址"...>`：引用外部文件验证 xml。

# CSS 引用

`<? xml-stylesheet type="text/css" href="地址" ?>`

# XSLT 引用 eXtensible Stylesheet Language Transformations

`<? xml-stylesheet type="text/xsl" href="地址" ?>`

# CDATA Unparsed Character Data

`<![CDATA[内容]]>`

# 解析

```JavaScript
if(window.DOMParser) {
    let xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
    xmlDoc.async = "false";
    xmlDoc.loadXML('文档');
} else {
    let xmlDoc=new DOMParser();
    xmlDoc.parse('文档', "text/xml");
}
```
