package com.itheima.docker;

public class DockerNetwork {
    /*
    Docker 网络操作

    加入自定义网络的容器才可以通过容器名互相访问，Docker的网络操作命令如下：

    命令                              说明
    ----------------------------------------------------------------
    docker network create          创建一个网络
    docker network ls              查看所有网络
    docker network rm              删除指定网络
    docker network prune           清除未使用的网络
    docker network connect         使指定容器连接加入某网络
    docker network disconnect      使指定容器连接离开某网络
    docker network inspect         查看网络详细信息

    ================================================================
    具体使用案例
    ================================================================

    1. 创建自定义网络
    ----------------------------------------------------------------
    # 创建一个名为 mynet 的网络（默认使用 bridge 驱动）
    docker network create mynet

    # 创建网络时指定子网和网关
    docker network create --subnet=172.18.0.0/16 --gateway=172.18.0.1 mynet

    # 创建网络时指定驱动类型
    docker network create --driver bridge mynet


    2. 查看所有网络
    ----------------------------------------------------------------
    # 列出所有网络
    docker network ls

    # 示例输出：
    # NETWORK ID     NAME      DRIVER    SCOPE
    # 3f8b7c2d1e9a   bridge    bridge    local
    # 5c9d1f2e3a4b   host      host      local
    # 7a1b2c3d4e5f   mynet     bridge    local
    # 9e8d7c6b5a4f   none      null      local


    3. 删除指定网络
    ----------------------------------------------------------------
    # 删除名为 mynet 的网络
    docker network rm mynet

    # 删除多个网络
    docker network rm mynet1 mynet2 mynet3

    # 注意：有容器连接的网络无法删除，需先断开容器连接


    4. 清除未使用的网络
    ----------------------------------------------------------------
    # 清除所有未被任何容器使用的网络
    docker network prune

    # 跳过确认提示，直接清除
    docker network prune -f

    # 示例输出：
    # Deleted Networks:
    # mynet
    # test-network


    5. 将容器连接到网络
    ----------------------------------------------------------------
    # 将运行中的容器 mysql 连接到 mynet 网络
    docker network connect mynet mysql

    # 创建容器时直接指定网络
    docker run -d --name mysql --network mynet mysql:8.0

    # 将容器连接到网络并指定 IP 地址
    docker network connect --ip 172.18.0.10 mynet mysql

    # 实际场景：将 Web 应用和数据库连接到同一网络
    docker network create app-network
    docker run -d --name db --network app-network mysql:8.0
    docker run -d --name web --network app-network nginx
    # 此时 web 容器可以通过容器名 db 访问数据库


    6. 将容器从网络断开
    ----------------------------------------------------------------
    # 将容器 mysql 从 mynet 网络断开
    docker network disconnect mynet mysql

    # 强制断开（即使容器正在使用该网络）
    docker network disconnect -f mynet mysql


    7. 查看网络详细信息
    ----------------------------------------------------------------
    # 查看 mynet 网络的详细配置
    docker network inspect mynet

    # 查看多个网络的详细信息
    docker network inspect mynet bridge

    # 示例输出（JSON 格式）：
    # [
    #     {
    #         "Name": "mynet",
    #         "Id": "7a1b2c3d4e5f...",
    #         "Driver": "bridge",
    #         "Scope": "local",
    #         "IPAM": {
    #             "Config": [
    #                 {
    #                     "Subnet": "172.18.0.0/16",
    #                     "Gateway": "172.18.0.1"
    #                 }
    #             ]
    #         },
    #         "Containers": {
    #             "abc123...": {
    #                 "Name": "mysql",
    #                 "IPv4Address": "172.18.0.2/16"
    #             }
    #         }
    #     }
    # ]


    ================================================================
    完整实战案例：部署前后端分离应用
    ================================================================

    # 1. 创建应用专用网络
    docker network create web-app-network

    # 2. 启动 MySQL 数据库容器
    docker run -d \
      --name mysql \
      --network web-app-network \
      -e MYSQL_ROOT_PASSWORD=root123 \
      -e MYSQL_DATABASE=mydb \
      mysql:8.0

    # 3. 启动后端 Spring Boot 应用（连接数据库使用容器名 mysql）
    docker run -d \
      --name backend \
      --network web-app-network \
      -p 8080:8080 \
      -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/mydb \
      my-springboot-app:latest

    # 4. 启动前端 Nginx 容器
    docker run -d \
      --name frontend \
      --network web-app-network \
      -p 80:80 \
      my-nginx-frontend:latest

    # 5. 查看网络中的所有容器
    docker network inspect web-app-network

    # 6. 测试容器间网络连通性
    docker exec backend ping mysql
    docker exec frontend ping backend

    # 7. 清理：停止并删除所有容器和网络
    docker stop mysql backend frontend
    docker rm mysql backend frontend
    docker network rm web-app-network


    ================================================================
    Docker 网络驱动类型说明
    ================================================================

    bridge（默认）：
      - 容器连接到虚拟网桥
      - 同一网络中的容器可以互相通信
      - 通过端口映射可以从宿主机访问
      - 最常用的网络模式

    host：
      - 容器直接使用宿主机的网络
      - 性能最好，但失去网络隔离
      - 不需要端口映射

    none：
      - 容器没有网络连接
      - 适用于只需要本地计算的场景

    overlay：
      - 用于 Docker Swarm 集群
      - 跨主机的容器网络


    ================================================================
    重要提示
    ================================================================

    1. 容器名互访前提：容器必须在同一个自定义网络中（默认 bridge 网络不支持）
    2. 默认网络：Docker 安装后自带 bridge、host、none 三个网络
    3. 网络隔离：不同网络中的容器无法直接通信（除非容器连接到多个网络）
    4. DNS 解析：自定义网络内置 DNS 服务器，支持容器名解析为 IP
    5. 端口映射：-p 参数只影响宿主机访问，不影响容器间通信
     */
}
