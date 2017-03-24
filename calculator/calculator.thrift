# 精减自官方示例tutorial.thrift（https://git-wip-us.apache.org/repos/asf?p=thrift.git;a=blob_plain;f=tutorial/tutorial.thrift）

# 用于生成java代码，其它语言暂不作考虑

# 编译之前需要的环境变量下有 thrift 编译器


/**
  输出时 java 类的包
 */

namespace java demo.thrift.hello


/**
 定义枚举类型
 */
enum Operation {
  ADD = 1,
  SUBTRACT = 2,
  MULTIPLY = 3,
  DIVIDE = 4
}

/**
 结构体，对Java类
 optional 还要研究有啥用
 */
struct Work {
  1: i32 num1 = 0,
  2: i32 num2,
  3: Operation op,
  4: optional string comment,
}

/**
 * Structs can also be exceptions, if they are nasty.
 */
exception InvalidOperation {
  1: i32 whatOp,
  2: string why
}

/**
 服务定义
 */
service Calculator {

  /**
  定义服务功能
   */

   void ping(),

   i32 calculate(1:i32 logid, 2:Work w) throws (1:InvalidOperation ouch),

}
