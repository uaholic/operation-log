package com.guanyanqi.operationlog;

import java.util.List;

import com.guanyanqi.operationlog.bean.LogInfo;
import com.guanyanqi.operationlog.bean.LogInfoHolder;
import com.guanyanqi.operationlog.resolver.JoinPointResolver;
import com.guanyanqi.operationlog.resolver.ProcessJoinPointResolver;
import com.guanyanqi.operationlog.resolver.ProcessReturnResolver;
import com.guanyanqi.operationlog.resolver.ThrowableResolver;
import com.guanyanqi.operationlog.util.OperationLogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 操作记录功能入口抽象类
 * 继承该抽象类并通过定义spring AOP切点的方式来指定需要执行操作记录功能的接口
 *
 * @Auther: guanyanqi
 * @Date: 2020-02-27 20:15
 */
public abstract class AbstractOperationLogHandler {

    private static final Logger log= LoggerFactory.getLogger(AbstractOperationLogHandler.class);

    /**
     * 持久化操作接口集合
     */
    private final List<? extends ApiLogger> apiLoggers;

    /**
     * joinPoint处理
     */
    private final JoinPointResolver processJoinPointResolver;

    /**
     * 异常处理
     */
    private final ThrowableResolver throwableResolver;

    /**
     * 返回值处理
     */
    private final ProcessReturnResolver processReturnResolver;

    public AbstractOperationLogHandler(List<? extends ApiLogger> apiLoggers) {
        this(apiLoggers, new ProcessJoinPointResolver(), new ThrowableResolver(), new ProcessReturnResolver());
    }

    public AbstractOperationLogHandler(List<? extends ApiLogger> apiLoggers,
                                       JoinPointResolver processJoinPointResolver,
                                       ThrowableResolver throwableResolver,
                                       ProcessReturnResolver processReturnResolver) {
        this.processJoinPointResolver = processJoinPointResolver;
        this.throwableResolver = throwableResolver;
        this.processReturnResolver = processReturnResolver;
        this.apiLoggers = apiLoggers;
    }

    protected Object handle(ProceedingJoinPoint jp) throws Throwable {
        Object result;
        if (OperationLogUtil.ignoreLogJoinPoint(jp)) {
            return jp.proceed();
        }
        try {
            processJoinPointResolver.resolve(jp);
        } catch (Throwable e) {
            log.error("操作记录获取入参异常",e);
        }finally {
            result = jp.proceed();
        }
        try {
            processReturnResolver.resolve(result);
            LogInfo logInfo = LogInfoHolder.get();
            apiLoggers.forEach(logger -> logger.log(logInfo));
        } catch (Throwable e) {
            log.error("操作记录获取返回值异常",e);
        } finally {
            LogInfoHolder.clear();
        }
        return result;
    }

    protected void handleException(JoinPoint jp, Throwable e) {
        if (OperationLogUtil.ignoreLogJoinPoint(jp)) {
            return;
        }
        throwableResolver.resolve(e);
        LogInfo logInfo = LogInfoHolder.get();
        apiLoggers.forEach(logger -> logger.log(logInfo));
    }
}
