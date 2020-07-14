package com.ytq.design.pattern;

import java.util.Scanner;

/**
 * @author yuantongqin
 * desc:
 * 2020-06-30
 */
public class Ta {

    public static void main(String[] args) {



        int objectLength = 8;
        int a = 1<< objectLength; // 乘以2^objectLength
        Integer flag=(1<<objectLength)-1;
        System.out.println(a+"=="+Integer.toBinaryString(flag));

        int errorIndex = 2;
        int mask=~(1<<errorIndex);
        String s = Integer.toBinaryString(mask);
        System.out.println(mask+"==mask=="+s);

        flag=flag&mask;

        System.out.println("=="+flag);

        int aa =  4;
        int bb = ~aa;


        System.out.println(bb+"==="+Integer.toBinaryString(bb));

        int a1= 5;
        int b1 = 7;
        int c1 = a1 & b1;
        System.out.println(c1+"=&="+Integer.toBinaryString(c1));


        int d1 = a1|b1;
        System.out.println(d1+"==|=="+Integer.toBinaryString(d1));



        int e1 = 5|-8;
        System.out.println(e1+"=="+Integer.toBinaryString(e1));
        System.out.println("-8="+Integer.toBinaryString(-8));
        System.out.println("8="+Integer.toBinaryString(8));

        int f1= 5^7;
        System.out.println(f1+"=="+Integer.toBinaryString(f1));

        int j1= 3>>5;
        System.out.println(j1+"===j1");

        int g1= 3<<5;
        System.out.println(g1+"===g1");

        String binaryString= "11111010";
        System.out.println("Output: "+Integer.parseInt(binaryString,2));




    }


}
