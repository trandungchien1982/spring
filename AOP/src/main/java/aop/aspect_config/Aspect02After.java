package aop.aspect_config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Aspect02After {

    Logger log = LoggerFactory.getLogger(getClass());

    @After(value = "execution(* aop.services.EmployeeService.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        log.info("");
        Object[] args = joinPoint.getArgs();
        log.info("After method:" + joinPoint.getSignature());
        log.info(" -- [After] joinPoint.signature.name:" + joinPoint.getSignature().getName());
        log.info(" -- [After] joinPoint.kind: " + joinPoint.getKind());
        log.info(" -- [After] joinPoint.sourceLocation: " + joinPoint.getSourceLocation());
        log.info(" -- [After] joinPoint.staticPart: " + joinPoint.getStaticPart());
        log.info(" -- [After] joinPoint.args : " + Arrays.toString(args));
        if (args != null && args.length > 0) {
            log.info(" -- args.length : " + args.length);
            for (Object arg: args) {
                log.info(" ---- current arg: " + arg + ", type: " + arg.getClass().getName());
            }
        }
    }
}
