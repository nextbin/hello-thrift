package com.nextbin.hello.thrift.inf.service;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("Paging")
public final class Paging
{
    public Paging() {
    }

    private int total;

    @ThriftField(value=1, name="total", requiredness=Requiredness.NONE)
    public int getTotal() { return total; }

    @ThriftField
    public void setTotal(final int total) { this.total = total; }

    private int pageNo;

    @ThriftField(value=2, name="pageNo", requiredness=Requiredness.NONE)
    public int getPageNo() { return pageNo; }

    @ThriftField
    public void setPageNo(final int pageNo) { this.pageNo = pageNo; }

    private int pageSize;

    @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE)
    public int getPageSize() { return pageSize; }

    @ThriftField
    public void setPageSize(final int pageSize) { this.pageSize = pageSize; }

    private User list0;

    @ThriftField(value=4, name="list0", requiredness=Requiredness.NONE)
    public User getList0() { return list0; }

    @ThriftField
    public void setList0(final User list0) { this.list0 = list0; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("total", total)
            .add("pageNo", pageNo)
            .add("pageSize", pageSize)
            .add("list0", list0)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Paging other = (Paging)o;

        return
            Objects.equals(total, other.total) &&
            Objects.equals(pageNo, other.pageNo) &&
            Objects.equals(pageSize, other.pageSize) &&
            Objects.equals(list0, other.list0);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            total,
            pageNo,
            pageSize,
            list0
        });
    }
}
