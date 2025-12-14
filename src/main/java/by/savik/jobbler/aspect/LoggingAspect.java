package by.savik.jobbler.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(public * by.savik.jobbler.controller..*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("execution(public * by.savik.jobbler.service..*.*(..))")
    public void serviceLog() {
    }

    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        if (request != null) {
            log.info("NEW REQUEST: IP: {}, URL: {}, HTTP_METHOD: {}, CONTROLLER_METHOD: {}.{}",
                    request.getRemoteAddr(),
                    request.getRequestURI(),
                    request.getMethod(),
                    jp.getSignature().getDeclaringTypeName(),
                    jp.getSignature().getName());
        }
    }

    @Before("serviceLog()")
    public void doBeforeService(JoinPoint jp) {
        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        String argsString = args.length > 0 ? Arrays.toString(args) : "METHOD HAS NO ARGUMENTS";
        log.info("RUN SERVICE: SERVICE_METHOD: {}.{}. METHOD ARGUMENTS: [{}]",
                className, methodName, argsString);
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("Return value: {}", returnObject);
    }

    @After("controllerLog()")
    public void doAfter(JoinPoint jp) {
        log.info("Controller Method executed successfully: {}.{}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    @Around("controllerLog()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("Execution method: {}.{}. Execution time: {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                executionTime);
        return proceed;
    }
}
