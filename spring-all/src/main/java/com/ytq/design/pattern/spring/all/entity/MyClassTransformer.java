package com.ytq.design.pattern.spring.all.entity;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author yuantongqin
 * desc:
 * 2020-08-07
 */
public class MyClassTransformer implements ClassFileTransformer {


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // 在这里读取、转换类文件

        return classfileBuffer;
    }
}
