FROM  registry.cn-hangzhou.aliyuncs.com/luyou/java:8_server-jre
ARG VERSION
ADD ../../server-impls-server/target/server-impls-server-${VERSION}-jar-with-dependencies.jar /app/

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -cp \"/app/*\"" ]
