major.minor.path-pre.xxx+buildmeta  

# 语法

- major minor path 为整数递增，禁止前缀补零。pre为[0-9a-zA-Z-]，以点号分割，buildmeta为[0-9a-zA-Z]
- 主版本号、次版本号及修订号以数值比较。
- 先行版本号由左到右的每个被句点分隔的标识符来比较，有数字的标识符以数值高低比较，有字母或连接号时则逐字以ASCII的排序来比较，数字的标识符比非数字的标识符优先层级低，栏位比较多的先行版本号优先层级比较高。
- 编译版本信息属于相同优先层级。
- Golang 伪版本为 vX.0.0-yyyymmddhhmmss-abcdefabcdef、vX.Y.(Z+1)-0.yyyymmddhhmmss-abcdefabcdef、vX.Y.Z-pre.0.yyyymmddhhmmss-abcdefabcdef。
