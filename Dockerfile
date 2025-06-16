# 引用基础镜像
FROM anapsix/alpine-java:21_server-jre_unlimited

# 添加作者信息
LABEL maintainer="min.liao"

# 构建后的jar文件、及服务名称
ARG JAR_FILE=build/libs/*.jar
ARG PROFILE=dev

# 创建并切换到jar 部署目录下
RUN mkdir -p /app/logs
WORKDIR /app

# 设置环境变量
ENV SPRING_PROFILES_ACTIVE=${PROFILE}
# 默认中文zh_CN.UTF-8，如需改成英文打开下面注释即可
# ENV LANG en_US.UTF-8
# ENV LANGUAGE en_US:en
# ENV LC_ALL en_US.UTF-8
ENV TZ=Asia/Shanghai

# 将应用程序的jar添加到容器中
COPY ${JAR_FILE} /app/app.jar

# 应用服务端口
EXPOSE 8888

# 启动服务，如果打印日志中有中文，可以添加参数支持中文显示：-Dfile.encoding=UTF-8
# ENTRYPOINT ["/bin/sh", "-c","java -javaagent:/opt/agent/skywalking-agent.jar -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar"]
ENTRYPOINT ["/bin/sh", "-c","java -jar /app/app.jar"]
