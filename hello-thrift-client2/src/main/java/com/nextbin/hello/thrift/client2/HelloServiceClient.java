package com.nextbin.hello.thrift.client2;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;
import com.google.common.net.HostAndPort;
import com.nextbin.hello.thrift.inf.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;

import java.util.concurrent.ExecutionException;

/**
 * @author zebin
 * @since 2018-12-02.
 */
@Slf4j
public class HelloServiceClient {
    public static void main(String[] args) throws TException, ExecutionException, InterruptedException {
        HelloService helloService = getService();
        log.info(helloService.hello());
        log.info("users: {}", helloService.getUsers(1, 1));
    }

    public static HelloService getService() throws ExecutionException, InterruptedException {
        ThriftClientManager clientManager = new ThriftClientManager();
        return clientManager.createClient(
                new FramedClientConnector(HostAndPort.fromParts("localhost", 12345)),
                HelloService.class).get();
    }
}
