package com.a.sncf.common.aspect;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Aspect
@Component
public class LogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);
    
    // 敏感字段常量
    private static final String[] EXCLUDE_PROPERTIES = {
        "password", "passwd", "pwd", "mobile", "phone", "tel", "telephone", "email", "mail", "mailbox"
    };
    
    // 静态过滤器，避免重复创建
    private static final PropertyPreFilters.MySimplePropertyPreFilter EXCLUDE_FILTER;
    
    static {
        PropertyPreFilters preFilters = new PropertyPreFilters();
        EXCLUDE_FILTER = preFilters.addFilter();
        EXCLUDE_FILTER.addExcludes(EXCLUDE_PROPERTIES);
    }

    @Pointcut("execution(* com.a..*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 设置日志ID
        MDC.put("LOG_ID", System.currentTimeMillis() + RandomUtil.randomString(3));
        
        try {
            // 记录请求信息
            logRequestInfo(joinPoint);
            
            // 执行目标方法
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            
            // 记录响应信息
            logResponseInfo(result, endTime - startTime);
            
            return result;
        } finally {
            // 清理MDC
            MDC.remove("LOG_ID");
        }
    }
    
    /**
     * 记录请求信息
     */
    private void logRequestInfo(ProceedingJoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                LOG.info("==> {} {} | {}.{}", 
                    request.getMethod(),
                    request.getRequestURI(),
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
                
                // 记录请求参数
                Object[] filteredArgs = filterSensitiveArgs(joinPoint.getArgs());
                if (filteredArgs.length > 0) {
                    LOG.info("Request params: {}", JSON.toJSONString(filteredArgs, EXCLUDE_FILTER));
                }
            }
        } catch (Exception e) {
            LOG.warn("Failed to log request info: {}", e.getMessage());
        }
    }
    
    /**
     * 记录响应信息
     */
    private void logResponseInfo(Object result, long executionTime) {
        try {
            LOG.info("<== Response: {} | {}ms", 
                JSON.toJSONString(result, EXCLUDE_FILTER), executionTime);
        } catch (Exception e) {
            LOG.warn("Failed to log response info: {}", e.getMessage());
        }
    }
    
    /**
     * 过滤敏感参数
     */
    private Object[] filterSensitiveArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return new Object[0];
        }
        
        Object[] filteredArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || 
                args[i] instanceof ServletResponse || 
                args[i] instanceof MultipartFile) {
                // 跳过不需要记录的参数
                continue;
            }
            filteredArgs[i] = args[i];
        }
        return filteredArgs;
    }
}
