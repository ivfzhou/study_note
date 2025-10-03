# 一、笔记

1. [html_standard_2024.pdf](./html_standard_2024.pdf)
1. html 标签名和属性名全部小写。
1. HTML5 中的默认字符集为 UTF-8。

# 二、标签

## 1. 基础

- **`<!DOCTYPE>`**：声明，不区分大小写，也没有结束标签。每一个 HTML 文档都必须以 DOCTYPE 元素开头。

    - HTML5：
        ```html
        <!DOCTYPE html>
        ```
    - HTML4.01：
        ```html
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
        ```
    - XHTML1.0：
        ```html
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
      ```
    - XHTML1.1：
        ```html
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
        ```
- **`<html>`**：定义 HTML 文档的根。
    - xmlns="http://www.w3.org/1999/xhtml"：规定 XML namespace 属性（如果需要内容符合 XHTML）。
    - 默认 CSS 值：
        ```css
        html {
            display: block;
        }
        html:focus {
            outline: none;
        }
        ```
- **`<head>`**：定义关于文档的信息。
    - 默认 CSS 值：
        ```css
        head {
            display: none;
        }
        ```
- **`<title>`**：定义文档的标题。一个 HTML 文档中不能包含多个 title 元素。
    - 默认 CSS 值：
        ```css
        title {
            display: none;
        }
        ```
- **`<body>`**：定义文档的主体。一个 HTML 文档中只能有一个 body 元素。
    - 默认 CSS 值：
        ```css
        body {
            display: block;
            margin: 8px;
        }
        body:focus {
            outline: none;
        }
        ```
- **`<h1> to <h6>`**：定义 HTML 标题。
    - 默认 CSS 值：
        ```css
        h1 {
            display: block;
            font-size: 2em;
            margin-top: 0.67em;
            margin-bottom: 0.67em;
            margin-left: 0;
            margin-right: 0;
            font-weight: bold;
        }
        h2 {
            display: block;
            font-size: 1.5em;
            margin-top: 0.83em;
            margin-bottom: 0.83em;
            margin-left: 0;
            margin-right: 0;
            font-weight: bold;
        }
        h3 {
            display: block;
            font-size: 1.17em;
            margin-top: 1em;
            margin-bottom: 1em;
            margin-left: 0;
            margin-right: 0;
            font-weight: bold;
        }
        h4 {
            display: block;
            font-size: 1em;
            margin-top: 1.33em;
            margin-bottom: 1.33em;
            margin-left: 0;
            margin-right: 0;
            font-weight: bold;
        }
        h5 {
            display: block;
            font-size: 0.83em;
            margin-top: 1.67em;
            margin-bottom: 1.67em;
            margin-left: 0;
            margin-right: 0;
            font-weight: bold;
        }
        h6 {
            display: block;
            font-size: 0.67em;
            margin-top: 2.33em;
            margin-bottom: 2.33em;
            margin-left: 0;
            margin-right: 0;
            font-weight: bold;
        }
        ```
- **`<p>`**：定义段落。
    - 默认 CSS 值：
        ```css
        p {
            display: block;
            margin-top: 1em;
            margin-bottom: 1em;
            margin-left: 0;
            margin-right: 0;
        }
        ```
- **`<br>`**：单标签。定义简单的折行。
- **`<hr>`**：单标签。定义内容的主题变化。通常显示为水平线。
    - 默认 CSS 值：
        ```css
        hr {
            display: block;
            margin-top: 0.5em;
            margin-bottom: 0.5em;
            margin-left: auto;
            margin-right: auto;
            border-style: inset;
            border-width: 1px;
        }
        ```
- **`<!--...-->`**：定义注释。
     - 条件注释例子：
         ```html
         <!--[if IE 8]> .... some HTML here .... <![endif]-->
         ```

## 2. 格式化

- **`<abbr>`**：定义缩写词或首字母缩略词。

    - 默认 CSS 值：
        ```css
        abbr {
            display: inline;
        }
        ```
- **`<address>`**：定义文档作者或拥有者的联系信息。
    - 默认 CSS 值：
        ```css
        address {
            display: block;
            font-style: italic;
        }
        ```
- **`<b>`**：定义粗体文本。
- **`<bdi>`**：定义文本方向，使其脱离其周围文本的方向设置。
- **`<bdo>`**：定义文字方向。
    - dir="ltr | rtl"：必需。规定 bdo 元素内文本的方向。
    - 默认 CSS 值：
        ```css
        bdo {
            unicode-bidi: bidi-override;
        }
        ```
- **`<blockquote>`**：定义长的引用。
    - cite="*URL*"：规定引用的来源。
    - 默认 CSS 值：
        ```css
        blockquote {
            display: block;
            margin-top: 1em;
            margin-bottom: 1em;
            margin-left: 40px;
            margin-right: 40px;
        }
        ```
- **`<cite>`**：定义作品的标题。
    - 默认 CSS 值：
        ```css
        cite {
            font-style: italic;
        }
        ```
- **`<code>`**：定义一段计算机代码。
    - 默认 CSS 值：
        ```css
        code {
            font-family: monospace;
        }
        ```
- **`<del>`**：定义已从文档中删除的文本。
    - cite="*URL*"：规定文档的 URL，解释文本被删除/更改的原因。
    - datetime="*YYYY-MM-DDThh:mm:ssTZD*"：规定删除/更改文本的日期和时间。
    - 默认 CSS 值：
        ```css
        del {
            text-decoration: line-through;
        }
        ```
- **`<dfn>`**：规定要在内容中定义的术语。
    - title="*value*"
    - 默认 CSS 值：
        ```css
        dfn {
            font-style: italic;
        }
        ```
- **`<em>`**：定义强调文本。
    - 默认 CSS 值：
        ```css
        em {
            font-style: italic;
        }
        ```
- **`<i>`**：定义以不同的语气或情态表达的文本部分。
    - 默认 CSS 值：
        ```css
        i {
            font-style: italic;
        }
        ```
- **`<ins>`**：定义已插入到文档中的文本。
    - cite="*URL*"：规定解释插入/更改文本的原因的文档的 URL。
    - datetime="*YYYY-MM-DDThh:mm:ssTZD*"：规定插入/更改文本的日期和时间。
    - 默认 CSS 值：
        ```css
        ins {
            text-decoration: underline;
        }
        ```
- **`<kbd>`**：定义键盘输入。
    - 默认 CSS 值：
        ```css
        kbd {
            font-family: monospace;
        }
        ```
