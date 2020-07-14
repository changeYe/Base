package com.ytq.design.pattern.chapter2.tomcat;

import com.ytq.design.pattern.chapter2.tomcat.http.GPRequest;
import com.ytq.design.pattern.chapter2.tomcat.http.GPResponse;
import com.ytq.design.pattern.chapter2.tomcat.http.GPServlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-02
 */
public class GPTomcat {


    /**
     * 纲目
     * 1. 配置端口号 默认8080 serverSocket IP:localhost
     * 2. 配置web.xml 自己写一个servlet继承 HttpServlet
     *    servlet-name
     *    servlet-class
     *    url-pattern
     * 3. 读取配置，url-pattern 与servlet建立一个映射关系
     * 4. Http请求，发送的数据时字符串，有规则的字符串（HTTP协议）
     * 5. 从协议内容中拿到url,把相应的Servlet通过反射进行实例化
     * 6. 调用实例servlet的方法，执行具体的doGet/doPost方法
     * 7. request(inputStream)/response(outputStream)
     *
     */


    private int port = 8080;

    private ServerSocket server;

    private Map<String, GPServlet> servletMapping = new HashMap<>();

    private Properties webxml = new Properties();

    private void init() throws IOException {

        //读取配置文件
//        String path = this.getClass().getResource("/").getPath();
//        FileInputStream file = new FileInputStream(path+"/web.properties");
//        webxml.load(file);

        InputStream inputStream = this.getClass().getResourceAsStream("/web.properties");
        webxml.load(inputStream);

        webxml.keySet().forEach(info->{
            String key;
            if(Objects.nonNull(info) && info.toString().endsWith("url")){
                key = info.toString();
                String url = webxml.getProperty(key);
                String urlPrefix = key.replaceAll("\\.url$", "");
                String className = webxml.getProperty(urlPrefix + ".class");
                try {
                    GPServlet gpServlet = (GPServlet) Class.forName(className).newInstance();
                    servletMapping.put(url,gpServlet);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });


    }


    /**
     * 服务启动
     */
    public void start(){
        try {
            init();

            server = new ServerSocket(this.port);

            System.out.println("启动成功端口："+this.port);
            while (true){
                Socket client = server.accept();
                process(client);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws IOException {
        // 用于创建请求与相应结果
        InputStream is = client.getInputStream();

        OutputStream os = client.getOutputStream();
        GPRequest request = new GPRequest(is);
        GPResponse response = new GPResponse(os);

        String url = request.getUrl();
        if (servletMapping.containsKey(url)) {
            servletMapping.get(url).service(request,response);
        }else{
            response.write("404");
        }

        os.flush();
        os.close();

        is.close();
        client.close();

    }


    public static void main(String[] args) {

        // 启动项目
        new GPTomcat().start();

    }


}
