# 笔记

1. /ect/default/grub 的一些参数：`GRUB_CMDLINE_LINUX_DEFAULT="quiet splash i8042.redirect i8042.dumbkbd i8042.reset=0 i915.enable_psr=0"`。
1. 设置 grub 启动黑色背景：`splash video=efifb:off`。
1. ctrl+z 挂起当前进程。ctrl+d 结束命令。
1. ~{account} 表示这个 account 的家目录，- 表示上次所在目录。
1. /dev/sd*x* 盘信息，主设备号（磁盘驱动程序），从设备号（访问地址）。
1. ls -l 输出中的第二字段表示文件夹说明子文件个数。
1. umask 文件是 777 umask 111，文件夹 777 umask。
1. cron 书写格式：分 小时 天 月 星期 命令。
1. LVM 一些名称：PE LE PV VG LV。
1. Chrome 浏览器下载地址：https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb。
1. ssh 复制文件：scp src ivfzhou@ivfzhoudebian:~/src。

# UID 类型

- 1-999 虚拟用户，1000+ 普通用户。

# 文件类型

- \- 普通文件
- d 目录
- l 链接文件
- p 管理文件
- b 设备文件
- c 设备文件
- s 套接字文件
- f 命令管道

# 文件权限

- 文件的权限含义：r 可以查看内容，w 可以修改内容，x 可以执行。
- 文件夹的权限含义：r 可以查看文件夹下文件名和目录名，w 可以删除增加文件夹下文件和目录，x 可以 cd 到该文件夹下，可以看到文件权限信息。
- 特殊权限 suid4：用于二进制可执行文件，执行命令时获得文件属主权限。例如 passwd。
- 特殊权限 sgid2：用于目录，在该目录下创建文件和目录，权限自动更改为该目录的属组。用于文件共享。
- 特殊权限 sbit1：用于目录，在该目录下创建文件和目录时，仅 root 和自己可以删除。

# Debian12 软件源配置

- 配置所在路径：/etc/apt/sources.list。源配置例子，把 https 协议改成 http 仍然可用。
    ```
    deb https://mirrors.163.com/debian bookworm main non-free non-free-firmware contrib
    deb-src https://mirrors.163.com/debian bookworm main non-free non-free-firmware contrib

    deb https://mirrors.163.com/debian-security bookworm-security main
    deb-src https://mirrors.163.com/debian-security bookworm-security main

    deb https://mirrors.163.com/debian/ bookworm-updates main non-free non-free-firmware contrib
    deb-src https://mirrors.163.com/debian bookworm-updates main non-free non-free-firmware contrib

    deb https://mirrors.163.com/debian/ bookworm-backports main non-free non-free-firmware contrib
    deb-src https://mirrors.163.com/debian bookworm-backports main non-free non-free-firmware contrib
    ```
- Debian WSL 上的默认配置：
    ```
    deb http://deb.debian.org/debian bookworm main
    deb http://deb.debian.org/debian bookworm-updates main
    deb http://security.debian.org/debian-security bookworm-security main
    deb http://ftp.debian.org/debian bookworm-backports main
    ```
- Debian 官方配置：
    ```
    deb http://deb.debian.org/debian/ bookworm main contrib non-free-firmware non-free
    deb-src http://deb.debian.org/debian/ bookworm main contrib non-free-firmware non-free

    deb http://security.debian.org/debian-security/ bookworm-security main contrib non-free-firmware non-free
    deb-src http://security.debian.org/debian-security/ bookworm-security main contrib non-free-firmware non-free

    deb http://deb.debian.org/debian/ bookworm-updates main non-free non-free-firmware contrib
    deb-src http://deb.debian.org/debian/ bookworm-updates main non-free non-free-firmware contrib

    deb http://ftp.debian.org/debian/ bookworm-backports main contrib non-free non-free-firmware
    deb-src http://ftp.debian.org/debian/ bookworm-backports main contrib non-free non-free-firmware
    ```

# LVM 操作案例

## LV 扩容

```shell
sudo lvresize -L +10GiB /dev/vg/var
sudo resize2fs /dev/mapper/vg-var
```

## LV 缩容

