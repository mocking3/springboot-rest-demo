package net.runningcoder.config;

import net.runningcoder.web.annotaion.version.ExtendedRequestMappingHandlerMapping;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@Configuration
public class WebMvcRegistrationsConfig extends WebMvcRegistrationsAdapter {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ExtendedRequestMappingHandlerMapping();
    }
}
