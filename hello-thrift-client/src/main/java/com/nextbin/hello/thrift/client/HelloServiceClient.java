package com.nextbin.hello.thrift.client;

import com.google.common.net.HostAndPort;
import com.nextbin.hello.thrift.inf.service.HelloService;
import io.airlift.drift.client.DriftClientFactory;
import io.airlift.drift.client.ExceptionClassifier;
import io.airlift.drift.client.address.SimpleAddressSelector;
import io.airlift.drift.client.address.SimpleAddressSelectorConfig;
import io.airlift.drift.codec.ThriftCodecManager;
import io.airlift.drift.transport.netty.client.DriftNettyClientConfig;
import io.airlift.drift.transport.netty.client.DriftNettyMethodInvokerFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

import static io.airlift.drift.transport.netty.client.DriftNettyMethodInvokerFactory.createStaticDriftNettyMethodInvokerFactory;


/**
 * @author zebin
 * @since 2018-10-02.
 */
@Slf4j
public class HelloServiceClient {

    public static void main(String[] args) {
        try (DriftNettyMethodInvokerFactory<?> invokerFactory = createStaticDriftNettyMethodInvokerFactory(new DriftNettyClientConfig())) {
            DriftClientFactory clientFactory = createClientFactory(12345, invokerFactory);
            HelloService service1 = clientFactory.createDriftClient(HelloService.class).get();
            log.info("hello: {}", service1.hello());
            log.info("getUsers: {}", service1.getUsers(1, 10));
        }
    }

    public static DriftClientFactory createClientFactory(int port, DriftNettyMethodInvokerFactory<?> invokerFactory) {
        SimpleAddressSelectorConfig config = new SimpleAddressSelectorConfig();
        config.setAddressesList(Collections.singletonList(HostAndPort.fromParts("localhost", port)));
        return new DriftClientFactory(
                new ThriftCodecManager(),
                invokerFactory,
                new SimpleAddressSelector(config),
                ExceptionClassifier.NORMAL_RESULT);
    }

}
