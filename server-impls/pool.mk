# 启动一个 TSimpleServer
server-pool:
	docker run --rm  -p 9090:9090 -v `pwd`/logs:/app/logs $(VERSIONED_IMAGE_SERVER) \
	java -cp /app/server-impls-server-$(VERSION)-jar-with-dependencies.jar \
	demo.thrift.server.impls.server.Server pool
	
# 基础测试 启动客户端  10 个请求线程，每个 100ms, 
client-pool-10100:
	docker run --rm  -v `pwd`/logs:/app/logs $(VERSIONED_IMAGE_CLIENT) \
	java -cp /app/server-impls-client-$(VERSION)-jar-with-dependencies.jar \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer \
	192.168.1.139 9090 10 100 
	
# 较大连接数测试， 启动客户端 500 个线程， 每个 100ms
client-pool-500100:
	docker run --rm  -v `pwd`/logs:/app/logs $(VERSIONED_IMAGE_CLIENT) \
	java -cp /app/server-impls-client-$(VERSION)-jar-with-dependencies.jar \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer \
	 192.168.1.139 9090 500 100
	
# 较长处理时间测试， 启动客户端 10 个线程，每个 3000ms
client-pool-103000:
	docker run --rm  -v `pwd`/logs:/app/logs $(VERSIONED_IMAGE_CLIENT) \
	java -cp /app/server-impls-client-$(VERSION)-jar-with-dependencies.jar \
	demo.thrift.server.impls.client.test.simple.TestSimpleServer \
	 192.168.1.139 9090 10 3000l