- **`<mark>`**：定义应标记或突出显示的文本。
    - 默认 CSS 值：
        ```css
        mark {
            background-color: yellow;
            color: black;
        }
        ```
- **`<meter>`**：定义已知范围内的仪表。
    - form="*formId*"：规定 meter 元素属于哪个表单。
    - high="*num*"：规定范围的高值。
    - low="*num*"：规定范围的低值。
    - max="*num*"：规定范围的最大值。
    - min="*num*"：规定范围的最小值。默认值为 0。
    - optimum="*num*"：规定仪表的最佳值。
    - value="*num*"：必需。规定仪表的当前值。
- **`<pre>`**：定义预格式文本。文本保留空格和换行符。
    - 默认 CSS 值：
        ```css
        pre {
            display: block;
            font-family: monospace;
            white-space: pre;
            margin: 1em 0;
        }
        ```
- **`<progress>`**：定义任务进度。
    - max="*num*"：规定任务所需的总工作量。默认值为 1。
    - value="*num*"：规定任务已完成的部分。
- **`<q>`**：定义短的引用。
    - cite="*URL*"：规定引用的源 URL。
    - 默认 CSS 值：
        ```css
        q {
            display: inline;
        }
        q:before {
            content: open-quote;
        }
        q:after {
            content: close-quote;
        }
        ```
- **`<rp>`**：定义在不支持 ruby 注释的浏览器中显示的内容。
- **`<rt>`**：定义字符的解释/发音（针对东亚印刷术）。
    - 默认 CSS 值：
        ```css
        rt {
            line-height: normal;
        }
        ```
- **`<ruby>`**：定义 ruby 注释（针对东亚印刷术）。
    - 例子：
        ```html
        <ruby> 漢 <rp>(</rp><rt>ㄏㄢˋ</rt><rp>)</rp> </ruby>
        ```
- **`<s>`**：定义不再正确的文本。
    - 默认 CSS 值：
        ```css
        s {
            text-decoration: line-through;
        }
        ```
- **`<samp>`**：定义计算机程序的样本输出。
    - 默认 CSS 值：
        ```css
        samp {
            font-family: monospace;
        }
        ```
- **`<small>`**：定义小号文本。
- **`<strong>`**：定义重要的文本。
    - 默认 CSS 值：
        ```css
        strong {
            font-weight: bold;
        }
        ```
- **`<sup>`**：定义上标文本。
    - 默认 CSS 值：
        ```css
        sup {
            vertical-align: super;
            font-size: smaller;
        }
        ```
- **`<sub>`**：定义下标文本。
    - 默认 CSS 值：
        ```css
        sub {
            vertical-align: sub;
            font-size: smaller;
        }
        ```
- **`<template>`**：定义用作容纳页面加载时隐藏内容的容器。
- **`<time>`**：定义日期/时间。
    - datetime="*datetime*"：表示 time 元素的机器可读的格式。
- **`<u>`**：定义未明确表达且样式与普通文本不同的文本。
    - 默认 CSS 值：
        ```css
        u {
            text-decoration: underline;
        }
        ```
- **`<var>`**：定义变量。
    - 默认 CSS 值：
        ```css
        var {
            font-style: italic;
        }
        ```
- **`<wbr>`**：定义可能的换行符。

## 3. 表单和输入

- **`<form>`**：定义供用户输入的 HTML 表单。

    - accept-charset="*charset*"：规定提交表单时要使用的字符编码。
    - action="*URL*"：规定提交表单时将表单数据发送到哪里。
    - autocomplete="on | off"：规定表单是否应启用自动完成功能。
    - enctype="application/x-www-form-urlencoded | multipart/form-data | text/plain"：规定在向服务器提交表单数据时，应该如何对表单数据进行编码。
    - method="get | post"：规定发送表单数据时使用的 HTTP 方法。
    - name="*txt*"：规定表单的名称。
    - novalidate="novalidate"：规定提交表单时不应验证表单。
    - rel="external | help | license | next | nofollow | noopener | noreferrer | opener | prev | search"：规定链接资源和当前文档之间的关系。
    - target="_blank | _self | _parent | _top"：规定在何处显示提交表单后收到的响应。
    - 默认 CSS 值：
        ```css
        form {
            display: block;
            margin-top: 0em;
        }
        ```
- **`<input>`**：单标签。定义输入控件。
    - accept="*文件扩展名：audio/\* | video/\* | image/\**"：媒体类型，规定通过文件上传来提交的文件的类型。规定用户可以从文件输入对话框中选择哪些文件类型的过滤器（仅适用于 type="file"）。
    - alt="*txt*"：规定图像的替代文本（仅适用于 type="image"）。
    - autocomplete="on | off"：规定 input 元素是否应该启用自动完成。
    - autofocus="autofocus"：规定 input 元素应在页面加载时应自动获得焦点。
    - checked="checked"：规定在页面加载时应预先选中 input 元素（适用于 type="checkbox" 或 type="radio"）。
    - dirname="*dir*"：规定将被提交的文本方向。
    - disabled="disabled"：规定应禁用 input 元素。
    - form="*formId*"：规定 input 元素所属的表单。
    - formaction="*URL*"：规定提交表单时将处理输入控件的文件的 URL（适用于 type="submit" 和 type="image"）。
    - formenctype="application/x-www-form-urlencoded | multipart/form-data | text/plain"：规定将表单数据提交到服务器时应如何对其进行编码（适用于 type="submit" 和 type="image"）。
    - formmethod="get | post"：定义用于将数据发送到 action URL 的 HTTP 方法（适用于 type="submit" 和 type="image"）。
    - formnovalidate="formnovalidate"：定义在提交表单元素时不应对其进行验证。
    - formtarget="_blank | _self | _parent | _top | *frameName*"：规定在何处显示提交表单后收到的响应（适用于 type="submit" 和 type="image"）。
    - height="*px*"：规定 input 元素的高度（仅适用于 type="image"）。
    - list="*datalistId*"：引用包含 input 元素的预定义选项的 datalist 元素。
    - max="*num* | *date*"：规定 input 元素的最大值。
    - maxlength="*num*"：规定 input 元素允许的最大字符数。
    - min="*num* | *date*"：规定 input 元素的最小值。
    - minlength="*num*"：规定 input 元素中所需的最少字符数。
    - multiple="multiple"：规定用户可以在 input 元素中输入多个值。
    - name="*txt*"：规定 input 元素的名称。
    - pattern="*reg*"：规定检查 input 元素值的正则表达式。
    - placeholder="*txt*"：规定描述 input 元素预期值的简短提示。
    - popovertarget="*elementId*"：规定要调用的弹出框元素（仅适用于 type="button"）。
    - popovertargetaction="hide | show | toggle"：规定当您单击按钮时弹出框元素会发生什么（仅适用于 type="button"）。
    - readonly="readonly"：规定输入字段是只读的。
    - required="required"：规定在提交表单之前必须填写输入字段。
    - size="*num*"：规定 input 元素的宽度（以字符数为单位）。
    - src="*URL*"：规定用作提交按钮的图像的 URL（仅适用于 type="image"）。
    - step="*num* | any"：规定输入字段中合法数字之间的间隔。
    - type="button | checkbox | color | date | datetime-local | email | file | hidden | image | month | number | password | radio | range | reset | search | submit | tel | text | time | url | week"：规定要显示的 input 元素的类型。
    - value="*txt*"：规定 input 元素的值。
    - width="*px*"：规定 input 元素的宽度（仅适用于 type="image"）。