```shell
sudo lsof | grep '/dev/mapper/vg-var' # 查看文件占用进程
sudo vim /etc/fstab # 编辑启动分区挂载配置，取消分区自动挂载，然后重启
sudo umount /dev/mapper/vg-var
sudo e2fsck -f /dev/mapper/vg-var
sudo resize2fs /dev/mapper/vg-var 50G
sudo lvreduce -L 50G /dev/vg/var
mount /dev/mapper/vg-var /var
```

## PV 缩减

```shell
sudo pvmove /dev/nvme0n1p7 # 将使用的PE转移到别的PV上去
pvmove -n /dev/vg01/lv01 /dev/sdb1 /dev/sdc1 # 将/dev/sdb1上的所有 PE 移动到/dev/sdc1上。只想移动特定的逻辑卷
sudo vgreduce vg /dev/nvmeon1p7
```

## PV 缩容

```shell
sudo fdisk -l
sudo pvdisplay --units b
sudo pvresize --setphysicalvolumesize 40GiB /dev/xxx
# 分区缩容
sudo pvresize /dev/xxx
```

## PV 增加

```shell
sudo pvcreate /dev/nvmeon1p7
sudo vgextend vg /dev/nvmeon1p7
```

## 删除分区

```shell
sudo parted -l
sudo parted /dev/nvme0n1
help
print
rm 7
```

## 分区缩容

```shell
fdisk /dev/xxx
p
d
n
w
```

# openssl 操作案例

1. 创建私钥：`openssl genrsa -out ivfzhou.pem.key -passout pass:123456 4096`
1. 查看私钥信息：`openssl rsa -in ivfzhou.pem.key -text -noout -passin pass:123456`
1. 查看私钥信息：`openssl pkcs8 -in ivfzhou.pem.key.p8 [ -passin pass:123456 | -nocrypt ] -text -noout`
1. 创建证书：`openssl req -x509 -new -key ivfzhou.pem.key -days 365 -out ivfzhou.pem.crt -subj '/C=CN/ST=HuNan/L=Changsha/O=ivfzhou Ltd/OU=ivfzhou/CN=ivfzhou/emailAddress=ivfzhou@126.com'`，C=Country 国家代码 CN=CommonName 通用名称 O=Organization 组织 OU=OrganizationUnit 组织单位 ST=StateOrProvince 州或省 L=Locality 城市 STREET=StreetAddress 街道 EMAIL=EmailAddress 电子邮件 DC=DomainComponent 域名组件。
1. 查看证书信息：`openssl x509 -in ivfzhou.pem.crt -text -noout`
1. 查看证书信息：`openssl crl2pkcs7 -nocrl -certfile ivfzhou.pem.crt | openssl pkcs7 -print_certs -text -noout`
1. 查看证书指纹：`openssl x509 -in ivfzhou.pem.crt -noout -fingerprint -sha1`
1. 查看证书指纹：`sha1sum ivfzhou.der.crt`
1. 创建证书签名请求：`openssl req -new -key ivfzhou0.pem.key -out ivfzhou0.pem.csr -subj '/C=CN/ST=HuNan/L=Changsha/O=ivfzhou0 Ltd/OU=ivfzhou0/CN=ivfzhou0/emailAddress=ivfzhou0@126.com'`
1. 对请求签名：`openssl x509 -req -in ivfzhou0.pem.csr -CA ivfzhou.pem.crt -CAkey ivfzhou.pem.key -days 365 -out ivfzhou0.ivfzhou.pem.crt`
1. 导出 pkcs#12 格式：`openssl pkcs12 -export -inkey ivfzhou0.pem.key -in ivfzhou0.ivfzhou.pem.crt -out ivfzhou0.pfx -passout pass:123456`
1. pkcs#12 中导出私钥：`openssl pkcs12 -in ivfzhou0.pfx -legacy -nocerts -out ivfzhou0_1.pem.key -passin pass:123456 -passout pass:123456`
1. pkcs#12 中导出证书：`openssl pkcs12 -in ivfzhou0.pfx -nokeys -out ivfzhou0_1.pem.crt -passin pass:123456`
1. 证书 pem 转 der 格式：`openssl x509 -in ivfzhou0.ivfzhou.pem.crt -inform pem -out ivfzhou0.ivfzhou.der.crt -outform der`
1. 证书请求格式转换：`openssl req -in ivfzhou1.pem.csr -inform pem -out ivfzhou1.der.csr -outform der`
1. 对证书请求签名：`openssl sha1 -sign ivfzhou1.pem.key ivfzhou1.der.csr > sha1.sign`
1. 对私钥加密：`openssl pkey -in <file> -out <file> -aes256 -passin pass:<123456>`
1. 判断证书和私钥模数：`openssl [rsa | x509] -noout -modulus -in <file>`
1. 从证书中提取公钥：`openssl x509 -pubkey -noout -in <cert> -out <pub>`
1. 校验私钥签名：`openssl dgst -sha1 -verify <pub> -signature <sign> <origin>`
1. 从签名数据中获取散列值：`openssl rsautl -verify -pubin -inkey <pub> -in <sign> -out <hash>`

