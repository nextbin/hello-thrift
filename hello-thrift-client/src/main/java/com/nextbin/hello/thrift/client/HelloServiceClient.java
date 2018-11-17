package com.nextbin.hello.thrift.client;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;
import com.google.common.net.HostAndPort;
import com.nextbin.hello.thrift.inf.service.HelloService;

import java.util.concurrent.ExecutionException;


/**
 * @author zebin
 * @since 2018-10-02.
 */
public class HelloServiceClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HelloService helloService = getService();
        System.out.println(helloService.hello());
        System.out.println(helloService.getUsers(1, 1));
//        int max = 100000;
//        Long start = System.currentTimeMillis();
//        for (int i = 0; i < max; i++) {
//            helloService.hello();
//        }
//        Long end = System.currentTimeMillis();
//        Long elapse = end - start;
//        int perform = Double.valueOf(max / (elapse / 1000d)).intValue();
//        System.out.print("thrift " + max + " 次RPC调用，耗时：" + elapse + "毫秒，平均" + perform + "次/秒");
    }

    public static HelloService getService() throws ExecutionException, InterruptedException {
        ThriftClientManager clientManager = new ThriftClientManager();
        return clientManager.createClient(
                new FramedClientConnector(HostAndPort.fromParts("localhost", 12345)),
                HelloService.class).get();
    }
}
