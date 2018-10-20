package com.nextbin.hello.facebook.swift.service;

import com.facebook.swift.service.ThriftException;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import com.nextbin.hello.facebook.swift.bean.ServiceResponse;
import com.nextbin.hello.facebook.swift.exception.ServiceException;

/**
 * @author zebin
 * @since 2018-10-01.
 */
@ThriftService
public interface HelloService {

    @ThriftMethod
    ServiceResponse hello();

    @ThriftMethod(exception = {@ThriftException(id = 1, type = ServiceException.class)})
    ServiceResponse exception() throws ServiceException;

    @ThriftMethod
    ServiceResponse sum(Integer a, Integer b);
}
