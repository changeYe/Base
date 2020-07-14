package com.ytq.design.pattern.strategy;

/**
 * @author yuantongqin
 * 2020-05-12
 */
public abstract class OrderStrategy {

    public abstract boolean strategyProcess(String orderType);


    public abstract String execute();
}