# 配置文件

## 启动配置

/usr/lib/systemd/system  
runlevelx.target

## sudo 配置

文件所在位置：/etc/sudoers  
使用 visudo 编辑  
配置格式：user host=(asuser:asgroup) NOPASSWD: command，使用 ALL 表示所有，%sudo 表示 sudo 用户组成员。

## DNS

路径：/etc/resolv.conf  
内容：nameserver 114.114.114.114

## gnome 桌面图标配置路径

/usr/share/applications/

## 启动脚本目录

/etc/init.d/

## 启动挂载配置

/etc/fstab  
FileSystem Dir Type Options Dump Pass

## 定义用户文件夹名配置文件位置

/home/xxx/.config/user-dirs.dirs

## 内核配置文件路径

/boot/configxxx

## SELinux 配置文件路径

/etc/selinux/config

## 用户配置文件

/etc/passwd 七段：名、是否需要密码、uid、gid、注释、家路径、命令解释器。  
/etc/shadow 存贮密码。用户名、密码、最后一修改时间、最小修改时间间隔、密码有效期、密码需要更改前的警告天数、密码过期后的宽限天数、账号失效时间、保留字段。  
/etc/group 用户组名、组密码、gid、组内成员。

## 网卡配置路径

- /etc/sysconfig/network-scripts/
  - BOOTPROTO=dhcp 或者 static none，动态或者静态地址 IP。
  - IPADDR IP 地址。
  - NETMASK 子网掩码。
  - DEVICE 网卡名。
  - ONBOOT=yes 或者 no 开机启动与否。

## 系统信息路径

- /proc/cpuinfo 显示 cpu 信息。
- /proc/interrupts 显示中断。
- /proc/meminfo 校验内存使用。
- /proc/swaps 显示 swap 使用。
- /proc/version 显示内核版本。
- /proc/net/dev 显示网络适配器及统计。
- /proc/mounts 显示已加载的文件系统。
- /proc/pid/fd 程序输入输出。
- /proc/pid/cwd 运行目录。

## 进程日志信息

- /var/log/cron 周期性的程序日志。
- /var/log/secure 安全日志。
- /var/log/message 异常信息。
- /var/log/dmesg 内核日志。

# 导入证书

路径 /usr/local/share/ca-certificates/ivfzhou.crt  
运行 sudo update-ca-certificates --verbose

# 添加字体

```shell
sudo mkdir /usr/share/fonts/myfonts
sudo mv fonts/* /usr/share/fonts/myfonts/
sudo chmod 644 /usr/share/fonts/myfonts/*
cd /usr/share/fonts/myfonts/
sudo mkfontscale
sudo mkfontdir
sudo fc-cache
```

# 设置交换分区

```shell
sudo swapoff /swapfile
sudo rm /swapfile
sudo dd if=/dev/zero of=/swapfile bs=1G count=8
sudo chmod 600 /swapfile
sudo mkswap /swapfile
sudo swapon /swapfile
sudo vim /etc/fstab
```
```shell
sudo fdisk /dev/sdx # 新建分区。
sudo mkswap /dev/sdax
sudo swapon /dev/sdax
free -h
echo '/dev/sdax none swap sw 0 0' | sudo tee -a /etc/fstab
```

# 设置时区时间

1. export TZ='Asia/Shanghai'
1. sudo rm -f /etc/localtime
1. sudo ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

```shell
timedatectl # 查看时区。
timedatectl list-timezones # 列出可用时区。
sudo timedatectl set-timezone Asia/Shanghai # 设置系统时区。
sudo apt install systemd-timesyncd
sudo systemctl enable systemd-timesyncd
sudo systemctl start systemd-timesyncd
```

