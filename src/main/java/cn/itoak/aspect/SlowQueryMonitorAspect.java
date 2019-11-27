package cn.itoak.aspect;

import cn.itoak.properties.SlowQueryProperty;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author itoak
 */
//@Aspect
public class SlowQueryMonitorAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Resource
    private SqlSessionFactory sqlSessionFactory;
//    @Resource
    private SlowQueryProperty property;

//    @Pointcut("execution(* cn.itoak.config..*.*(..)) || execution(* cn.itoak.config..*.*(..))")
    public void monitorPointCut() {
    }

//    @Around("monitorPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String declaringType = joinPoint.getSignature().getDeclaringTypeName();
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis() - start;
        if (logger.isInfoEnabled() && end > 1) {
            String sql = sqlSessionFactory.getConfiguration().getMappedStatement(declaringType + "." + methodName).getBoundSql(null).getSql();
            logger.info("==> Preparing：{}", sql);
            logger.info("==> Parameters：{}", Arrays.toString(joinPoint.getArgs()));
            logger.info("<== Time consuming：{} ms", end);
        }

        return object;
    }
}
