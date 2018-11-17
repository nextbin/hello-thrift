package com.nextbin.hello.thrift.impl;

import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;
import com.nextbin.hello.thrift.impl.service.impl.HelloServiceImpl;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author zebin
 * @since 2018-10-01.
 */
public class Server {
    private ExecutorService taskWorkerExecutor;
    private ThriftServer server;
    private ExecutorService bossExecutor;
    private ExecutorService ioWorkerExecutor;

    public ThriftServer getServer() {
        return server;
    }

    public Server invoke() {
        ThriftServiceProcessor processor = new ThriftServiceProcessor(
                new ThriftCodecManager(),
                ImmutableList.<ThriftEventHandler>of(),
                new HelloServiceImpl()
        );

        taskWorkerExecutor = newFixedThreadPool(1);

        ThriftServerDef serverDef = ThriftServerDef.newBuilder()
                .listen(12345)
                .withProcessor(processor)
                .using(taskWorkerExecutor)
                .build();

        bossExecutor = newCachedThreadPool();
        ioWorkerExecutor = newCachedThreadPool();

        NettyServerConfig serverConfig = NettyServerConfig.newBuilder()
                .setBossThreadExecutor(bossExecutor)
                .setWorkerThreadExecutor(ioWorkerExecutor)
                .build();

        server = new ThriftServer(serverConfig, serverDef);
        return this;
    }

    public void checkExecutorsTerminated() {
//        Assert.assertTrue(bossExecutor.isTerminated());
//        Assert.assertTrue(ioWorkerExecutor.isTerminated());
//        Assert.assertTrue(taskWorkerExecutor.isTerminated());
    }

    public void stop() {
        server.close();
    }

    public static void main(String[] args) {
        Server serverCreator = new Server().invoke();
        ThriftServer server = serverCreator.getServer();

        server.start();
        System.out.println("服务已启动!");

        //serverCreator.stop();
        //serverCreator.checkExecutorsTerminated();
    }
}
