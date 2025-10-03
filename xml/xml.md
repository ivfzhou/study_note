# 一、注释

\<!-- --\>

# 二、语法

1. 元素都须有关闭标签。
2. 标签对大小写敏感。
3. 必须正确地嵌套。
4. 文档必须有根元素。
5. 属性值须加单或双引号。
6. 空格会被保留。
7. 以 LF 存储换行。
8. 名称不能以数字或者标点符号开始。
9. 名称不能以字符 xml XML Xml 开始。
10. 名称不能包含空格。

# 三、模块

1. 标签
2. 属性
3. 实体
4. PCDATA
5. CDATA

# 四、实体

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

# 五、DTD 引用

`<!DOCTYPE 根标签 SYSTEM或者（PUBLIC） "地址"...>`：引用外部文件验证 xml。

# 六、CSS 引用

`<? xml-stylesheet type="text/css" href="地址" ?>`

# 七、XSLT 引用

eXtensible Stylesheet Language Transformations

`<? xml-stylesheet type="text/xsl" href="地址" ?>`

# 八、CDATA Unparsed Character Data

`<![CDATA[内容]]>`

# 九、解析

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
