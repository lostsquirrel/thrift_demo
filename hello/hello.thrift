# 用于生成java代码，其它语言暂不作考虑

# 编译之前需要的环境变量下有 thrift 编译器
/**
  输出时 java 类的包
  注释也会输出到对应的位置
 */
namespace java demo.thrift.hello.core

/**
在生成的对应接口类上的注释
 Thrift 的核心， 定义一个服务
 这是一个Hello服务，对应一个接口类
*/
service IHelloService {
    /**
    在生成的对应的接口方法上也会有些注释
    接收客户端发送的消息，添加加 ‘echo:’ 返回
    */
    string echo(
        /**
        * 在生成对应的接口方法时会在其注释添加@param
        消息的序号，（只是为了表示多个参数）
        */
        1:i32 serial,
        /**
        * 在生成对应的接口方法时会在其注释添加@param
        接收客户端消息
        */
        2:string msg
        )
}
