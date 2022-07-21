package com.spring.cloud.api.gw.user.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * LoggingAspect
 * application.yml logging config
 */

@Aspect
@Component
public class Logging {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String BLANK = " ";

    /**
     * 이하 패키지의 모든 클래스 이하 모든 메서드에 적용
     */
    @Pointcut("execution(* com.spring.cloud.api.gw.user.controller..*Controller*.*(..)) || execution(* com.spring.cloud.api.gw.user.service..*Service*.*(..))")
    private void cut() {
    }

    /**
     * Pointcut에 의해 필터링된 경로로 들어오는 경우 메서드 호출 전에 적용
     */
    @Before("cut()")
    public void beforeLog(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        StringBuilder builder = new StringBuilder();
        builder.append("REQ=>").append(BLANK);
        builder.append(method.getDeclaringClass().getSimpleName()).append(BLANK);
//        builder.append(joinPoint.getSourceLocation().getLine()).append(BLANK);  // UnsupportedOperationException
        builder.append(method.getName()).append(BLANK);

        // 파라미터 받아오기
        Object[] args = joinPoint.getArgs();
        if (args.length <= 0) {
            builder.append("no parameter");
        }

        for (Object arg : args) {
            if (Objects.isNull(arg)) return;
            builder.append(arg.getClass().getSimpleName()).append("[").append(arg).append("]").append(BLANK);
        }
        log.info("{}", builder);
//        prettyPrinter(builder);
    }

    /**
     * Poincut에 의해 필터링된 경로로 들어오는 경우 메서드 리턴 후에 적용
     */
    @AfterReturning(value = "cut()", returning = "returnObject")
    public void afterLog(JoinPoint joinPoint, Object returnObject) throws JsonProcessingException {
        if (Objects.isNull(returnObject)) {
            return; // "Object.getClass()" because "returnObj" is null
        }

        Method method = getMethod(joinPoint);
        String builder = "RES<=" + BLANK +
                method.getDeclaringClass().getSimpleName() + BLANK +
                method.getName() + BLANK +
//        builder.append(joinPoint.getSourceLocation().getLine()).append(BLANK);    // UnsupportedOperationException
                "type=" + returnObject.getClass().getSimpleName() + BLANK +
                "value=" + new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(returnObject);
        log.info("{}", builder);
//        prettyPrinter(returnObj);
    }

    // JoinPoint로 메서드 정보 가져오기
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

//    private void prettyPrinter(Object obj) {
//        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//        try {
//            log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
}