package aop.aspect_config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Aspect
@Component
public class LockRecordAspect {

    Logger log = LoggerFactory.getLogger(getClass());

    @Before(value = "@annotation(aop.aspect_config.GetLockRecord)")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("");
        Object[] args = joinPoint.getArgs();
        log.info("Before method:" + joinPoint.getSignature());
        log.info(" -- [GetLock] joinPoint.signature.name:" + joinPoint.getSignature().getName());
        log.info(" -- [GetLock] joinPoint.kind: " + joinPoint.getKind());
        log.info(" -- [GetLock] joinPoint.sourceLocation: " + joinPoint.getSourceLocation());
        log.info(" -- [GetLock] joinPoint.staticPart: " + joinPoint.getStaticPart());
        log.info(" -- [GetLock] Current args : " + Arrays.toString(args));

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        GetLockRecord lockRecordCfg = signature.getMethod().getAnnotation(GetLockRecord.class);
        Class<GetIdHelper> t = lockRecordCfg.getIdHelper();
        ApplicationContext context = null;
        GetIdHelper helper = context.getBean(t);
        UUID recordId = helper.getRecordId(args);
        lockRecordCfg.getIdHelper().getDeclaringClass();

        log.info(" -- [GetLock] Current record id : " + recordId);
        if (args != null && args.length > 0) {
            log.info(" -- args.length : " + args.length);
            for (Object arg: args) {
                log.info(" ---- current arg: " + arg + ", type: " + arg.getClass().getName());
            }
        }

    }

    @Before(value = "@annotation(aop.aspect_config.ReleaseLockRecord)")
    public void releaseLockRecord(JoinPoint joinPoint) {
        log.info("");
        Object[] args = joinPoint.getArgs();
        log.info("Before method:" + joinPoint.getSignature());
        log.info(" -- [ReleaseLock] joinPoint.signature.name:" + joinPoint.getSignature().getName());
        log.info(" -- [ReleaseLock] joinPoint.kind: " + joinPoint.getKind());
        log.info(" -- [ReleaseLock] joinPoint.sourceLocation: " + joinPoint.getSourceLocation());
        log.info(" -- [ReleaseLock] joinPoint.staticPart: " + joinPoint.getStaticPart());
        log.info(" -- [ReleaseLock] Current args : " + Arrays.toString(args));
        if (args != null && args.length > 0) {
            log.info(" -- args.length : " + args.length);
            for (Object arg: args) {
                log.info(" ---- current arg: " + arg + ", type: " + arg.getClass().getName());
            }
        }

    }

}
