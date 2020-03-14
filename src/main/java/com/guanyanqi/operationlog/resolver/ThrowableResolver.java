package com.guanyanqi.operationlog.resolver;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.guanyanqi.operationlog.bean.LogInfo;
import com.guanyanqi.operationlog.bean.LogInfoHolder;

/**
 * 异常信息处理类
 *
 * @Auther: guanyanqi
 * @Date: 2020-02-27 21:46
 */
public class ThrowableResolver {

    public void resolve(Throwable e) {
        LogInfo logInfo = LogInfoHolder.get();
        logInfo.setResponseTime(new Date());
        JSONObject exceptionJson = new JSONObject();
        exceptionJson.put("return", "Exception occurred");
        logInfo.setResponseContent(exceptionJson);
        logInfo.setThrowable(e);
    }
}