# 设置 apt 网络代理

1. 配置 apt 网络代理，配置文件路径：/etc/apt/apt.conf.d/proxy.conf。配置内容：
    ```conf
    Acquire {
      HTTP::proxy "http://127.0.0.1:8889";
      HTTPS::proxy "http://127.0.0.1:8889;
    }
    ```
    ```conf
    Acquire {
      HTTP::proxy::download.docker.com "http://127.0.0.1:8889";
      HTTPS::proxy::download.docker.com "http://127.0.0.1:8889;
    }
    ```

# 配置 Debian12 网络

1. ip link show：查看网络。
1. ip link set *eth0* up：启用网卡 *eth0*。
1. ip addr add 192.168.137.128/24 dev eth0：配置网卡 ip。
1. ip route add default via 192.168.137.1 dev eth0：配置网卡网关。
1. echo "nameserver 114.114.114.114" > /etc/resolv.conf
1. ping baidu.com
1. sudo systemctl restart networking：重启网络。
1. 编辑 /etc/network/interfaces 设置永久网卡配置。
    - 例子：
        ```
        auto eth0
        iface eth0 inet static
            address 192.168.42.128
            netmask 255.255.255.0
            gateway 192.168.42.2
            dns-nameservers 114.114.114.114 8.8.8.8 8.8.4.4
        ```
        ```
        auto eth0
        iface eth0 inet dhcp
        ```
        ```
        auto wlan0
        iface wlan0 inet dhcp
            wpa-ssid "your_SSID"
            wpa-psk "your_password"
        ```

# 设置系统语言

```shell
# sudo vim /etc/locale.gen
# sudo locale-gen
localectl status # 查看语言。
sudo localectl set-locale LANG=en_US.UTF-8 # zh_CN.UTF-8
cat /etc/default/locale
sudo reboot
```

# 配置 iptables

[iptables](./iptables.conf) 配置文件路径 /etc/iptables/rules.v4  
raw > mangle > nat > filter，prerouting(r, m ,n) > input(m, f) > forward(m, f) > output(r, m, n, f) > postrouting(m, n)，table > chain > rule

```shell
sudo apt install iptables iptables-persistent
sudo iptables -A INPUT -m state --state RELATED,ESTABLISHED -j ACCEPT
sudo iptables -A INPUT -p icmp -j ACCEPT
sudo iptables -A INPUT -i lo -j ACCEPT
sudo iptables -A INPUT -p tcp -m state --state NEW -m tcp --dport 22 -j ACCEPT
sudo iptables -A INPUT -j REJECT --reject-with icmp-host-prohibited
sudo iptables -A FORWARD -j REJECT --reject-with icmp-host-prohibited
sudo netfilter-persistent save
sudo iptables-restore < /etc/iptables/xxx # 应用配置文件的规则。
sudo iptables -F # 清空规则，机器重启后失效。
```

# 命令

## 常用命令

1. 查看系统信息：lscpu free fdisk top printenv
1. 基础配置：localectl timedatectl date hwclock
1. 管理用户：useradd id passwd cracklib-unpacker create-cracklib-dict usermod userdel groupadd groupmod groupdel newgrp
1. 管理软件包：vi dnf createrepo
1. 管理服务：systemctl ln
1. 管理进程：who ps top kill at crontab jobs fg bg atrm atq nice renice kill killall nohub
1. 配置网络：nmcli ip ifup ifdown modprobe modinfo ss route nslookup
1. LVM：pvcreate pvdisplay pvremove pvchange vgcreate vgdisplay vgchange vgextend vgreduce vgremove lvcreate lvresize lvextend lvreduce lvremove mkfs mount umount blkid resize2fs e2fsck lvchange
1. 其他：uptimevmstat sar ps top free lsblk lspci ethtool dmidecode

## more

