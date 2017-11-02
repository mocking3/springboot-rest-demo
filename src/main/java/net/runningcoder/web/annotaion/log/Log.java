package net.runningcoder.web.annotaion.log;

import java.lang.annotation.*;

/**
 * Created by bjliubo on 2017/2/14.
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

}
