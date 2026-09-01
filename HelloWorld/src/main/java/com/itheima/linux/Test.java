package com.itheima.linux;

/**
  Linux目录结构
    bin  存放二进制可执行文件
    boot 存放系统引导时使用的各种文件
    dev  存放设备文件
    etc  存放系统配置文件
    home 存放系统用户的文件
    lib  存放程序运行所需的共享库和内核模块
    opt  额外安装的可选应用程序包所放置的位置
    root 超级用户目录
    sbin 存放二进制可执行文件，只有root用户才能访问
    tmp  存放临时文件
    usr  存放系统应用程序
    var  存放运行时需要改变数据的文件，例如日志文件

    linux常用命令
      pwd: 显示当前目录


     ls: 显示指定目录下的内容
     语法： ls [-al] [dir]
     选项：
     -a 显示所有文件及目录（.开头的隐藏文件也会列出）
     -l 除文件名称外，同时将文件类型（d表示目录，-表示文件）、权限、拥有者、文件大小等信息详细列出
     注意：由于我们使用ls命令经常需要加入-l选项，所以linux为ls -l 命令提供了一种简写方式，即ll


     cd: 切换目录
            语法： cd [dir]
              . 表示当前目录
              .. 表示上一级目录
              ~ 表示用户主目录
              - 表示上次所在目录

     mkdir: 创建目录
            -p 表示如果目录不存在则创建

     rm: 删除文件或目录
        语法： rm [-rf] [file|dir]
        选项：
            -r 表示递归删除
            -f 表示强制删除
         eg: rm -r itcast/ 删除名为itcast的目录和目录中所有文件，删除前需要确认
         eg: rm -rf itcast/ 删除名为itcast的目录和目录中所有文件，删除前不需要确认
         eg: rm -f hello.txt 删除名为hello.txt的文件，删除前不需要确认

     cat: 一次性显示文件内容，适合查看小文件
        语法： cat [-n] fileName
        选项：
            -n 表示显示文件内容时，同时显示行号

     more: 分页显示文件内容，适合查看大文件
        语法： more [-num] [+num] fileName
        作用：当文件内容过多、一屏显示不完时，一屏一屏地查看（cat 会把内容全部刷出去，只能看到末尾）
             也常配合管道使用，用来分页查看命令的输出结果
        选项：
            -num 指定每屏显示的行数，num 为数字，如 -10 表示每屏显示 10 行
            +num 从文件的第 num 行开始显示，如 +100 表示从第 100 行开始看
        交互按键（进入分页后按键操作，无需回车）：
            空格   向下翻一屏
            回车   向下滚动一行
            b     向上翻一屏（管道输入时不支持）
            /字符串 向下搜索指定内容
            q     退出查看
            h     查看帮助
        eg: more hello.txt          分页查看 hello.txt 的内容
        eg: more -10 hello.txt      每屏只显示 10 行
        eg: more +100 hello.txt     从第 100 行开始分页查看
        eg: ls -l /etc | more       分页查看 /etc 目录的详细列表
        注意：more 只能向前翻页，功能较弱；less 是它的增强版，支持自由上下翻页和更强的搜索


 head: 显示文件开头内容
        语法： head [-nf] fileName
        作用：显示文件的开头内容，默认显示前 10 行
        选项：
            -n num 表示显示文件的前 num 行，num 为数字，如 -n 20 表示显示前 20 行
            -f 表示实时更新，通常用于日志文件的内容输出
        eg: head hello.txt          显示 hello.txt 的前 10 行
        eg: head -20 hello.txt      显示 hello.txt 的前 20 行

 tail: 显示文件结尾内容
        语法： tail [-n num] fileName
        作用：显示文件的结尾内容，默认显示最后 10 行
        选项：
            -n num 表示显示文件的后 num 行，num 为数字，如 -n 20 表示显示后 20 行
        eg: tail hello.txt          显示 hello.txt 的后 10 行
        eg: tail -20 hello.txt      显示 hello.txt 的后 20 行
        eg: tail -f 1.log           实时查看 1.log 文件的更新内容

 cp: 复制文件或目录
        语法： cp [-r] source destination
        选项：
            -r 表示递归复制，即复制目录时，目录中的文件也会被复制
        eg: cp hello.txt itcast/    复制 hello.txt 文件到 itcast 目录下
        eg: cp hello.1txt ./hi.txt   复制 hello.1txt 文件到当前目录下，并重命名为 hi.txt
        eg: cp -r itcast/ ./itheima/  将itcast目录和目录下的所有文件复制到itheima目录下，包括itcast目录本身
        eg: cp -r itcast/* ./itheima/  将itcast目录下的所有文件复制到itheima目录下，不包括 itcast 目录本身
        eg: cp -r itcast/. ./itheima/  将itcast目录下的所有文件复制到itheima目录下，包括隐藏文件，不包括itcast目录本身


 mv: 移动文件或目录，也可以重命名文件或目录(第二个参数是已存在的目录就会执行移动)
        语法： mv source destination
        eg: mv hello.txt hi.txt     将 hello.txt 文件重命名为 hi.txt
        eg: mv hello.txt itcast/    将 hello.txt 文件移动到 itcast 目录下

 tar: 对文件进行打包、解包、压缩、解压缩
        语法：tar [-zcxvf] fileName [files]
         说明：
            包文件后缀为.tar表示只是完成打包，没有压缩
            包文件后缀为.tar.gz表示使用 gzip 压缩
        选项：
            -z 表示使用 gzip 压缩或解压缩
            -c 表示创建新的归档文件，打包
            -x 表示从归档文件中提取文件，解包
            -v 表示显示归档文件中的文件列表
            -f 表示指定归档文件名

        常用组合：
            打包：
                tar -cvf hello.tar hello  将hello文件或目录打包成hello.tar
            压缩：
                tar -zcvf hello.tar.gz hello 将hello文件或目录打包压缩成hello.tar.gz

            解包：
                tar -xvf hello.tar         解包hello.tar文件到当前目录
            解压缩：
                tar -zxvf hello.tar.gz     解压缩hello.tar.gz文件到当前目录
            解压到指定目录：
                tar -zxvf hello.tar.gz -C /usr/local 解压hello.tar.gz文件到/usr/local目录下

 zip / unzip: 压缩、解压 zip 格式文件
        说明：zip 和 unzip 是两个独立的命令（不属于 tar 体系），部分系统需要先安装
             安装：yum install -y zip unzip  或  apt install zip unzip
             与 tar 的区别：zip 一步完成"打包 + 压缩"，不需要像 tar 那样用 -z 去调 gzip

        zip 压缩：
        语法：zip [-r] 压缩包名.zip 文件或目录
        选项：
            -r 表示递归处理，压缩目录时必须加，否则只会存入一个空目录
            -x 表示排除指定文件
        eg: zip hello.zip a.txt b.txt      将 a.txt、b.txt 压缩成 hello.zip
        eg: zip -r hello.zip hello/        将 hello 目录及其下所有文件压缩成 hello.zip
        eg: zip -r hello.zip hello/ -x "*.log"   压缩 hello 目录，但排除所有 .log 文件

        unzip 解压：
        语法：unzip [-lo] 压缩包名.zip [-d dir]
        选项：
            -d dir 表示指定解压到哪个目录（对应 tar 的 -C）
            -l 表示只查看压缩包内的文件列表，不解压
            -o 表示覆盖已存在的文件时不再询问
        eg: unzip hello.zip                解压 hello.zip 到当前目录
        eg: unzip hello.zip -d /tmp/target 解压 hello.zip 到 /tmp/target 目录
        eg: unzip -l hello.zip             只查看 hello.zip 里有哪些文件
        eg: unzip -o hello.zip             解压并直接覆盖同名文件

        tar 与 zip 对照：
            打包压缩：tar -zcvf x.tar.gz dir/   <=>  zip -r x.zip dir/
            解压：    tar -zxvf x.tar.gz        <=>  unzip x.zip
            指定目录：-C /path                  <=>  -d /path
            查看内容：tar -tzvf x.tar.gz        <=>  unzip -l x.zip

        实际怎么选：
            Linux 服务器之间传文件用 tar.gz，能保留权限、软链接、所有者，压缩率也更高
            要和 Windows 用户交换文件用 zip，Windows 原生支持双击解压

        注意：unzip 解压 Windows 压缩的中文文件名容易乱码（编码不是 UTF-8）
             可用 unzip -O GBK hello.zip 指定编码
             部分发行版的 unzip 不带 -O 选项，可安装 p7zip 后用 7z x hello.zip 代替

 vi/vim: Linux 常用的文本编辑器
        1. 启动：vi fileName
        2. 保存退出：Esc + :wq
        3. 不保存退出：Esc + :q!
        4. 保存不退出：Esc + :w
        5. 切换模式：Esc
        6. 删除内容：dd 删除一行，dw 删除一个单词，x 删除一个字符
        7. 插入内容：i 在光标前插入，a 在光标后插入，o 在新行插入
        8. 复制内容：yy 复制一行，yw 复制一个单词，p 粘贴

 find: 在指定目录下查找文件
    语法： find dirName -option fileName
    选项：
        -name 按文件名查找
        -size 按文件大小查找
        -mtime 按文件修改时间查找
        -perm 按文件权限查找
    eg: find /home -name "hello.txt"  在 /home 目录下按文件名查找 hello.txt
    eg: find / -name "*.txt"  在根目录下按文件名查找所有 .txt 文件
    eg: find /home -size +100M  在 /home 目录下按文件大小查找大于 100M 的文件
    eg: find /home -mtime -7  在 /home 目录下按文件修改时间查找最近 7 天内修改过的文件
    eg: find /home -perm 755  在 /home 目录下按文件权限查找权限为 755 的文件

 grep: 从指定文件中查找指定的文本内容
    语法： grep [-inAB] word fileName
    选项：
        -i 表示忽略大小写
        -n 表示显示行号
        -A 表示显示匹配行之后的 n 行
        -B 表示显示匹配行之前的 n 行

 eg: grep hello helloword.java  在 helloword.java 文件中查找包含 hello 的行
 eg: grep hello *.java 查找当前目录中所有 .java 文件中包含 hello 的行

 */
public class Test {
}
