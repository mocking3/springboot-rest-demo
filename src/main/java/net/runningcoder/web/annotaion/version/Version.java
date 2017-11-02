package net.runningcoder.web.annotaion.version;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * Created by wangmaocheng on 2017/2/4.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface Version {
    int value();
}
