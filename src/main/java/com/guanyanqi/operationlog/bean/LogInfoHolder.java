package com.guanyanqi.operationlog.bean;

/**
 * 封装操作记录 请求信息对象的 ThreadLocal
 *
 * @Auther: guanyanqi
 * @Date: 2020-02-27 21:39
 */
public class LogInfoHolder {

    private static final ThreadLocal<LogInfo> logInfoThreadLocal = new ThreadLocal<>();

    public static void set(LogInfo logInfo) {
        logInfoThreadLocal.set(logInfo);
    }

    public static void clear() {
        logInfoThreadLocal.set(null);
    }

    public static LogInfo get() {
        return logInfoThreadLocal.get();
    }

}
