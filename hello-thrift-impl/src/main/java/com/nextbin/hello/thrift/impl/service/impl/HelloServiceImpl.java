package com.nextbin.hello.thrift.impl.service.impl;

import com.nextbin.hello.thrift.inf.bean.Paging;
import com.nextbin.hello.thrift.inf.bean.User;
import com.nextbin.hello.thrift.inf.exception.ServiceException;
import com.nextbin.hello.thrift.inf.service.HelloService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zebin
 * @since 2018-10-01.
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public void exception() throws ServiceException {
        throw new ServiceException();
    }

    @Override
    public int sum(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public List<Paging<User>> getUsers(int pageNo, int pageSize) {
        return new LinkedList<>();
    }
}
