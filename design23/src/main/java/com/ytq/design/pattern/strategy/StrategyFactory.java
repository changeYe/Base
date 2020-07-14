package com.ytq.design.pattern.strategy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuantongqin
 * 2020-05-12
 */
public class StrategyFactory {

    static ConcurrentHashMap<String,OrderStrategy> chm = new ConcurrentHashMap<>();

    static {
        chm.put("kmh",new KmnOrder());
        chm.put("freeGood",new FreeGoodOrder());
        chm.put("helpBuy",new HelpBuyOrder());
    }

    public OrderStrategy getOrder(String key){
        if(key != null){
            throw new IllegalArgumentException("参数异常");
        }
        return chm.get(key);
    }
}
