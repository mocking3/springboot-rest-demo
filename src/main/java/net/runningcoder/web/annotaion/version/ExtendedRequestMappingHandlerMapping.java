package net.runningcoder.web.annotaion.version;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * Created by wangmaocheng on 2017/2/4.
 */
public class ExtendedRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<VersionCondition> getCustomTypeCondition(Class<?> handlerType) {
        Version apiVersion = AnnotationUtils.findAnnotation(handlerType, Version.class);
        return createCondition(apiVersion);
    }

    @Override
    protected RequestCondition<VersionCondition> getCustomMethodCondition(Method method) {
        Version apiVersion = AnnotationUtils.findAnnotation(method, Version.class);
        return createCondition(apiVersion);
    }

    private RequestCondition<VersionCondition> createCondition(Version apiVersion) {
        return apiVersion == null ? null : new VersionCondition(apiVersion.value());
    }
}