# 一、笔记

1. 运行 JavaScript 文件：node *path-to-\*.js*
1. 更新源：
   - npm config get registry：查看现有源。https://registry.npmjs.org/
   - npm config set registry https://registry.npm.taobao.org：设置为淘宝源。
   - npm update
1. npm 配置文件路径：$HOME/.npmrc。
1. npm 网络代理：
   - npm config set proxy http://127.0.0.1:8889
   - npm config set https-proxy http://127.0.0.1:8889
