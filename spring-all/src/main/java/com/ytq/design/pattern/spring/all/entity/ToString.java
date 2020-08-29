package com.ytq.design.pattern.spring.all.entity;

import java.lang.annotation.*;

/**
 * @author yuantongqin
 * desc:
 * 2020-08-07
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToString {
}
