package com.itheima.docker;

public class Test {
    /*
  Docker: 一款快速构建、运行、管理应用的工具。


  4. 附录: Docker安装

  4.1 卸载旧版

  首先如果系统中已经存在旧的Docker，则先卸载：

  Shell
      yum remove docker \
          docker-client \
          docker-client-latest \
          docker-common \
          docker-latest \
          docker-latest-logrotate \
          docker-logrotate \
          docker-engine \
          docker-selinux

  4.2 配置Docker的yum库

  首先要安装一个yum工具

      sudo yum install -y yum-utils device-mapper-persistent-data lvm2

  安装成功后，执行命令，配置Docker的yum源（已更新为阿里云源）：

  Bash
      sudo yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

      sudo sed -i 's+download.docker.com+mirrors.aliyun.com/docker-ce+' /etc/yum.repos.d/docker-ce.repo

  更新yum，建立缓存

      sudo yum makecache fast

  4.3 安装Docker

  最后，执行命令，安装Docker
      yum install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

  4.4 启动和校验

  Bash
      # 启动Docker
      systemctl start docker

      # 停止Docker
      systemctl stop docker

      # 重启
      systemctl restart docker

      # 设置开机自启
      systemctl enable docker

      # 执行docker ps命令，如果不报错，说明安装启动成功
      docker ps

  4.5 配置镜像加速

  镜像地址可能会变更，如果失效可以百度找最新的docker镜像。

  配置镜像步骤如下：

  Bash
      # 创建目录
      rm -f /etc/docker/daemon.json

      # 复制内容
      tee /etc/docker/daemon.json <<-'EOF'
      {
          "registry-mirrors": [
              "http://hub-mirror.c.163.com",
              "https://mirrors.tuna.tsinghua.edu.cn",
              "http://mirrors.sohu.com",
              "https://ustc-edu-cn.mirror.aliyuncs.com",
              "https://ccr.ccs.tencentyun.com",
              "https://docker.m.daocloud.io",
              "https://docker.awsl9527.cn"
          ]
      }
      EOF

      # 重新加载配置
      systemctl daemon-reload

      # 重启Docker
      systemctl restart docker


   ==========================================
  镜像和容器

  当我们利用Docker安装应用时，Docker会自动搜索并下载应用镜像（image）。镜像不仅包含应用本身，还包含应用运
  行所需要的环境、配置、系统函数库。Docker会在运行镜像时创建一个隔离环境，称为容器（container）。

  镜像：将应用所需的运行环境、配置文件、系统函数库等与应用一起打包得到的就是镜像。
  容器：为每个镜像的应用进程创建的隔离运行环境就是容器。
  镜像仓库：存储和管理镜像的平台就是镜像仓库。
            https://hub.docker.com/ DockerHub是目前最大的镜像仓库，其中包含各种常见的应用镜像。

  4.6 安装 MySQL 容器（实战案例）

  Docker 安装完成后，可以使用 docker run 命令运行容器。以下是安装 MySQL 8 的示例：

  Bash
      docker run -d \
        --name mysql \
        -p 3307:3306 \
        -e TZ=Asia/Shanghai \
        -e MYSQL_ROOT_PASSWORD=123 \
        mysql:8

  参数说明：
      -d                           # 后台运行（detached mode）
      --name mysql                 # 容器命名为 "mysql"
      -p 3307:3306                 # 端口映射：宿主机 3307 → 容器 3306
      -e TZ=Asia/Shanghai          # 设置时区为上海
      -e MYSQL_ROOT_PASSWORD=123   # 设置 root 密码为 123
      mysql:8                      # 使用 MySQL 8.x 镜像

  连接方式：
      外部通过 localhost:3307 访问该 MySQL 容器
      root 密码：123


  ==========================================
  数据卷介绍

  数据卷（volume）是一个虚拟目录，是 容器内目录 与 宿主机目录 之间映射的桥梁。
  方便我们操作容器内的文件，或者方便迁移容器内产生的数据。

  在执行docker run命令时，使用 -v 数据卷:容器 内目录 的方式可以完成数据卷挂载（数据卷不存在时，Docker会自动创建数据卷）
  docker run -d --name nginx-web -p 9001:80 -v html:/usr/share/nginx/html nginx:1.20.0
  ==========================================
  数据卷-操作命令

  命令                        说明                    文档地址
  ------------------------------------------------------------------------------------------
  docker volume create        创建数据卷              https://docs.docker.com/engine/reference/commandline/volume_create/
  docker volume ls            查看所有数据卷          https://docs.docker.com/engine/reference/commandline/volume_ls/
  docker volume rm            删除指定数据卷          https://docs.docker.com/engine/reference/commandline/volume_rm/
  docker volume inspect       查看某个数据卷的详情    https://docs.docker.com/engine/reference/commandline/volume_inspect/
  docker volume prune         清除所有未使用的数据卷  https://docs.docker.com/engine/reference/commandline/volume_prune/

  ==========================================
  本地目录挂载

  命令格式：
  docker run -d --name 容器名 -p 宿主机端口:容器端口 -v 宿主机目录或文件:容器内目录或文件 镜像名

  注意事项：

  1. 本地目录必须以 / 或 ./ 开头，如果直接以名称开头，会被识别为数据卷而非本地目录

  2. -v mysql:/var/lib/mysql
     → 会被识别为一个数据卷，数据卷叫 mysql

  3. -v ./mysql:/var/lib/mysql
     → 会被识别为当前目录下的 mysql 目录

  示例：
  docker run -d --name nginx01 -p 9001:80 \
    -v /root/nginx/html:/usr/share/nginx/html \
    -v /root/nginx/conf.d:/etc/nginx/conf.d \
    nginx

     */
}

