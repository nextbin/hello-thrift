package com.nextbin.hello.thrift.impl;

import com.google.common.collect.ImmutableSet;
import com.nextbin.hello.thrift.inf.Drift2ThriftGenerator;
import io.airlift.drift.codec.ThriftCodecManager;
import io.airlift.drift.server.DriftServer;
import io.airlift.drift.server.DriftService;
import io.airlift.drift.server.stats.NullMethodInvocationStatsFactory;
import io.airlift.drift.transport.netty.server.DriftNettyServerConfig;
import io.airlift.drift.transport.netty.server.DriftNettyServerTransportFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zebin
 * @since 2018-10-01.
 */
@Slf4j
public class Server {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DriftNettyServerConfig config = new DriftNettyServerConfig();
        config.setPort(12345);
        Set<DriftService> services = new HashSet<>();
        for (Class clazz : Drift2ThriftGenerator.getThriftServiceClass()) {
            services.add(new DriftService(clazz.newInstance()));
        }
        DriftServer server = new DriftServer(
                new DriftNettyServerTransportFactory(config),
                new ThriftCodecManager(),
                new NullMethodInvocationStatsFactory(),
                services,
                ImmutableSet.of());
        server.start();
        System.out.println("服务已启动!");
    }
}
