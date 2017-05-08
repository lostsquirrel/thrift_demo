SHELL=/bin/bash
VERSION=$(SHELL echo git describe --abbrev=0 --tags)
VERSION_TAG=$(VERSION)
REGISTRY=registry.cn-hangzhou.aliyuncs.com
IMAGE=$(REGISTRY)/lisong/thrift-server-impls
VERSIONED_IMAGE_SERVER=$(IMAGE)-server:$(VERSION_TAG)
VERSIONED_IMAGE_CLIENT=$(IMAGE):$(VERSION_TAG)

code:
	git reset --hard && git pull
	
compile: code
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
	
build: compile server-build client-build
	# nothing to do
	
include simple.mk pool.mk