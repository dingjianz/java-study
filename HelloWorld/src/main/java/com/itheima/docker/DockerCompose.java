package com.itheima.docker;

public class DockerCompose {
    /*
        Docker Compose 通过一个单独的 docker-compose.yml 模板文件（YAML 格式）来定义一组相关联的应用容器，
        帮助我们实现多个相互关联的 Docker 容器的快速部署。

        Docker Compose 的命令格式：docker compose [OPTIONS] [COMMAND]

        选项参数说明：
        ┌──────────┬─────────────┬──────────────────────────┐
        │ 类型     │ 参数或指令  │ 说明                     │
        ├──────────┼─────────────┼──────────────────────────┤
        │ Options  │ -f          │ 指定 compose 文件的路径和名称 │
        │          │ -p          │ 指定 project 名称        │
        ├──────────┼─────────────┼──────────────────────────┤
        │ Commands │ up          │ 创建并启动所有 service 容器 │
        │          │ down        │ 停止并移除所有容器、网络 │
        │          │ ps          │ 列出所有启动的容器       │
        │          │ logs        │ 查看指定容器的日志       │
        │          │ stop        │ 停止容器                 │
        │          │ start       │ 启动容器                 │
        │          │ restart     │ 重启容器                 │
        │          │ top         │ 查看运行的进程           │
        └──────────┴─────────────┴──────────────────────────┘
     */
}
