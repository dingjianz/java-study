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
        6、初始化数据库
            groupadd mysql                                  创建 mysql 用户组
            useradd -r -g mysql -s /bin/false mysql         创建不能登录的系统用户 mysql，归属 mysql 组
            mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
            注意：初始化完毕后，日志中会输出mysql的root用户的临时密码，记得复制记录下来。

            命令拆解：
                useradd -r          创建系统账号（system account），uid 较小，不创建家目录
                useradd -g mysql    指定主组为 mysql
                useradd -s /bin/false  指定登录 shell 为 /bin/false，即禁止该用户登录，只用于运行服务
                mysqld --initialize    初始化数据目录并生成 root 临时密码
                --basedir           MySQL 的安装目录
                --datadir           数据文件存放目录

        7、启动服务登录MySQL
            systemctl start mysql       启动 mysql 服务
            mysql -uroot -pxxxxx        用上一步记录的临时密码登录（-p 后紧跟密码，无空格）

        8、配置MySQL的root用户的密码，授权远程访问
            ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';
            CREATE USER 'root'@'%' IDENTIFIED BY '1234';
            GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
            FLUSH PRIVILEGES;

            语句说明：
                ALTER USER ... IDENTIFIED WITH mysql_native_password
                    把 root@localhost 的临时密码改成 1234，并切换为 mysql_native_password 加密方式
                    （MySQL 8 默认是 caching_sha2_password，部分老客户端不支持）
                CREATE USER 'root'@'%'      新建允许从任意主机（% 通配）连接的 root 用户
                GRANT ALL PRIVILEGES ON *.*  授予该用户对所有库、所有表的全部权限
                FLUSH PRIVILEGES            刷新权限表，使授权立即生效

            注意：'root'@'%' + 全部权限属于高风险配置，仅适合本地学习环境；
                 生产环境应限定来源 IP，并按需最小化授权。

    安装Nginx（源码编译安装方式）：
        1、安装nginx运行时需要的依赖
            yum install -y pcre pcre-devel zlib zlib-devel openssl openssl-devel
        2、上传nginx的源码包
            nginx-1.20.2.tar.gz
        3、解压源码包到当前目录
            tar -zxvf nginx-1.20.2.tar.gz
        4、进入到解压目录（cd nginx-1.20.2）后，执行指令
            ./configure --prefix=/usr/local/nginx
        5、执行编译nginx的指令
            make
        6、执行安装nginx的指令，安装到上述指定的 /usr/local/nginx 目录
            make install
        7、进入到nginx安装目录/usr/local/nginx，启动nginx服务
            sbin/nginx

        命令拆解：
            yum install -y      -y 表示安装过程中所有询问自动回答 yes，无需交互确认
            xxx-devel           开发包，包含头文件和静态库，源码编译时才需要
                pcre / pcre-devel       正则表达式库，nginx 的 rewrite、location 匹配依赖它
                zlib / zlib-devel       压缩库，用于 gzip 压缩响应
                openssl / openssl-devel 加密库，用于支持 HTTPS
            ./configure         检测系统环境并生成 Makefile，--prefix 指定最终的安装目录
            make                按 Makefile 把源码编译成可执行文件（此时还在源码目录里）
            make install        把编译产物拷贝到 --prefix 指定的目录，完成安装
            sbin/nginx          相对路径启动，等价于 /usr/local/nginx/sbin/nginx

        注意：nginx 默认监听 80 端口，若开启了防火墙，需要先放开对应端口才能从外部访问。

    防火墙操作：
        服务级操作（systemctl）：
            查看防火墙状态     systemctl status firewalld  、 firewall-cmd --state
            关闭防火墙        systemctl stop firewalld
            开启防火墙        systemctl start firewalld
            永久关闭防火墙     systemctl disable firewalld

        端口级操作（firewall-cmd）：
            开放指定端口      firewall-cmd --zone=public --add-port=8080/tcp --permanent
            关闭指定端口      firewall-cmd --zone=public --remove-port=8080/tcp --permanent
            立即生效         firewall-cmd --reload
            查看开放的端口     firewall-cmd --zone=public --list-ports

        注意：
            1、systemctl是管理Linux中服务的命令，可以对服务进行启动、停止、重启、查看状态等操作
            2、firewall-cmd是Linux中专门用于控制防火墙的命令
            3、为了保证系统安全，生产服务器的防火墙不建议关闭

    后端项目部署：
        1. 执行maven的父工程中的package生命周期，对项目进行打包【打包之前，先连接上服务器数据库，先测试通过】。

        2. 在linux服务器的/usr/local目录下，创建一个目录tlias-app，将jar包上传到服务器的 /usr/local/tlias-app 目录中。

        3. 然后在命令行执行命令，运行jar包：java -jar xxxxxx.jar

        注意：
            • 上述执行运行jar包之后，会占用前台窗口，窗口关闭服务也就停了。可以使用 nohup 指令，后台运行服务，执行指令：
              nohup java -jar xxxxxx.jar &> tlias.log &

              命令拆解：
                  nohup               no hang up，忽略挂断信号，保证进程在终端关闭后继续运行
                  &>                  将标准输出和标准错误都重定向到同一个文件（等价于 > tlias.log 2>&1）
                  tlias.log           日志输出文件
                  &                   放到后台执行，释放当前终端

            • 查看进程：ps -ef | grep xxxx
              ps                  Process Status，显示进程状态
              ps -e               -e 表示 every/all，显示所有进程（包括其他用户的）
              ps -ef              -f 表示 full format，显示完整的详细信息
              grep xxxx           过滤出包含 xxxx 的进程（通常用 jar 包名或 java 进程）

              输出示例：
                UID        PID  PPID  C STIME TTY          TIME CMD
                root         1     0  0 10:00 ?        00:00:02 /usr/lib/systemd/systemd
                root      1234     1  0 10:30 ?        00:00:15 java -jar tlias.jar
                admin     5678  5000  0 14:20 pts/0    00:00:00 ps -ef

              各列含义：
                UID     运行该进程的用户
                PID     进程ID（Process ID），唯一标识，用于 kill 命令
                PPID    父进程ID（Parent Process ID）
                C       CPU使用率
                STIME   进程启动时间
                TTY     终端类型（? 表示后台进程，pts/0 表示终端0）
                TIME    累计CPU时间
                CMD     完整的启动命令

            • 停止后台服务：
              步骤1：查找进程 ID
                ps -ef | grep java      查找所有 java 进程
                ps -ef | grep tlias     更精确地查找包含 tlias 的进程

              步骤2：终止进程
                kill -9 PID             强制终止进程（PID 是上一步查到的进程 ID，第二列数字）
                kill PID                温和终止（默认 -15，SIGTERM 信号，允许进程清理资源）

              或者一步到位：
                pkill -9 -f tlias       按完整命令行匹配并终止（-f 匹配完整命令，-9 强制终止）

              命令拆解：
                kill -9         发送 SIGKILL 信号，强制终止，进程无法捕获或忽略
                kill            默认发送 SIGTERM 信号（-15），进程可以捕获并优雅关闭
                pkill -f        按完整命令行字符串匹配进程（不只是进程名）
                PID             进程 ID，ps 命令输出的第二列

     */
}
