package com.ytq.design.pattern.spring.all.entity;

import java.lang.instrument.Instrumentation;

/**
 * @author yuantongqin
 * desc:
 * 2020-08-07
 */
public class Main {

    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("pre main 之前运行");


    }

    public static void main(String[] args) {
        Foo foo = new Foo();

        System.out.println(foo.toString());
    }
}