/*
 * ==================== Docker 常用命令详解 ====================
 *
 * 1. docker ps
 *    作用：列出正在运行的容器
 *    - docker ps              - 只显示运行中的容器
 *    - docker ps -a           - 显示所有容器（包括已停止的）
 *    - docker ps -q           - 只显示容器 ID
 *    - docker ps --format "table {{.ID}}\t{{.Names}}\t{{.Status}}"  - 自定义输出格式
 *
 *    输出信息包括：
 *    - CONTAINER ID  - 容器唯一标识符（短 ID）
 *    - IMAGE         - 使用的镜像
 *    - COMMAND       - 容器启动时执行的命令
 *    - CREATED       - 创建时间
 *    - STATUS        - 运行状态（Up/Exited）
 *    - PORTS         - 端口映射
 *    - NAMES         - 容器名称
 *
 * 2. docker run
 *    作用：创建并启动一个新容器
 *    基本语法：docker run [选项] 镜像名 [命令]
 *
 *    常用选项：
 *    -d              - 后台运行（detached mode）
 *    -p 8080:80      - 端口映射（宿主机端口:容器端口）
 *    -v /host:/container  - 数据卷挂载
 *    --name myapp    - 指定容器名称 必须唯一
 *    -e KEY=VALUE    - 设置环境变量
 *    -it             - 交互式终端（-i 保持标准输入，-t 分配伪终端）
 *    --rm            - 容器退出后自动删除
 *    --network       - 指定网络模式
 *    --restart=always - 设置重启策略
 *
 *    示例：
 *    docker run -d -p 8080:80 --name nginx-web nginx
 *    → 后台运行 nginx，将容器 80 端口映射到宿主机 8080
 *
 * 3. docker pull
 *    作用：从 Docker Registry（默认 Docker Hub）下载镜像
 *    语法：docker pull [选项] 镜像名[:标签]
 *
 *    示例：
 *    docker pull nginx                - 下载最新版本（默认 :latest）
 *    docker pull nginx:1.21           - 下载指定版本
 *    docker pull mysql:8.0            - 下载 MySQL 8.0 镜像
 *    docker pull registry.cn-hangzhou.aliyuncs.com/namespace/image:tag
 *                                     - 从阿里云镜像仓库下载
 *
 *    下载过程：
 *    - 镜像由多个层（layer）组成
 *    - Docker 使用分层存储，相同层可复用
 *    - 显示每层的下载进度
 *
 * 4. docker save
 *    作用：将 Docker 镜像导出为 tar 归档文件
 *    语法：docker save [选项] 镜像名[:标签]
 *
 *    常用选项：
 *    -o, --output <文件名>  - 指定输出文件路径
 *
 *    示例：
 *    docker save -o nginx.tar nginx:latest
 *    → 将 nginx:latest 镜像保存为 nginx.tar 文件
 *
 *    docker save -o my-images.tar nginx:1.21 mysql:8.0
 *    → 同时导出多个镜像到一个 tar 文件
 *
 *    docker save nginx:latest > nginx.tar
 *    → 使用重定向方式保存（等效于 -o 参数）
 *
 *    使用场景：
 *    - 在无网络环境下迁移镜像
 *    - 备份镜像到本地存储
 *    - 镜像版本归档管理
 *    - 离线部署（配合 docker load 使用）
 *
 *    注意事项：
 *    - 导出的 tar 文件包含镜像的所有层和元数据
 *    - 文件大小可能较大，取决于镜像大小
 *    - 不会压缩，如需压缩可配合 gzip 使用：
 *      docker save nginx:latest | gzip > nginx.tar.gz
 *
 * 5. docker load
 *    作用：从 tar 归档文件加载镜像（与 docker save 配对使用）
 *    语法：docker load [选项]
 *
 *    常用选项：
 *    -i, --input <文件名>  - 指定输入的 tar 文件
 *
 *    示例：
 *    docker load -i nginx.tar
 *    → 从 nginx.tar 文件加载镜像
 *
 *    docker load < nginx.tar
 *    → 使用重定向方式加载（等效于 -i 参数）
 *
 *    gunzip -c nginx.tar.gz | docker load
 *    → 加载压缩的镜像文件
 *
 *    完整的镜像迁移流程：
 *    【源机器】docker save -o app.tar myapp:v1.0
 *    【传输】  scp app.tar user@target-host:/tmp/
 *    【目标机器】docker load -i /tmp/app.tar
 *
 * 6. docker inspect
 *    作用：查看 Docker 对象的详细信息（JSON 格式）
 *    支持对象：容器、镜像、网络、卷、节点等
 *
 *    语法：docker inspect [选项] 容器/镜像 ID
 *
 *    返回的关键信息：
 *    - State          - 容器状态（运行/停止/重启中）
 *    - NetworkSettings - IP 地址、端口映射、网络配置
 *    - Mounts         - 数据卷挂载信息
 *    - Config.Env     - 环境变量
 *    - HostConfig     - 资源限制、端口绑定、重启策略
 *    - Image          - 使用的镜像 SHA256
 *    - LogPath        - 日志文件路径
 *
 *    实用技巧 - 使用 --format 提取特定字段：
 *    docker inspect --format='{{.NetworkSettings.IPAddress}}' 容器名
 *    → 只显示容器的 IP 地址
 *
 *    docker inspect --format='{{.State.Status}}' 容器名
 *    → 只显示容器运行状态
 *
 *    docker inspect --format='{{.Config.Env}}' 容器名
 *    → 显示环境变量
 *
 *    docker inspect --format='{{json .NetworkSettings.Ports}}' 容器名
 *    → 以 JSON 格式显示端口映射
 *
 * 其他常用命令：
 *
 * 5. docker images
 *    列出本地所有镜像
 *
 * 6. docker stop <容器ID/名称>
 *    停止运行中的容器（发送 SIGTERM，10秒后 SIGKILL）
 *
 * 7. docker start <容器ID/名称>
 *    启动已停止的容器
 *
 * 8. docker restart <容器ID/名称>
 *    重启容器
 *
 * 9. docker rm <容器ID/名称>
 *    删除已停止的容器
 *    docker rm -f <容器ID>  - 强制删除运行中的容器
 *
 * 10. docker rmi <镜像ID/名称>
 *     删除镜像
 *
 * 11. docker logs <容器ID/名称>
 *     查看容器日志
 *     -f  - 实时跟踪日志输出
 *     --tail 100  - 只显示最后 100 行
 *
 * 12. docker exec -it <容器ID/名称> /bin/bash
 *     进入运行中的容器执行命令
 *     常用于调试和排查问题
 *
 * 13. docker build -t 镜像名:标签 .
 *     根据 Dockerfile 构建镜像
 *
 * 14. docker push <镜像名:标签>
 *     将本地镜像推送到远程仓库
 *
 * 15. docker network ls
 *     列出所有 Docker 网络
 *
 * 16. docker volume ls
 *     列出所有数据卷
 *
 * 17. docker stats
 *     实时显示容器资源使用情况（CPU、内存、网络 IO）
 *
 * ==================== docker inspect 输出信息详解 ====================
 *
 * 以容器 81c59528594c（3002-api-1，steel-browser-api 服务）为例：
 *
 * 【基本信息】
 * - Id: 容器的完整 SHA256 ID
 * - Name: /3002-api-1（由 Docker Compose 管理）
 * - Image: ghcr.io/steel-dev/steel-browser-api:latest（浏览器自动化 API 服务）
 * - State.Status: running（当前运行状态）
 * - State.Pid: 25460（宿主机上的进程 ID）
 *
 * 【端口映射】
 * - HostConfig.PortBindings: 定义端口映射规则
 *   "3000/tcp": [{"HostPort": "3002"}]
 *   → 容器内部 3000 端口映射到宿主机 3002 端口
 * - NetworkSettings.Ports: 实际的端口映射状态
 *   可以看到同时绑定了 IPv4(0.0.0.0) 和 IPv6(::)
 *
 * 【数据卷挂载】
 * - HostConfig.Binds: 绑定挂载配置
 *   ["/data/steel-compose/3002/.cache:/app/.cache:rw"]
 *   → 宿主机目录挂载到容器，rw 表示读写模式
 * - Mounts: 挂载点的详细信息
 *   Type: "bind"（绑定挂载）
 *   Source: 宿主机路径
 *   Destination: 容器内路径
 *   RW: true（可读写）
 *
 * 【网络配置】
 * - NetworkSettings.Networks.3002_default:
 *   - IPAddress: "172.26.0.2"（容器在该网络中的 IP）
 *   - Gateway: "172.26.0.1"（网关地址）
 *   - NetworkID: Docker 网络的唯一标识
 *   - Aliases: ["3002-api-1", "api", "81c59528594c"]（网络别名）
 *
 * 【环境变量】
 * - Config.Env: 容器启动时的环境变量数组
 *   - DOMAIN=172.31.101.75:3002（服务域名）
 *   - NODE_ENV=production（生产环境）
 *   - PUPPETEER_CACHE_DIR=/app/.cache（Puppeteer 缓存目录）
 *   - CHROME_BIN=/usr/bin/chromium（Chrome 可执行文件路径）
 *   - DISPLAY=:10（X11 显示服务器，用于无头浏览器）
 *
 * 【资源配置】
 * - HostConfig.DeviceRequests:
 *   [{"Driver": "nvidia", "Count": 1, "Capabilities": [["gpu"]]}]
 *   → 该容器请求了 1 个 NVIDIA GPU
 * - HostConfig.Memory: 0 表示无内存限制
 * - HostConfig.CpuShares: 0 表示无 CPU 限制
 *
 * 【启动配置】
 * - Config.Entrypoint: ["/app/api/entrypoint.sh"]（容器启动入口点）
 * - Config.Cmd: null（没有额外命令参数）
 * - Config.WorkingDir: "/app"（工作目录）
 *
 * 【Docker Compose 元数据】
 * - Labels 中包含 Compose 相关信息：
 *   - com.docker.compose.project: "3002"（项目名）
 *   - com.docker.compose.service: "api"（服务名）
 *   - com.docker.compose.config_files: docker-compose.yml 路径
 *   - com.docker.compose.project.working_dir: 项目工作目录
 *
 * 【日志和存储】
 * - LogPath: 容器日志文件的宿主机路径
 * - GraphDriver.Data: Overlay2 存储驱动的层信息
 *   - LowerDir: 只读层（镜像层）
 *   - UpperDir: 可写层（容器层）
 *   - MergedDir: 联合挂载后的目录
 *
 * ==================== Maven 命令详解 ====================
 *
 * 1. mvn clean
 *    作用：清理项目
 *    - 删除 target/ 目录及其所有编译生成的文件
 *    - 通常在重新构建前执行，确保没有旧的编译产物干扰
 *
 * 2. mvn deploy -am -pl digit-cloud-gc-component
 *    作用：部署指定模块到远程仓库
 *    - deploy           - 将构建产物部署到远程 Maven 仓库（如 Nexus、Artifactory）
 *    - -pl <模块名>     - 只构建指定的模块
 *    - -am (--also-make) - 同时构建该模块依赖的其他模块
 *
 *    使用场景：当模块依赖项目中的其他模块时，-am 确保依赖的模块也会被构建
 *
 * 3. mvn clean package -pl digit-cloud-gc-runner -Dmaven.test.skip=true
 *    作用：清理并打包指定模块，跳过测试
 *    - clean            - 先清理 target 目录
 *    - package          - 将项目打包成 jar/war 文件（生成在 target 目录）
 *    - -pl <模块名>     - 只对指定模块执行操作
 *    - -Dmaven.test.skip=true - 跳过测试的编译和执行（比 -DskipTests 更彻底）
 *
 *    使用场景：快速打包运行模块，常用于开发调试阶段
 *
 * 关键参数说明：
 *    -pl (--projects)           - 指定要构建的模块
 *    -am (--also-make)          - 同时构建依赖的模块
 *    -amd (--also-make-dependents) - 同时构建依赖当前模块的其他模块
 *    -DskipTests               - 跳过测试执行（但编译测试代码）
 *    -Dmaven.test.skip=true    - 跳过测试编译和执行
 *
 * ================================================================
 */
