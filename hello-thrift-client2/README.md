#hello-thrift-client2

客户端样例2（不使用Maven依赖接口模块hello-thrift-inf），样例编程语言：

- Java
- Python

##Maven libthrift依赖

由于本机安装的thrift版本为0.11.0，命令行查看版本

```bash
thrift -version
#Thrift version 0.11.0
```

需要对drift项目与org.apache.thrift:libthrift有关的依赖排除掉，参见pom.xml

```xml
<dependency>
	<groupId>io.airlift.drift</groupId>
    <artifactId>drift-transport-apache</artifactId>
    <version>${drift.version}</version>
    <exclusions>
        <exclusion>
        	<groupId>org.apache.thrift</groupId>
            <artifactId>libthrift</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.apache.thrift</groupId>
    <artifactId>libthrift</artifactId>
    <version>0.11.0</version>
</dependency>
```

