package aop.aspect_config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Aspect05AfterThrowing {

    Logger log = LoggerFactory.getLogger(getClass());

    @AfterThrowing(value = "execution(* aop.services.EmployeeService.*(..))")
    public void afterThrowingAdvice(JoinPoint joinPoint) throws Throwable {
        log.info("");
        Object[] args = joinPoint.getArgs();
        log.info("After throwing method:" + joinPoint.getSignature());
        log.info(" -- [AfterThr] joinPoint.signature.name:" + joinPoint.getSignature().getName());
        log.info(" -- [AfterThr] joinPoint.kind: " + joinPoint.getKind());
        log.info(" -- [AfterThr] joinPoint.sourceLocation: " + joinPoint.getSourceLocation());
        log.info(" -- [AfterThr] joinPoint.staticPart: " + joinPoint.getStaticPart());
        log.info(" -- [AfterThr] joinPoint.args : " + Arrays.toString(args));
        if (args != null && args.length > 0) {
            log.info(" -- args.length : " + args.length);
            for (Object arg: args) {
                log.info(" ---- current arg: " + arg + ", type: " + arg.getClass().getName());
            }
        }
    }
}
