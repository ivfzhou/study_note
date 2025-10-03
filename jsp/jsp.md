# 一、语法

1. \<% %>：声明局部变量。
1. \<%! %>：声明成员变量，方法。使用方法。
1. \<%= %>：输出语句。
1. \<%-- -->：注释，不会在页面显示也不会在对应 Java 类中。

# 二、指令

1. 语法：\<%@ *指令名称* *属性名称1*＝"*属性值1*" *属性名称2*＝"*属性值2*" %>
    - 指令名：page
        - import="包名"：导包。
        - session=true：默认true。
        - buffer="8kb"：java.servlet.jsp.JspWriter 输出字符流，设置输出数据的缓存大小。
        - errorPage="页面路径"：如果页面中有错误，跳转到指定的资源。
        - isErrorPage=false：默认是否创建 throwable 对象。
        - contentType="text/html;charset=UTF-8"：服务器发送客户端的内容编码。
        - pageEncoding="UTF-8"：JSP 文件本身的编码。
        - isELIgnored=false：是否忽略 EL 表达式。
        - language="*语言*"：默认java。
    - 指令名：include
        - file="页面文件路径"：静态包含引用的页面。
    - 指令名：taglib，导入 JSTL 标签库。
    - url="http://java.sun.com/jsp/jstl/core"
    - prefix="c"
