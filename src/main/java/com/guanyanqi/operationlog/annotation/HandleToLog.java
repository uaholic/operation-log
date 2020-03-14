package com.guanyanqi.operationlog.annotation;

import com.guanyanqi.operationlog.resolver.DefaultParamResolver;
import com.guanyanqi.operationlog.resolver.ParamResolver;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 适用于操作记录功能，仅允许标记在需要记录日志的接口（方法）参数上面，来指定一个
 *
 * @Auther: guanyanqi
 * @Date: 2020-02-27 21:11
 * @see ParamResolver 实现类，执行对该参数的自定义处理逻辑。
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HandleToLog {

    Class<? extends ParamResolver> value() default DefaultParamResolver.class;

}