- **`<textarea>`**：定义多行的文本输入控件。
    - autofocus="autofocus"：规定文本区域应在页面加载时自动获得焦点。
    - cols="*num*"：规定文本区域的可见宽度。
    - dirname="*dir*"：规定被提交的文本区域的的文字方向。
    - disabled="disabled"：规定应禁用文本区域。
    - form="*formId*"：规定文本区域所属的表单。
    - maxlength="*num*"：规定文本区域允许的最大字符数。
    - name="*txt*"：规定文本区域的名称。
    - placeholder="*txt*"：规定描述文本区域预期值的简短提示。
    - readonly="readonly"：规定文本区域应该是只读的。
    - required="required"：规定文本区域是必填的。
    - rows="*num*"：规定文本区域中可见的行数。
    - wrap="hard | soft"：规定在表单中提交时文本区域中的文本如何换行。
- **`<button>`**：定义可点击的按钮。
    - autofocus="autofocus"：规定按钮应在页面加载时自动获得焦点。
    - disabled="disabled"：规定应禁用按钮。
    - form="*formId*"：规定按钮属于哪个表单。
    - formaction="*URL*"：规定提交表单时将表单数据发送到哪里。仅适用于 type="submit"。
    - formenctype="application/x-www-form-urlencoded | multipart/form-data | text/plain"：规定在将表单数据发送到服务器之前应如何对其进行编码。仅适用于 type="submit"。
    - formmethod="get | post"：规定如何发送表单数据（使用哪种 HTTP 方法）。仅适用于 type="submit"。
    - formnovalidate="formnovalidate"：规定不应在提交时验证表单数据。仅适用于 type="submit"。
    - formtarget="_blank | _self | _parent | _top | *frameName*"：规定在提交表单后响应应该显示在哪里。仅适用于 type="submit"。
    - name="*txt*"：规定按钮的名称。
    - popovertarget="*elementId*"：规定要调用的弹出窗口元素。
    - popovertargetaction="hide | show | toggle"：规定按钮被点击时对弹出窗口元素的操作。
    - type="button | reset | submit"：规定按钮的类型。
    - value="*txt*"：规定按钮的初始值。
- **`<select>`**：定义下拉列表。
    - autofocus="autofocus"：规定下拉列表应在页面加载时自动获得焦点。
    - disabled="disabled"：规定应禁用下拉列表。
    - form="*formId*"：定义下拉列表所属的表单。
    - multiple="multiple"：规定可以一次选择多个选项。
    - name="*txt*"：定义下拉列表的名称。
    - required="required"：规定在提交表单之前用户必须选择一个值。
    - size="*num*"：定义下拉列表中可见选项的数量。
- **`<optgroup>`**：定义下拉列表中相关选项的分组。
    - disabled="disabled"：规定应禁用选项组。
    - label="*txt*"：规定选项组的标签。
- **`<option>`**：定义下拉列表中的选项。
    - disabled="disabled"：规定选项应该被禁用。
    - label="*txt*"：为选项规定较短的标签。
    - selected="selected"：规定在页面加载时应预先选择一个选项。
    - value="*txt*"：规定要发送到服务器的值。
- **`<label>`**：定义 input 元素的标注。
    - for="*elementId*"：规定 label 绑定到哪个表单元素。
    - form="*formId*"：规定 label 字段所属的表单。
    - 默认 CSS 值：
        ```css
        label {
            cursor: default;
        }
        ```
- **`<fieldset>`**：对表单中的相关元素进行分组。
    - disabled="disabled"：规定应禁用一组相关的表单元素。
    - form="*formId*"：规定字段集属于哪个表单。
    - name="*txt*"：规定字段集的名称。
    - 默认 CSS 值：
        ```css
        fieldset {
            display: block;
            margin-left: 2px;
            margin-right: 2px;
            padding-top: 0.35em;
            padding-bottom: 0.625em;
            padding-left: 0.75em;
            padding-right: 0.75em;
            border: 2px groove (internal value);
        }
        ```
- **`<legend>`**：定义 fieldset 元素的标题。
    - 默认 CSS 值：
        ```css
        legend {
            display: block;
            padding-left: 2px;
            padding-right: 2px;
            border: none;
        }
        ```
- **`<datalist>`**：规定输入控件的预定义选项列表。
    - 默认 CSS 值：
        ```css
        datalist {
            display: none;
        }
        ```
    - 例子：
        ```html
        <label for="browser">请从列表中选择您的浏览器：</label>
        <input list="browsers" name="browser" id="browser" />
        <datalist id="browsers">
            <option value="Edge"></option>
            <option value="Firefox"></option>
            <option value="Chrome"></option>
            <option value="Opera"></option>
            <option value="Safari"></option>
        </datalist>
        ```
- **`<output>`**：定义计算的结果。
    - for="*elementId*"：规定计算结果与计算中使用的元素之间的关系。
    - form="*formId*"：规定 output 元素所属的表单。
    - name="*txt*"：规定 output 元素的名称。
    - 默认 CSS 值：
        ```css
        output {
            display: inline;
        }
        ```
    - 例子：
        ```html
        <form oninput="x.value=parseInt(a.value)+parseInt(b.value)">
            <input type="range" id="a" value="50" />
            +
            <input type="number" id="b" value="25" />
            =
            <output name="x" for="a b"></output>
        </form>
        ```

## 4. 框架

