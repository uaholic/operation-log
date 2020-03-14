package com.guanyanqi.operationlog.resolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.guanyanqi.operationlog.annotation.HandleToLog;
import com.guanyanqi.operationlog.bean.LogInfo;
import com.guanyanqi.operationlog.bean.LogInfoHolder;
import com.guanyanqi.operationlog.util.OperationLogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: guanyanqi
 * @Date: 2020-02-27 22:31
 */
public class ProcessJoinPointResolver implements JoinPointResolver {

    private static final Logger logger = LoggerFactory.getLogger(ProcessJoinPointResolver.class);

    @Override
    public void resolve(JoinPoint jp) {
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Method processMethod = methodSignature.getMethod();
        // 获取方法参数中的注解 用于处理 IgnoreLog 和HandleToLog逻辑
        Annotation[][] parameterAnnotations = processMethod.getParameterAnnotations();
        // 获取方法参数值
        Object[] parameters = jp.getArgs();
        // 获取方法参数名
        String[] parameterNames = methodSignature.getParameterNames();
        // 将所有参数封装到json对账中
        JSONObject jsonParams = new JSONObject();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            if (Objects.nonNull(OperationLogUtil.findIgnoreLogAnnotation(parameterAnnotations[i]))) {
                // 忽略该参数
                continue;
            }
            ParamResolver paramResolver = new DefaultParamResolver();
            Annotation handleToLogAnnotation = OperationLogUtil.findHandleToLogAnnotation(parameterAnnotations[i]);
            if (Objects.nonNull(handleToLogAnnotation)) {
                HandleToLog handleToLog = (HandleToLog) handleToLogAnnotation;
                try {
                    // 取到HandleToLog中指指定的自定义参数处理类并获取该实例
                    paramResolver = handleToLog.value().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    logger.error("获取指定的参数处理实现类:{} 异常，请注意参数处理实现类中是否存在无参构造方法，及其权限访问修饰符是否合理。",
                            handleToLog.value().getSimpleName());
                }
            }
            // 所有参数组装成json
            jsonParams.put(parameterNames[i], paramResolver.resolver(parameters[i]));
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        // 获取请求url
        String url = request.getRequestURL().toString();
        // 获取请求方式
        String method = request.getMethod();

        // 封装请求信息放入ThreadLocal
        LogInfo logInfo = new LogInfo();
        logInfo.setRequestContent(jsonParams);
        logInfo.setRequestTime(new Date());
        logInfo.setRequestUrl(url);
        logInfo.setRequestMethod(method);
        LogInfoHolder.set(logInfo);
    }

}
