package com.guanyanqi.operationlog.resolver;

import org.aspectj.lang.JoinPoint;

/**
 * 处理切面中的joinPoint
 *
 * @Auther: guanyanqi
 * @Date: 2020-02-27 21:04
 */
public interface JoinPointResolver {

    void resolve(JoinPoint jp);
}
