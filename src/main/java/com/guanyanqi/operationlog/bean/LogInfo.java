package com.guanyanqi.operationlog.bean;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/**
 * 操作记录信息，用于记录请求接口相关信息。
 *
 * @Auther: guanyanqi
 * @Date: 2020-02-27 20:27
 */
public class LogInfo {

    /**
     * 请求url
     */
    private String requestUrl;

    /**
     * 请求方式 GET/POST/PUT ...
     */
    private String requestMethod;

    /**
     * 发起请求时间
     */
    private Date requestTime;

    /**
     * 返回结果时间
     */
    private Date responseTime;

    /**
     * 请求内容
     */
    private JSONObject requestContent;

    /**
     * 返回内容
     */
    private JSONObject responseContent;

    /**
     * 异常信息
     */
    private Throwable throwable;

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public JSONObject getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(JSONObject requestContent) {
        this.requestContent = requestContent;
    }

    public JSONObject getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(JSONObject responseContent) {
        this.responseContent = responseContent;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
