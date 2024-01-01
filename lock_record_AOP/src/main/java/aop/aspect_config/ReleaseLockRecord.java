package aop.aspect_config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReleaseLockRecord {
  String value();
  String argNames() default "";
  String resourceName() default "";

}
