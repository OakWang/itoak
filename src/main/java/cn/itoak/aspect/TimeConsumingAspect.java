package cn.itoak.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author itoak
 */
@Aspect
public class TimeConsumingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(cn.itoak.annotation.TimeConsuming)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        if (logger.isInfoEnabled()) {
            String fullName = joinPoint.getTarget().getClass().getName();
            String className = fullName.substring(fullName.lastIndexOf(".") + 1);
            logger.info("Method [{}.{}] completed in {} ms", className, method.getName(), (System.currentTimeMillis() - start));
        }

        return object;
    }
}
