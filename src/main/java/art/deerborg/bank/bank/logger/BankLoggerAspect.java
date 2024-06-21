package art.deerborg.bank.bank.logger;

import art.deerborg.bank.common.config.logger.LoggingAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BankLoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(BankLoggerAspect.class);

    @Around("execution(* art.deerborg.bank.bank.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        logger.debug("Method {}() - arguments: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());

        Object result = null;

        try {
            result = joinPoint.proceed();
        }catch (Throwable ex){
            logger.error("Exception in {}() with cause = '{}' and exception = '{}'", joinPoint.getSignature().getName(), ex.getCause(), ex.getMessage());
            throw ex;
        }
        long endTime = System.currentTimeMillis() - startTime;
        logger.debug("Method {}() - result: {} - execution time: {} ms", joinPoint.getSignature().getName(), result, endTime);
        return result;
    }
}
