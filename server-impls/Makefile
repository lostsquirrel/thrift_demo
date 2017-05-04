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
	server-impls-client

client-build:
	docker build --no-cache -t $(VERSIONED_IMAGE_CLIENT) \
	--build-arg VERSION=$(VERSION) \
	server-impls-server
	
# 启动一个 TSimpleServer
server-simple:
	docker run --rm -it $(VERSIONED_IMAGE_SERVER) \
	demo.thrift.server.impls.server.simple.SimpleServer
	
# 基础测试 启动客户端  10 个请求线程，每个 100ms, 
client-simple-10100:
	docker run --rm -it $(VERSIONED_IMAGE_CLIENT) \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer 10 100
	
# 较大连接数测试， 启动客户端 500 个线程， 每个 100ms
client-simple-500100:
	docker run --rm -it $(VERSIONED_IMAGE_CLIENT) \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer 500 100
	
# 较长处理时间测试， 启动客户端 10 个线程，每个 3000ms
client-simple-103000:
	docker run --rm -it $(VERSIONED_IMAGE_CLIENT) \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer 10 300