package com.guanyanqi.operationlog.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

import com.guanyanqi.operationlog.annotation.HandleToLog;
import com.guanyanqi.operationlog.annotation.IgnoreLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 操作记录功能相关工具类
 *
 * @Auther: guanyanqi
 * @Date: 2020-03-03 17:59
 */
public class OperationLogUtil {

    private static final Logger logger = LoggerFactory.getLogger(OperationLogUtil.class);

    public static boolean ignoreLogJoinPoint(JoinPoint jp) {
        boolean isIgnore = false;
        try {
            MethodSignature methodSignature = (MethodSignature) jp.getSignature();
            Method processMethod = methodSignature.getMethod();
            IgnoreLog classIgnoreLog = processMethod.getDeclaringClass().getAnnotation(IgnoreLog.class);
            IgnoreLog processMethodDeclaredAnnotations = processMethod.getAnnotation(IgnoreLog.class);
            isIgnore = Objects.nonNull(processMethodDeclaredAnnotations) || Objects.nonNull(classIgnoreLog);
        } catch (Exception e) {
            logger.error("校验IgnoreLog注解异常", e);
        }
        return isIgnore;
    }

    public static Annotation findIgnoreLogAnnotation(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof IgnoreLog) {
                return annotation;
            }
        }
        return null;
    }

    public static Annotation findHandleToLogAnnotation(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof HandleToLog) {
                return annotation;
            }
        }
        return null;
    }
}
