package com.itheima.linux;

public class LinuxSoftware {
    /*
 Linux 软件安装：
    安装方式：
        1.二进制发布包安装
            软件已经针对Linux进行编译，只要解压，修改配置即可
        2.rpm安装- redhat package manager
            软件已经按照 redhat 的包管理规范进行打包，使用 rpm 命令进行安装，不能自行解决库依赖问题
        3.yum安装
            一种在线软件安装方式，本质上还是rpm安装，自行下载安装包并安装，安装过程中自行解决库依赖问题
        4.源码编译安装
            软件以源码工程的形式安装，需要自己编译打包

    安装JDK（二进制发布包安装方式）：
        操作步骤：
        1、使用FinalShell自带的上传工具将jdk的二进制发布包上传到Linux
           （jdk-17.0.10_linux-x64_bin.tar.gz）
        2、解压安装包，命令为 tar -zxvf jdk-17.0.10_linux-x64_bin.tar.gz -C /usr/local
        3、配置环境变量，使用vim命令修改/etc/profile文件，在文件末尾加入如下配置
            export JAVA_HOME=/usr/local/jdk-17.0.10
            export PATH=$JAVA_HOME/bin:$PATH
        4、重新加载profile文件，使更改的配置立即生效，命令为 source /etc/profile
        5、检查安装是否成功，命令为 java -version

    安装MySQL（二进制发布包安装方式）：
        1、准备工作：卸载Linux系统中自带的mysql/mariadb安装包，否则MySQL将安装失败
            rpm -qa | grep mariadb                              查询系统中已安装的 mariadb 相关包
            rpm -e --nodeps mariadb-libs-5.5.60-1.el7_5.x86_64   卸载时忽略依赖关系，强制删除

            命令拆解：
                rpm -q      query，查询模式
                rpm -qa     -a 表示 all，列出系统中所有已安装的 rpm 包（可能上千行）
                grep xxx    逐行过滤，只保留包含 xxx 的行

            管道符 | 的作用：
                把左边命令的标准输出（stdout）直接接到右边命令的标准输入（stdin），
                数据在内存中流过去，不需要落地成中间文件。
                    rpm -qa  ──上千行输出──▶ | ──作为输入──▶ grep mariadb ──▶ 屏幕
                特点：
                1、数据单向流动，只能左 -> 右
                2、右边命令必须支持从 stdin 读数据（grep、wc、sort、awk 等）
                3、可以串联多级，如 rpm -qa | grep mysql | wc -l  统计 mysql 相关包的数量
                4、传递的是数据流而不是文件名，所以 ls | rm 是无效的（rm 不从 stdin 读文件名）

            对比重定向：
                |    输出 -> 另一个命令的输入      rpm -qa | grep mariadb
                >    输出 -> 覆盖写入文件          rpm -qa > pkgs.txt
                >>   输出 -> 追加到文件末尾        rpm -qa >> pkgs.txt

        2、下载并上传mysql安装包
            mysql-8.0.30-linux-glibc2.12-x86_64.tar.xz
        3、解压安装包到当前目录，并将解压后的文件夹移动到 /usr/local 目录下，改名为 mysql
            tar -xvf mysql-8.0.30-linux-glibc2.12-x86_64.tar.xz
            注意：.tar.xz 用 xz 压缩，tar 会自动识别，不需要加 -z（-z 是给 .tar.gz 用的）
        4、配置环境变量（编辑/etc/profile，追加内容）
            export MYSQL_HOME=/usr/local/mysql
            export PATH=$MYSQL_HOME/bin:$PATH
        5、注册MySQL为系统服务
            cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql
            chkconfig --add mysql

     */
}
