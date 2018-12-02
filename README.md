# hello-thrift

从零开始，学习Thrift，并做一些简单的实现

## 项目结构

1. hello-thrift-inf：接口定义和基本实体
2. hello-thrift-impl：接口实现和服务端
3. hello-thrift-client：客户端样例
3. hello-thrift-client2：客户端样例2（不使用Maven依赖接口模块hello-thrift-inf）

## 项目内容

1. 基于Thrift RPC服务端、客户端

2. 客户端只需依赖接口模块，不需要关心实现逻辑

3. 接口依赖：
   1. Java Maven依赖

      ```xml
      <dependency>
      	<groupId>com.nextbin.hello</groupId>
      	<artifactId>hello-thrift-inf</artifactId>
      	<version>0.0.1-SNAPSHOT</version>
      </dependency>
      ```

   2. Java Swift转thrift，thrift转Java Swift

   3. Java Swift转thrift，thrift转Python（TODO）

   4. Java Swift转thrift，thrift转Go（TODO）

4. TODO

# Swift To Thrift

参见：

1. com.nextbin.hello.thrift.inf.Swift2ThriftGenerator#main

注意事项：

1. 避免方法、变量名使用thrift保留字，如：exception, list
2. 保证服务类有@ThriftService，接口方法有@ThriftMethod，传参变量有@ThriftField
3. 异常需要在@ThriftService注解中设置变量exception = xxx
4. 传参变量可为null需要在@ThriftField注解中设置requiredness = ThriftField.Requiredness.OPTIONAL
5. 不可使用泛型

## 参考

1. https://github.com/facebookarchive/swift
2. https://www.cnblogs.com/yjmyzz/p/thrift-swift-sample.html
3. https://blog.csdn.net/qq_25788637/article/details/79503964

