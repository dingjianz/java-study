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


 */
public class Test {
}
