SHELL=/bin/bash
VERSION_TAG=0.0.1
REGISTRY=registry.cn-hangzhou.aliyuncs.com
IMAGE=$(REGISTRY)/lisong/thrift-server-impls
VERSIONED_IMAGE=$(IMAGE):$(VERSION_TAG)

build:
	docker build --no-cache -t $(VERSIONED_IMAGE)
	
# 启动一个 TSimpleServer
server-simple:
	docker run --rm $(VERSIONED_IMAGE) \
	demo.thrift.server.impls.server.simple.SimpleServer
	
# 基础测试 启动客户端  10 个请求线程，每个 100ms, 
client-simple-10100:
	docker run --rm -it $(VERSIONED_IMAGE) \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer 10 100
	
# 较大连接数测试， 启动客户端 500 个线程， 每个 100ms
client-simple-500100:
	docker run --rm -it $(VERSIONED_IMAGE) \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer 500 100
	
# 较长处理时间测试， 启动客户端 10 个线程，每个 3000ms
client-simple-103000:
	docker run --rm -it $(VERSIONED_IMAGE) \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer 10 300