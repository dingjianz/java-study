package com.itheima.mavenDemo;

public class Test {
    /*
    Maven 是一个强大的 Java 项目管理和构建自动化工具，由 Apache 软件基金会开发。
       1.依赖管理：方便快捷的管理项目以来的资源（jar包）
       2.项目构建：标准化的跨平台(Linux、Windows、MacOS)的自动化项目构建方式
       3.统一项目结构：提供标准、统一的项目结构

     Maven 中的仓库用来存储什么？
         Maven的仓库是用来存储和管理jar包的

     Maven中有哪几类仓库？查找依赖(jar)的顺序是什么样的？
        本地仓库：Maven会优先从本地仓库中查找依赖

        远程仓库：然后配置了远程仓库，Maven会从远程仓库中查找依赖
            修改 conf/settings.xml 中的 <localRepository> 为一个指定目录。
            <localRepository>D:/maven/repository</localRepository>

        中央仓库：最后Maven会从中央仓库中查找依赖
            配置阿里云私服：修改conf/settings.xml 中的 <mirror>
            <mirror>
                <id>alimaven</id>
                <name>aliyun maven</name>
                <url>http://maven.aliyun.com/nexus/content/groups/public</url>
                <mirrorOf>central</mirrorOf>
             </mirror>

        配置环境变量：MAVEN_HOME 为 Maven的解压目录，并将其bin目录假如PATH环境变量

     */
}
