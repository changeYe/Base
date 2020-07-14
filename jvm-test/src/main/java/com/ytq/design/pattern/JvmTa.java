package com.ytq.design.pattern;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yuantongqin
 * desc:
 * 2020-05-30
 */
public class JvmTa {

    public static void main(String[] args) {
        System.out.println("aa");

        System.out.println("HelloGC!");
        List list = new LinkedList();
        for(;;) {
            byte[] b = new byte[1024*1024];
            list.add(b);
        }
    }
}
