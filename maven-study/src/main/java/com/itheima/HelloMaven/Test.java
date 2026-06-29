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

     maven的依赖范围：scope
        compile：默认范围，参与编译、测试编译、测试运行、运行、打包全过程。
        provided：参与编译、测试编译、测试运行、运行，但不会被打包到最终的artifact中（如war/jar文件）。
                  适用场景：servlet-api、jsp-api等（运行环境如Tomcat已提供）
        runtime：不参与编译，但会参与测试编译、测试运行、运行和打包。
                 适用场景：JDBC驱动（编译时只需要接口，运行时需要具体实现）
        test：只参与测试编译和测试运行，不会被打包到最终的artifact中。
              适用场景：JUnit、Mockito等测试框架
        system：类似provided，参与编译、测试编译、测试运行、运行，但不会被打包。
                需要通过systemPath属性显式指定jar包的本地路径（不推荐使用）。

        依赖范围对比表：
        ┌──────────┬──────┬──────────┬──────────┬──────┬──────┐
        │  scope   │ 编译 │ 测试编译 │ 测试运行 │ 运行 │ 打包 │
        ├──────────┼──────┼──────────┼──────────┼──────┼──────┤
        │ compile  │  ✓   │    ✓     │    ✓     │  ✓   │  ✓   │
        │ provided │  ✓   │    ✓     │    ✓     │  ✓   │  ✗   │
        │ runtime  │  ✗   │    ✓     │    ✓     │  ✓   │  ✓   │
        │ test     │  ✗   │    ✓     │    ✓     │  ✗   │  ✗   │
        │ system   │  ✓   │    ✓     │    ✓     │  ✓   │  ✗   │
        └──────────┴──────┴──────────┴──────────┴──────┴──────┘

    maven常见问题解决方案：
        1.由于网络原因，依赖没有下载完全，在maven仓库中生成了xxx.lastUpdated文件，该文件不删除，不会再重新下载。
        解决方案：根据maven依赖的坐标，找到仓库中对应的xxx.lastUpdated文件，删除该文件，重新下载依赖。
                或者通过命令(del /s *.lastUpdated)批量递归删除指定目录下所有的 xxx.lastUpdated 文件,删除之后maven刷新项目即可下载。

     */
    public static void main() {
        System.out.println("Hello Maven!!!");
    }
}