- **`<iframe>`**：定义内联框架。

    - allow：规定 iframe 的功能策略。
    - allowfullscreen="true | false"：如果 iframe 可以通过调用 requestFullscreen() 方法激活全屏模式，则设置为 true。
    - allowpaymentrequest="true | false"：如果允许跨源 iframe 调用 Payment Request API，则设置为 true。
    - height="*px*"：规定 iframe 的高度。默认高度为 150 像素。
    - loading="eager | lazy"：规定浏览器是应立即加载 iframe 还是推迟加载 iframe，直到满足某些条件为止。
    - name="*txt*"：规定 iframe 的名称。
    - referrerpolicy="no-referrer | no-referrer-when-downgrade | origin | origin-when-cross-origin | same-origin | strict-origin-when-cross-origin | unsafe-url"：规定在获取 iframe 时要发送的引用信息。
    - sandbox="allow-forms | allow-pointer-lock | allow-popups | allow-same-origin | allow-scripts | allow-top-navigation"：启用一系列对 iframe 中内容的额外限制。
    - src="*URL*"：规定要嵌入到 iframe 中的文档的地址。
    - srcdoc="*htmlCode*"：规定要在 iframe 中显示的页面的 HTML 内容。
    - width="*px*"：规定 iframe 的宽度。默认宽度为 300 像素。
    - 默认 CSS 值：
        ```css
        iframe:focus {
            outline: none;
        }
        iframe[seamless] {
            display: block;
        }
        ```

## 5. 图像

- **`<img>`**：定义图像。

    - alt="*txt*"：规定图像的替代文本。
    - crossorigin="anonymous | use-credentials"：允许使用来自允许跨域访问的第三方网站的图像与画布（canvas）一起使用。
    - height="*px*"：规定图像的高度。
    - ismap="ismap"：将图像定义为服务器端图像映射。
    - loading="eager | lazy"：指定浏览器是否应立即加载图像，或者推迟加载图像直到满足某些条件。
    - longdesc="*URL*"：规定指向图像详细描述的 URL。
    - referrerpolicy="no-referrer | no-referrer-when-downgrade | origin | origin-when-cross-origin | unsafe-url"：规定在获取图像时要使用的引用信息。
    - sizes="*size*"：规定不同页面布局的图像尺寸。
    - src="*URL*"：规定图像的路径。
    - srcset="*URLList*"：规定在不同情况下使用的图像文件列表。
    - usemap="*#mapname*"：将图像定义为客户器端图像映射。
    - width="*px*"：规定图像的宽度。
    - 默认 CSS 值：
        ```css
        img {
            display: inline-block;
        }
        ```
- **`<map>`**：定义图像映射。
    - name="*txt*"：必需。规定图像地图的名称。
    - 默认 CSS 值：
        ```css
        map {
            display: inline;
        }
        ```
    - 例子：
        ```html
        <img src="life.png" alt="Life" usemap="#lifemap" width="650" height="451" />
        <map name="lifemap">
            <area shape="rect" coords="10,208,155,338" alt="AirPods" href="airpods.html" />
            <area shape="rect" coords="214,65,364,365" alt="iPhone" href="iphone.html" />
            <area shape="circle" coords="570,291,75" alt="Coffee" href="coffee.html" />
        </map>
        ```
- **`<area>`**：定义图像地图内部的区域。
    - alt="*txt*"：规定区域的替代文本。如果存在 href 属性则为必需。
    - coords="*坐标值*"：规定区域的坐标。
    - download="*文件名*"：规定当用户单击超链接时将下载目标。
    - href="*URL*"：规定区域的超链接目标。
    - hreflang="*语言代码*"：规定目标 URL 的语言。
    - media="*媒体查询*"：规定目标 URL 优化的媒体/设备。
    - referrerpolicy="no-referrer | no-referrer-when-downgrade | origin | origin-when-cross-origin | same-origin | strict-origin-when-cross-origin | unsafe-url"：规定要与链接一起发送的引用信息。
    - rel="alternate | author | bookmark | help | license | next | nofollow | noreferrer | prefetch | prev | search | tag"：规定当前文档和目标 URL 之间的关系。
    - shape="default | rect | circle | poly"：规定区域的形状。
    - target="_blank | _parent | _self | _top | *frameName*"：规定在何处打开目标 URL。
    - type="*媒体类型*"：规定目标 URL 的媒体类型。
    - 默认 CSS 值：
        ```css
        area {
            display: none;
        }
        ```
- **`<canvas>`**：用于通过脚本（通常是 JavaScript）动态绘制图形。在禁用 JavaScript 的浏览器和不支持 canvas 的浏览器中，会显示出 canvas 元素内部的任何文本。
    - height="*px*"：规定画布的高度。默认值为 150。
    - width="*px*"：规定画布的宽度。默认值为 300。
    - 默认 CSS 值：
        ```css
        canvas {
            height: 150px;
            width: 300px;
        }
        ```
- **`<figcaption>`**：定义 figure 元素的标题。
    - 默认 CSS 值：
        ```css
        figcaption {
            display: block;
        }
        ```
- **`<figure>`**：规定自包含的内容。
    - 默认 CSS 值：
        ```css
        figure {
            display: block;
            margin-top: 1em;
            margin-bottom: 1em;
            margin-left: 40px;
            margin-right: 40px;
        }
        ```
- **`<picture>`**：定义多个图像资源的容器。img 元素作为 picture 元素的最后一个子元素是必需的，用作当没有任何 source 标签匹配时的备用选项。
    - 例子：
        ```html
        <picture>
            <source media="(min-width:650px)" srcset="flowers-1.jpg" />
            <source media="(min-width:465px)" srcset="flowers-2.jpg" />
            <img src="flowers-3.jpg" alt="Flowers" style="width:auto;" />
        </picture>
        ```
- **`<svg>`**：定义 SVG 图形的容器。

## 6. 音频/视频

- **`<audio>`**：定义嵌入的声音内容。

    - autoplay="autoplay"：规定音频将在准备就绪后立即开始播放。
    - controls="controls"：规定应显示音频控件（例如播放/暂停按钮等）。
    - loop="loop"：规定音频将在每次结束后重新开始。
    - muted="muted"：规定音频输出应静音。
    - preload="auto | metadata | none"：规定是否以及如何在页面加载时加载音频。
    - src="*URL*"：规定音频文件的 URL。
    - 例子：
        ```html
        <audio controls>
            <source src="song.ogg" type="audio/ogg" />
            <source src="song.mp3" type="audio/mpeg" />
            您的浏览器不支持 audio 标签。
        </audio>
        ```
