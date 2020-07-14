package com.ytq.design.pattern.chapter3.netty.protocol;

import lombok.Data;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-05
 */
@Data
public class CustomProtocol {

    //类名
    private String className;
    // 方法名
    private String methodName;
    // 参数类型
    private Class<?>[] paramClass;
    //参数值
    private Object[] values;

}
