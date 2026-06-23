package com.itheima.HelloMaven;

public class Test {
    /*
    Maven坐标：Maven中的坐标是资源(jar)的唯一标识，通过该坐标可以唯一定位资源位置。
    也可以使用坐标来定义项目或引入项目中需要的依赖。

    Maven坐标主要组成：
        groupId：定义当前Maven项目隶属组织名称（通常是域名反写，例如：com.itheima）
        artifactId：定义当前Maven项目名称（通常是模块名称，例如：order-service、goods-service）
        version：定义当前项目版本号
            SNAPSHOT:功能不稳定、尚处于开发中的版本，即快照版本
            RELEASE:功能稳定、已经测试完毕的版本，即发布版本

     导入Maven项目：
         1.建议将要导入的maven项目复制到你的项目目录下；
         2.建议选择maven项目的pom.xml文件进行导入
     */
    public static void main() {
        System.out.println("Hello Maven!!!");
    }
}
