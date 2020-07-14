package com.ytq.design.pattern.chapter2.netty.servlet;


import com.ytq.design.pattern.chapter2.netty.http.GPRequest;
import com.ytq.design.pattern.chapter2.netty.http.GPResponse;
import com.ytq.design.pattern.chapter2.netty.http.GPServlet;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-02
 */
public class FirstServlet extends GPServlet {


    @Override
    protected void doGet(GPRequest request, GPResponse response) {
        doPost(request,response);
    }

    @Override
    protected void doPost(GPRequest request, GPResponse response) {
        response.write("this is first");
    }
}
