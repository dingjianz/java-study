package com.itheima.DepencencyManage;

public class Test {
  /*
  依赖：指当前项目运行所需要的jar包，一个项目中可以引入多个依赖。
  配置：
     1.在pom.xml 中编写<dependencies>标签
     2.添加<dependency>标签,引入坐标
     3.定义坐标的groupId,artifactId,version
     4.点击pom.xml文件中，idea右上角刷新按钮，引入最新加入的依赖

    排除依赖：指主动断开依赖的资源，被排除的资源无需指定版本。
    <exclusions>
        <exclusion>
            <artifactId>junit</artifactId>
            <groupId>junit</groupId>
        </exclusion>
    </exclusions>


    Maven的生命周期：Maven中有3套相互独立的生命周期。
    每套生命周期包含一些阶段(phase)，阶段是有顺序的，后面的阶段依赖于前面的阶段。
    主要关心以下5个phase：clean、compile、test、package、install。
        clean：移除上一次构建生成的文件
        compile：编译项目源代码
        test：使用合适的单元测试框架运行测试(junit)
        package:将编译后的文件打包，如：jar、war等
        install：安装项目到本地仓库

    1.clean：清理
        pre-clean
        clean
        post-clean
    2.default：核心工作，如：编译、测试、安装、打包、部署等
        validate
        initialize
        generate-sources
        process-sources
        generate-resources
        process-resources
        compile
        process-classes
        generate-test-sources
        process-test-sources
        generate-test-resources
        process-test-resources
        test-compile
        process-test-classes
        test
        prepare-package
        package
        verify
        install
        deploy
    3.site：生成报告、发布站点等
        prepare-site
        site
        post-site
        site-deploy

     上面的阶段
   */

}
