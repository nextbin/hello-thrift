package com.nextbin.hello.facebook.swift.bean;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;
import lombok.Setter;

/**
 * @author zebin
 * @since 2018-10-01.
 */
@Setter
@ThriftStruct
public class ServiceResponse {
    private int code = 0;
    private String msg = "success";
    private String data;

    public static ServiceResponse ok() {
        return new ServiceResponse();
    }

    @ThriftField(1)
    public int getCode() {
        return code;
    }

    @ThriftField(2)
    public String getMsg() {
        return msg;
    }

    @ThriftField(3)
    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
