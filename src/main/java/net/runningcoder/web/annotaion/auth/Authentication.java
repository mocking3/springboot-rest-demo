package net.runningcoder.web.annotaion.auth;

import java.lang.annotation.*;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authentication {
    boolean verify() default true;
}
