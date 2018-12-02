package com.nextbin.hello.thrift.inf.service;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.service.ThriftException;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import com.nextbin.hello.thrift.inf.bean.Paging;
import com.nextbin.hello.thrift.inf.bean.User;
import com.nextbin.hello.thrift.inf.exception.ServiceException;

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
    void exp() throws ServiceException;

    @ThriftMethod
    int sum(@ThriftField(value = 1, name = "a") int a,
            @ThriftField(value = 2, name = "b") int b);

    @ThriftMethod
    List<User> getUsers(
            @ThriftField(value = 1, name = "pageNo", requiredness = ThriftField.Requiredness.OPTIONAL) int pageNo,
            @ThriftField(value = 2, name = "pageSize", requiredness = ThriftField.Requiredness.OPTIONAL) int pageSize);


    // ==================  不支持泛型，下列方法为错误示例  ==================

    @ThriftMethod
    List<Paging<User>> error1(
            @ThriftField(value = 1, name = "pageNo") int pageNo,
            @ThriftField(value = 2, name = "pageSize") int pageSize);

    @ThriftMethod
    List<Paging<Long>> error2(
            @ThriftField(value = 1, name = "pageNo") int pageNo,
            @ThriftField(value = 2, name = "pageSize") int pageSize);

}
