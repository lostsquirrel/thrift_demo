# thrift_demos
demos with apache thrift

## code gen with docker
```
docker run --rm -v `pwd`:/data -it registry.cn-hangzhou.aliyuncs.com/luyou/thrift:0.10.0  -o /data --gen java /data/hello.thrift
```

### gererated code

```
gen-java/
└── demo
    └── thrift
        └── hello
            ├── Calculator.java
            ├── InvalidOperation.java
            ├── Operation.java
            └── Work.java
```

 
## run
mvn clean package

`server.cmd`

`client.cmd`

to stop the server using `ctrl+c` 