- more *参数*... *文件*...：适合屏幕查看的文件阅读输出工具。more +num
    - -d 显示帮助而非响铃。
    - -f 计算逻辑行数，而非屏幕行数。
    - -l 屏蔽换页(form feed)后的暂停。
    - -c 不滚动，显示文本并清理行末。
    - -p 不滚动，清除屏幕并显示文本。
    - -s 将多行空行压缩为一行。
    - -u 屏蔽下划线。
    - -<数字> 每屏的行数。
    - +<数字> 从指定行开始显示文件。
    - +/<字符串> 从匹配搜索字符串的位置开始显示文件。
    - -V 打印版本信息。
    - 查看模式：
    - q 或者 Q 退出。
    - n 空格或者 z 翻页。滚动出 n 行。
    - n 回车下一行。滚动出 n 行。
    - h 或者 ? 帮助。
    - ns 跳过下面一行或者 n 行。
    - nd 或者 ctrl+D 滚动十一行或者 n 行，之后默认滚动 n。
    - f 跳过下面一屏或者 n 行。
    - b 或者 ctrl+B 跳过上面一屏或者 n 屏。
    - ' 转到上次搜索开始处。
    - = 打印当前行号。
    - n/PATTERN 搜索正则表达式第 n 次出现处。
    - nn 搜索前一正则表达式第 n 次出现处。
    - !cmd 或者 :!cmd 在子 shell 中执行 \<cmd> 命令。
    - v 启动编辑。
    - ctrl+l 重绘屏幕。
    - n:n 转到后面第 n 个文件。
    - n:p 转到前面第 n 个文件。
    - :f 显示当前文件名和行号。
    - . 重复前一命令。

## less

- less *文件*：类似于 more 命令，但是它允许在文件中和正向操作一样的反向操作，浏览多个文件时，输入:n 切换到上一个文件，输入:p 切换到下一个文件。
    - 空格键 滚动一页。
    - 回车键 滚动一行。
    - \[pagedown] 向下翻动一页。
    - \[pageup] 向上翻动一页。
    - /str 向下搜索字符串的功能。
    - ?str 向上搜索字符串的功能。
    - n 重复前一个搜索（与 / 或 ? 有关）。
    - N 反向重复前一个搜索（与 / 或 ? 有关）。

## vi

- vi *参数*... *文件*：编辑文件。
    - 正常模式：
        - hjkl 左上下右，可以使用 30j 或 30↓ 的组合按键。
        - ctrl+f 前一页。
        - ctrl+b 后一页。
        - ctrl+u 前半页。
        - ctrl+d 后半页。
        - ctrl+n 切换左侧类目树。
        - ctrl+] 函数跳转。
        - ctrl+o 函数跳回。
        - tab 选择 ycm 实时补全选项。
        - ^ 光标移至本行首字符。
        - $ 光标移至本行尾字符。
        - nw 光标移至后 n 个单词首字母。
        - nb 光标移至前 n 个单词首字母。
        - ne 光标移至后 n 个单词尾字母。
        - gg 跳转到首行。
        - G 跳到行位。
        - nG 跳转到第 n 行。
        - nx 删除包含光标处以后 n 个字符。
        - r 修改替换当前光标字符。
        - ndd 剪切 n 行。
        - ndw 剪切包含光标处 n 个单词（含空格）。
        - nde 剪切包含光标处 n 个单词（不含空格）。
        - d$ 剪切从光标字符到行尾。
        - d^ 剪切从光标字符到行首。
        - nJ 合并当前行以下 n 行内容。
        - nyy 复制 n 行。
        - nyw 复制包含光标处 n 个单词（含空格）。
        - nye 复制包含光标处 n 个单词（不含空格）。
        - y$ 复制光标到行尾。
        - y^ 复制光标到行首。
        - p 粘贴到当前文档。
        - u 撤销上次更改。
        - U 取消对当前行进行的所有操作。
        - ctrl+r 撤销上一个动作。
        - . 重复上次动作。
        - i 进入编辑模式，从当前光标处。
        - I 进入编辑模式，从当前行首。
        - a 进入编辑模式，从当前光标后。
        - A 进入编辑模式，从当前行末。
        - o 进入编辑模式，从当前行下插入新行。
        - O 进入编辑模式，从当前行上插入新行。
        - cw 进入编辑模式，删除当前光标到所在单词尾部字符。
        - c$ 进入编辑模式，删除当前光标到行尾的字符。
        - c^ 进入编辑模式，删除当前光标(不包括)之前到行首的字符。
    - 命令模式：
        - :行 跳转到指定行。
        - :set nu 显示行号。
        - :set nonu 取消显示行号。
        - :set nohlsearch 取消搜索高亮。
        - :m,ny m 行到 n 行之间的文本，:100,200y，100 行到 200 的内容。
        - :/word 自上而下查找指定的字符串 word，n 查找下一个（自上而下）,N 反向查找下一个（自下而上）。
        - :?word 自下而上查找指定字符串 word，n 查找下一个（自下而上），N 反向查找下一个（自上而下）。
        - :s/old/new old 被替换成 new。
        - :s/old/new/g 行内全部替换。
        - :#,#s/old/new/g 在行区域内进行替换，#,# 表示行号。
        - :%s/old/new/g 整个文件内的替换，加 % 表示整篇文档。
        - :s/old/new/c 替换确认。
        - esc 退出编辑。
        - :w 文件 保存。
        - :q 退出。
        - :! 命令 临时退出。
        - :q! 强制退出。
        - :qa! 退出，不保存。
        - v 进入字符可视模式。
        - shfit+v 进入行可视模式。
        - ctrl+v 进入块可视模式。

