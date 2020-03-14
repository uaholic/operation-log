package com.guanyanqi.operationlog;


import com.guanyanqi.operationlog.bean.LogInfo;

/**
 * 操作记录数据处理接口，通过实现该接口来执行对接口请求操作相关数据的持久化逻辑。
 *
 * @Auther: guanyanqi
 * @Date: 2020-02-27 20:40
 */
public interface ApiLogger {

    void log(LogInfo logInfo);

}
