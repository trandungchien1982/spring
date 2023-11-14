package aop.aspect_config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Aspect03AfterReturn {

    Logger log = LoggerFactory.getLogger(getClass());

    @AfterReturning(value = "execution(* aop.services.EmployeeService.*(..))")
    public void afterReturningAdvice(JoinPoint joinPoint) {
        log.info("");
        Object[] args = joinPoint.getArgs();
        log.info("After returning method:" + joinPoint.getSignature());
        log.info(" -- [AfterR] joinPoint.signature.name:" + joinPoint.getSignature().getName());
        log.info(" -- [AfterR] joinPoint.kind: " + joinPoint.getKind());
        log.info(" -- [AfterR] joinPoint.sourceLocation: " + joinPoint.getSourceLocation());
        log.info(" -- [AfterR] joinPoint.staticPart: " + joinPoint.getStaticPart());
        log.info(" -- [AfterR] joinPoint.args : " + Arrays.toString(args));
        if (args != null && args.length > 0) {
            log.info(" -- args.length : " + args.length);
            for (Object arg: args) {
                log.info(" ---- current arg: " + arg + ", type: " + arg.getClass().getName());
            }
        }
    }
}
