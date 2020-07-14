package com.ytq.design.pattern.strategy;

/**
 * @author yuantongqin
 * 2020-05-12
 */
public class FreeGoodOrder extends OrderStrategy {
    @Override
    public boolean strategyProcess(String orderType) {
        return false;
    }

    @Override
    public String execute() {
        return null;
    }
}
