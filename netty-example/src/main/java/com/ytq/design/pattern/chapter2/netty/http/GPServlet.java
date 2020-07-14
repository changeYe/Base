package com.ytq.design.pattern.chapter2.netty.http;

/**
 * @author yuantongqin
 * desc: extends HttpServlet
 * 2020-07-02
 */
public abstract class GPServlet {

    public void service(GPRequest request, GPResponse response){
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else{
            doPost(request,response);
        }
    }

    protected abstract void doGet(GPRequest request, GPResponse response);

    protected abstract void doPost(GPRequest request, GPResponse response);


}
