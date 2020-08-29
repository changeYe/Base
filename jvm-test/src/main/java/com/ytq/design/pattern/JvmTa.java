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

        String aa = "a" + "b" + "c";
        System.out.println("HelloGC!");
        List list = new LinkedList();

        String str = "aa";
        String dd = str + "bb" + "cc";
        for (int i = 0; i < 10; i++) {
            str += "" + i;
        }

    }
}
