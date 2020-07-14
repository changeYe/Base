package com.ytq.design.pattern.chapter2.tomcat.servlet;

import com.ytq.design.pattern.chapter2.tomcat.http.GPRequest;
import com.ytq.design.pattern.chapter2.tomcat.http.GPResponse;
import com.ytq.design.pattern.chapter2.tomcat.http.GPServlet;

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
