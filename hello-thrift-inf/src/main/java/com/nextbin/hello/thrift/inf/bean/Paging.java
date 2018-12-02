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
public class Paging<T> {
    private int total;
    private int pageNo;
    private int pageSize;
    private T list;

    @ThriftField(1)
    public int getTotal() {
        return total;
    }

    @ThriftField(2)
    public int getPageNo() {
        return pageNo;
    }

    @ThriftField(3)
    public int getPageSize() {
        return pageSize;
    }

    @ThriftField(value = 4, name = "list0")
    public T getList() {
        return list;
    }
}
