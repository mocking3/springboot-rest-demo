package net.runningcoder.web.annotaion.auth;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by bjliubo on 2017/2/14.
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

    @AliasFor("operations")
    String[] value() default {};

    String[] operations() default {};
}
