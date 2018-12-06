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

   2. Java Swift转Thrift，Thrift转Java Swift

   3. Java Swift转Thrift，Thrift转Python（TODO）

   4. Java Swift转Thrift，Thrift转Go（TODO）

4. TODO

## Java Swift To Thrift

参见：

1. com.nextbin.hello.thrift.inf.Swift2ThriftGenerator#main
2. https://blog.csdn.net/qq_25788637/article/details/79503964

注意事项：

1. 避免方法名、变量名使用thrift保留字，如：exception, list
2. 保证Thrift相关的服务、实体、异常、枚举等都有相关的注解@ThriftService、@ThriftStruct、@ThriftEnum
3. 保证Thrfit相关的成员变量、成员方法等等也是Thrift Enable（递归有效性）
4. 保证成员变量、传参变量、异常抛出都带有序号
5. 封装类型（如Integer、Long、User）传参变量可为null需要在@ThriftField注解中设置requiredness = ThriftField.Requiredness.OPTIONAL
6. 不可使用泛型

## Thrift To Java Swift

参见：

1. https://www.cnblogs.com/yjmyzz/p/thrift-swift-sample.html
2. https://github.com/nextbin/hello-thrift/doc/images/image-20181202220344735.png

注意事项：

1. 如发现使用了thrift保留字，需要反馈给服务端修改（list=>list0, exception=>exp）
2. 如发现需要传空的参数未设置OPTIONAL，需要与服务端协商（约定传0表示空，或者服务端修改requiredness）
3. 尽量使用与服务端版本一致的swift-service、swift-maven-plugin



## 参考

1. https://github.com/facebookarchive/swift
2. https://www.cnblogs.com/yjmyzz/p/thrift-swift-sample.html
3. https://blog.csdn.net/qq_25788637/article/details/79503964

