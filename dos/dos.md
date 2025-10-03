# 一、命令

1. cd *路径*：切换目录。
1. cd ..：返回上一级目录。
1. cd \：返回到根目录。
1. cd *盘符*：切换盘符。
1. md *文件夹* ：新建文件夹。可以创建以 . 开头的文件夹。
1. rd *文件夹*：删除目录，只能删除空目录，不能删除当前目录。
1. dir *路径* *参数*：显示目录下的文件及文件夹。
    - /p：分页显示下一页内容，按任意键查看下一屏。
    - /s：显示所有目录及其子目录下所有文件。
    - /w：宽屏显示。
    - /o：分类顺序显示。
1. copy *源路径* *目的路径*：复制文件，只能复制文件不包括子文件夹中的文件，可以使用通配符 *。
1. xcopy *源路径* *目的路径*：参数同上，但能复制子文件夹中文件。
    - /s：不能复制空目录。
    - /e：复制空目录。
1. echo *内容* > *文件名*：新建文件。
1. type nul > *文件名*：新建空文件。
1. del *文件名*：删除文件，不能删除文件夹。可以使用同配符 *。
1. rename *旧名* *新名*：更改文件名。
1. ipconfig：查看 IP 信息。
1. ping *ip*：测试与 IP 地址链接。
1. net start *服务名*：启动服务。
1. net stop *服务名*：关闭服务。
1. *服务名* restart：重启服务。
1. sc delete *服务名*：删除服务。
1. diskpart：进入磁盘管理。
1. list volume：列出所有盘。
1. select volume *盘*：选择要操作的盘。
1. shirk desired=8000：压缩当前盘符内存 8000MB。
1. diskpart：进入磁盘。
    - list disk：列出磁盘。
    - select disk *盘号*：选择盘。
    - list partition：列出分区。
    - select partition *区号*：选择分区。
    - assign letter=*盘符*：挂在分区分配盘符。
    - remove letter=*盘符*：移除挂在的区。
    - delete partition *num*：删除分区。
1. powercfg -h off<sub>或者</sub>on：关闭或打开系统休眠功能。
1. chcp *字符编码*：临时改变命令窗字符编码。936 为 GBK，65001 为 UTF-8。
1. cls：清空屏幕。
1. exit：退出。
1. setx *键* *值*：设置用户环境变量。
1. drivrerquery：列出系统已安装驱动。
1. findstr *str*：筛选输出。
1. tasklist：后台进程列表。
1. reg add *路径* /v *名称* /t *类型* /d *数值*：添加值。
1. query hklm /v *key*：查询注册表路径下数据。
1. delete path /v *key* /f：删除注册表和它的子项。
1. export *path* *dest*：导出注册表。
1. import *dest*：导入注册表。
1. nslookup：解析域名 IP 地址。
1. bcdedit /set {bootmgr} path \EFI\debian\grubx64.efi
1. bcdedit /set {bootmgr} description "Debian Linux"
1. bcdedit /enum all
