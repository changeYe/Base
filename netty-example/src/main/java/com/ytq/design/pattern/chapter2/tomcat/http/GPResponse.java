package com.ytq.design.pattern.chapter2.tomcat.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-02
 */
public class GPResponse {

    private final OutputStream os;

    public GPResponse(OutputStream os) {
        this.os = os;
    }

    public void write(String str){
        // 如果要http返回，我们需要定义http请求头规范
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(str);

        try {
            os.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