- **`<source>`**：定义媒体元素（如 video、audio 和 picture）的多个媒体资源。
    - media="*媒体查询*"：接受任何有效的媒体查询，通常在 CSS 中定义。
    - sizes：为不同的页面布局指定图像大小。
    - src="*URL*"：用于指定媒体文件的 URL。当 source 用于 audio 和 video 时，此属性是必需的。
    - srcset="*URL*"：用于指定在不同情况下使用的图像的 URL。当 source 用于 picture 时，此属性是必需的。
    - type="*MIME 类型*"：规定资源的 MIME 类型。
- **`<track>`**：定义用在媒体播放器中的文本轨道。
    - default="default"：规定如果用户的首选项不指示其他轨道更合适，则启用该轨道。
    - kind="captions | chapters | descriptions | metadata | subtitles"：规定文本轨道的类型。
    - label="*txt*"：规定文本轨道的标题。
    - src="*URL*"：必需。规定轨道文件的 URL。
    - srclang="*语言代码*"：规定轨道文本数据的语言（如果 kind="subtitles"，则必需）。
    - 例子：
        ```html
        <video width="320" height="240" controls>
            <source src="forrest_gump.mp4" type="video/mp4" />
            <source src="forrest_gump.ogg" type="video/ogg" />
            <track src="fgsubtitles_en.vtt" kind="subtitles" srclang="en" label="English" />
            <track src="fgsubtitles_no.vtt" kind="subtitles" srclang="no" label="Norwegian" />
        </video>
        ```
- **`<video>`**：定义嵌入的视频内容。
    - autoplay="autoplay"：规定视频准备就绪后立即开始播放。
    - controls="controls"：规定应显示的视频控件（例如播放/暂停按钮等）。
    - height="*px*"：设置视频播放器的高度。
    - loop="loop"：规定视频将在每次结束时重新开始。
    - muted="muted"：规定应将视频的音频输出静音。
    - poster="*URL*"：规定在下载视频期间或在用户点击播放按钮之前显示的图像。
    - preload="auto | metadata | none"：规定在页面加载时，视频是否应加载或应如何加载。
    - src="*URL*"：规定视频文件的 URL。
    - width="*px*"：设置视频播放器的宽度。

## 7. 链接

- **`<a>`**：定义超链接。

    - download="*文件名*"：规定当用户单击超链接时将下载目标。
    - href="*URL*"：规定链接指向的页面的 URL。
    - hreflang="*语言代码*"：规定被链接文档的语言。
    - media="*媒体查询*"：规定被链接文档是为何种媒介/设备优化的。
    - ping="*URL 列表*"：规定以空格分隔的 URL 列表，当链接被访问时，浏览器将发送带有 ping 正文的 POST 请求（在后台发送）。通常用于跟踪。
    - referrerpolicy="no-referrer | no-referrer-when-downgrade | origin | origin-when-cross-origin | same-origin | strict-origin-when-cross-origin | unsafe-url"：规定要与链接一起发送的引用信息。
    - rel="alternate | author | bookmark | external | help | license | next | nofollow | noreferrer | noopener | prev | search | tag"：规定当前文档和被链接文档之间的关系。
    - target="_blank | _parent | _self | _top"：规定在何处打开被链接文档。
    - type="*媒体类型*"：规定被链接文档的媒体类型。
    - 默认 CSS 值：
        ```css
        a:link, a:visited {
            color: (internal value);
            text-decoration: underline;
            cursor: auto;
        }
        a:link:active, a:visited:active {
            color: (internal value);
        }
        ```
- **`<link>`**：定义文档与外部资源的关系（最常用于链接样式表）。
    - crossorigin="anonymous | use-credentials"：规定元素如何处理跨源请求。
    - href="*URL*"：规定被链接文档的位置。
    - hreflang="*语言代码*"：规定被链接文档中文本的语言。
    - media="*媒体查询*"：规定被链接文档将在什么设备上显示。
    - referrerpolicy="no-referrer | no-referrer-when-downgrade | origin | origin-when-cross-origin | unsafe-url"：规定获取资源时要使用哪个引用者。
    - rel="alternate| author | dns-prefetch | help | icon | license | next | pingback | preconnect | prefetch | preload | prerender | prev | search | stylesheet"：必需。规定当前文档和被链接文档之间的关系。
    - sizes="*HeightxWidth* | any"：规定被链接资源的尺寸。仅适用于 rel="icon"。
    - title：定义首选或备用样式表。
    - type="媒体类型"：规定被链接文档的媒体类型。
    - 默认 CSS 值：
        ```css
        link {
            display: none;
        }
        ```
- **`<nav>`**：定义导航链接。
    - 默认 CSS 值：
        ```css
        nav {
            display: block;
        }
        ```

## 8. 列表

- **`<menu>`**：定义无序列表。

    - 默认 CSS 值：
        ```css
        menu {
            display: block;
            list-style-type: disc;
            margin-block-start: 1em;
            margin-block-end: 1em;
            margin-inline-start: 0px;
            margin-inline-end: 0px;
            padding-inline-start: 40px;
        }
        ```
- **`<ul>`**：定义无序列表。
    - 默认 CSS 值：
        ```css
        ul {
            display: block;
            list-style-type: disc;
            margin-top: 1em;
            margin-bottom: 1 em;
            margin-left: 0;
            margin-right: 0;
            padding-left: 40px;
        }
        ```
- **`<ol>`**：定义有序列表。
    - reversed="reversed"：规定列表顺序应该反转（9,8,7 ...）。
    - start="*num*"：规定有序列表的起始值。
    - type="1 | A | a | I | i"：规定要在列表中使用的标记类型。
    - 默认 CSS 值：
        ```css
        ol {
            display: block;
            list-style-type: decimal;
            margin-top: 1em;
            margin-bottom: 1em;
            margin-left: 0;
            margin-right: 0;
            padding-left: 40px;
        }
        ```
- **`<li>`**：定义列表的项目。
    - value="*num*"：仅适用于 ol 列表。规定列表项的起始值。随后的列表项将从该数字递增。
    - 默认 CSS 值：
        ```css
        li {
            display: list-item;
        }
        ```
- **`<dl>`**：定义描述列表。
    - 默认 CSS 值：
        ```css
        dl {
            display: block;
            margin-top: 1em;
            margin-bottom: 1em;
            margin-left: 0;
            margin-right: 0;
        }
        ```
- **`<dt>`**：定义描述列表中的术语/名称。
    - 默认 CSS 值：
        ```css
        dt {
            display: block;
        }
        ```
- **`<dd>`**：定义描述列表中术语的描述/值。
    - 默认 CSS 值：
        ```css
        dd {
            display: block;
            margin-left: 40px;
        }
        ```

## 9. 表格

