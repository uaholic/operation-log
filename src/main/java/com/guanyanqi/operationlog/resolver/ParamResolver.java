package com.guanyanqi.operationlog.resolver;

/**
 * 处理接口参数，自定义参数处理实现类会通过其无参构造放法来实例化。
 *
 * @Auther: guanyanqi
 * @Date: 2020-02-27 21:15
 */
public interface ParamResolver {

    Object resolver(Object param);
}
