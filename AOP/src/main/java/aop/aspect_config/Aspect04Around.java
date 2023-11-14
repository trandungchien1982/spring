package aop.aspect_config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Aspect04Around {

    Logger log = LoggerFactory.getLogger(getClass());

    @Around(value = "execution(* aop.services.EmployeeService.*(..))")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("");
        Object[] args = joinPoint.getArgs();
        log.info("Around method:" + joinPoint.getSignature());
        log.info(" -- [Around] joinPoint.signature.name:" + joinPoint.getSignature().getName());
        log.info(" -- [Around] joinPoint.kind: " + joinPoint.getKind());
        log.info(" -- [Around] joinPoint.sourceLocation: " + joinPoint.getSourceLocation());
        log.info(" -- [Around] joinPoint.staticPart: " + joinPoint.getStaticPart());
        log.info(" -- [Around] joinPoint.args : " + Arrays.toString(args));
        if (args != null && args.length > 0) {
            log.info(" -- args.length : " + args.length);
            for (Object arg: args) {
                log.info(" ---- current arg: " + arg + ", type: " + arg.getClass().getName());
            }
        }

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info(" -- [Around] Total exec time : " + executionTime + " millis");
        log.info(" -- [Around] Result : " + result);
    }
}
