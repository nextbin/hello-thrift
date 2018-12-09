package com.nextbin.hello.thrift.client2;

import com.nextbin.hello.thrift.external.gen.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

/**
 * @author zebin
 * @since 2018-12-02.
 */
@Slf4j
public class HelloServiceClient {

    public static void main(String[] args) throws TException {
        final int port = 12345;
        TSocket clientTransport = new TSocket("localhost", port);
        clientTransport.open();
        final HelloService.Iface service1 = new HelloService.Client.Factory().getClient(new TBinaryProtocol(
                new TFramedTransport(clientTransport)));
        log.info("result from service1: {}", service1.hello());
        log.info("result from service1: {}", service1.getUsers(1, 10));
        clientTransport.close();
    }

}