- **`<table>`**：定义表格。

    - 默认 CSS 值：
        ```css
        table {
            display: table;
            border-collapse: separate;
            border-spacing: 2px;
            border-color: gray;
        }
        ```
    - 例子：
        ```html
        <table>
            <caption>
              每月存款
            </caption>
            <colgroup>
                <col span="2" style="background-color:red" />
                <col style="background-color:yellow" />
            </colgroup>
            <thead>
                <tr>
                    <th>书号</th>
                    <th>标题</th>
                    <th>价格</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>3476896</td>
                    <td>HTML 入门</td>
                    <td>$53</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td>二月</td>
                    <td>￥4500</td>
                    <td>￥3400</td>
                </tr>
            </tfoot>
        </table>
        ```
- **`<caption>`**：定义表格标题。
    - 默认 CSS 值：
        ```css
        caption {
            display: table-caption;
            text-align: center;
        }
        ```
- **`<th>`**：定义表格中的表头单元格。
    - abbr="*txt*"：规定标题单元格中内容的缩写版本。
    - colspan="*num*"：规定标题单元格应跨越的列数。
    - headers="*headerId*"：规定一个或多个与单元格相关的标题单元格。
    - rowspan="*num*"：规定标题单元格应跨越的行数。
    - scope="col| colgroup | row | rowgroup"：表头单元格是列头、行头还是一组列或行的头部。
    - 默认 CSS 值：
        ```css
        th {
            display: table-cell;
            vertical-align: inherit;
            font-weight: bold;
            text-align: center;
        }
        ```
- **`<tr>`**：定义表格中的行。
    - 默认 CSS 值：
        ```css
        tr {
            display: table-row;
            vertical-align: inherit;
            border-color: inherit;
        }
        ```
- **`<td>`**：定义表格中的单元。
    - colspan="*num*"：规定单元格应跨越的列数。
    - headers="*headerId*"：规定一个或多个与单元格相关的标题单元格。
    - rowspan="*num*"：设置单元格应跨越的行数。
    - 默认 CSS 值：
        ```css
        td {
            display: table-cell;
            vertical-align: inherit;
        }
        ```
- **`<thead>`**：定义表格中的表头内容。
    - 默认 CSS 值：
        ```css
        thead {
            display: table-header-group;
            vertical-align: middle;
            border-color: inherit;
        }
        ```
- **`<tbody>`**：定义表格中的主体内容。
    - 默认 CSS 值：
        ```css
        tbody {
            display: table-row-group;
            vertical-align: middle;
            border-color: inherit;
        }
        ```
- **`<tfoot>`**：定义表格中的表注内容（脚注）。
    - 默认 CSS 值：
        ```css
        tfoot {
            display: table-footer-group;
            vertical-align: middle;
            border-color: inherit;
        }
        ```
- **`<col>`**：规定 colgroup 元素中每列的列属性。
    - span="*num*"：规定 col 元素应跨越的列数。
    - 默认 CSS 值：
        ```css
        col {
            display: table-column;
        }
        ```
- **`<colgroup>`**：规定表格中供格式化的列组。
    - span="*num*"：规定列组应跨越的列数。
    - 默认 CSS 值：
        ```css
        colgroup {
            display: table-column-group;
        }
        ```

## 10. 样式和语义

- **`<style>`**：定义文档的样式信息。

    - media="*媒体查询*"：规定样式使用的媒体。
    - type="text/css"：规定 style 标签的媒体类型。
    - 默认 CSS 值：
        ```css
        style {
            display: none;
        }
        ```
- **`<div>`**：定义文档中的节（片段）。
    - 默认 CSS 值：
        ```css
        div {
            display: block;
        }
        ```
- **`<span>`**：定义文本的一部分，或文档的一部分。
- **`<header>`**：定义文档或小节的页眉。
    - 默认 CSS 值：
        ```css
        div {
            display: block;
        }
        ```
- **`<hgroup>`**：定义标题和相关内容。
    - 默认 CSS 值：
        ```css
        div {
            display: block;
        }
        ```
- **`<footer>`**：定义文档或小节的页脚。
    - 默认 CSS 值：
        ```css
        div {
            display: block;
        }
        ```
- **`<main>`**：定义文档的主要内容。
- **`<section>`**：定义文档中的一个部分。
    - 默认 CSS 值：
        ```css
        div {
            display: block;
        }
        ```
- **`<search>`**：定义搜索部分。
    - 默认 CSS 值：
        ```css
        div {
            display: block;
        }
        ```
- **`<article>`**：定义文章。
    - 默认 CSS 值：
        ```css
        div {
            display: block;
        }
        ```
- **`<aside>`**：定义页面内容之外的内容。
    - 默认 CSS 值：
        ```css
        div {
            display: block;
        }
        ```
- **`<details>`**：定义用户可查看或隐藏的其他详细信息。
    - open="open"：规定详细信息应该对用户可见（打开）。
    - 默认 CSS 值：
        ```css
        div {
            display: block;
        }
        ```
- **`<dialog>`**：定义对话框或窗口。
    - open="open"：规定 dialog 元素处于活动状态，并且用户可以与之交互。
- **`<summary>`**：定义 details 元素的可见标题。
    - 默认 CSS 值：
        ```css
        div {
            display: block;
        }
        ```
- **`<data>`**：添加给定内容的机器可读的翻译。
    - value="*machine-readable format*"：规定元素内容的机器可读翻译。
    - 例子：
        ```html
        <ul>
            <li><data value="10535">圣女果</data></li>
            <li><data value="10536">牛肉番茄</data></li>
            <li><data value="10537">零食番茄</data></li>
        </ul>
        ```

## 11. 元信息

- **`<head>`**：包含文档的元数据/信息。

    - 默认 CSS 值：
        ```css
        head {
            display: none;
        }
        ```
- **`<meta>`**：定义关于 HTML 文档的元数据。
    - charset="*字符集*"：规定 HTML 文档的字符编码。
    - content="*txt*"：规定与 http-equiv 或 name 属性关联的值。
    - http-equiv="content-security-policy | content-type | default-style | refresh"：为 content 属性的信息/值提供 HTTP 标头。
    - name="application-name | author | description | generator | keywords | viewport"：规定元数据的名称。
    - 例子：
        ```html
        <head>
            <meta charset="UTF-8" />
            <meta name="description" content="免费的 Web 教程" />
            <meta name="keywords" content="HTML, CSS, JavaScript" />
            <meta name="author" content="Bill Gates" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        </head>
        ```
