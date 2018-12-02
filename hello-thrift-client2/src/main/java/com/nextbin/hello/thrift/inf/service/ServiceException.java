package com.nextbin.hello.thrift.inf.service;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

@ThriftStruct("ServiceException")
public final class ServiceException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public ServiceException() {
    }


}
