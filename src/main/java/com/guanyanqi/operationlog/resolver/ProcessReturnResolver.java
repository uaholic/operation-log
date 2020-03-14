package com.guanyanqi.operationlog.resolver;

import java.util.Date;
import java.util.Objects;

import com.alibaba.fastjson.JSONObject;
import com.guanyanqi.operationlog.bean.LogInfo;
import com.guanyanqi.operationlog.bean.LogInfoHolder;

/**
 * 处理接口返回值
 *
 * @Auther: guanyanqi
 * @Date: 2020-03-03 16:52
 */
public class ProcessReturnResolver {

    public void resolve(Object result) {
        LogInfo logInfo = LogInfoHolder.get();
        logInfo.setResponseTime(new Date());
        JSONObject returnJson = new JSONObject();
        returnJson.put("return", Objects.nonNull(result) ? result : "none");
        logInfo.setResponseContent(returnJson);
    }
}
