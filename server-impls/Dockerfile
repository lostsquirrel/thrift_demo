FROM  registry.cn-hangzhou.aliyuncs.com/luyou/java:8_server-jre
ADD server-impl-client/target/classes /app/client
ADD server-impls-server/target/classes /app/server

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -cp /app" ]
