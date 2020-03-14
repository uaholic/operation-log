package com.guanyanqi.operationlog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 适用于操作记录功能，可以标记在类、方法以及参数上
 * 标记在类上：该类中所有方法均忽略操作记录
 * 标记在方法上：该方法忽略操作记录
 * 标记在参数上：该参数忽略操作记录
 *
 * @Auther: guanyanqi
 * @Date: 2020-02-27 21:08
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreLog {
}
