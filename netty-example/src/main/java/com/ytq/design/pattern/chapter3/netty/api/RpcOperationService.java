package com.ytq.design.pattern.chapter3.netty.api;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-05
 */
public interface RpcOperationService {

    int add(int a,int b);

    int sub(int a,int b);

    int mul(int a,int b);

    int div(int a,int b);
}