- **`<base>`**：规定文档中所有相对 URL、/ 或目标的基准 URL。一份文档中只能有一个 base 元素，而且它必须位于 head 元素内部。
    - href="*URL*"：规定页面中所有相对 URL 的基本 URL。
    - target="_blank | _parent | _self | _top"：规定页面中所有超链接和表单的默认目标。

## 12. 编程

- **`<script>`**：定义客户端脚本。script 元素要么包含脚本语句，要么通过 src 属性指向外部脚本文件。script 元素所属类型，位于 head 元素中的 script 元素属于元数据元素，位于其他元素（如 body 或 section）中的则属于短语元素。

    - async="async"：规定脚本在解析页面的同时进行并行下载，并在可用时立即执行（在解析完成之前）（仅适用于外部脚本）。
    - crossorigin="anonymous | use-credentials"：将请求的模式设置为 HTTP CORS 请求。
    - defer="defer"：规定脚本在解析页面的同时进行并行下载，并在页面完成解析后执行（仅适用于外部脚本）。
    - integrity="filehash"：允许浏览器检查获取的脚本，以确保如果源代码被篡改，代码永远不会被加载。
    - nomodule="True | False"：规定脚本不应在支持 ES2015 模块的浏览器中执行。
    - referrerpolicy="no-referrer | no-referrer-when-downgrade | origin | origin-when-cross-origin | same-origin | strict-origin | strict-origin-when-cross-origin | unsafe-url"：规定在获取脚本时发送哪些引用者信息。
    - src="*URL*"：规定外部脚本文件的 URL。
    - type="*脚本类型*"：规定脚本的媒体类型。可能值：module、text/javascript、importmap。
    - 默认 CSS 值：
        ```css
        script {
            display: none;
        }
        ```
- **`<noscript>`**：定义针对不支持客户端脚本的用户的替代内容。当在 head 中使用时，noscript 元素只能包含 link、style 和 meta 元素。
- **`<embed>`**：单标签。定义外部资源的容器。例如网页、图片、媒体播放器或插件应用程序。
    - height="*px*"：规定嵌入内容的高度。
    - src="*URL*"：规定要嵌入的外部文件的地址。
    - type="*媒体类型*"：规定嵌入内容的媒体类型。
    - width="*px*"：规定嵌入内容的宽度。
    - 默认 CSS 值：
        ```css
        embed:focus {
            outline: none;
        }
        ```
- **`<object>`**：定义外部资源的容器。最初是为了嵌入浏览器插件而设计的。
    - data="*URL*"：规定要由对象使用的资源的 URL。
    - form="*formId*"：规定对象所属的表单。
    - height="*px*"：规定对象的高度。
    - name="*txt*"：规定对象的名称。
    - type="*媒体类型*"：规定 data 属性中指定的数据的媒体类型。
    - typemustmatch="true | false"：规定 type 属性与资源的实际内容是否必须匹配才能显示。
    - usemap="*#mapname*"：规定要与对象一起使用的客户端图像映射的名称。
    - width="*px*"：规定对象的宽度。
    - 默认 CSS 值：
        ```css
        object:focus {
            outline: none;
        }
        ```
- **`<param>`**：单标签。用于定义 object 元素的参数。
    - name="*名称*"：规定参数的名称。
    - value="*值*"：规定参数的值。
    - 默认 CSS 值：
        ```css
        param {
            display: none;
        }
        ```

# 三、全局属性


1. **accesskey**="*character*"：规定激活元素的快捷键。属性的值必须是单字符（一个字母或一个数字）。
1. **class**="*classname*"：规定元素的一个或多个类名（引用样式表中的类）。如果要指定多个类，可以用空格分隔类名。命名规则：必须以字母 A-Z 或 a-z 开头，可以后接：字母（A-Za-z）、数字（0-9）、连字符（"-"）和下划线（"_"）。
1. **contenteditable**="true | false"：规定元素内容是否可编辑。会从其父元素继承该属性。
1. **contextmenu**="*menuId*"：规定元素的上下文菜单。上下文菜单在用户点击元素时显示。
    - 例子：
        ```html
        <div contextmenu="mymenu">
            <menu type="context" id="mymenu">
                <menuitem label="Refresh"></menuitem>
                <menuitem label="Twitter"></menuitem>
            </menu>
        </div>
        ```
1. **data-\***="*somevalue*"：用于存储页面或应用程序的私有定制数据。
    - 例子：
        ```html
        <ul>
            <li data-animal-type="鸟类">喜鹊</li>
            <li data-animal-type="鱼类">金枪鱼</li>
            <li data-animal-type="蜘蛛">蝇虎</li>
        </ul>
        ```
1. **dir**="ltr | rtl | auto"`：规定元素中内容的文本方向。
1. **draggable**="true | false | auto"：规定元素是否可拖动。链接和图像默认是可拖动的。
1. **dropzone**="copy | move | link"：规定在拖动被拖动数据时是否进行复制、移动或链接。
    - copy：拖动数据会产生被拖动数据的副本。
    - move：拖动数据会导致被拖动数据被移动到新位置。
    - link：拖动数据会产生指向原始数据的链接。
1. **enterkeyhint**="*value*"`：规定虚拟键盘上 enter 键的文本。
    - 例子：
        ```html
        <input type="text" enterkeyhint="search" />
        ```
1. **hidden**="hidden"：规定元素仍未或不再相关。
1. **id**="*id*"：规定元素的唯一 id。命名规则：必须包含至少一个字符，不能包含任何空格字符。
1. **inert**="inert"：规定浏览器应忽略此部分。
    - 例子：
        ```html
        <div inert>
            <button onclick="alert(42)">
            <input type="text">
            <a href="https://w3school.com.cn">W3School.com.cn</a>
        </div>
        ```
