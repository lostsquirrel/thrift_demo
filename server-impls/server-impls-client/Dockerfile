FROM  registry.cn-hangzhou.aliyuncs.com/luyou/java:8_server-jre
ARG VERSION
RUN mkdir -p /app/logs
ADD target/server-impls-client-${VERSION}-jar-with-dependencies.jar /app/
