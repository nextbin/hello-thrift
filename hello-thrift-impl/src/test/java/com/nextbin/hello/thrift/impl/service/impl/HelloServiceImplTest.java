package com.nextbin.hello.thrift.impl.service.impl;

import com.nextbin.hello.thrift.inf.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zebin
 * @since 2018-11-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/application.xml")
public class HelloServiceImplTest {

    @Autowired
    HelloService helloService;

    @Test
    public void hello() {
        assert "hello".equals(helloService.hello());
    }

    @Test
    public void sum() {
        assert helloService.sum(1, 2) == 3;
    }

    @Test
    public void getUsers() {
        System.out.println(helloService.getUsers(1, 1));
    }
}