1. **inputmode**="decimal | email | none | numeric | search | tel | text | url"：规定虚拟键盘的模式。
    - decimal：只显示数字键盘，通常还有一个逗号键。
    - email：文本键盘，键通常用于电子邮件地址，如 [@]。
    - none：不应出现键盘。
    - numeric：只显示数字键盘。
    - search：文本键盘，[enter] 键通常显示为 [go]。
    - tel：只显示数字键盘，通常还有 [+]、[*] 和 [#] 键。
    - text：默认。文本键盘。
    - url：文本键盘，键通常用于网址，如 [.] 和 [/]，以及特殊的 [.com] 键，或者其他通常用于本地设置的域名结束符。
    - 例子：
        ```html
        <input type="text" inputmode="numeric" />
        ```
1. **lang**="*languageCode*"：规定元素内容的语言。"en" 代表英语，"es" 代表西班牙语，"fr" 代表法语。
1. **popover**：规定弹出框元素。
    - 例子：
        ```html
        <h1 popover id="myheader">Hello</h1>
        <button popovertarget="myheader">点击我！</button>
        ```
1. **spellcheck**="true | false"：规定是否对元素进行拼写和语法检查。
1. **style**="*styleDefinitions*"：规定元素的行内 CSS 样式。一个或多个由分号分隔的 CSS 属性和值。
1. **tabindex**="*number*"：规定元素的 tab 键次序。1 是第一个。
1. **title**="*text*"：规定有关元素的额外信息。
1. **translate**="yes | no"：规定是否应该翻译元素内容。

# 四、事件

## 1. Windows 事件

- **onafterprint**：文档打印之后运行的脚本。
- **onbeforeprint**：文档打印之前运行的脚本。
- **onbeforeunload**：文档卸载之前运行的脚本。
- **onerror**：在错误发生时运行的脚本。
- **onhaschange**：当文档已改变时运行的脚本。
- **onload**：页面结束加载之后触发。一旦完全加载所有内容（包括图像、脚本文件、CSS 文件等），就执行一段脚本。
- **onmessage**：在消息被触发时运行的脚本。
- **onoffline**：当文档离线时运行的脚本。
- **ononline**：当文档上线时运行的脚本。
- **onpagehide**：当窗口隐藏时运行的脚本。
- **onpageshow**：当窗口成为可见时运行的脚本。
- **onpopstate**：当窗口历史记录改变时运行的脚本。
- **onredo**：当文档执行撤销（redo）时运行的脚本。
- **onresize**：当浏览器窗口被调整大小时触发。
- **onstorage**：在 Web Storage 区域更新后运行的脚本。
- **onundo**：在文档执行 undo 时运行的脚本。
- **onunload**：一旦页面已下载时触发（或者浏览器窗口已被关闭）。
- **onhashchange**：URL 中 # 后部分改变时触发。

## 2. Form 事件

- **onblur**：元素失去焦点时运行的脚本。
- **onchange**：在元素值被改变时运行的脚本。
    - 例子：
        ```html
        <input type="text" name="txt" value="Hello" onchange="checkField(this.value)" />
        ```
- **oncontextmenu**：当上下文菜单被触发时运行的脚本。
- **onfocus**：当元素获得焦点时运行的脚本。
- **onformchange**：在表单改变时运行的脚本。
- **onforminput**：当表单获得用户输入时运行的脚本。
- **oninput**：当元素获得用户输入时运行的脚本。
- **oninvalid**：当元素无效时运行的脚本。
- **onselect**：在元素中文本被选中后触发。
- **onsubmit**：在提交表单时触发。

## 3. Keyboard 事件

- **onkeydown**：在用户按下按键时触发。事件次序：onkeydown，onkeypress，onkeyup。
- **onkeypress**：在用户敲击按钮时触发。不会被所有按键触发（例如 ALT、CTRL、SHIFT、ESC）。
- **onkeyup**：当用户释放按键时触发。

## 4. Mouse 事件

- **onclick**：元素上发生鼠标点击时触发。
- **ondblclick**：元素上发生鼠标双击时触发。
- **ondrag**：元素被拖动时运行的脚本。
- **ondragend**：在拖动操作末端运行的脚本。
- **ondragenter**：当元素已被拖动到有效拖放区域时运行的脚本。
- **ondragleave**：当元素离开有效拖放目标时运行的脚本。
- **ondragover**：当元素在有效拖放目标上正在被拖动时运行的脚本。
- **ondragstart**：在拖动操作开端运行的脚本。
- **ondrop**：当被拖元素正在被拖放时运行的脚本。
- **onmousedown**：当元素上按下鼠标按钮时触发。
    - 相对于 onmousedown 事件的事件次序（限于鼠标左/中键）：onmousedown，onmouseup，onclick。
    - 相对于 onmousedown 事件的事件次序（限于鼠标右键）：onmousedown，onmouseup，oncontextmenu。
- **onmousemove**：当鼠标指针移动到元素上时触发。
- **onmouseout**：当鼠标指针移出元素时触发。
- **onmouseover**：当鼠标指针移动到元素上时触发。
- **onmouseup**：当在元素上释放鼠标按钮时触发。
- **onmousewheel**：当鼠标滚轮正在被滚动时运行的脚本。
- **onscroll**：当元素滚动条被滚动时运行的脚本。

## 5. Media 事件

- **onabort**：在退出时运行的脚本。
- **oncanplay**：当文件就绪可以开始播放时运行的脚本（缓冲已足够开始时）。
- **oncanplaythrough**：当媒介能够无需因缓冲而停止即可播放至结尾时运行的脚本。
- **ondurationchange**：当媒介长度改变时运行的脚本。
- **onemptied**：当发生故障并且文件突然不可用时运行的脚本（比如连接意外断开时）。
- **onended**：当媒介已到达结尾时运行的脚本（可发送类似“感谢观看”之类的消息）。
- **onerror**：当在文件加载期间发生错误时运行的脚本。
- **onloadeddata**：当媒介数据已加载时运行的脚本。
- **onloadedmetadata**：当元数据（比如分辨率和时长）被加载时运行的脚本。
- **onloadstart**：在文件开始加载且未实际加载任何数据前运行的脚本。
- **onpause**：当媒介被用户或程序暂停时运行的脚本。
- **onplay**：当媒介已就绪可以开始播放时运行的脚本。
- **onplaying**：当媒介已开始播放时运行的脚本。
- **onprogress**：当浏览器正在获取媒介数据时运行的脚本。
- **onratechange**：每当回放速率改变时运行的脚本（比如当用户切换到慢动作或快进模式）。
- **onreadystatechange**：每当就绪状态改变时运行的脚本（就绪状态监测媒介数据的状态）。
- **onseeked**：当 seeking 属性设置为 false（指示定位已结束）时运行的脚本。
- **onseeking**：当 seeking 属性设置为 true（指示定位是活动的）时运行的脚本。
- **onstalled**：在浏览器不论何种原因未能取回媒介数据时运行的脚本。
- **onsuspend**：在媒介数据完全加载之前不论何种原因终止取回媒介数据时运行的脚本。
- **ontimeupdate**：当播放位置改变时（比如当用户快进到媒介中一个不同的位置时）运行的脚本。
- **onvolumechange**：每当音量改变时（包括将音量设置为静音）时运行的脚本。
- **onwaiting**：当媒介已停止播放但打算继续播放时（比如当媒介暂停已缓冲更多数据）运行脚本。

