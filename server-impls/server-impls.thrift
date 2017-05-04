# 用于生成java代码，其它语言暂不作考虑

namespace java demo.thrift.server.impls.core

/**
Sleepy 服务接口，用于测试，Thrift 服务端实现方式的区别
*/
service ISleepyService {
    /**
                接收客户端阻塞时间
    */
    void sleep(
        /**
		客户端序号
        */
        1:i32 clientNo,
        /**
        * 阻塞时长，单位毫秒
        */
        2:i32 timeout
       )
}