SHELL=/bin/bash
VERSION=0.0.1
VERSION_TAG=$(VERSION)
REGISTRY=registry.cn-hangzhou.aliyuncs.com
IMAGE=$(REGISTRY)/lisong/thrift-server-impls
VERSIONED_IMAGE_SERVER=$(IMAGE)-server:$(VERSION_TAG)
VERSIONED_IMAGE_CLIENT=$(IMAGE):$(VERSION_TAG)

compile:
	mvn clean package

server-test:
	docker run -it --rm --entrypoint "/bin/bash" $(VERSIONED_IMAGE_SERVER) -s
client-test:
	docker run -it --rm --entrypoint "/bin/bash" $(VERSIONED_IMAGE_CLIENT) -s
	
server-build:
	docker build --no-cache -t $(VERSIONED_IMAGE_SERVER) \
	--build-arg VERSION=$(VERSION) \
	server-impls-server
	

client-build:
	docker build --no-cache -t $(VERSIONED_IMAGE_CLIENT) \
	--build-arg VERSION=$(VERSION) \
	server-impls-client
	
# 启动一个 TSimpleServer
server-simple:
	docker run --rm -it -p 9090:9090 $(VERSIONED_IMAGE_SERVER) \
	java -cp /app/server-impls-server-$(VERSION)-jar-with-dependencies.jar \
	demo.thrift.server.impls.server.simple.SimpleServer
	
# 基础测试 启动客户端  10 个请求线程，每个 100ms, 
client-simple-10100:
	docker run --rm -it $(VERSIONED_IMAGE_CLIENT) \
	java -cp /app/server-impls-client-$(VERSION)-jar-with-dependencies.jar \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer \
	192.168.1.139 9090 10 100 
	
# 较大连接数测试， 启动客户端 500 个线程， 每个 100ms
client-simple-500100:
	docker run --rm -it $(VERSIONED_IMAGE_CLIENT) \
	java -cp /app/server-impls-client-$(VERSION)-jar-with-dependencies.jar \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer \
	 192.168.1.139 9090 500 100
	
# 较长处理时间测试， 启动客户端 10 个线程，每个 3000ms
client-simple-103000:
	docker run --rm -it $(VERSIONED_IMAGE_CLIENT) \
	java -cp /app/server-impls-client-$(VERSION)-jar-with-dependencies.jar \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer \
	 192.168.1.139 9090 10 300