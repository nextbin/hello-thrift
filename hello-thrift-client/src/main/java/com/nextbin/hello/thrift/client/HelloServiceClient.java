package com.nextbin.hello.thrift.client;

import com.facebook.nifty.client.FramedClientChannel;
import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.RuntimeTTransportException;
import com.facebook.swift.service.ThriftClientManager;
import com.google.common.util.concurrent.ListenableFuture;
import com.nextbin.hello.thrift.inf.service.HelloService;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * @author zebin
 * @since 2018-10-02.
 */
@Slf4j
public class HelloServiceClient {
    private static final ThriftClientManager THRIFT_CLIENT_MANAGER = new ThriftClientManager();
    private static final ThreadLocal<Map<InetSocketAddress, FramedClientChannel>> SYNC_CHANNEL = new ThreadLocal<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SYNC_CHANNEL.set(new HashMap<>());
        HelloService helloService = getService();
        log.info(helloService.hello());
        log.info("users: {}", helloService.getUsers(1, 1));
        for (int i = 0; i < 100; ++i) {
            try {
                getService().hello();
            } catch (RuntimeTTransportException e) {
                log.error(e.getMessage(), e);
                InetSocketAddress address = getServerAddress();
                if (!SYNC_CHANNEL.get().get(address).getNettyChannel().isConnected()) {
                    SYNC_CHANNEL.get().get(address).getNettyChannel().disconnect();
                    SYNC_CHANNEL.get().remove(address);
                }
            }
            log.info(i + "");
            TimeUnit.SECONDS.sleep(5);
        }
        TimeUnit.MINUTES.sleep(60);
//        int total = 100000;
//        Long start = System.currentTimeMillis();
//        for (int i = 0; i < total; i++) {
//            getService().hello();
//        }
//        long end = System.currentTimeMillis();
//        long cost = end - start;
//        int perform = (int) (total / (cost / 1000D));
//        log.info("thrift " + total + " 次RPC调用，耗时：" + cost + "毫秒，平均" + perform + "次/秒");
    }

    private static InetSocketAddress getServerAddress() {
        return new InetSocketAddress("localhost", 12345);
    }

    private static HelloService getService() throws ExecutionException, InterruptedException {
        InetSocketAddress address = getServerAddress();
        if (SYNC_CHANNEL.get().get(address) == null) {
            ListenableFuture<FramedClientChannel> channel = THRIFT_CLIENT_MANAGER.createChannel(new FramedClientConnector(address));
            SYNC_CHANNEL.get().put(address, channel.get());
        }
        return THRIFT_CLIENT_MANAGER.createClient(SYNC_CHANNEL.get().get(address), HelloService.class);
    }
}
