package com.nextbin.hello.thrift.inf.bean;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;
import lombok.Setter;

/**
 * @author zebin
 * @since 2018-10-20.
 */
@Setter
@ThriftStruct
public class User {
    private int id;
    private String name;

    @ThriftField(1)
    public int getId() {
        return id;
    }

    @ThriftField(2)
    public String getName() {
        return name;
    }
}
