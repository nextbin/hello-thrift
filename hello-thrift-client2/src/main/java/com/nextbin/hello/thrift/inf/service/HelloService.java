package com.nextbin.hello.thrift.inf.service;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("HelloService")
public interface HelloService
{
    @ThriftService("HelloService")
    public interface Async
    {
        @ThriftMethod(value = "error1")
        ListenableFuture<List<Paging>> error1(
            @ThriftField(value=1, name="pageNo", requiredness=Requiredness.NONE) final int pageNo,
            @ThriftField(value=2, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "error2")
        ListenableFuture<List<Paging>> error2(
            @ThriftField(value=1, name="pageNo", requiredness=Requiredness.NONE) final int pageNo,
            @ThriftField(value=2, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "exp",
                      exception = {
                          @ThriftException(type=ServiceException.class, id=1)
                      })
        ListenableFuture<Void> exp();

        @ThriftMethod(value = "getUsers")
        ListenableFuture<List<User>> getUsers(
            @ThriftField(value=1, name="pageNo", requiredness=Requiredness.OPTIONAL) final Integer pageNo,
            @ThriftField(value=2, name="pageSize", requiredness=Requiredness.OPTIONAL) final Integer pageSize
        );

        @ThriftMethod(value = "hello")
        ListenableFuture<String> hello();

        @ThriftMethod(value = "sum")
        ListenableFuture<Integer> sum(
            @ThriftField(value=1, name="a", requiredness=Requiredness.NONE) final int a,
            @ThriftField(value=2, name="b", requiredness=Requiredness.NONE) final int b
        );
    }
    @ThriftMethod(value = "error1")
    List<Paging> error1(
        @ThriftField(value=1, name="pageNo", requiredness=Requiredness.NONE) final int pageNo,
        @ThriftField(value=2, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "error2")
    List<Paging> error2(
        @ThriftField(value=1, name="pageNo", requiredness=Requiredness.NONE) final int pageNo,
        @ThriftField(value=2, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "exp",
                  exception = {
                      @ThriftException(type=ServiceException.class, id=1)
                  })
    void exp() throws ServiceException, org.apache.thrift.TException;

    @ThriftMethod(value = "getUsers")
    List<User> getUsers(
        @ThriftField(value=1, name="pageNo", requiredness=Requiredness.OPTIONAL) final Integer pageNo,
        @ThriftField(value=2, name="pageSize", requiredness=Requiredness.OPTIONAL) final Integer pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "hello")
    String hello() throws org.apache.thrift.TException;

    @ThriftMethod(value = "sum")
    int sum(
        @ThriftField(value=1, name="a", requiredness=Requiredness.NONE) final int a,
        @ThriftField(value=2, name="b", requiredness=Requiredness.NONE) final int b
    ) throws org.apache.thrift.TException;
}