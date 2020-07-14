package com.ytq.design.pattern.chapter3.netty.provider;

import com.ytq.design.pattern.chapter3.netty.api.RpcOperationService;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-05
 */
public class RpcOperationServiceImpl implements RpcOperationService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
