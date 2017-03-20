# thrift_demos
demos with apache thrift

## code gen with docker
docker run --rm -v `pwd`:/data -it registry.cn-hangzhou.aliyuncs.com/luyou/thrift:0.10.0  -o /data --gen java /data/hello.thrift


gen-java/
└── demo
    └── thrift
        └── hello
            ├── Calculator.java
            ├── InvalidOperation.java
            ├── Operation.java
            └── Work.java