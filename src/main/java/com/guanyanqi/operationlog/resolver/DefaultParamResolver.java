package com.guanyanqi.operationlog.resolver;

/**
 * 默认请求参数信息处理类
 *
 * @Auther: guanyanqi
 * @Date: 2020-02-27 21:16
 */
public class DefaultParamResolver implements ParamResolver {

    @Override
    public Object resolver(Object param) {
        return param;
    }

}
