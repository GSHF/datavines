FROM openjdk:8

LABEL "author"="735140144"

# 设置工作目录
WORKDIR /datavines

# 安装必要的工具
RUN apt-get update && \
    apt-get install -y wget sudo && \
    rm -rf /var/lib/apt/lists/*

# 下载并安装Flink 1.13.6
RUN wget https://archive.apache.org/dist/flink/flink-1.13.6/flink-1.13.6-bin-scala_2.11.tgz && \
    tar -xzf flink-1.13.6-bin-scala_2.11.tgz && \
    rm flink-1.13.6-bin-scala_2.11.tgz && \
    mv flink-1.13.6 /opt/flink

# 设置FLINK_HOME环境变量
ENV FLINK_HOME=/opt/flink

# 复制并解压datavines
COPY ./datavines-1.0.0-SNAPSHOT-bin.tar.gz /datavines/
RUN tar -zxvf datavines-1.0.0-SNAPSHOT-bin.tar.gz && \
    rm datavines-1.0.0-SNAPSHOT-bin.tar.gz && \
    chmod +x datavines-1.0.0-SNAPSHOT-bin/bin/datavines-daemon.sh

# 复制datavines-flink-core.jar到Flink lib目录
RUN mkdir -p /opt/flink/lib && \
    find datavines-1.0.0-SNAPSHOT-bin -name "datavines-flink-core*.jar" -exec cp {} /opt/flink/lib/datavines-flink-core.jar \;

# 创建必要的目录并设置权限
RUN mkdir -p /tmp/datavines/exec/job/flink && \
    chmod -R 777 /tmp/datavines

# 设置时区和语言环境
ENV TZ=Asia/Shanghai
ENV LANG=zh_CN.UTF-8

# 暴露端口
EXPOSE 5600

# 启动命令
CMD ["datavines-1.0.0-SNAPSHOT-bin/bin/datavines-daemon.sh", "start_container", ""]
