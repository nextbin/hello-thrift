package com.nextbin.hello.facebook.swift.service;

import com.facebook.swift.service.ThriftException;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import com.nextbin.hello.facebook.swift.bean.Paging;
import com.nextbin.hello.facebook.swift.bean.User;
import com.nextbin.hello.facebook.swift.exception.ServiceException;

import java.util.List;

/**
 * @author zebin
 * @since 2018-10-01.
 */
@ThriftService
public interface HelloService {

    @ThriftMethod
    String hello();

    @ThriftMethod(exception = {@ThriftException(id = 1, type = ServiceException.class)})
    void exception() throws ServiceException;

    @ThriftMethod
    int sum(Integer a, Integer b);

    @ThriftMethod
    List<Paging<User>> getUsers(int pageNo, int pageSize);
}
