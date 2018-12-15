package com.nextbin.hello.thrift.client2;

import com.nextbin.hello.thrift.external.gen.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author zebin
 * @since 2018-12-02.
 * 由于现在 airlift/drift 项目目前没有提供根据 idl 生成 Java 源码的工具
 * ，导致无法通过 airlift/drift 的相关封装客户端调用 Thrift RPC 服务
 * ，故只能用原生thrift client进行远程调用
 * [link](https://github.com/airlift/drift/issues/76)
 */
@Slf4j
public class HelloServiceClient2 {

    public static void main(String[] args) throws TException {
        final int port = 12345;
        TSocket socket = new TSocket("localhost", port);
        TTransport transport = new TFramedTransport(socket);
        TProtocol protocol = new TBinaryProtocol(transport);
        socket.open();
        final HelloService.Iface service1 = new HelloService.Client(protocol);
        log.info("result from service1: {}", service1.hello());
        log.info("result from service1: {}", service1.getUsers(1, 10));
        socket.close();
    }

}
