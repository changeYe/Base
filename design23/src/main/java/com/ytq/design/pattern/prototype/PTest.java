package com.ytq.design.pattern.prototype;

/**
 * @author yuantongqin
 * 2020-05-12
 */
public class PTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Student s = new Student();
        for (int i = 0; i < 10; i++) {
            Student a = s.clone();

        }
    }
}
