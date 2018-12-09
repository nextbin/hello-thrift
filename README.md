# hello-thrift

从零开始，学习Thrift，并做一些简单的实现

## 关于分支

由于facebook开发的swift项目（不是Apple开发的swift编程语言）已经[停止维护](https://github.com/facebookarchive/swift)，所以基于facebook swift项目做的Java Thrift开发可以参考分支 [dev/facebook-swift](https://github.com/nextbin/hello-thrift/tree/dev/facebook-swift) 。master分支将根据由airlift维护的drift项目进行更深入的Thrift RPC使用。

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

   3. Java Swift转Thrift，Thrift转Python

   4. ~~Java Swift转Thrift，Thrift转Go~~

4. TODO

## Java Swift To Thrift

参见：

1. com.nextbin.hello.thrift.inf.Drift2ThriftGenerator#main

注意事项：

1. 由于client、server依赖drift项目使用，不可以使用 com.facebook.swift:swift2thrift-generator-cli
2. 避免方法名、变量名使用thrift保留字，如：exception, list
3. 保证Thrift相关的服务、实体、异常、枚举等都有相关的注解@ThriftService、@ThriftStruct、@ThriftEnum
4. 保证Thrfit相关的成员变量、成员方法等等也是Thrift Enable（递归有效性）
5. 保证成员变量、传参变量、异常抛出都带有序号
6. 封装类型（如Integer、Long、User）传参变量可为null需要在@ThriftField注解中设置requiredness = ThriftField.Requiredness.OPTIONAL
7. 不可使用泛型

## Thrift To Java Swift

参见：

1. hello-thrift-client2/src/main/shell/thrift-gen-java.sh
2. https://thrift.apache.org/tutorial/java

注意事项：

1. 由于client、server依赖drift项目使用，不可以使用 com.facebook.mojo:swift-maven-plugin
2. 如发现使用了thrift保留字，需要反馈给服务端修改（list=>list0, exception=>exp）
3. 如发现需要传空的参数未设置OPTIONAL，需要与服务端协商（约定传0表示空，或者服务端修改requiredness）
4. 命令行终端执行 thrift 必须与客户端项目依赖的 libthrift 版本一致



## Thrift To Python3 Thrift

参见：

1. http://thrift.apache.org/tutorial/py
2. hello-thrift-client2/requirements.txt

注意事项：

1. 尽量使用与服务端版本一致的python thrift



## 参考

1. https://github.com/facebookarchive/swift
2. https://thrift.apache.org
3. https://thrift.apache.org/tutorial/py
4. https://github.com/airlift/drift
5. https://github.com/panchenko/drift-demo
