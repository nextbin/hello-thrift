package com.nextbin.hello.facebook.swift.service.impl;

import com.nextbin.hello.facebook.swift.bean.ServiceResponse;
import com.nextbin.hello.facebook.swift.exception.ServiceException;
import com.nextbin.hello.facebook.swift.service.HelloService;

/**
 * @author zebin
 * @since 2018-10-01.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public ServiceResponse hello() {
        return ServiceResponse.ok();
    }

    @Override
    public ServiceResponse exception() throws ServiceException {
        throw new ServiceException();
    }

    @Override
    public ServiceResponse sum(Integer a, Integer b) {
        ServiceResponse response = new ServiceResponse();
        response.setData(String.valueOf(a+b));
        return response;
    }
}
