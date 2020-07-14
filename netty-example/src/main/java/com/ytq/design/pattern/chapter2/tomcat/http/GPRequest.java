package com.ytq.design.pattern.chapter2.tomcat.http;

import java.io.InputStream;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-02
 */
public class GPRequest {

    public String method;
    public String url;
    public GPRequest(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            byte[] bytes = new byte[1024];
            int len = 0;
            if ((len = is.read(bytes)) > 0) {
                sb.append(new String(bytes,0,len));
            }
            String s = sb.toString();
            if(s != null && !"".equals(s)){
                if(s.contains("GET")){
                    method = "GET";
                }
                int get = s.indexOf("GET");
                int http = s.indexOf("HTTP");
                String s1 = s.substring(get+3, http);
                url = s1.trim();
            }
            System.out.println("内容："+sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getUrl() {

        return url;
    }


    public String getMethod() {

        return method;
    }
}
