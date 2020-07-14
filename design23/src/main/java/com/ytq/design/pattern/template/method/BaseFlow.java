package com.ytq.design.pattern.template.method;

/**
 * @author yuantongqin
 * 2020-05-12
 */
public abstract class BaseFlow {


    public abstract void execute(ShopInfo shopInfo) throws Exception;

    public void invoke() {

        ShopInfo info = init();

        // 抓取店铺订单信息
        try {
            execute(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        destroy();
    }

    final public ShopInfo init(){
        // 获取店铺信息。。。
        return new ShopInfo();
    }



    public void destroy() {
    }

}
