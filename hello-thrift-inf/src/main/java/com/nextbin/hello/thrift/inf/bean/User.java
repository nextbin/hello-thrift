package com.nextbin.hello.thrift.inf.bean;

import io.airlift.drift.annotations.ThriftField;
import io.airlift.drift.annotations.ThriftStruct;
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