## sed

- sed：如果斜线匹配冲突可以换成别的符号作分割符。
    - -n 只输出匹配行。
    - 's/old/new;s/old/new/;s!old!new!' 文件名
    - -e 's/old/new/' -e 's/old/new/' 文件名
    - -i 's/old/new/' 文件名
    - -r 's/支持扩展正则表达式/new/' 文件名
    - -r 's//\1/' 文件名 其中 \1 表示回调一组字符串。
    - -r 's/old/new/2' 文件名 替换俩次。
    - '2s/old/new/' 文件名 只匹配第二行。
    - '正则 s/old/new/' 文件名 只匹配正则匹配的行。
    - '2,$s/old/new/' 文件名 只匹配第二行到最后一行。
    - '/regular/{s///;s///}' 文件名。
    - '/regular/d' 文件名 删除匹配的行。
    - '/regular/=' 文件名 打印行号。
    - '/regular/a xxx' 文件名 将匹配行向下插一行字符串。
    - '/regular/i xxx' 文件名 将匹配行向上插一行字符串。
    - '/regular/c xxx' 文件名 将匹配行改写成字符串。
    - '/regular/r xxx' 文件名 将匹配行向下插入指定文件的内容。
    - '/regular/w xxx' 文件名。
    - '/regular/n' 文件名 下一行。
    - '/regular/p' 文件名 将匹配行输出。
    - '/regular/10q' 文件名 读是行退出。
    - 'N;s//\n/;P;D' 文件名 N 将下一行加入到模式空间。D 删除模式空间第一个字符到第一个换行符。P 打印模式空间第一个字符到第一个换行符。
    - '1h;1!G;$!x;$p' 文件名 h H 模式空间存放到保存空间。g G 保存空间取出到模式空间。x 交换模式空间和保存空间内容。

## awk

- awk *参数* *文件*
    - -F '分割符或者正则'
    - -f 文件
    - 'print $1 $2' *文件* 打印每行的第一个字段和第二个字段。
    - '/^xxx/print $0' 文件 打印匹配的行内容。
    - FS 和 OFS 字段分割符， OFS 表示输出的字段分割符。
    - RS 记录分割符。
    - NR 和 FNR 行数。
    - NF 字段数量，最后一个可以用 $NF 取出。
    - ARGC ARGV
    - = 变量声明
    - 赋值操作符：++ -- += -= *= /= %= ^=
    - 关系操作符：< > <= >= == != ~ !~ || && !
    - BEGIN{} {} END{}
    - if() else if() else
    - while()
    - do{}while()
    - for(;;)
    - for(v in array)
    - delete array[index]
    - break continue
    - sin() cos() int() rand() srand()
    - gsub(r,s,t) sub(r,s,t) substr(s,p,n) index(s,t) length(s) match(s,r) split(s,a,sep)
    - function name(params){ return result}

## ulimit

- ulimit
    - -a 查看当前用户系统资源使用限制，例如打开文件数。

## tar

- tar
    - -zxvf xxx.tar.gzip 解压 gzip 文件
    - -Jxvf xxx.tar.xz 解压 xz 文件

## ln

- ln
    - ln -s lib64 /usr/local/lib：创建软连接

## find

- find：搜索文件
    - sudo find / -name xxx -type f：搜索文件。

## uname

- uname
    - uname -s：Linux
    - uname -r：6.1.0-28-amd64
