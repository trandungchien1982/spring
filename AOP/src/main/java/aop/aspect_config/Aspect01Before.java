package aop.aspect_config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Aspect01Before {

    Logger log = LoggerFactory.getLogger(getClass());

    @Before(value = "execution(* aop.services.EmployeeService.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("");
        Object[] args = joinPoint.getArgs();
        log.info("Before method:" + joinPoint.getSignature());
        log.info(" -- [Before] joinPoint.signature.name:" + joinPoint.getSignature().getName());
        log.info(" -- [Before] joinPoint.kind: " + joinPoint.getKind());
        log.info(" -- [Before] joinPoint.sourceLocation: " + joinPoint.getSourceLocation());
        log.info(" -- [Before] joinPoint.staticPart: " + joinPoint.getStaticPart());
        log.info(" -- [Before] Current args : " + Arrays.toString(args));
        if (args != null && args.length > 0) {
            log.info(" -- args.length : " + args.length);
            for (Object arg: args) {
                log.info(" ---- current arg: " + arg + ", type: " + arg.getClass().getName());
            }
        }

    }

}
